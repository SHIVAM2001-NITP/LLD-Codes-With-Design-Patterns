package enums;

public enum MatchFormat {
    T20(20),
    ODI(50);

    private final int standardOvers;

    MatchFormat(int standardOvers) { this.standardOvers = standardOvers; }

    public int getStandardOvers() { return standardOvers; }
}
