package net.khoudmi.ebankingbackend.repositories;

import net.khoudmi.ebankingbackend.entities.BankAccount;
import net.khoudmi.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
