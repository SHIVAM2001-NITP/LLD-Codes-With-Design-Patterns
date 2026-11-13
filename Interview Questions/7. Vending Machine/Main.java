import entities.Product;
import enums.Coin;
import exceptions.IllegalVendingOperationException;
import exceptions.InvalidProductException;
import exceptions.OutOfStockException;
import service.VendingMachine;

public class Main {
    public static void main(String[] args) {

        VendingMachine machine = new VendingMachine();
        machine.addSlot("A1", new Product("A1", "Coke", 125), 2);
        machine.addSlot("A2", new Product("A2", "Chips", 150), 1);
        machine.addSlot("A3", new Product("A3", "Water", 100), 0);

        System.out.println("=== Buy a Coke with exact change ===");
        machine.selectProduct("A1");
        System.out.println("State: " + machine.currentState());
        machine.insertCoin(Coin.DOLLAR);
        machine.insertCoin(Coin.QUARTER);
        System.out.println("State: " + machine.currentState());
        machine.dispense();
        System.out.println("State: " + machine.currentState());

        System.out.println("\n=== Buy a Coke with change due ===");
        machine.selectProduct("A1");
        machine.insertCoin(Coin.DOLLAR);
        machine.insertCoin(Coin.DOLLAR);
        machine.dispense();

        System.out.println("\n-- The slot is now empty --");
        try {
            machine.selectProduct("A1");
        } catch (OutOfStockException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n-- An out-of-stock slot that was never started --");
        try {
            machine.selectProduct("A3");
        } catch (OutOfStockException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("\n=== Select, insert some coins, then cancel: full refund ===");
        machine.selectProduct("A2");
        machine.insertCoin(Coin.QUARTER);
        machine.insertCoin(Coin.DIME);
        machine.cancel();
        System.out.println("State after cancel: " + machine.currentState());

        System.out.println("\n-- Invalid operations for the current state --");
        try {
            machine.insertCoin(Coin.DOLLAR);     // no product selected yet
        } catch (IllegalVendingOperationException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        try {
            machine.selectProduct("Z9");
        } catch (InvalidProductException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
