package ma.dev7hd.apn1_banking_micro_service;

import ma.dev7hd.apn1_banking_micro_service.entities.BankAccount;
import ma.dev7hd.apn1_banking_micro_service.entities.Customer;
import ma.dev7hd.apn1_banking_micro_service.enums.AccountType;
import ma.dev7hd.apn1_banking_micro_service.repositories.BankAccountRepository;
import ma.dev7hd.apn1_banking_micro_service.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ApN1BankingMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApN1BankingMicroServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        return args -> {
            List<Customer> customers = new ArrayList<>();
            List.of("Hamza", "Hind", "Khadija", "Mustapha", "Yahya").forEach(name ->{
                Customer customer = Customer.builder()
                        .name(name)
                        .email(name + "@mail.ma")
                        .build();
                customers.add(customer);
            });

            List<Customer> savedCustomers = customerRepository.saveAll(customers);

            List<BankAccount> bankAccounts = new ArrayList<>();

            savedCustomers.forEach(customer -> {
                for (int i = 0; i < 4; i++) {
                    double random = Math.random();

                    BankAccount bankAccount = BankAccount.builder()
                            .accountType(random > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .balance(random * 10000 + 1000)
                            .createdAt(new Date())
                            .currency("MAD")
                            .customer(customer)
                            .build();
                    bankAccounts.add(bankAccount);
                }
            });
            bankAccountRepository.saveAll(bankAccounts);
        };
    }

}
