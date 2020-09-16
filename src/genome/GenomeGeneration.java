package genome;

import traffic.TrafficGenome;
import traffic.TrafficOption;

import java.util.*;

public class GenomeGeneration {
    private final int generationSize;
    private final int reproductionSize;
    private final int maxIterations;
    private final float mutationRate;
    private final int tournamentSize;

    private final int genomeSize;
    private final SelectionType selectionType;
    private final int targetFitness;

    public GenomeGeneration(SelectionType selectionType,
                            int targetFitness) {
        this.genomeSize = 7;
        this.selectionType = selectionType;
        this.targetFitness = targetFitness;

        generationSize = 5000;
        reproductionSize = 200;
        maxIterations = 1000;
        mutationRate = 0.1f;
        tournamentSize = 40;
    }

    public List<TrafficGenome> initialPopulation() {
        List<TrafficGenome> population = new ArrayList<>();
        for (int i = 0; i < generationSize; i++) {
            population.add(new TrafficGenome());
        }
        return population;
    }

    public List<TrafficGenome> selection(List<TrafficGenome> population) {
        List<TrafficGenome> selected = new ArrayList<>();
        for (int i = 0; i < reproductionSize; i++) {
            switch (selectionType) {
                case TOURNAMENT:
                	selected.add(rouletteSelection(population));
                case ROULETTE:
                	selected.add(tournamentSelection(population));
            }
        }
        return selected;
    }

    public TrafficGenome rouletteSelection(List<TrafficGenome> population) {
        int totalFitness = population.stream().map(TrafficGenome::getFitness).mapToInt(Integer::intValue).sum();
        final Random random = new Random();
        int selectedValue = random.nextInt(totalFitness);
        float recValue = (float) 1 / selectedValue;
        float currentSum = 0;
        for (TrafficGenome genome : population) {
            currentSum += (float) 1 / genome.getFitness();
            if (currentSum >= recValue) {
                return genome;
            }
        }
        int selectRandom = random.nextInt(generationSize);
        return population.get(selectRandom);
    }

    public static <E> List<E> pickNRandomElements(List<E> list, int n) {
        int length = list.size();
        if (length < n) {
            return null;
        }

        final Random r = new Random();
        for (int i = length - 1; i >= length - n; --i) {
            Collections.swap(list, i, r.nextInt(i + 1));
        }
        return list.subList(length - n, length);
    }

    public TrafficGenome tournamentSelection(List<TrafficGenome> population) {
        List<TrafficGenome> selected = pickNRandomElements(population, tournamentSize);
        return Collections.min(selected);
    }

    public TrafficGenome mutate(TrafficGenome salesman) {
        Random random = new Random();
        float mutate = random.nextFloat();
        if (mutate < mutationRate) {
            List<TrafficOption> genome = salesman.getGenome();
            Collections.swap(genome, random.nextInt(genomeSize), random.nextInt(genomeSize));
            return new TrafficGenome(genome);
        }
        return salesman;
    }

    public List<TrafficGenome> createGeneration(List<TrafficGenome> population) {
        List<TrafficGenome> generation = new ArrayList<>();
        int currentGenerationSize = 0;
        while (currentGenerationSize < generationSize) {
            List<TrafficGenome> parents = pickNRandomElements(population, 2);
            List<TrafficGenome> children = crossover(parents);
            children.set(0, mutate(children.get(0)));
            children.set(1, mutate(children.get(1)));
            generation.addAll(children);
            currentGenerationSize += 2;
        }
        return generation;
    }

    public List<TrafficGenome> crossover(List<TrafficGenome> parents) {
        // housekeeping
        final Random random = new Random();
        int breakpoint = random.nextInt(genomeSize);
        List<TrafficGenome> children = new ArrayList<>();

        // copy parental genomes - we copy so we wouldn't modify in case they were
        // chosen to participate in crossover multiple times
        List<TrafficOption> firstGenome = new ArrayList<>(parents.get(0).getGenome());
        List<TrafficOption> secondGenome = new ArrayList<>(parents.get(1).getGenome());

        // creating child 1
        for (int i = 0; i < breakpoint; i++) {
            Collections.swap(firstGenome, firstGenome.indexOf(secondGenome.get(i)), i);
        }
        children.add(new TrafficGenome(firstGenome));
        firstGenome = parents.get(0).getGenome(); // reseting the edited parent

        // creating child 2
        for (int i = breakpoint; i < genomeSize; i++) {
            Collections.swap(secondGenome, secondGenome.indexOf(firstGenome.get(i)), i);
        }
        children.add(new TrafficGenome(secondGenome));

        return children;
    }

    public TrafficGenome optimize() {
        List<TrafficGenome> population = initialPopulation();
        TrafficGenome globalBestGenome = population.get(0);
        for (int i = 0; i < maxIterations; i++) {
            List<TrafficGenome> selected = selection(population);
            population = createGeneration(selected);
            globalBestGenome = Collections.min(population);
            if (globalBestGenome.getFitness() < targetFitness) {
                break;
            }
        }
        return globalBestGenome;
    }

    public void printGeneration(List<TrafficGenome> generation) {
        for (TrafficGenome genome : generation) {
            System.out.println(genome);
        }
    }
}