package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Knows the size and where snakes and ladders are. It does not know about players or dice.
public class Board {
    private final int size;
    private final Map<Integer, BoardEntity> entities = new HashMap<>();

    public Board(int size, List<BoardEntity> boardEntities) {
        if (size < 2) {
            throw new IllegalArgumentException("Board needs at least 2 cells");
        }
        this.size = size;
        for (BoardEntity entity : boardEntities) {
            validate(entity);
            entities.put(entity.getStart(), entity);
        }
    }

    private void validate(BoardEntity e) {
        if (e.getStart() < 2 || e.getStart() >= size || e.getEnd() < 1 || e.getEnd() > size) {
            throw new IllegalArgumentException("Entity is outside the board: " + e.getStart() + "->" + e.getEnd());
        }
        if (entities.containsKey(e.getStart())) {
            throw new IllegalArgumentException("Two entities start on cell " + e.getStart());
        }
    }

    public int getSize() { return size; }

    // One jump only: a snake that drops you on a ladder's foot does not chain
    public BoardEntity entityAt(int position) {
        return entities.get(position);
    }
}
