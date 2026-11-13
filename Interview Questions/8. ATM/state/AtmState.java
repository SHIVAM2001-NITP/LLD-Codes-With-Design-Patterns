package state;

import entities.Card;

// State: insertCard / enterPin / withdraw / ejectCard do different things depending on where
// we are in the session. Each state implements only what makes sense in it.
public interface AtmState {
    default void insertCard(service.Atm atm, Card card) {
        throw new exceptions.IllegalAtmOperationException("A card is already in use, or none is expected now");
    }

    default void enterPin(service.Atm atm, int pin) {
        throw new exceptions.IllegalAtmOperationException("No card inserted");
    }

    default void withdraw(service.Atm atm, long amount) {
        throw new exceptions.IllegalAtmOperationException("Enter the correct PIN first");
    }

    default void ejectCard(service.Atm atm) {
        throw new exceptions.IllegalAtmOperationException("No card to eject");
    }

    String name();
}
