package ma.dev7hd.apn1_banking_micro_service.entities;

import lombok.Builder;
import ma.dev7hd.apn1_banking_micro_service.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String currency;

    private Double balance;

    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
