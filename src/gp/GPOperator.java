package gp;

import algorithm_interface.IOperator;
import algorithm_interface.Individual;
import algorithm_interface.Parameter;
import experiment.Experiment;

public class GPOperator implements IOperator {

	@Override
	public Individual[] crossover(Individual[] indiv) {
		GPIndividual[] parents = { ((GPIndividual) indiv[0]).clone(), ((GPIndividual) indiv[1]).clone() };
		GPIndividual[] offspring = { ((GPIndividual) parents[0]).clone(), ((GPIndividual) parents[1]).clone() };
		GPElement subA = null;
		GPElement subB = null;
		int idA, idB;
		// 条件を満たせばtrue
		boolean off1 = false, off2 = false;
		do {
			if (off1) {
				idA = Experiment.random.nextInt(parents[0].getNoOfNodes());
				subA = parents[0].getElement(idA);
				idB = Experiment.random.nextInt(parents[1].getNoOfNodes());
				offspring[1].replaceNode(idB, subA);
			} else if (off2) {
				idA = Experiment.random.nextInt(parents[0].getNoOfNodes());
				idB = Experiment.random.nextInt(parents[1].getNoOfNodes());
				subB = parents[1].getElement(idB);
				offspring[0].replaceNode(idA, subB);
			} else {
				idA = Experiment.random.nextInt(parents[0].getNoOfNodes());
				subA = parents[0].getElement(idA);
				idB = Experiment.random.nextInt(parents[1].getNoOfNodes());
				subB = parents[1].getElement(idB);
				offspring[0].replaceNode(idA, subB);
				offspring[1].replaceNode(idB, subA);
			}
			off1 = !(offspring[0].getMaxHeight() > Parameter.GP_MAX_TREE);
			off2 = !(offspring[1].getMaxHeight() > Parameter.GP_MAX_TREE);
		}
		// 一定以上の個体は次世代に引き継がない
		while (!off1 || !off2);

		return offspring;
	}

	@Override
	public Individual mutation(Experiment experiment, Individual indiv) {
		GPIndividual newIndiv = null;
		GPIndividual parent = ((GPIndividual) indiv).clone();
		GPElement node = new GPElement("");
		do {
			newIndiv = parent.clone();
			int id = Experiment.random.nextInt(parent.getNoOfNodes());
			node = node.initialize(Parameter.GP_INIT_TREE, false, experiment.getNonterminals(),
					experiment.getTerminals());
			newIndiv.replaceNode(id, node);
		}
		// 一定以上の個体は次世代に引き継がない
		while (newIndiv.getMaxHeight() > Parameter.GP_MAX_TREE);
		return newIndiv;
	}

}
