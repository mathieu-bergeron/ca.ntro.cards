package ca.ntro.cards.common;


import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.TestCasesModel;
import ca.ntro.cards.common.test_cases.execution.DoneHandler;
import ca.ntro.cards.common.test_cases.execution.Execution;
import ca.ntro.cards.common.test_cases.execution.TestCaseJobEngine;
import ca.ntro.core.NtroJdk;
import ca.ntro.core.initialization.Ntro;

public abstract class GenerateTestCases<EXECUTABLE_MODEL extends CommonExecutableModel,
                                        STUDENT_MODEL extends EXECUTABLE_MODEL,
                                        TEST_CASE extends TestCase,
                                        TEST_CASES_MODEL extends TestCasesModel> {


	private TestCaseJobEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine = new TestCaseJobEngine<>();
	private TEST_CASES_MODEL testCasesModel = Ntro.factory().newInstance(testCasesModelClass());
	
	private int numberOfThreads = CommonConstants.DEFAULT_NUMBER_OF_EXECUTION_THREADS;
	
	private long startTime;
	private long endTime;
	
	private boolean generationDone = false;
	private boolean writingDone = false;

	public void generateTestCases() {
		
		determineNumberOfThreads();
		
		System.out.println("\n\n[INIT]\n");
		System.out.flush();

		initialize();
		
		System.out.println("\n\n[GENERATING TEST CASES]");
		System.out.println(String.format("\n... using %s threads", numberOfThreads));
		System.out.flush();
		
		startTime = System.currentTimeMillis();
		
		testCasesModel.queueTestCaseCreationTasks();
		
		executionEngine.run();
		
		testCasesModel.onGenerationDone(() -> {

			endTime = System.currentTimeMillis();
			
			System.out.println(String.format("\n... generation done in %.2f seconds\n", (endTime - startTime) / 1E3));
			System.out.flush();
			
			generationDone = true;
			quitWhenAllDone();
			
		});

		testCasesModel.onWritingDone(() -> {

			endTime = System.currentTimeMillis();
			
			System.out.println(String.format("\n... writing done in %.2f seconds\n", (endTime - startTime) / 1E3));
			System.out.flush();

			writingDone = true;
			quitWhenAllDone();
			
		});
	}

	private void quitWhenAllDone() {
		if(generationDone 
				&& writingDone) {

			endTime = System.currentTimeMillis();

			System.out.println("\n\n[ALL DONE]");
			System.out.println(String.format("\n... in %.2f seconds\n", (endTime - startTime) / 1E3));
			System.out.flush();

			executionEngine.shutdown();

		}
	}


	private void determineNumberOfThreads() {
		try {
			int numberOfCpus = Runtime.getRuntime().availableProcessors();
			if(numberOfCpus >= 1) {
				numberOfThreads = numberOfCpus;
			}
		} finally {}
	}

	protected abstract Class<EXECUTABLE_MODEL> executableModelClass();
	protected abstract Class<STUDENT_MODEL> studentModelClass();
	protected abstract Class<TEST_CASE> testCaseClass();
	protected abstract Class<TEST_CASES_MODEL> testCasesModelClass();
	protected abstract boolean shouldWriteJson();

	private void initialize() {
		NtroJdk.initializer().executeBlocking();
		
		initializeExecutionEngine();
		
		initializeTestCasesModel();

	}


	private void initializeExecutionEngine() {
		Execution.registerExecutionEngine(executionEngine);

		executionEngine.registerExecutableModelClass(executableModelClass());
		executionEngine.registerStudentModelClass(studentModelClass());
		executionEngine.registerTestCaseClass(testCaseClass());
		
		executionEngine.initialize(numberOfThreads);
		
		executionEngine.resetTestCasesDirectory();
	}

	@SuppressWarnings("unchecked")
	private void initializeTestCasesModel() {

		testCasesModel.registerExecutableModelClass(executableModelClass());
		testCasesModel.registerStudentModelClass(studentModelClass());
		
		testCasesModel.registerExecutionEngine(executionEngine);
	}


	private void queueTestCaseCreationTasks() {

		testCasesModel.queueTestCaseCreationTasks();

	}

	private void writeTestCases(DoneHandler doneHandler) {

		testCasesModel.writeTestCasesAsync(shouldWriteJson(), doneHandler);

	}
}
