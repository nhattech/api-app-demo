package com.example.demo_api.modules.customer.dadabase.repository;

import com.example.demo_api.modules.customer.dadabase.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;



@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    @Query(value = "select * from customers where email = ?", nativeQuery = true)
    Optional<CustomerEntity> findByEmail(String email);
}
