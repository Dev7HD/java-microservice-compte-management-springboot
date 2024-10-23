package ma.dev7hd.apn1_banking_micro_service.web;

import ma.dev7hd.apn1_banking_micro_service.dtos.InfoBankAccountDto;
import lombok.AllArgsConstructor;
import ma.dev7hd.apn1_banking_micro_service.dtos.NewBankAccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ma.dev7hd.apn1_banking_micro_service.services.IAccountService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BankAccountRestController {
    private final IAccountService iAccountService;

    @GetMapping("/all-accounts")
   ResponseEntity<List<InfoBankAccountDto>> getAllBankAccounts(){
        List<InfoBankAccountDto> bankAccounts = iAccountService.getAllBankAccounts();
        return bankAccounts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(bankAccounts);
    }

    @DeleteMapping("/{id}/delete")
    ResponseEntity<String> deleteBankAccount(@PathVariable(name = "id") UUID bankAccountId){
        return iAccountService.deleteBankAccountById(bankAccountId);
    }

    @PostMapping("/new-account")
    ResponseEntity<InfoBankAccountDto> createBankAccount(@RequestBody NewBankAccountDto bankAccountDto){
        InfoBankAccountDto bankAccount = iAccountService.createBankAccount(bankAccountDto);
        return bankAccount != null ? ResponseEntity.ok(bankAccount) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/bank-account")
    ResponseEntity<InfoBankAccountDto> getBankAccountById(@RequestParam(name = "id") UUID bankAccountId){
        InfoBankAccountDto accountDto = iAccountService.getBankAccountById(bankAccountId);
        return accountDto != null ? ResponseEntity.ok(accountDto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update-account")
    ResponseEntity<InfoBankAccountDto> updateBankAccount(@RequestBody InfoBankAccountDto bankAccountDto){
        InfoBankAccountDto accountDto = iAccountService.updateBankAccount(bankAccountDto);
        return accountDto != null ? ResponseEntity.ok(accountDto) : ResponseEntity.notFound().build();
    }
}
