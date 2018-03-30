package mep;

import org.junit.Test;

import algorithm_interface.Individual;
import experiment.Experiment;
import experiment.SymbolicRegressionExperiment;

public class MEPTest {
	Experiment experiment = new SymbolicRegressionExperiment();

	@Test
	public void testMEPInitialize() {
		System.out.println("--- MEPInitialize ---");
		for (int i = 0; i < 10; i++) {
			MEPIndividual indiv = new MEPIndividual(10);
			indiv.initialize(experiment);
			System.out.println(indiv.toString());
		}
	}

	@Test
	public void testMEPUpdatePhenotype() {
		System.out.println("--- MEPUpdatePhenotype ---");
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

	@Test
	public void testMEPClone() {
		System.out.println("--- MEPClone ---");
		MEPIndividual indiv1 = new MEPIndividual(10);
		indiv1.initialize(experiment);
		System.out.println(indiv1.toString());
		MEPIndividual indiv2 = indiv1.clone();
		System.out.println(indiv2.toString());
		indiv1.programs[0] = new MEPElement("test");
		indiv2.setEvalValue(100);
		System.out.println(indiv1.toString());
		System.out.println(indiv1.getEvalValue());
		System.out.println(indiv2.toString());
		System.out.println(indiv2.getEvalValue());
	}

	@Test
	public void testMEPSelect() {
		System.out.println("--- MEPSelect ---");
		MEPGenerationGap gGap = new MEPGenerationGap(experiment, 10, 10);
		gGap.initialize();
		Individual[] population = gGap.getPopulation();
		for (int i = 0; i < population.length; i++) {
			population[i].setEvalValue(Experiment.random.nextDouble());
		}
		System.out.println(gGap.selectByTournament(2).toString());
	}

}
