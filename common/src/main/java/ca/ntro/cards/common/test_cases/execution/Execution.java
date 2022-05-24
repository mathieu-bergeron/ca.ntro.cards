package ca.ntro.cards.common.test_cases.execution;

public class Execution {
	
	@SuppressWarnings("rawtypes")
	private static TestCaseJobEngine executionEngine;
	
	public static void registerExecutionEngine(TestCaseJobEngine executionEngine) {
		Execution.executionEngine = executionEngine;
	}

	public static void ajouterEtape() {
		executionEngine.addExecutionStep(Thread.currentThread().getId());
	}

}
