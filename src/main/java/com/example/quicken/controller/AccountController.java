package com.example.quicken.controller;

import com.example.quicken.model.Account;
import com.example.quicken.model.AccountSummary;
import com.example.quicken.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}/summary")
    public AccountSummary getAccountSummary(
            @PathVariable("id") Long accountId,
            @RequestParam("from") String fromDateStr,
            @RequestParam("to") String toDateStr
    ) {
        LocalDate from = LocalDate.parse(fromDateStr);
        LocalDate to = LocalDate.parse(toDateStr);

        return accountService.getAccountSummary(accountId, from, to);
    }
}
