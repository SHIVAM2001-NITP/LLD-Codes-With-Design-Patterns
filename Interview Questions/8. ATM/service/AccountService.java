package service;

import entities.Account;

// Abstracts the bank's backend: the ATM never touches account storage directly
public interface AccountService {
    Account findByAccountNumber(String accountNumber);
}
