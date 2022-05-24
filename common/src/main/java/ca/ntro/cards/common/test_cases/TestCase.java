package ca.ntro.cards.common.test_cases;

import java.io.Serializable;

import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.test_cases.execution_trace.ExecutionTrace;
import ca.ntro.core.identifyers.Identifiable;
import ca.ntro.core.initialization.Ntro;

public class TestCase<EXECUTABLE_MODEL extends CommonExecutableModel,
                      STUDENT_MODEL    extends EXECUTABLE_MODEL> 

       implements Value, Identifiable, Serializable {
	
	private String category;
	private String testCaseId;
	private long size;
	
	private transient STUDENT_MODEL studentModel;
	private transient Class<EXECUTABLE_MODEL> executableModelClass;

	private ExecutionTrace<EXECUTABLE_MODEL> trace;
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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public ExecutionTrace<EXECUTABLE_MODEL> getTrace() {
		return trace;
	}

	public void setTrace(ExecutionTrace<EXECUTABLE_MODEL> trace) {
		this.trace = trace;
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

	@Override
	public String id() {
		return category + "_" + String.valueOf(size) + "_" + testCaseId;
	}

	public void run() {
		studentModel.run();
	}

	public void addStep() {
		EXECUTABLE_MODEL snapshot = Ntro.factory().newInstance(executableModelClass);
		snapshot.copyDataFrom(studentModel);

		trace.pushReferenceTo(snapshot);
	}
}
