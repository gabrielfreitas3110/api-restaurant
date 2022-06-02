package com.example.apirestaurant.model;

import com.example.apirestaurant.model.enums.ClientTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
@SQLDelete(sql = "UPDATE tb_client SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String cpfOrCnpj;

    private Integer type;

    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "tb_cellphone")
    private Set<String> cellphone = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    private String email;

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

    public boolean deleted = Boolean.FALSE;
}
