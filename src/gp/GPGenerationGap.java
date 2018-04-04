package gp;

import java.util.Arrays;

import algorithm_interface.GenerationGap;
import algorithm_interface.Individual;
import experiment.Experiment;

public class GPGenerationGap extends GenerationGap {

	Experiment experiment;
	GPOperator operator;
	double crossoverRatio = 0.9;
	double mutationRatio = 1 - crossoverRatio;
	int noOfElite = 1;

	public GPGenerationGap(Experiment experiment, int noOfPopulation) {
		this.experiment = experiment;
		population = new GPIndividual[noOfPopulation];
		operator = new GPOperator();
	}

	@Override
	public void initialize() {
		for (int i = 0; i < population.length; i++) {
			population[i] = new GPIndividual();
			population[i].initialize(experiment);
			population[i].updatePhenotype();
			population[i].setEvalValue(experiment.setEvalvalue(population[i]));
		}
	}

	@Override
	public int doOneIteration() {
		int popCount = 0;
		Arrays.sort(population, (a, b) -> ((Double) a.getEvalValue()).compareTo((Double) b.getEvalValue()));
		//エリート保存
		int index = noOfElite;
		while (index < population.length) {
			Individual[] children = { selectByTournament(2), selectByTournament(2) };
			//遺伝操作
			if (Experiment.random.nextDouble() < crossoverRatio && index < population.length - 2) {
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
		}

		return popCount;
	}
}
