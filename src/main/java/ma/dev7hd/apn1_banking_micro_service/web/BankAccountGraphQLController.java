package ma.dev7hd.apn1_banking_micro_service.web;

import lombok.AllArgsConstructor;
import ma.dev7hd.apn1_banking_micro_service.entities.BankAccount;
import ma.dev7hd.apn1_banking_micro_service.repositories.BankAccountRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class BankAccountGraphQLController {
    private final BankAccountRepository bankAccountRepository;

    @QueryMapping
    public List<BankAccount> accountsList() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount accountById(@Argument UUID id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("id %s not ", id)));
    }
}
