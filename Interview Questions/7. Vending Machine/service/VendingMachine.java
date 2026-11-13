package service;

import entities.Product;
import entities.Slot;
import enums.Coin;
import state.IdleState;
import state.VendingMachineState;

import java.util.HashMap;
import java.util.Map;

// Context of the State pattern: holds the current state and the data the states act on
// (inventory, selection, running balance), and forwards every public call to that state.
public class VendingMachine {
    private final Map<String, Slot> inventory = new HashMap<>();
    private VendingMachineState state = new IdleState();
    private String selectedCode;
    private int balanceCents = 0;

    public void addSlot(String code, Product product, int quantity) {
        inventory.put(code, new Slot(product, quantity));
    }

    public synchronized void selectProduct(String code) { state.selectProduct(this, code); }

    public synchronized void insertCoin(Coin coin) { state.insertCoin(this, coin); }

    public synchronized void dispense() { state.dispense(this); }

    public synchronized void cancel() { state.cancel(this); }

    public String currentState() { return state.name(); }

    // ----- package-visible helpers the states call on the context -----
    public Slot getSlot(String code) { return inventory.get(code); }

    public Product getSelectedProduct() { return inventory.get(selectedCode).getProduct(); }

    public String getSelectedCode() { return selectedCode; }

    public void setSelectedCode(String code) { this.selectedCode = code; }

    public int getBalanceCents() { return balanceCents; }

    public void addBalance(int cents) { balanceCents += cents; }

    public void setState(VendingMachineState state) { this.state = state; }

    public VendingMachineState getState() { return state; }

    public void refund(int cents) {
        System.out.println("Returning change: $" + cents / 100.0);
    }

    public void reset() {
        selectedCode = null;
        balanceCents = 0;
        state = new IdleState();
    }
}
