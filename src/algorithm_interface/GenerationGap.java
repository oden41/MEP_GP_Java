package algorithm_interface;

import java.util.Arrays;

import experiment.Experiment;

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

	public Individual selectByTournament(int sampleSize) {
		shuffle(sampleSize);
		Individual[] candidate = new Individual[sampleSize];
		for (int i = 0; i < candidate.length; i++) {
			candidate[i] = population[i].clone();
		}
		Arrays.sort(candidate, (a, b) -> ((Double) a.getEvalValue()).compareTo((Double) b.getEvalValue()));

		return candidate[0].clone();
	}

	private void shuffle(int size) {
		for (int i = 0; i < size; i++) {
			int index = Experiment.random.nextInt(population.length - i) + i;
			swap(i, index);
		}
	}

	/**
	 * index1とindex2にある要素を入れ替える
	 *
	 * @param index1
	 * @param index2
	 */
	public void swap(int index1, int index2) {
		Individual node1 = population[index1].clone();
		Individual node2 = population[index2].clone();
		population[index2] = node1;
		population[index1] = node2;
	}
}
