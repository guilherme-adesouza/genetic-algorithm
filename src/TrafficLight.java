public enum TrafficLight {
    A('A'), 
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F');

    private char id;

    private TrafficLight(char id) {
        this.id = id;
    }

    public char getId() {
        return id;
    }
}
