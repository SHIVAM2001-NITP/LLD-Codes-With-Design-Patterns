package state;

import entities.Slot;
import service.VendingMachine;

// Transient state: drop the product, refund the change, and go back to idle
public class DispensingState implements VendingMachineState {

    @Override
    public void dispense(VendingMachine machine) {
        Slot slot = machine.getSlot(machine.getSelectedCode());
        int change = machine.getBalanceCents() - slot.getProduct().getPriceCents();
        slot.dispenseOne();
        System.out.println("Dispensing " + slot.getProduct().getName());
        if (change > 0) {
            machine.refund(change);
        }
        machine.reset();
    }

    @Override
    public String name() { return "DISPENSING"; }
}
