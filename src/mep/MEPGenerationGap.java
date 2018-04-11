package mep;

import java.util.Arrays;

import algorithm_interface.GenerationGap;
import algorithm_interface.Individual;
import experiment.Experiment;

public class MEPGenerationGap extends GenerationGap {
	Experiment experiment;
	MEPOperator operator;
	int geneLength;
	double crossoverRatio = 0.7;
	double mutationRatio = 1 - crossoverRatio;

	public MEPGenerationGap(Experiment experiment, int noOfPopulation, int geneLength) {
		this.experiment = experiment;
		operator = new MEPOperator();
		population = new MEPIndividual[noOfPopulation];
		this.geneLength = geneLength;
	}

	@Override
	public void initialize() {
		for (int i = 0; i < population.length; i++) {
			population[i] = new MEPIndividual(geneLength);
			population[i].initialize(experiment);
			population[i].updatePhenotype();
			population[i].setEvalValue(experiment.setEvalvalue(population[i]));
		}
	}

	@Override
	public int doOneIteration() {
		int popCount = 0;
		for (int i = 0; i < population.length; i += 2) {
			Individual[] children = { selectByTournament(2), selectByTournament(2) };
			//遺伝操作
			if (Experiment.random.nextDouble() < crossoverRatio) {
				//交叉
				children = operator.crossover(children);
			} else {
				//突然変異
				children = new Individual[] { operator.mutation(experiment, children[0]) };
			}
			popCount += children.length;
			//評価
			for (int j = 0; j < children.length; j++) {
				children[j].updatePhenotype();
				children[j].setEvalValue(experiment.setEvalvalue(children[j]));
			}
			// Steady-State世代交代モデル
			Arrays.sort(population, (a, b) -> ((Double) a.getEvalValue()).compareTo((Double) b.getEvalValue()));
			for (int j = 0; j < children.length; j++) {
				if (children[j].getEvalValue() < population[population.length - 1].getEvalValue()) {
					population[population.length - 1] = children[j].clone();
					Arrays.sort(population, (a, b) -> ((Double) a.getEvalValue()).compareTo((Double) b.getEvalValue()));
				}
			}
		}

		return popCount;
	}

}
