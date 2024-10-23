package ma.dev7hd.apn1_banking_micro_service.repositories;

import ma.dev7hd.apn1_banking_micro_service.entities.BankAccount;
import ma.dev7hd.apn1_banking_micro_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Set;
import java.util.UUID;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
    @RestResource(path = "/byType")
    Set<BankAccount> findByAccountType(@Param("t") AccountType accountType);

    @RestResource(path = "/byCurrency")
    Set<BankAccount> findByCurrencyIgnoreCase(@Param("c") String currency);

    @RestResource(path = "/balance-between")
    Set<BankAccount> findByBalanceBetween(@Param("min") double minBalance,@Param("max") double maxBalance);
}
