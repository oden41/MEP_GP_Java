package gp;

import org.junit.Test;

import experiment.Experiment;
import experiment.SymbolicRegressionExperiment;

public class GPTest {
	Experiment experiment = new SymbolicRegressionExperiment();

	@Test
	public void testGPInitialize() {
		System.out.println("--- GPInitialize ---");
		for (int i = 0; i < 10; i++) {
			GPIndividual indiv = new GPIndividual();
			indiv.initialize(experiment);
			System.out.println(indiv.getTreeString());
		}
	}

}
