package algorithm_interface;

public abstract class GenerationGap {
	protected Individual[] population;

	public Individual[] getPopulation() {
		return population;
	}

	public abstract void initialize();

	public abstract void doOneIteration();

	public abstract Individual selectByTournament(int q);
}
