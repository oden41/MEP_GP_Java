package algorithm_interface;

public abstract class GenerationGap {
	protected Individual[] population;

	public abstract void initialize();

	public abstract void doOneIteration();
}
