package ma.dev7hd.apn1_banking_micro_service;

import ma.dev7hd.apn1_banking_micro_service.entities.BankAccount;
import ma.dev7hd.apn1_banking_micro_service.enums.AccountType;
import ma.dev7hd.apn1_banking_micro_service.repositories.BankAccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

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
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                double random = Math.random();
                BankAccount bankAccount = BankAccount.builder()
                        .accountType(random > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .balance(random * 10000 + 1000)
                        .createdAt(new Date())
                        .currency("MAD")
                        .build();
                bankAccountRepository.save(bankAccount);
            }
        };
    }

}
