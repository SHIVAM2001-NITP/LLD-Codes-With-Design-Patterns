import entities.Account;
import entities.Card;
import enums.Denomination;
import exceptions.CardBlockedException;
import exceptions.IllegalAtmOperationException;
import exceptions.InsufficientBalanceException;
import exceptions.InsufficientCashException;
import exceptions.InvalidPinException;
import service.Atm;
import service.CashDispenser;
import service.InMemoryAccountService;

public class Main {
    public static void main(String[] args) {

        InMemoryAccountService bank = new InMemoryAccountService();
        bank.addAccount(new Account("ACC1", 1234, 5000));    // balance 5000
        bank.addAccount(new Account("ACC2", 4321, 200));     // balance 200

        CashDispenser dispenser = new CashDispenser();
        dispenser.loadNotes(Denomination.TWO_THOUSAND, 5);
        dispenser.loadNotes(Denomination.FIVE_HUNDRED, 10);
        dispenser.loadNotes(Denomination.TWO_HUNDRED, 10);
        dispenser.loadNotes(Denomination.HUNDRED, 10);

        Atm atm = new Atm(bank, dispenser);
        Card card = new Card("CARD1", "ACC1");

        System.out.println("=== Successful withdrawal ===");
        atm.insertCard(card);
        System.out.println("State: " + atm.currentState());
        atm.enterPin(1234);
        System.out.println("State: " + atm.currentState());
        atm.withdraw(2700);         // should use the biggest notes first
        atm.ejectCard();
        System.out.println("State: " + atm.currentState());

        System.out.println("\n=== Wrong PIN twice, then correct ===");
        atm.insertCard(card);
        try {
            atm.enterPin(1111);
        } catch (InvalidPinException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            atm.enterPin(2222);
        } catch (InvalidPinException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        atm.enterPin(1234);
        System.out.println("State after correct PIN: " + atm.currentState());
        atm.ejectCard();

        System.out.println("\n=== Three wrong PINs block the card ===");
        Card second = new Card("CARD2", "ACC2");
        atm.insertCard(second);
        for (int i = 0; i < 2; i++) {
            try {
                atm.enterPin(9999);
            } catch (InvalidPinException e) {
                System.out.println("Rejected: " + e.getMessage());
            }
        }
        try {
            atm.enterPin(9999);
        } catch (CardBlockedException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("second.isBlocked() = " + second.isBlocked());
        try {
            atm.insertCard(second);
        } catch (CardBlockedException e) {
            System.out.println("Rejected re-insert: " + e.getMessage());
        }

        System.out.println("\n=== Overdraw and insufficient cash ===");
        atm.insertCard(card);
        atm.enterPin(1234);
        try {
            atm.withdraw(1000000);
        } catch (InsufficientBalanceException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            atm.withdraw(150);          // not a multiple of 100
        } catch (InsufficientCashException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        atm.ejectCard();

        System.out.println("\n-- Operations out of order --");
        try {
            atm.withdraw(100);          // no card inserted
        } catch (IllegalAtmOperationException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
