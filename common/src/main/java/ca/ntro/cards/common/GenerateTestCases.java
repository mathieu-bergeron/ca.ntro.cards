package ca.ntro.cards.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.TestCasesModel;
import ca.ntro.cards.common.test_cases.execution.DoneHandler;
import ca.ntro.cards.common.test_cases.execution.Execution;
import ca.ntro.cards.common.test_cases.execution.ExecutionEngine;
import ca.ntro.core.NtroJdk;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Path;

public abstract class GenerateTestCases<EXECUTABLE_MODEL extends CommonExecutableModel,
                                        STUDENT_MODEL extends EXECUTABLE_MODEL,
                                        TEST_CASE extends TestCase,
                                        TEST_CASES_MODEL extends TestCasesModel> {


	private ExecutionEngine<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> executionEngine = new ExecutionEngine<>();
	private TEST_CASES_MODEL testCasesModel = Ntro.factory().newInstance(testCasesModelClass());
	
	private int numberOfThreads = CommonConstants.DEFAULT_NUMBER_OF_EXECUTION_THREADS;
	
	private long startTime;
	private long endTime;

	public void generateTestCases() {
		
		determineNumberOfThreads();
		
		System.out.println("\n\n[INIT]\n");
		System.out.flush();

		initialize();
		
		System.out.println("\n\n[GENERATING TEST CASES]");
		System.out.println(String.format("\n... using %s threads", numberOfThreads));
		System.out.flush();
		
		startTime = System.currentTimeMillis();
		
		generateTestCasesImpl(() -> {

			endTime = System.currentTimeMillis();
			
			System.out.println(String.format("\n... done in %.2f seconds\n", (endTime - startTime) / 1E3));
			System.out.flush();
			
			System.out.println("\n\n[WRITING TEST CASES]");
			System.out.println(String.format("\n... using %s threads", numberOfThreads));
			System.out.flush();

			startTime = System.currentTimeMillis();

			writeTestCases(() -> {

				endTime = System.currentTimeMillis();

				System.out.println(String.format("\n... done in %.2f seconds\n", (endTime - startTime) / 1E3));
				System.out.flush();
			});
		});
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
		
		executionEngine.registerExecutableModelClass(executableModelClass());
		executionEngine.registerStudentModelClass(studentModelClass());
		executionEngine.registerTestCaseClass(testCaseClass());
		
		executionEngine.initialize(numberOfThreads);
		
		Execution.registerExecutionEngine(executionEngine);
	}

	@SuppressWarnings("unchecked")
	private void initializeTestCasesModel() {

		testCasesModel.registerExecutableModelClass(executableModelClass());
		testCasesModel.registerStudentModelClass(studentModelClass());
		
		testCasesModel.registerExecutionEngine(executionEngine);
	}


	private void generateTestCasesImpl(DoneHandler doneHandler) {
		
		testCasesModel.generateTestCasesAsync(doneHandler);

	}

	private void writeTestCases(DoneHandler doneHandler) {

		testCasesModel.writeTestCasesAsync(shouldWriteJson(), doneHandler);

	}
}
