package traffic;

import java.util.Random;

public class TrafficOption {
    private TrafficLight trafficLight;
    private TimeRange timeRange;
    private Time time;

    public TrafficOption() {
    }

    public TrafficOption(TrafficLight trafficLight, TimeRange timeRange) {
        this.trafficLight = trafficLight;
        this.timeRange = timeRange;
        this.time = Time.values()[new Random().nextInt(Time.values().length)];
    }

    public static TrafficOption random() {
        final Random random = new Random();
        TrafficOption opt = new TrafficOption();
        opt.time = Time.values()[random.nextInt(Time.values().length)];
        opt.timeRange = TimeRange.values()[random.nextInt(TimeRange.values().length)];
        opt.trafficLight = TrafficLight.values()[random.nextInt(TrafficLight.values().length)];
        return opt;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "TrafficLight: " +
                trafficLight.getId() +
                "\nTime Range: " +
                timeRange.toString() +
                "\nTime: " +
                time + "\n";
    }
}
