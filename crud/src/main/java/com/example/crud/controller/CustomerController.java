package com.example.crud.controller;

import com.example.crud.request.CustomerRequest;
import com.example.crud.response.ApplicationResponse;
import com.example.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {


    @Autowired
    CustomerService customerService;

    @PostMapping("customer/save")
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerRequest customerRequest) {
       return ResponseEntity.ok(customerService.saveCustomer(customerRequest));
    }

    @PutMapping("customer/update/customerId/{customerId}")
    public ResponseEntity<Integer> updateCustomer(@RequestBody CustomerRequest customerRequest, @PathVariable String customerId) {
        return ResponseEntity.ok(customerService.updateCustomer(customerRequest, customerId));
    }

    @DeleteMapping("customer/delete/customerId/{customerId}")
    public ResponseEntity<Integer> deleteCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.deleteCustomer(customerId));
    }

    @GetMapping("customer/find/customerId/{customerId}")
    public ResponseEntity<ApplicationResponse> findbyId(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

}
