package org.spring.theguesthouse.service;

import org.spring.theguesthouse.dto.DetailedCustomerDto;

public interface CustomerService {

    public String addCustomer (DetailedCustomerDto customer);

    public String deleteCustomer(DetailedCustomerDto customer);
}
