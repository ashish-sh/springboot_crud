package com.example.crud.service;

import com.example.crud.entity.Customer;
import com.example.crud.request.CustomerRequest;
import com.example.crud.response.ApplicationResponse;

import java.util.List;

public interface CustomerService {

    String saveCustomer(CustomerRequest customerRequest);

    int updateCustomer(CustomerRequest customerRequest, String customerId);

    int deleteCustomer(String customerId);

    ApplicationResponse findById(String customerId);


}
