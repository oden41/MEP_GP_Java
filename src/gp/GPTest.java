package gp;

import org.junit.Test;

import experiment.Experiment;
import experiment.SymbolicRegressionExperiment;

public class GPTest {
	Experiment experiment = new SymbolicRegressionExperiment();

	//@Test
	public void testGPInitialize() {
		System.out.println("--- GPInitialize ---");
		for (int i = 0; i < 10; i++) {
			GPIndividual indiv = new GPIndividual();
			indiv.initialize(experiment);
			System.out.println(indiv.getTreeString());
			indiv.updatePhenotype();
			System.out.println(indiv.expression);
		}
	}

	//@Test
	public void testGetNoOfNodes() {
		System.out.println("--- GPGetNoOfNodes ---");
		for (int i = 0; i < 10; i++) {
			GPIndividual indiv = new GPIndividual();
			indiv.initialize(experiment);
			System.out.println(indiv.getTreeString());
			System.out.println("nodes :" + indiv.getNoOfNodes());
			System.out.println("max-height :" + indiv.getMaxHeight());
			System.out.println("child-height :" + indiv.root.children[0].getMaxHeight());
		}
	}

	@Test
	public void testGetElement() {
		System.out.println("--- GPGetElement ---");
		for (int i = 0; i < 10; i++) {
			GPIndividual indiv = new GPIndividual();
			indiv.initialize(experiment);
			System.out.println(indiv.getTreeString());
			int id = Experiment.random.nextInt(indiv.getNoOfNodes());
			System.out.println(id);
			System.out.println(indiv.getElement(id).getTreeString());
		}
	}

}
