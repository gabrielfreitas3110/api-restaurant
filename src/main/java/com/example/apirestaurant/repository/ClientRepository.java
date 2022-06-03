package com.example.apirestaurant.repository;

import com.example.apirestaurant.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Transactional(readOnly = true)
    Client findByCpfCnpj(String cpfCnpj);

    @Transactional(readOnly = true)
    Client findByEmail(String email);
}
