package gp;

import java.util.ArrayDeque;
import java.util.Queue;

import org.matheclipse.core.eval.ExprEvaluator;

import algorithm_interface.Individual;
import algorithm_interface.Parameter;
import experiment.Experiment;

public class GPIndividual extends Individual {
	GPElement root;

	public String getExpression() {
		return expression;
	}

	public GPIndividual() {
		evalValue = Double.MAX_VALUE;
	}

	@Override
	public GPIndividual clone() {
		GPIndividual elem = new GPIndividual();
		elem.evalValue = evalValue;
		elem.expression = expression;
		elem.root = root.clone();
		return elem;
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
		expression = root.printInOrder();
		ExprEvaluator util = new ExprEvaluator();
		expression = util.evaluate("ExpandAll(" + expression + ")").toString().replace(" ", "");
	}

	public String getTreeString() {
		return root.getTreeString();
	}

	public int getNoOfNodes() {
		return root.getNoOfNodes();
	}

	public int getMaxHeight() {
		return root.getMaxHeight();
	}

	public GPElement getElement(int id) {
		if (id == 0)
			return root;

		GPElement node;
		Queue<GPElement> queue = new ArrayDeque<>();
		for (int i = 0; i < root.children.length; i++) {
			queue.add(root.children[i]);
		}

		while (!queue.isEmpty()) {
			node = queue.poll();
			id--;
			if (id == 0)
				return node;

			for (int i = 0; i < node.children.length; i++) {
				queue.add(node.children[i]);
			}
		}
		return null;
	}

	public void replaceNode(int id, GPElement elem) {
		// 親ノードだった場合
		if (id == 0) {
			root = elem.clone();
			root.parentNode = null;
			return;
		}

		Queue<GPElement> queue = new ArrayDeque<>();
		for (int i = 0; i < root.children.length; i++) {
			root.children[i].parentNode = root;
			queue.add(root.children[i]);
		}
		while (!queue.isEmpty()) {
			GPElement tempNode = queue.poll();
			id--;
			if (id == 0) {
				GPElement parent = tempNode.parentNode;
				for (int i = 0; i < parent.children.length; i++) {
					if (parent.children[i].equals(tempNode)) {
						parent.children[i] = elem.clone();
					}
				}
				tempNode.parentNode = null;
				return;
			} else {
				tempNode.parentNode = null;
				for (int j = 0; j < tempNode.children.length; j++) {
					tempNode.children[j].parentNode = tempNode;
					queue.add(tempNode.children[j]);
				}
			}
		}
	}

}
