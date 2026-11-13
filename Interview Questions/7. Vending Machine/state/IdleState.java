package state;

import entities.Slot;
import exceptions.InvalidProductException;
import exceptions.OutOfStockException;
import service.VendingMachine;

// Nothing selected yet: the only valid move is picking a product
public class IdleState implements VendingMachineState {

    @Override
    public void selectProduct(VendingMachine machine, String code) {
        Slot slot = machine.getSlot(code);
        if (slot == null) {
            throw new InvalidProductException("No such product: " + code);
        }
        if (slot.isEmpty()) {
            throw new OutOfStockException(slot.getProduct().getName() + " is out of stock");
        }
        machine.setSelectedCode(code);
        machine.setState(new ProductSelectedState());
    }

    @Override
    public String name() { return "IDLE"; }
}
