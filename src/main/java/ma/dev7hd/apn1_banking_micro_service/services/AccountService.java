package ma.dev7hd.apn1_banking_micro_service.services;

import ma.dev7hd.apn1_banking_micro_service.dtos.InfoBankAccountDto;
import ma.dev7hd.apn1_banking_micro_service.dtos.NewBankAccountDto;
import ma.dev7hd.apn1_banking_micro_service.entities.BankAccount;
import lombok.AllArgsConstructor;
import ma.dev7hd.apn1_banking_micro_service.enums.AccountType;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.dev7hd.apn1_banking_micro_service.repositories.BankAccountRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<InfoBankAccountDto> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return convertListBankAccountsToDto(bankAccounts);
    }

    private  List<InfoBankAccountDto> convertListBankAccountsToDto(List<BankAccount> bankAccounts) {
        return bankAccounts.stream().map(bankAccount -> modelMapper.map(bankAccount, InfoBankAccountDto.class)).toList();
    }

    @Override
    public InfoBankAccountDto getBankAccountById(UUID id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        return bankAccount != null ? modelMapper.map(bankAccount, InfoBankAccountDto.class) : null;
    }

    @Transactional
    @Override
    public ResponseEntity<String> deleteBankAccountById(UUID id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        if (bankAccount != null) {
            bankAccountRepository.deleteById(id);
            return ResponseEntity.ok("Bank account deleted");
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @Override
    public InfoBankAccountDto createBankAccount(NewBankAccountDto bankAccountDto) {
        BankAccount bankAccount = modelMapper.map(bankAccountDto, BankAccount.class);
        bankAccount.setCreatedAt(new Date());
        bankAccountRepository.save(bankAccount);
        return modelMapper.map(bankAccount, InfoBankAccountDto.class);
    }

    @Transactional
    @Override
    public InfoBankAccountDto updateBankAccount(InfoBankAccountDto bankAccountDto) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountDto.getId()).orElse(null);
        return updateAccountInfo(bankAccount, bankAccountDto.getBalance(), bankAccountDto.getCurrency(), bankAccountDto.getAccountType());
    }

    @Transactional
    @Override
    public InfoBankAccountDto updateBankAccount(NewBankAccountDto bankAccountDto, UUID id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Id '%s' not valid", id)));
        return updateAccountInfo(bankAccount, bankAccountDto.getBalance(), bankAccountDto.getCurrency(), bankAccountDto.getAccountType());
    }

    private InfoBankAccountDto updateAccountInfo(BankAccount bankAccount, Double balance, String currency, AccountType accountType) {
        if (bankAccount != null) {
            if(balance != null) bankAccount.setBalance(balance);
            if(currency != null && !currency.isEmpty()) bankAccount.setCurrency(currency);
            if(accountType != null) bankAccount.setAccountType(accountType);
            bankAccountRepository.save(bankAccount);
            return modelMapper.map(bankAccount, InfoBankAccountDto.class);
        }
        return null;
    }

    @Override
    public InfoBankAccountDto getAccountById(UUID id){
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("ID: '%s' not found.", id)));
        return convertBankAccountToDto(bankAccount);
    }

    private InfoBankAccountDto convertBankAccountToDto(BankAccount bankAccount){
        return modelMapper.map(bankAccount, InfoBankAccountDto.class);
    }
}
