package com.example.demo_api.modules.customer.controller;


import com.example.demo_api.modules.customer.model.CustomerRequest;
import com.example.demo_api.modules.customer.model.CustomerResponse;
import com.example.demo_api.modules.customer.service.CustomerService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerResponse>> get() {
        return ResponseEntity.ok(customerService.get());
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest cus) {
        return ResponseEntity.ok(customerService.create(cus));
    }

    @PatchMapping("/save")
    public ResponseEntity<CustomerResponse> update(@PathVariable String id, @RequestBody CustomerRequest cus) {
        return ResponseEntity.ok(customerService.update(id, cus));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomerResponse> delete(@PathVariable String id) {
        System.out.println("id = " + id);
        return ResponseEntity.ok(customerService.delete(id));
    }


}
