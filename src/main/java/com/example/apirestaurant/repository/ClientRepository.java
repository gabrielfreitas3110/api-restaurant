package com.example.apirestaurant.repository;

import com.example.apirestaurant.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.cpfOrCnpj = ?1")
    Client findByCpfOrCnpj(String cpfOrCnpj);
}
