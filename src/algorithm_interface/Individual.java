package algorithm_interface;

import experiment.Experiment;

public abstract class Individual {
	protected double evalValue;

	public abstract void initialize(Experiment experiment);

	public abstract void updatePhenotype();
}
