package experiment;

import algorithm_interface.Individual;
import gp.GPIndividual;
import mep.MEPIndividual;

public class SymbolicRegressionExperiment extends Experiment {
	//x^4-x^3+x^2+x
	Double[] x = { 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 };
	Double[] y = { 0.0, 2.0, 14.0, 66.0, 212.0, 530.0, 1122.0, 2114.0, 3656.0, 5922.0, 9110.0 };

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
		terminals = new String[] { "x" };
		maxEvals = 300000;
	}

	@Override
	public double setEvalvalue(Individual individual) {
		double eval = 0.0;
		if (individual instanceof MEPIndividual) {
			MEPIndividual mepIndividual = (MEPIndividual) individual;
			double best = Double.MAX_VALUE;
			int bestIndex = -1;
			String[] exps = mepIndividual.getPhenotypes();
			for (int i = 0; i < exps.length; i++) {
				String exp = exps[i];
				eval = 0.0;
				for (int j = 0; j < x.length; j++) {
					util.evaluate("x=" + x[j]);
					double y_ = util.evaluateDoube(exp);
					eval += Math.pow(Math.abs(y_ - y[j]), 2);
				}
				if (best > eval) {
					best = eval;
					bestIndex = i;
				}
			}
			eval = best;
			mepIndividual.setBestIndex(bestIndex);

		} else if (individual instanceof GPIndividual) {
			GPIndividual gpIndividual = (GPIndividual) individual;
			String exp = gpIndividual.getExpression();
			for (int i = 0; i < x.length; i++) {
				util.evaluate("x=" + x[i]);
				double y_ = util.evaluateDoube(exp);
				eval += Math.pow(Math.abs(y_ - y[i]), 2);
			}
		}
		return eval;
	}

}
