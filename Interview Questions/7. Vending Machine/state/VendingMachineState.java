package state;

import enums.Coin;

// State: what selectProduct / insertCoin / dispense / cancel do depends on where we are
// in the flow. Each state only implements the operations that make sense in it.
public interface VendingMachineState {
    default void selectProduct(service.VendingMachine machine, String code) {
        throw new exceptions.IllegalVendingOperationException("Cannot select a product right now");
    }

    default void insertCoin(service.VendingMachine machine, Coin coin) {
        throw new exceptions.IllegalVendingOperationException("Cannot insert a coin right now");
    }

    default void dispense(service.VendingMachine machine) {
        throw new exceptions.IllegalVendingOperationException("Cannot dispense right now");
    }

    default void cancel(service.VendingMachine machine) {
        throw new exceptions.IllegalVendingOperationException("Nothing to cancel right now");
    }

    String name();
}
