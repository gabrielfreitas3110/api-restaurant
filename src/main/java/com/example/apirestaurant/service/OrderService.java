package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Order;
import com.example.apirestaurant.model.OrderItem;
import com.example.apirestaurant.model.SlipPayment;
import com.example.apirestaurant.model.dto.request.OrderItemRequestDto;
import com.example.apirestaurant.model.dto.request.OrderRequestDto;
import com.example.apirestaurant.model.dto.response.OrderResponseDto;
import com.example.apirestaurant.model.enums.PaymentStatusEnum;
import com.example.apirestaurant.repository.OrderRepository;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        Order order = modelMapper.map(orderRequestDto, Order.class);
        Date date = new Date();
        order.setInstant(date);
        order.getPayment().setPaymentStatus(PaymentStatusEnum.PENDING);
        order.getPayment().setOrder(order);
        if(orderRequestDto.getPayment() instanceof SlipPayment) {
            SlipPayment slipPayment = (SlipPayment) orderRequestDto.getPayment();
            slipPaymentService.completeSlipPayment(slipPayment, date);
        }
        order.setClient(clientService.getClientById(order.getClient().getId()));
        var address = order.getClient().getAddresses().stream()
                .filter(a -> a.getId().equals(orderRequestDto.getDeliveryAddress().getId())).findFirst().get();
        order.setDeliveryAddress(address);
        order = orderRepository.save(order);
        paymentService.create(orderRequestDto.getPayment());
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequestDto oi : orderRequestDto.getItens()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productService.getProductById(oi.getProduct().getId()));
            orderItem.setDiscount(0.0);
            orderItem.setQuantity(oi.getQuantity());
            orderItem.setPrice(orderItem.getProduct().getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        orderItemService.create(orderItems);
        order.setItens(orderItems);
        System.out.println(order);
        return modelMapper.map(order, OrderResponseDto.class);
    }
}
