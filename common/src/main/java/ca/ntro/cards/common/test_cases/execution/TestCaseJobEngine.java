package ca.ntro.cards.common.test_cases.execution;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.TestCase;
import ca.ntro.cards.common.test_cases.descriptor.TestCaseDescriptor;
import ca.ntro.cards.common.test_cases.execution.handlers.DoneHandler;
import ca.ntro.cards.common.test_cases.execution.jobs.ExecutionJob;
import ca.ntro.cards.common.test_cases.execution.jobs.Job;
import ca.ntro.cards.common.test_cases.execution.signals.ExitSignal;
import ca.ntro.core.initialization.Ntro;

public class TestCaseJobEngine<EXECUTABLE_MODEL extends CommonExecutableModel,
                             STUDENT_MODEL extends EXECUTABLE_MODEL,
                             TEST_CASE extends TestCase>  
       extends Thread {
	
	private Class<EXECUTABLE_MODEL> executableModelClass;
	private Class<STUDENT_MODEL> studentModelClass;
	private Class<TEST_CASE> testCaseClass;
	
	private Map<Long, TestCaseJobThread> threadById = new ConcurrentHashMap<>();

	private Set<Long> idleThreads = Collections.synchronizedSet(new HashSet<>());
	private Set<Long> runningThreads = Collections.synchronizedSet(new HashSet<>());
	private Set<Long> unresponsiveThreads = Collections.synchronizedSet(new HashSet<>());

	private Queue<Job> jobs = new ConcurrentLinkedQueue<>();

	private File dbDir = new File(CommonConstants.TEST_CASE_DATABASE_DIR);
	
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
			thread.registerExecutionEngine(this);

			threadById.put(thread.getId(), thread);
		}
	}
	
	public void executeJob(Job job, DoneHandler doneHandler) {
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
		for(TestCaseJobThread thread : threadById.values()) {
			
			if(!jobs.isEmpty()) {

				Job job = jobs.remove();
				
				thread.pushSignal(job);
			}
		}
	}

	private void forceQuitThreads() {
		for(TestCaseJobThread thread : threadById.values()) {
			System.out.println(String.format("thread %s %s", thread.getId(), thread.getState()));
		}
		
	}

	public void shutdown() {
		for(TestCaseJobThread thread : threadById.values()) {
			thread.pushSignal(new ExitSignal());
		}
		
		this.shouldQuit = true;

	}

	public void forceShutdown() {
		System.out.println("forceShutdown");
	}

	public void notifyThreadIsIdle(long threadId) {
	}

	public void notifyThreadIsTerminated(long threadId) {
	}

	public void notifyThreadIsRunning(long threadId) {
	}

}
