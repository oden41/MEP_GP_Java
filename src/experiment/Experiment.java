package experiment;

import java.util.Random;

import org.matheclipse.core.eval.ExprEvaluator;

import algorithm_interface.Individual;

public abstract class Experiment {
	protected String[] nonTerminals;
	protected String[] terminals;
	protected int maxEvals;
	protected ExprEvaluator util;

	public int getMaxEvals() {
		return maxEvals;
	}

	public static Random random;

	public Experiment() {
		random = new Random();
		util = new ExprEvaluator();
	}

	public Experiment(long seed) {
		random = new Random(seed);
		util = new ExprEvaluator();
	}

	public abstract double setEvalvalue(Individual individual);

	public String[] getNonterminals() {
		return nonTerminals;
	}

	public String[] getTerminals() {
		return terminals;
	}
}
