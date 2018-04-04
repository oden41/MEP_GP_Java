package main;

import algorithm_interface.GenerationGap;
import experiment.Experiment;
import experiment.SymbolicRegressionExperiment;
import gp.GPGenerationGap;

public class MainGP {

	public static void main(String[] args) {
		Experiment experiment = new SymbolicRegressionExperiment();
		int noOfPopulation = 500;
		int eval = 0;

		GenerationGap generationGap = new GPGenerationGap(experiment, noOfPopulation);
		generationGap.initialize();
		eval += noOfPopulation;
		while (eval < experiment.getMaxEvals()) {
			int popCount = generationGap.doOneIteration();
			eval += popCount;
		}
	}

}
