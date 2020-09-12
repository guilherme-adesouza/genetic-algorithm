package traffic;

public enum Time {
    A(10), 
    B(15),
    C(20),
    D(25),
    E(30),
    F(40), 
    G(50),
    H(60);

    private final int value;

    Time(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + value + " segundos}";
    }

}
