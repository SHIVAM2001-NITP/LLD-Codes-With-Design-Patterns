package service;

import entities.Account;
import entities.Card;
import state.AtmState;
import state.IdleState;

// Context of the State pattern: the session data (current card, current account, dispenser,
// account backend) plus the current state, which every public call is forwarded to.
public class Atm {
    private final AccountService accountService;
    private final CashDispenser cashDispenser;
    private AtmState state = new IdleState();
    private Card currentCard;
    private Account currentAccount;

    public Atm(AccountService accountService, CashDispenser cashDispenser) {
        this.accountService = accountService;
        this.cashDispenser = cashDispenser;
    }

    public synchronized void insertCard(Card card) { state.insertCard(this, card); }

    public synchronized void enterPin(int pin) { state.enterPin(this, pin); }

    public synchronized void withdraw(long amount) { state.withdraw(this, amount); }

    public synchronized void ejectCard() { state.ejectCard(this); }

    public String currentState() { return state.name(); }

    // ----- helpers the states call on the context -----
    public AccountService getAccountService() { return accountService; }

    public CashDispenser getCashDispenser() { return cashDispenser; }

    public Card getCurrentCard() { return currentCard; }

    public void setCurrentCard(Card card) { this.currentCard = card; }

    public Account getCurrentAccount() { return currentAccount; }

    public void setCurrentAccount(Account account) { this.currentAccount = account; }

    public void setState(AtmState state) { this.state = state; }
}
