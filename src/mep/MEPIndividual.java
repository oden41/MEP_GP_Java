package mep;

import org.matheclipse.core.eval.ExprEvaluator;

import algorithm_interface.Individual;
import experiment.Experiment;
import experiment.SymbolicRegressionExperiment;

public class MEPIndividual extends Individual implements Cloneable {
	MEPElement[] programs;
	String[] phenotypes;
	int bestIndex;

	public MEPIndividual(int length) {
		programs = new MEPElement[length];
		phenotypes = new String[length];
		bestIndex = -1;
		evalValue = Double.MAX_VALUE;
	}

	@Override
	public void initialize(Experiment experiment) {
		String[] nonTerminals = experiment.getNonterminals();
		String[] terminals = experiment.getTerminals();
		int opIndex = Experiment.random.nextInt(terminals.length);
		programs[0] = new MEPElement(terminals[opIndex]);
		if (experiment instanceof SymbolicRegressionExperiment && "c".equals(programs[0].getSymbol())) {
			SymbolicRegressionExperiment srExp = (SymbolicRegressionExperiment) experiment;
			programs[0] = new MEPElement(String.valueOf(
					Experiment.random.nextDouble() * (srExp.getConstMax() - srExp.getConstMin())
							+ srExp.getConstMin()));
		}
		for (int i = 1; i < programs.length; i++) {
			if (Experiment.random.nextInt(2) == 0) {
				// 終端記号
				opIndex = Experiment.random.nextInt(terminals.length);
				programs[i] = new MEPElement(terminals[opIndex]);
				if (experiment instanceof SymbolicRegressionExperiment && "c".equals(programs[i].getSymbol())) {
					SymbolicRegressionExperiment srExp = (SymbolicRegressionExperiment) experiment;
					programs[i] = new MEPElement(String.valueOf(
							Experiment.random.nextDouble() * (srExp.getConstMax() - srExp.getConstMin())
									+ srExp.getConstMin()));
				}
			} else {
				// 非終端記号
				opIndex = Experiment.random.nextInt(nonTerminals.length);
				int addr1 = Experiment.random.nextInt(i);
				int addr2 = Experiment.random.nextInt(i);
				programs[i] = new MEPElement(nonTerminals[opIndex], addr1, addr2);
			}
		}
	}

	@Override
	public void updatePhenotype() {
		ExprEvaluator util = new ExprEvaluator();
		for (int i = 0; i < programs.length; i++) {
			phenotypes[i] = getMathExp(i);
			phenotypes[i] = util.evaluate("ExpandAll(" + phenotypes[i] + ")").toString().replace(" ", "");
		}
	}

	@Override
	public MEPIndividual clone() {
		MEPIndividual cIndiv = null;
		cIndiv = (MEPIndividual) super.clone();
		cIndiv.bestIndex = bestIndex;
		cIndiv.phenotypes = phenotypes.clone();
		cIndiv.programs = programs.clone();
		for (int i = 0; i < programs.length; i++) {
			cIndiv.programs[i] = new MEPElement(programs[i].getSymbol(), programs[i].getIndex1(),
					programs[i].getIndex2());
		}
		return cIndiv;
	}

	/**
	 * index番目の数式を取得する
	 *
	 * @param index
	 * @return
	 */
	String getMathExp(int index) {
		if (programs[index].getIndex1() == -1 && programs[index].getIndex2() == -1) {
			// 終端記号
			return "(" + programs[index].getSymbol() + ")";
		} else {
			if (programs[index].getIndex2() == -1)
				// 1引数を想定
				return programs[index].getSymbol() + "( " + getMathExp(programs[index].getIndex1()) + ")";

			else
				// 2引数
				return "( " + getMathExp(programs[index].getIndex1()) + programs[index].getSymbol()
						+ getMathExp(programs[index].getIndex2()) + ")";
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < programs.length; i++) {
			result += programs[i].toString();
		}
		return result + "\n";
	}

}
