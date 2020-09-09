public enum Time {
    A(10), 
    B(15),
    C(20),
    D(25),
    E(30),
    F(40), 
    G(50),
    H(60);

    private int time;

    private Time(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "{" + time + " segundos}";
    }

}
