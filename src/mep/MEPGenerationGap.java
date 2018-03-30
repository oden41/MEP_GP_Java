package mep;

import java.util.Arrays;

import algorithm_interface.GenerationGap;
import algorithm_interface.Individual;
import experiment.Experiment;

public class MEPGenerationGap extends GenerationGap {
	Experiment experiment;
	int geneLength;

	public MEPGenerationGap(Experiment experiment, int noOfPopulation, int geneLength) {
		this.experiment = experiment;
		population = new Individual[noOfPopulation];
		this.geneLength = geneLength;
	}

	@Override
	public void initialize() {
		for (int i = 0; i < population.length; i++) {
			population[i] = new MEPIndividual(geneLength);
			population[i].initialize(experiment);
		}
	}

	@Override
	public void doOneIteration() {
		// TODO 自動生成されたメソッド・スタブ

	}

	public Individual selectByTournament(int sampleSize) {
		shuffle(sampleSize);
		Individual[] candidate = new Individual[sampleSize];
		for (int i = 0; i < candidate.length; i++) {
			candidate[i] = population[i];
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
