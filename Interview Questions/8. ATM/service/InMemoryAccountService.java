package service;

import entities.Account;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountService implements AccountService {
    private final Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }
}
