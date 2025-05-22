package org.spring.theguesthouse.service;

import org.spring.theguesthouse.dto.CustomerDto;
import org.spring.theguesthouse.dto.DetailedCustomerDto;
import org.spring.theguesthouse.entity.Customer;

public interface CustomerService {

    Customer detailedCustomerDtoToCustomer(DetailedCustomerDto c);

    Customer getAllCustomers(CustomerDto customerDto);

    Customer getCustomerById(Long id);

    String addCustomer (DetailedCustomerDto customer);

    String deleteCustomerById(Long customerID);

    String updateCustomer(DetailedCustomerDto customer);


}
