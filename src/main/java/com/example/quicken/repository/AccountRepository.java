
package com.example.quicken.repository;

import com.example.quicken.model.Account;
import com.example.quicken.model.AccountSummary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // --- Fetch all accounts ---
    public List<Account> findAllAccounts() {
        String sql = "SELECT id, name, description FROM accounts";
        return jdbcTemplate.query(sql, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Account(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
        });
    }

    // --- Get summary for an account in a date range ---
    public AccountSummary getAccountSummary(Long accountId, LocalDate from, LocalDate to) {
        String sql = "SELECT " +
                     "COALESCE(SUM(CASE WHEN amount > 0 THEN amount ELSE 0 END),0) AS total_income, " +
                     "COALESCE(SUM(CASE WHEN amount < 0 THEN amount ELSE 0 END),0) AS total_expenses " +
                     "FROM transactions " +
                     "WHERE account_id = ? AND date BETWEEN ? AND ?";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{accountId, from, to},
                (rs, rowNum) -> {
                    BigDecimal income = rs.getBigDecimal("total_income");
                    BigDecimal expenses = rs.getBigDecimal("total_expenses");
                    BigDecimal net = income.add(expenses); // expenses are negative
                    return new AccountSummary(income, expenses, net);
                }
        );
    }
}
