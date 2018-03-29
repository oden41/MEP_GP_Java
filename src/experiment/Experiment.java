package experiment;

import java.util.Random;

import algorithm_interface.Individual;

public abstract class Experiment {
	protected String[] nonTerminals;
	protected String[] terminals;
	public static Random random;

	public Experiment() {
		random = new Random();
	}

	public Experiment(long seed) {
		random = new Random(seed);
	}

	public abstract double setEvalvalue(Individual individual);

	public String[] getNonterminals() {
		return nonTerminals;
	}

	public String[] getTerminals() {
		return terminals;
	}
}
