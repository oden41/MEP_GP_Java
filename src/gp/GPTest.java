package gp;

import org.junit.Test;

import algorithm_interface.Individual;
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
	public void testClone() {
		System.out.println("--- GPClone ---");
		GPIndividual indiv = new GPIndividual();
		indiv.initialize(3, true, experiment.getNonterminals(), experiment.getTerminals());
		System.out.println("root1");
		GPElement root1 = indiv.root;
		System.out.println(root1.getTreeString());
		GPElement root2 = root1.clone();
		System.out.println("root2");
		System.out.println(root2.getTreeString());
		root1.children[0].setSymbol("test");
		root2.children[1].setSymbol("test1");

		System.out.println(root1.getTreeString());
		System.out.println(root2.getTreeString());
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

	//@Test
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

	//@Test
	public void testReplaceNodes() {
		System.out.println("--- GPReplaceNodes ---");
		GPIndividual indiv = new GPIndividual();
		indiv.initialize(3, true, experiment.getNonterminals(), experiment.getTerminals());
		System.out.println("indiv1");
		System.out.println(indiv.getTreeString());
		GPIndividual indiv2 = new GPIndividual();
		indiv2.initialize(1, true, experiment.getNonterminals(), experiment.getTerminals());
		indiv2.root.setSymbol("test");
		System.out.println("indiv2");
		System.out.println(indiv2.getTreeString());
		indiv.replaceNode(7, indiv2.root);
		System.out.println(indiv.getTreeString());
	}

	@Test
	public void testCrossover() {
		System.out.println("--- GPClossover ---");
		GPIndividual indiv = new GPIndividual();
		indiv.initialize(3, true, experiment.getNonterminals(), experiment.getTerminals());
		System.out.println("indiv1");
		System.out.println(indiv.getTreeString());
		GPIndividual indiv2 = new GPIndividual();
		indiv2.initialize(3, true, experiment.getNonterminals(), experiment.getTerminals());
		System.out.println("indiv2");
		System.out.println(indiv2.getTreeString());
		GPOperator operator = new GPOperator();
		Individual[] offsprings = operator.crossover(new Individual[] { indiv, indiv2 });
		System.out.println(((GPIndividual) offsprings[0]).getTreeString());
		System.out.println(((GPIndividual) offsprings[1]).getTreeString());

	}

}
