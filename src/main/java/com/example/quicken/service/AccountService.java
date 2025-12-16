package com.example.quicken.service;

import com.example.quicken.model.Account;
import com.example.quicken.model.AccountSummary;
import com.example.quicken.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    public AccountSummary getAccountSummary(Long accountId, LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("From and To dates must be provided");
        }
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("To date cannot be before From date");
        }

        return accountRepository.getAccountSummary(accountId, from, to);
    }
}
