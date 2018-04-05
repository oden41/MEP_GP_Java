package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import algorithm_interface.GenerationGap;
import algorithm_interface.Individual;
import experiment.Experiment;
import experiment.SymbolicRegressionExperiment;
import mep.MEPGenerationGap;

public class MainMEP {

	public static void main(String[] args) {
		Experiment experiment = new SymbolicRegressionExperiment();
		int noOfPopulation = 25;
		int geneLength = 20;
		int eval = 0;

		GenerationGap generationGap = new MEPGenerationGap(experiment, noOfPopulation, geneLength);
		generationGap.initialize();
		eval += noOfPopulation * geneLength;
		Individual bestIndiv = generationGap.getBestIndividual();
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("log/eval.csv"), true)))) {
			pw.println("evalCount,evalValue,exp");
			pw.println(eval + "," + bestIndiv.getEvalValue() + "," + bestIndiv.getExpression());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		while (eval < experiment.getMaxEvals()) {
			int popCount = generationGap.doOneIteration();
			eval += popCount * geneLength;
			bestIndiv = generationGap.getBestIndividual();
			try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("log/eval.csv"), true)))) {
				pw.println(eval + "," + bestIndiv.getEvalValue() + "," + bestIndiv.getExpression());
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

}
