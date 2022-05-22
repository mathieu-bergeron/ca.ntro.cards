package ca.ntro.cards.common.models;

import ca.ntro.app.models.Model;
import ca.ntro.cards.common.models.values.TestCase;
import ca.ntro.cards.common.models.values.TestCaseById;
import ca.ntro.cards.common.models.values.TestCasesByCategory;

public abstract class      TestCasesModel<STUDENT_MODEL extends CommonExecutableModel, 
                                          TEST_CASE     extends TestCase<STUDENT_MODEL>> 


                implements Model {
	
	private long version = 0;
	
	private TestCaseById<STUDENT_MODEL, TEST_CASE> testCasesById = new TestCaseById<>();

	private TestCasesByCategory<STUDENT_MODEL, TEST_CASE> testCasesByCategory = new TestCasesByCategory<>();

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public TestCaseById<STUDENT_MODEL, TEST_CASE> getTestCasesById() {
		return testCasesById;
	}

	public void setTestCasesById(TestCaseById<STUDENT_MODEL, TEST_CASE> testCasesById) {
		this.testCasesById = testCasesById;
	}

	public TestCasesByCategory<STUDENT_MODEL, TEST_CASE> getTestCasesByCategory() {
		return testCasesByCategory;
	}

	public void setTestCasesByCategory(TestCasesByCategory<STUDENT_MODEL, TEST_CASE> testCasesByCategory) {
		this.testCasesByCategory = testCasesByCategory;
	}

	public void generateFirstVersionIfNeeded(Class<STUDENT_MODEL> studentModelClass) {
		if(version == 0) {
			generateFirstVersion(studentModelClass);
			version++;
		}
	}
	
	public void addTestCase(TestCaseDescriptor descriptor, STUDENT_MODEL model) {
		TEST_CASE testCase = emptyTestCase();
		testCase.setCategory(descriptor.category());
		testCase.setSize(model.testCaseSize());
		testCase.setTestCaseId(descriptor.testCaseId());
		testCase.setModel(model);
		
		testCasesById.addTestCase(testCase);
		testCasesByCategory.addTestCase(testCase);
		
	}

	protected abstract void generateFirstVersion(Class<STUDENT_MODEL> studentModelClass);

	public abstract TEST_CASE emptyTestCase();


}
