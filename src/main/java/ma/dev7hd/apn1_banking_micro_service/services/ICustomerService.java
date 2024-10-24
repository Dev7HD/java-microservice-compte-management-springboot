package ma.dev7hd.apn1_banking_micro_service.services;

import ma.dev7hd.apn1_banking_micro_service.entities.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();
}
