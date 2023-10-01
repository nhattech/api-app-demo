package com.example.demo_api.modules.customer.service;

import com.example.demo_api.modules.customer.dadabase.entity.CustomerEntity;
import com.example.demo_api.modules.customer.dadabase.repository.ICustomerRepository;
import com.example.demo_api.modules.customer.model.CustomerRequest;
import com.example.demo_api.modules.customer.model.CustomerResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponse> get() {

        List<CustomerEntity> list = customerRepository.findAll();

        List<CustomerResponse> responses = new ArrayList<>();

        for (CustomerEntity cus : list) {

            CustomerResponse customerResponse = new CustomerResponse(cus.getId(), cus.getEmail(), cus.getPassword());
            responses.add(customerResponse);
        }

        return responses;
    }

    public CustomerResponse create(CustomerRequest cus) {
        CustomerEntity customerEntity = new CustomerEntity();
        Optional<CustomerEntity> isExist = customerRepository.findByEmail(cus.getEmail());
        isExist.ifPresent(entity -> customerEntity.setId(entity.getId()));

        customerEntity.setEmail(cus.getEmail());
        customerEntity.setPassword(cus.getPassword());

        CustomerEntity res = customerRepository.save(customerEntity);

        return new CustomerResponse(res.getId(), res.getEmail(), res.getPassword());
    }

    public CustomerResponse update(String id, CustomerRequest cus) {
        UUID uuid = UUID.fromString((id));

        Optional<CustomerEntity> isExist = customerRepository.findById(uuid);
        if (isExist.isEmpty()) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(uuid);
        customerEntity.setEmail(cus.getEmail());
        customerEntity.setPassword(cus.getPassword());

        CustomerEntity res = customerRepository.save(customerEntity);

        return new CustomerResponse(res.getId(), res.getEmail(), res.getPassword());
    }

    public CustomerResponse delete(String id) {
        UUID uuid = UUID.fromString(id);
        System.out.println("uuid = " + uuid);
        Optional<CustomerEntity> isExist = customerRepository.findById(uuid);
        System.out.println("isExist = " + isExist.isPresent());

        customerRepository.deleteById(uuid);

        return new CustomerResponse(uuid, isExist.get().getEmail(), isExist.get().getPassword());
    }
}
