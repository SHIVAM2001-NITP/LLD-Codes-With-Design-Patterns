package state;

import entities.Account;
import exceptions.InsufficientBalanceException;
import service.Atm;

import java.util.Map;

// PIN verified: withdrawals are allowed; ejecting the card ends the session
public class AuthenticatedState implements AtmState {

    @Override
    public void withdraw(Atm atm, long amount) {
        Account account = atm.getCurrentAccount();
        if (amount > account.getBalance()) {
            throw new InsufficientBalanceException("Balance is only " + account.getBalance() + ", cannot withdraw " + amount);
        }
        Map<enums.Denomination, Integer> notes = atm.getCashDispenser().withdraw(amount);
        account.debit(amount);
        System.out.println("Dispensing " + amount + ": " + notes);
    }

    @Override
    public void ejectCard(Atm atm) {
        atm.setCurrentCard(null);
        atm.setCurrentAccount(null);
        atm.setState(new IdleState());
    }

    @Override
    public String name() { return "AUTHENTICATED"; }
}
