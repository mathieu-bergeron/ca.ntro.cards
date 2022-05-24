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
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;

import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution.handlers.DoneHandler;
import ca.ntro.cards.common.test_cases.execution.jobs.TestCaseCreationJob;
import ca.ntro.cards.common.test_cases.execution.jobs.TestCaseJob;
import ca.ntro.core.initialization.Ntro;

public class TestCaseJobEngine<EXECUTABLE_MODEL extends CommonExecutableModel,
                             STUDENT_MODEL extends EXECUTABLE_MODEL,
                             TEST_CASE extends TestCase>  
       extends Thread {
	
	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	private Class<TEST_CASE> testCaseClass;
	
	private Map<Long, TestCaseJobThread> threadById = new ConcurrentHashMap<>();

	private Queue<TestCaseJob> jobs = new LinkedBlockingQueue<>();

	private File dbDir = new File(CommonConstants.TEST_CASES_DIR);
	
	private boolean shouldQuit = false;
	
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

	public void addExecutionStep(long threadId) {
		TestCaseJobThread thread = threadById.get(threadId);
		
		thread.addExecutionStep();
	}

	public void initialize(int numberOfThreads) {
		for(int i = 0; i < numberOfThreads; i++) {
			
			TestCaseJobThread thread = new TestCaseJobThread();
			threadById.put(thread.getId(), thread);

		}
	}
	
	public void executeJob(TestCaseJob job, DoneHandler doneHandler) {
		job.setDoneHandler(doneHandler);

		jobs.add(job);
	}
	
	public void resetTestCasesDirectory() {

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

	@Override
	public void run() {
		startThreads();
		
		while(!shouldQuit) {
			
			try {
				
				updateJobs();

				sleep(CommonConstants.ENGINE_THREAD_SLEEP_TIME_MILISECONDS);

			} catch (InterruptedException e) {

				shouldQuit = true;

			}
		}
		
	}
	
	private void startThreads() {
		for(TestCaseJobThread thread : threadById.values()) {
			thread.start();
		}
	}

	private void updateJobs() {
		
	}

	private void forceQuitThreads() {
		for(TestCaseJobThread thread : threadById.values()) {
			System.out.println(String.format("thread %s %s", thread.getId(), thread.getState()));
		}
		
	}

	public void shutdown() {
		for(TestCaseJobThread thread : threadById.values()) {
			thread.quit();
		}
		
		this.shouldQuit = true;

	}

	public void forceShutdown() {
		System.out.println("forceShutdown");
	}

}
