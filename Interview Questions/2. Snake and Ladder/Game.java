import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// Runs the turns: roll, move, apply snake/ladder, check for the win, rotate the queue.
public class Game {
    private final Board board;
    private final Dice dice;
    private final Deque<Player> turnQueue = new ArrayDeque<>();
    private Player winner;

    public Game(Board board, Dice dice, List<Player> players) {
        if (players.size() < 2) {
            throw new IllegalArgumentException("At least two players are needed");
        }
        this.board = board;
        this.dice = dice;
        turnQueue.addAll(players);
    }

    public boolean isOver() { return winner != null; }

    public Player getWinner() { return winner; }

    public TurnResult playTurn() {
        if (isOver()) {
            throw new IllegalStateException("The game is already over");
        }
        Player player = turnQueue.pollFirst();
        int roll = dice.roll();
        int from = player.getPosition();
        int target = from + roll;

        // exact finish: an overshooting roll is wasted
        if (target > board.getSize()) {
            turnQueue.addLast(player);
            return new TurnResult(player, roll, from, from, from, null, false, false);
        }

        BoardEntity entity = board.entityAt(target);
        int finalPosition = entity == null ? target : entity.getEnd();
        player.setPosition(finalPosition);

        boolean won = finalPosition == board.getSize();
        if (won) {
            winner = player;
        } else {
            turnQueue.addLast(player);
        }
        return new TurnResult(player, roll, from, target, finalPosition, entity, true, won);
    }
}
