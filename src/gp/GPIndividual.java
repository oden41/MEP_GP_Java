package gp;

import algorithm_interface.Individual;
import algorithm_interface.Parameter;
import experiment.Experiment;

public class GPIndividual extends Individual {
	GPElement root;
	String expression;

	public GPIndividual() {
	}

	@Override
	public void initialize(Experiment experiment) {
		String[] nonTerminals = experiment.getNonterminals();
		String[] terminals = experiment.getTerminals();
		initialize(Parameter.GP_INIT_TREE, Parameter.IS_FULL, nonTerminals, terminals);
	}

	void initialize(int gpInitTree, boolean isFull, String[] nonTerminals, String[] terminals) {
		if (gpInitTree <= 0) {
			//終端記号を設定
			String terminal = terminals[Experiment.random.nextInt(terminals.length)];
			root = new GPElement(terminal);
		}

		String symbol = "";
		if (isFull || Experiment.random.nextInt(2) == 0) {
			//非終端記号
			symbol = nonTerminals[Experiment.random.nextInt(nonTerminals.length)];
		} else {
			//終端記号
			symbol = terminals[Experiment.random.nextInt(terminals.length)];
		}
		root = new GPElement(symbol);
		root.children = new GPElement[Parameter.getNoOfChildren(symbol)];
		for (int i = 0; i < root.children.length; i++) {
			root.children[i] = new GPElement("");
			root.children[i] = root.children[i].initialize(gpInitTree - 1, isFull, nonTerminals, terminals);
		}
	}

	@Override
	public void updatePhenotype() {
		// TODO 自動生成されたメソッド・スタブ

	}

	public String getTreeString() {
		return root.getTreeString();
	}

}
