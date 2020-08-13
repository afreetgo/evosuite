package org.evosuite.novelty;

import org.evosuite.ga.metaheuristics.GeneticAlgorithm;
import org.evosuite.ga.metaheuristics.SearchListener;
import org.evosuite.testcase.TestChromosome;
import org.evosuite.testsuite.TestSuiteChromosome;
import org.evosuite.testsuite.TestSuiteFitnessFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SuiteFitnessEvaluationListener implements SearchListener<TestSuiteChromosome> {

    private final List<TestSuiteFitnessFunction> fitnessFunctions;

    public SuiteFitnessEvaluationListener(List<TestSuiteFitnessFunction> fitnessFunctions) {
        this.fitnessFunctions = new ArrayList<>(fitnessFunctions);
    }

    public SuiteFitnessEvaluationListener(SuiteFitnessEvaluationListener that) {
        this(that.fitnessFunctions);
    }

    private TestSuiteChromosome createMergedSolution(Collection<TestChromosome> population) {
        TestSuiteChromosome suite = new TestSuiteChromosome();
        suite.addTests(population);
        return suite;
    }

    public TestSuiteChromosome getSuiteWithFitness(GeneticAlgorithm<TestChromosome> algorithm) {
        List<TestChromosome> population = algorithm.getPopulation();
        TestSuiteChromosome suite = createMergedSolution(population);
        for (TestSuiteFitnessFunction fitnessFunction : fitnessFunctions) {
            fitnessFunction.getFitness(suite);
        }

        return suite;
    }

    @Override
    public void iteration(GeneticAlgorithm<TestSuiteChromosome> algorithm) {
        // ===========================================================================================
        // FIXME: The following line contains a type error.
        //  getSuiteWithFitness expects a GeneticAlgorithm<TestChromosome> but it receives a
        //  GeneticAlgorithm<TestSuiteChromosome>
//        getSuiteWithFitness(algorithm);
        if (true) { // to avoid javac's "unreachable code" error
            throw new RuntimeException("Broken code :("); // deliberately crashing the program
        }
        // ===========================================================================================


        // Update fitness functions based on goals just added to archive
        algorithm.updateFitnessFunctionsAndValues();
    }


    @Override
    public void searchStarted(GeneticAlgorithm<TestSuiteChromosome> algorithm) {

    }

    @Override
    public void searchFinished(GeneticAlgorithm<TestSuiteChromosome> algorithm) {

    }

    @Override
    public void fitnessEvaluation(TestSuiteChromosome individual) {

    }

    @Override
    public void modification(TestSuiteChromosome individual) {

    }
}
