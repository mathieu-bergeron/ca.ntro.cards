package ca.ntro.cards.common.test_cases.execution;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.core.initialization.Ntro;

public class ExecutionEngine<EXECUTABLE_MODEL extends CommonExecutableModel,
                             STUDENT_MODEL extends EXECUTABLE_MODEL,
                             TEST_CASE extends TestCase>  {
	
	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	private Class<TEST_CASE> testCaseClass;
	
	private Map<Long, TEST_CASE> testCaseByThreadId = new HashMap<>();
	
	private Map<Long, TestCaseThread> threadById = new ConcurrentHashMap<>();

	private Deque<TestCaseCreationTask> testCasesToCreate = new ConcurrentLinkedDeque<>();
	private Deque<TEST_CASE> testCasesToWrite = new ConcurrentLinkedDeque<>();
	
	private Map<String, TestCaseHandler> testCaseHandlers = new HashMap<>();

	private DoneHandler doneHandler;
	private File dbDir = new File(CommonConstants.TEST_CASES_DIR);
	
	public Class<EXECUTABLE_MODEL> executableModelClass() {
		return executableModelClass;
	}

	public void registerExecutableModelClass(Class<EXECUTABLE_MODEL> executableModelClass) {
		this.executableModelClass = executableModelClass;
	}

	public Class<STUDENT_MODEL> studentModelClass() {
		return studentModelClass;
	}

	public void registerStudentModelClass(Class<STUDENT_MODEL> studentModelClass) {
		this.studentModelClass = studentModelClass;
	}

	public Class<TEST_CASE> testCaseClass() {
		return testCaseClass;
	}

	public void registerTestCaseClass(Class<TEST_CASE> testCaseClass) {
		this.testCaseClass = testCaseClass;
	}
	
	public void setDoneHandler(DoneHandler doneHandler) {
		this.doneHandler = doneHandler;
	}

	public void addStep(long threadId) {
		TEST_CASE testCase = testCaseByThreadId.get(threadId);

		testCase.addStep();
	}

	public void initialize(int numberOfThreads) {
		for(int i = 0; i < numberOfThreads; i++) {
			
			TestCaseThread thread = new TestCaseThread();
			threadById.put(thread.getId(), thread);
			
			thread.start();
		}

	}
	
	
	
	

	public void runTestCase(TEST_CASE testCase) {
		testCaseByThreadId.put(Thread.currentThread().getId(), testCase);
		
		testCase.run();
	}

	public void createTestCase(TestCaseDescriptor descriptor, TestCaseHandler<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> testCaseHandler) {
		TestCaseCreationTask<EXECUTABLE_MODEL, STUDENT_MODEL, TEST_CASE> task = new TestCaseCreationTask<>();
		task.setExecutableModelClass(executableModelClass);
		task.setStudentModelClass(studentModelClass);
		task.setTestCaseClass(testCaseClass);
		task.setDescriptor(descriptor);
		task.setHandler(testCaseHandler);
		task.setExecutionEngine(this);
		
		testCasesToCreate.push(task);
	}

	public void prepareToWriteTestCases() {
		if(dbDir.exists()) {
			deleteFiles(dbDir);
			dbDir.delete();
		}

		dbDir.mkdir();
	}

	private void deleteFiles(File dbDir) {
		for(File file : dbDir.listFiles()) {
			file.delete();
		}
	}

	public void writeTestCaseAsync(TEST_CASE testCase, boolean shouldWriteJson) {
		if(shouldWriteJson) {
			writeJson(testCase);
		}
		
		writeBin(testCase);
		
	}

	public void writeTestCases() {
	}

	private void writeJson(TEST_CASE testCase) {
		File outFile = testCaseFile(testCase, "json");
		
		try {

			FileOutputStream fileOutput = new FileOutputStream(outFile);
			fileOutput.write(Ntro.reflection().toJsonObject(testCase).toJsonString().getBytes());
			fileOutput.close();

		} catch (IOException e) {
			
			Ntro.throwException(e);
		}
	}

	private File testCaseFile(TEST_CASE testCase, String extension) {
		return Paths.get(dbDir.getAbsolutePath(), testCase.getTestCaseId() + "." + extension).toFile();
	}

	private void writeBin(TEST_CASE testCase) {
		File outFile = testCaseFile(testCase, "bin");

		try {

			FileOutputStream fileOutput = new FileOutputStream(outFile);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(testCase);

			objectOutput.close();

		} catch (IOException e) {
			
			Ntro.throwException(e);

		}
	}

	public void prepareToGenerateTestCases() {
		testCasesToCreate.clear();
		
		
		
	}

	public void generateTestCases(DoneHandler doneHandler) {
		// TODO: simply start the first N threads and wait
		//       for threads to finish

		for(TestCaseCreationTask task : testCasesToCreate) {
			task.createTestCase();
		}

		doneHandler.done();
	}


}
