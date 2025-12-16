package com.example.quicken.service;

import com.example.quicken.model.AccountSummary;
import com.example.quicken.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    private AccountRepository accountRepository;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @Test
    void testGetAccountSummaryWithTransactions() {
        AccountSummary summary = new AccountSummary(
                new BigDecimal("3900.00"),
                new BigDecimal("-2101.75"),
                new BigDecimal("1798.25")
        );
        when(accountRepository.getAccountSummary(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(summary);

        AccountSummary result = accountService.getAccountSummary(1L, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"));

        assertEquals(new BigDecimal("3900.00"), result.getTotalIncome());
        assertEquals(new BigDecimal("-2101.75"), result.getTotalExpenses());
        assertEquals(new BigDecimal("1798.25"), result.getNet());
    }

    @Test
    void testGetAccountSummaryWithNoTransactions() {
        AccountSummary summary = new AccountSummary(
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );
        when(accountRepository.getAccountSummary(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(summary);

        AccountSummary result = accountService.getAccountSummary(99L, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"));

        assertEquals(BigDecimal.ZERO, result.getTotalIncome());
        assertEquals(BigDecimal.ZERO, result.getTotalExpenses());
        assertEquals(BigDecimal.ZERO, result.getNet());
    }
}
