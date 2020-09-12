package traffic;

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrafficGenome implements Comparable {

    private final int fitness;
    private final List<TrafficOption> genome;

    public TrafficGenome() {
        this.genome = randomTraffic();
        this.fitness = this.calculateFitness();
    }

    public TrafficGenome(List<TrafficOption> genome) {
        this.genome = genome;
        this.fitness = this.calculateFitness();
    }

    public List<TrafficOption> getGenome() {
        return genome;
    }

    public int getFitness() {
        return fitness;
    }

    private int calculateFitness() {
        int totalTime = 0;
        for (TrafficOption trafficOption : this.genome) {
            totalTime += trafficOption.getTime().getValue();
        }
        return totalTime;
    }

    private List<TrafficOption> randomTraffic() {
        List<TrafficOption> randomTraffic = new ArrayList<>();
        for (TrafficLight trafficLight : TrafficLight.values()) {
            for (TimeRange timeRange : TimeRange.values()) {
                final TrafficOption opt = new TrafficOption(trafficLight, timeRange);
                randomTraffic.add(opt);
            }
        }
        Collections.shuffle(randomTraffic);
        return randomTraffic;
    }

    @Override
    public int compareTo(Object o) {
        TrafficGenome genome = (TrafficGenome) o;
        if (this.fitness > genome.getFitness()) {
            return -1;
        } else if (this.fitness < genome.getFitness()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Traffic: \n");
        for (TrafficOption gene : genome) {
            sb.append(gene);
        }
        sb.append("\nTotal Time: ");
        sb.append(this.fitness);
        return sb.toString();
    }
}