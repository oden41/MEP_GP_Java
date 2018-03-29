package algorithm_interface;

public interface IOperator {
	public Individual crossover(Individual[] indivs);

	public Individual mutation(Individual indiv);
}
