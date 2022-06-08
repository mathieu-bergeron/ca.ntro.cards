package ca.ntro.cards.common.test_cases;

import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.enums.Attempt;
import ca.ntro.cards.common.test_cases.descriptor.AbstractAttemptDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.CommonTestCaseDescriptor;
import ca.ntro.cards.common.test_cases.descriptor.CurrentAttemptHolder;
import ca.ntro.cards.common.test_cases.execution_trace.CommonExecutionTrace;
import ca.ntro.cards.common.test_cases.indexing.ExecutionTraceByMode;
import ca.ntro.core.initialization.Ntro;

public abstract class CommonTestCase<EXECUTABLE_MODEL extends CommonExecutableModel,
                                     STUDENT_MODEL    extends EXECUTABLE_MODEL,
                                     EXECUTION_TRACE  extends CommonExecutionTrace,
                                     DASHBOARD_MODEL  extends CommonDashboardModel> 

       implements AbstractTestCaseDescriptor {

	private static final long serialVersionUID = -2534868039865563235L;

	private String category;
	private String testCaseId;
	private int inputSize;
	
	private transient STUDENT_MODEL studentModel;
	private transient Class<EXECUTABLE_MODEL> executableModelClass;
	private transient Class<? extends EXECUTION_TRACE> executionTraceClass;

	private ExecutionTraceByMode<EXECUTABLE_MODEL, DASHBOARD_MODEL> traces = new ExecutionTraceByMode<>();
	private boolean passed;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public int getInputSize() {
		return inputSize;
	}

	public void setInputSize(int size) {
		this.inputSize = size;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public ExecutionTraceByMode<EXECUTABLE_MODEL, DASHBOARD_MODEL> getTraces() {
		return traces;
	}

	public void setTraces(ExecutionTraceByMode<EXECUTABLE_MODEL, DASHBOARD_MODEL> traces) {
		this.traces = traces;
	}

	public STUDENT_MODEL studentModel() {
		return studentModel;
	}

	public void registerStudentModel(STUDENT_MODEL studentModel) {
		this.studentModel = studentModel;
	}

	public Class<EXECUTABLE_MODEL> executableModelClass() {
		return executableModelClass;
	}

	public void registerExecutableModelClass(Class<EXECUTABLE_MODEL> executableModelClass) {
		this.executableModelClass = executableModelClass;
	}

	public void registerExecutionTraceClass(Class<? extends EXECUTION_TRACE> executionTraceClass) {
		this.executionTraceClass = executionTraceClass;
		traces.registerExecutionTraceClass((Class<? extends CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL>>) executionTraceClass);
	}

	public String id() {
		return category + "_" + String.valueOf(inputSize) + "_" + testCaseId;
	}

	public void run() {
		studentModel.run();
	}

	public void addExecutionStep(Attempt mode) {
		// XXX: push a EXECUTABLE_MODEL. This data can act as solutions
		//      (i.e. work in projects where the solution class is not accessible)
		EXECUTABLE_MODEL snapshot = Ntro.factory().newInstance(executableModelClass);
		snapshot.copyDataFrom(studentModel);
		
		traces.pushReference(mode, snapshot);
	}

	public void copyTracesFrom(AbstractTestCaseDescriptor testCase) {
		throw new RuntimeException("TODO");
	}

	public boolean hasId(String id) {
		return id().equals(id);
	}

	protected CommonExecutionTrace<EXECUTABLE_MODEL, DASHBOARD_MODEL> executionTraceByMode(Attempt mode) {
		return traces.trace(mode);
	}

	public void stepForward(Attempt mode) {
		traces.trace(mode).stepForward();
	}

	public void stepBackward(Attempt mode) {
		traces.trace(mode).stepBackward();
	} 


	@Override
	public String category() {
		return getCategory();
	}

	@Override
	public String testCaseId() {
		return getTestCaseId();
	}

	@Override
	public int inputSize() {
		return getInputSize();
	}

	public AbstractTestCaseDescriptor asTestCaseDescriptor() {
		CommonTestCaseDescriptor testCaseDescriptor = newTestCaseDescriptor();
		testCaseDescriptor.setCategory(category);
		testCaseDescriptor.setInputSize(inputSize());
		testCaseDescriptor.setTestCaseId(testCaseId);

		traces.addAttemptDescriptors(testCaseDescriptor);

		return testCaseDescriptor;
	}
	
	protected abstract CommonTestCaseDescriptor newTestCaseDescriptor();


	public void rewindToFirstStep(Attempt mode) {
		executionTraceByMode(mode).rewindToFirstStep();
	}

	public void fastForwardToLastStep(Attempt mode) {
		executionTraceByMode(mode).fastForwardToLastStep();
	}

}
