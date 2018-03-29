package experiment;

import algorithm_interface.Individual;

public class SymbolicRegressionExperiment extends Experiment {

	double constMin = -1;

	public double getConstMin() {
		return constMin;
	}

	double constMax = 1;

	public double getConstMax() {
		return constMax;
	}

	public SymbolicRegressionExperiment() {
		nonTerminals = new String[] { "*", "+", "-" };
		terminals = new String[] { "x", "c" };
	}

	@Override
	public double setEvalvalue(Individual individual) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
