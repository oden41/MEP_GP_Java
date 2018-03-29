package mep;

import org.junit.Test;

import experiment.Experiment;
import experiment.SymbolicRegressionExperiment;

public class MEPTest {
	Experiment experiment = new SymbolicRegressionExperiment();

	//@Test
	public void testMEPInitialize() {
		for (int i = 0; i < 10; i++) {
			MEPIndividual indiv = new MEPIndividual(10);
			indiv.initialize(experiment);
			System.out.println(indiv.toString());
		}
	}

	@Test
	public void testMEPUpdatePhenotype() {
		for (int i = 0; i < 10; i++) {
			MEPIndividual indiv = new MEPIndividual(10);
			indiv.initialize(experiment);
			System.out.println(indiv.toString());
			indiv.updatePhenotype();
			for (int j = 0; j < indiv.phenotypes.length; j++) {
				System.out.println(j + ": " + indiv.phenotypes[j]);
			}
		}
	}

}
