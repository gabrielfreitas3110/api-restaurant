package com.example.apirestaurant.model;

import com.example.apirestaurant.model.enums.ClientTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String cpfOrCnpj;

    private Integer type;

    @OneToMany(mappedBy = "client", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "tb_cellphone")
    private Set<String> cellphone = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client(Long id, String name, String cpfOrCnpj, ClientTypeEnum type) {
        this.id = id;
        this.name = name;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = type.getId();
    }

    public ClientTypeEnum getType() {
        return ClientTypeEnum.toEnum(type);
    }

    public void setType(ClientTypeEnum type) {
        this.type = type.getId();
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }
}
