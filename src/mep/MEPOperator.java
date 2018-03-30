package mep;

import algorithm_interface.IOperator;
import algorithm_interface.Individual;
import experiment.Experiment;

public class MEPOperator implements IOperator {

	/**
	 * 論文実験では基本的に一点交叉であったため，一点交叉を行う
	 *
	 * @param parents
	 * @return
	 */
	@Override
	public Individual[] crossover(Individual[] parents) {
		MEPIndividual[] MEPParents = { ((MEPIndividual) parents[0]).clone(), ((MEPIndividual) parents[1]).clone() };
		MEPIndividual[] offsprings = { ((MEPIndividual) parents[0]).clone(), ((MEPIndividual) parents[1]).clone() };
		int length = MEPParents[0].geneLength;
		int cut_point = Experiment.random.nextInt(length);
		for (int i = cut_point; i < length; i++) {
			offsprings[0].setElement(MEPParents[1].getElement(i).clone(), i);
			offsprings[1].setElement(MEPParents[0].getElement(i).clone(), i);
		}
		return offsprings;
	}

	/* (非 Javadoc)
	 * 論文実験では，2箇所変更しているので，2箇所突然変異させる
	 * @see algorithm_interface.IOperator#mutation(experiment.Experiment, algorithm_interface.Individual)
	 */
	@Override
	public Individual mutation(Experiment experiment, Individual parent) {
		MEPIndividual offspring = ((MEPIndividual) parent).clone();
		int length = offspring.geneLength;
		int firstMutPoint = -1;

		String[] terminals = experiment.getTerminals();
		String[] nonTerminals = experiment.getNonterminals();
		for (int i = 0; i < 2; i++) {
			int mut_point = Experiment.random.nextInt(length);
			while (mut_point == firstMutPoint) {
				mut_point = Experiment.random.nextInt(length);
			}
			if (mut_point == 0) {
				// 終端記号内で突然変異
				offspring.setElement(
						new MEPElement(terminals[Experiment.random.nextInt(terminals.length)]),
						mut_point);
			} else {
				// 非終端記号＆終端記号から突然変異
				MEPElement new_elem = null;
				if (Experiment.random.nextInt(2) == 0) {
					// 終端記号
					int opIndex = Experiment.random.nextInt(terminals.length);
					new_elem = new MEPElement(terminals[opIndex]);
				} else {
					// 非終端記号
					int opIndex = Experiment.random.nextInt(nonTerminals.length);
					int addr1 = Experiment.random.nextInt(mut_point);
					int addr2 = Experiment.random.nextInt(mut_point);
					new_elem = new MEPElement(nonTerminals[opIndex], addr1, addr2);
				}
				offspring.setElement(new_elem, mut_point);
			}
		}

		return offspring;
	}

}
