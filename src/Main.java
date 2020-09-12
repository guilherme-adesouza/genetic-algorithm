import genome.GenomeGeneration;
import genome.SelectionType;
import traffic.TrafficGenome;

public class Main {
    public static void main(String[] args) {
        GenomeGeneration geneticAlgorithm = new GenomeGeneration(SelectionType.TOURNAMENT, 0);
        TrafficGenome result = geneticAlgorithm.optimize();
        System.out.println(result);
        System.exit(0);
    }
}