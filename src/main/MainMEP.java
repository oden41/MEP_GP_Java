package main;

import algorithm_interface.GenerationGap;
import experiment.Experiment;
import experiment.SymbolicRegressionExperiment;
import mep.MEPGenerationGap;

public class MainMEP {

	public static void main(String[] args) {
		Experiment experiment = new SymbolicRegressionExperiment();
		int noOfPopulation = 30;
		int geneLength = 20;
		int eval = 0;

		GenerationGap generationGap = new MEPGenerationGap(experiment, noOfPopulation, geneLength);
		generationGap.initialize();
		eval += noOfPopulation * geneLength;
		while (eval < experiment.getMaxEvals()) {
			int popCount = generationGap.doOneIteration();
			eval += popCount * geneLength;
		}
	}

}
