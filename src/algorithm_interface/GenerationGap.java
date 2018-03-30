package algorithm_interface;

import java.util.Arrays;

public abstract class GenerationGap {
	protected Individual[] population;

	public Individual[] getPopulation() {
		return population;
	}

	public abstract void initialize();

	public abstract int doOneIteration();

	public Individual getBestIndividual() {
		Arrays.sort(population, (a, b) -> ((Double) a.getEvalValue()).compareTo((Double) b.getEvalValue()));
		return population[0];
	}

	public abstract Individual selectByTournament(int q);
}
