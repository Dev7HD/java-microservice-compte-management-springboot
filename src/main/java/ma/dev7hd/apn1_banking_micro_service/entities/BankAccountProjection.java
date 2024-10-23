package ma.dev7hd.apn1_banking_micro_service.entities;

import ma.dev7hd.apn1_banking_micro_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(types = BankAccount.class, name = "p1")
public interface BankAccountProjection {
    public UUID getId();
    public AccountType getAccountType();
    public String getCurrency();
}
