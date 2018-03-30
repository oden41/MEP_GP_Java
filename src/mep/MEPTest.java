package mep;

import org.junit.Test;

import algorithm_interface.Individual;
import experiment.Experiment;
import experiment.SymbolicRegressionExperiment;

public class MEPTest {
	Experiment experiment = new SymbolicRegressionExperiment();

	//@Test
	public void testMEPInitialize() {
		System.out.println("--- MEPInitialize ---");
		for (int i = 0; i < 10; i++) {
			MEPIndividual indiv = new MEPIndividual(10);
			indiv.initialize(experiment);
			System.out.println(indiv.toString());
		}
	}

	//@Test
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

	//@Test
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

	//@Test
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

	//@Test
	public void testMEPCrossoer() {
		System.out.println("--- MEPCrossover ---");
		MEPIndividual[] parents = new MEPIndividual[2];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = new MEPIndividual(10);
			parents[i].initialize(experiment);
			System.out.println("parents" + i + ":" + parents[i].toString());
		}
		MEPOperator operator = new MEPOperator();
		Individual[] child = operator.crossover(parents);
		for (int i = 0; i < child.length; i++) {
			System.out.println("parents" + i + ":" + parents[i].toString());
			System.out.println("child" + i + ":" + child[i].toString());
		}
	}

	@Test
	public void testMEPMutation() {
		System.out.println("--- MEPMutation ---");
		MEPIndividual parent = new MEPIndividual(10);
		parent.initialize(experiment);
		System.out.println("parents" + ":" + parent.toString());
		MEPOperator operator = new MEPOperator();
		Individual child = operator.mutation(experiment, parent);
		System.out.println("parents" + ":" + parent.toString());
		System.out.println("child  " + ":" + child.toString());
	}

}
