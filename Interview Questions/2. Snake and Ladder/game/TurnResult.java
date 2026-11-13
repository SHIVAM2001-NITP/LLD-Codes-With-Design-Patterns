package game;

import entities.BoardEntity;
import entities.Player;

// What happened in one turn, so the caller can print or assert on it
public class TurnResult {
    private final Player player;
    private final int diceValue;
    private final int from;
    private final int landedOn;
    private final int finalPosition;
    private final BoardEntity entity;
    private final boolean moved;
    private final boolean won;

    public TurnResult(Player player, int diceValue, int from, int landedOn,
                      int finalPosition, BoardEntity entity, boolean moved, boolean won) {
        this.player = player;
        this.diceValue = diceValue;
        this.from = from;
        this.landedOn = landedOn;
        this.finalPosition = finalPosition;
        this.entity = entity;
        this.moved = moved;
        this.won = won;
    }

    public boolean isWon() { return won; }

    public Player getPlayer() { return player; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(player.getName() + " rolled " + diceValue);
        if (!moved) {
            return sb.append(" and stays on ").append(from).append(" (needs an exact roll)").toString();
        }
        sb.append(": ").append(from).append(" -> ").append(landedOn);
        if (entity != null) {
            sb.append(", ").append(entity.describe()).append(" -> ").append(finalPosition);
        }
        if (won) {
            sb.append("  WINS!");
        }
        return sb.toString();
    }
}
