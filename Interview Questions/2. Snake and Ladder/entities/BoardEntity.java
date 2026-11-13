package entities;

// A snake or a ladder: if you land on `start`, you are moved to `end`
public abstract class BoardEntity {
    protected final int start;
    protected final int end;

    protected BoardEntity(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() { return start; }

    public int getEnd() { return end; }

    public abstract String describe();
}
