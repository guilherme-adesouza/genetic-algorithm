import java.lang.Comparable;

public class TrafficGenome implements Comparable {

    public TrafficLight trafficLight;
    public TimeRange timeRange;
    public Time time;
    public TrafficLight trafficLight;

    private final int fitness;

    public TrafficGenome() {
        this.fitness = this.calculateFitness();
    }

    public int getFitness() {
        return fitness;
    }

    private int calculateFitness() {
        return 1;
    }

    @Override
    public int compareTo(Object o) {
        TrafficGenome genome = (TrafficGenome) o;
        if(this.fitness > genome.getFitness()) {
            return 1;
        } else if(this.fitness < genome.getFitness()) {
            return -1;
        } else {
            return 0;
        }
    }
}