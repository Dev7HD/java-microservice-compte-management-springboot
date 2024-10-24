package ma.dev7hd.apn1_banking_micro_service.services;

import lombok.AllArgsConstructor;
import ma.dev7hd.apn1_banking_micro_service.entities.Customer;
import ma.dev7hd.apn1_banking_micro_service.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}
