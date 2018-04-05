package algorithm_interface;

import experiment.Experiment;

public abstract class Individual implements Cloneable {
	protected double evalValue;
	protected String expression;

	public String getExpression() {
		return expression;
	}

	public double getEvalValue() {
		return evalValue;
	}

	public void setEvalValue(double evalValue) {
		this.evalValue = evalValue;
	}

	@Override
	public Individual clone() {
		Individual cIndiv = null;
		try {
			cIndiv = (Individual) super.clone();
			cIndiv.evalValue = evalValue;
		} catch (CloneNotSupportedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return cIndiv;
	}

	public abstract void initialize(Experiment experiment);

	public abstract void updatePhenotype();
}
