package ca.ntro.cards.common.models;

import ca.ntro.app.models.Model;
import ca.ntro.cards.common.models.values.TestCase;
import ca.ntro.cards.common.models.values.TestCaseById;
import ca.ntro.cards.common.models.values.TestCasesByCategory;

public abstract class      TestCasesModel<STUDENT_MODEL extends CommonExecutableModel, 
                                          TEST_CASE     extends TestCase<STUDENT_MODEL>> 

                implements Model {
	
	private long version = 0;
	
	private TestCaseById<STUDENT_MODEL> testCasesById = new TestCaseById<>();

	private TestCasesByCategory<STUDENT_MODEL> testCasesByCategory = new TestCasesByCategory<>();

	public TestCaseById<STUDENT_MODEL> getTestCasesById() {
		return testCasesById;
	}

	public void setTestCasesById(TestCaseById<STUDENT_MODEL> testCasesById) {
		this.testCasesById = testCasesById;
	}

	public TestCasesByCategory<STUDENT_MODEL> getTestCasesByCategory() {
		return testCasesByCategory;
	}

	public void setTestCasesByCategory(TestCasesByCategory<STUDENT_MODEL> testCasesByCategory) {
		this.testCasesByCategory = testCasesByCategory;
	}

	public void generateFirstVersionIfNeeded(Class<STUDENT_MODEL> studentModelClass) {
		if(version == 0) {
			generateFirstVersion(studentModelClass);
			version++;
		}
	}

	protected abstract void generateFirstVersion(Class<STUDENT_MODEL> studentModelClass);

	public abstract void generateTestCase();

	
	/* TODO:
	 * 
	 *   - generate test cases on first launch
	 *   - use TestCasesModel.json if it exists
	 *   
	 *   - on start, load a test case in CardsModel
	 *   - (the one specified in args if it is the case)
	 *   
	 *   
	 *   NOTE: when validating, use all existing test cases + random test cases
	 *         if validation fails, add the failed test case to the list of test cases 
	 *         
	 */

}
