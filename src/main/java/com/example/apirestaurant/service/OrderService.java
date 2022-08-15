package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Order;
import com.example.apirestaurant.model.OrderItem;
import com.example.apirestaurant.model.Product;
import com.example.apirestaurant.model.SlipPayment;
import com.example.apirestaurant.model.dto.request.OrderRequestDto;
import com.example.apirestaurant.model.dto.response.OrderResponseDto;
import com.example.apirestaurant.model.enums.PaymentStatusEnum;
import com.example.apirestaurant.repository.OrderRepository;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SlipPaymentService slipPaymentService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ClientService clientService;

    public OrderResponseDto getById(Long id) {
        return modelMapper.map(orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found! Id: " + id)), OrderResponseDto.class);
    }

    protected List<OrderResponseDto> getByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId).stream()
                .map(order -> modelMapper.map(order, OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponseDto create(OrderRequestDto orderRequestDto) {
        Date date = new Date();
        orderRequestDto.getPayment().setPaymentStatus(PaymentStatusEnum.PENDING);
        orderRequestDto.getPayment().setOrder(modelMapper.map(orderRequestDto, Order.class));
        if(orderRequestDto.getPayment() instanceof SlipPayment) {
            SlipPayment slipPayment = (SlipPayment) orderRequestDto.getPayment();
            slipPaymentService.completeSlipPayment(slipPayment, date);
        }
        Order order = modelMapper.map(orderRequestDto, Order.class);
        order.setInstant(date);
        order.setClient(clientService.getClientById(order.getClient().getId()));
        order = orderRepository.save(order);
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        paymentService.create(orderResponseDto.getPayment());
        for(OrderItem oi : orderResponseDto.getItens()) {
            Product p = productService.getProductById(oi.getProduct().getId());
            oi.setProduct(p);
            oi.setDiscount(0.0);
            oi.setPrice(p.getPrice());
            oi.setOrder(modelMapper.map(orderResponseDto, Order.class));
        }
        orderItemService.create(orderResponseDto.getItens());
        return orderResponseDto;
    }
}
