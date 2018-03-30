package algorithm_interface;

import experiment.Experiment;

public interface IOperator {
	public Individual[] crossover(Individual[] indivs);

	public Individual mutation(Experiment experiment, Individual indiv);
}
