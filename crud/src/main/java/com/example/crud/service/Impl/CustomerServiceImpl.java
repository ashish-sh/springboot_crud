package com.example.crud.service.Impl;

import com.example.crud.entity.Customer;
import com.example.crud.exception.CustomerNotFoundException;
import com.example.crud.repo.CustomerRepo;
import com.example.crud.request.CustomerRequest;
import com.example.crud.response.ApplicationResponse;
import com.example.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepo customerRepo;


    public String saveCustomer(CustomerRequest customerRequest) {
        Customer  customer = new Customer();
        customer.setEmail(customerRequest.getEmail());
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setCreateDate(LocalDate.now());
        Customer customerPersisted = customerRepo.save(customer);
        return String.valueOf(customerPersisted.getId());
    }

    public int updateCustomer(CustomerRequest customerRequest, String customerId) {
       Optional<Customer> customerOptional = customerRepo.findById(Long.parseLong(customerId));
        if (customerOptional.isEmpty()) {
            throw  new CustomerNotFoundException("No customer found for given id " + customerId);
        }
        Customer customer =  customerOptional.get();
        customer.setEmail(customerRequest.getEmail());
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customerRepo.save(customer);
        return 1;
    }

    public int deleteCustomer(String customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(Long.parseLong(customerId));
        if (customerOptional.isEmpty()) {
            throw  new CustomerNotFoundException("No customer found for given id " + customerId);
        }
        Customer customer =  customerOptional.get();
        customerRepo.delete(customer);
        return 1;
    }

    public ApplicationResponse findById(String customerId) {
        Optional<Customer> customerOptional = customerRepo.findById(Long.parseLong(customerId));
        if (customerOptional.isEmpty()) {
            throw  new CustomerNotFoundException("No customer found for given id " + customerId);
        }
        Customer customer =  customerOptional.get();
        List<Customer> customers =  new ArrayList<>();
        customers.add(customer);
        ApplicationResponse response = new ApplicationResponse();
        response.setData(customers);
        response.setStatus("SUCCESS");
        response.setStatusCode(HttpStatus.OK.value());
        return response;
    }
}
