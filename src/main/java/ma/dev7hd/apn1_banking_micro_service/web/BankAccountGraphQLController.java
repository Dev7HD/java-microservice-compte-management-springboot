package ma.dev7hd.apn1_banking_micro_service.web;

import lombok.AllArgsConstructor;
import ma.dev7hd.apn1_banking_micro_service.dtos.InfoBankAccountDto;
import ma.dev7hd.apn1_banking_micro_service.dtos.NewBankAccountDto;
import ma.dev7hd.apn1_banking_micro_service.entities.Customer;
import ma.dev7hd.apn1_banking_micro_service.services.IAccountService;
import ma.dev7hd.apn1_banking_micro_service.services.ICustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class BankAccountGraphQLController {
    private final IAccountService accountService;
    private final ICustomerService customerService;

    @QueryMapping
    public List<InfoBankAccountDto> accountsList() {
        return accountService.getAllBankAccounts();
    }

    @QueryMapping
    public InfoBankAccountDto accountById(@Argument UUID id) {
        return accountService.getAccountById(id);
    }

    @MutationMapping
    public InfoBankAccountDto createAccount(@Argument NewBankAccountDto newBankAccountDto){
        return accountService.createBankAccount(newBankAccountDto);
    }

    @MutationMapping
    public InfoBankAccountDto updateAccount(@Argument NewBankAccountDto accountDto, @Argument UUID id){
        return accountService.updateBankAccount(accountDto, id);
    }

    @MutationMapping
    public String deleteAccount(@Argument UUID id){
        String response = accountService.deleteBankAccountById(id).getBody();
        if (response != null) {
            return response;
        } else {
            throw new RuntimeException(String.format("id '%s' not valid.", id));
        }
    }

    @QueryMapping
    List<Customer> customersList(){
        return customerService.getAllCustomers();
    }
}
