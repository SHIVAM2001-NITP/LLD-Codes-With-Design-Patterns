package state;

import enums.Coin;
import service.VendingMachine;

// Enough money is in: more coins still count (for a different purchase next time only, so we just
// accept and ignore extra for simplicity of refund), dispense gives the product and any change, or cancel refunds everything
public class HasSufficientFundsState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachine machine, Coin coin) {
        machine.addBalance(coin.getCents());   // extra coins just add to the change refunded at dispense time
    }

    @Override
    public void dispense(VendingMachine machine) {
        machine.setState(new DispensingState());
        machine.getState().dispense(machine);
    }

    @Override
    public void cancel(VendingMachine machine) {
        machine.refund(machine.getBalanceCents());
        machine.reset();
    }

    @Override
    public String name() { return "HAS_SUFFICIENT_FUNDS"; }
}
