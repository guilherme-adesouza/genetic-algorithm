public enum TimeRange {
    A( 6,  7), 
    B( 7,  8),
    C( 8, 11),
    D(11, 14),
    E(14, 17),
    F(17, 19), 
    G(19, 23);

    private int start;
    private int end;

    private TimeRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    @Override
    public String toString() {
        return "{" + start + ":00 - " + end + ":00" + "}";
    }
}
