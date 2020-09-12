package traffic;

public enum TrafficLight {
    A('A'), 
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F');

    private final char id;

    TrafficLight(char id) {
        this.id = id;
    }

    public char getId() {
        return id;
    }
}
