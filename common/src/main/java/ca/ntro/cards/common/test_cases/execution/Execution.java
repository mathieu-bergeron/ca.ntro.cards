package ca.ntro.cards.common.test_cases.execution;

public class Execution {
	
	@SuppressWarnings("rawtypes")
	private static ExecutionEngine executionEngine;
	
	public static void registerExecutionEngine(ExecutionEngine executionEngine) {
		Execution.executionEngine = executionEngine;
	}

	public static void ajouterEtape() {
		executionEngine.addStep(Thread.currentThread().getId());
	}

}
