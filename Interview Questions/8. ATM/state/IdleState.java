package state;

import entities.Card;
import exceptions.CardBlockedException;
import service.Atm;

// No card in the machine: the only valid move is inserting one
public class IdleState implements AtmState {

    @Override
    public void insertCard(Atm atm, Card card) {
        if (card.isBlocked()) {
            throw new CardBlockedException(card + " is blocked");
        }
        atm.setCurrentCard(card);
        atm.setState(new HasCardState());
    }

    @Override
    public String name() { return "IDLE"; }
}
