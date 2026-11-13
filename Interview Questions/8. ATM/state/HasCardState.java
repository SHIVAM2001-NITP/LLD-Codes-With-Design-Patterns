package state;

import entities.Account;
import entities.Card;
import exceptions.CardBlockedException;
import exceptions.InvalidPinException;
import service.Atm;

// A card is in: waiting for the PIN. Three wrong PINs block the card and eject it.
public class HasCardState implements AtmState {

    @Override
    public void enterPin(Atm atm, int pin) {
        Card card = atm.getCurrentCard();
        Account account = atm.getAccountService().findByAccountNumber(card.getAccountNumber());
        if (account.pinMatches(pin)) {
            card.resetFailedAttempts();
            atm.setCurrentAccount(account);
            atm.setState(new AuthenticatedState());
            return;
        }
        card.registerFailedAttempt();
        if (card.isBlocked()) {
            atm.setState(new IdleState());
            atm.setCurrentCard(null);
            throw new CardBlockedException(card + " blocked after 3 wrong PIN attempts, card ejected");
        }
        throw new InvalidPinException("Wrong PIN");
    }

    @Override
    public void ejectCard(Atm atm) {
        atm.setCurrentCard(null);
        atm.setState(new IdleState());
    }

    @Override
    public String name() { return "HAS_CARD"; }
}
