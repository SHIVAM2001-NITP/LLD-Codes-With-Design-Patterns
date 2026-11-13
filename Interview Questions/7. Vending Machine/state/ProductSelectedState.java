package state;

import enums.Coin;
import service.VendingMachine;

// A product is chosen: coins can be inserted, or the selection can be cancelled (no refund needed yet)
public class ProductSelectedState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        machine.addBalance(coin.getCents());
        int price = machine.getSelectedProduct().getPriceCents();
        if (machine.getBalanceCents() >= price) {
            machine.setState(new HasSufficientFundsState());
        }
    }

    @Override
    public void cancel(VendingMachine machine) {
        machine.reset();
    }

    @Override
    public String name() { return "PRODUCT_SELECTED"; }
}
