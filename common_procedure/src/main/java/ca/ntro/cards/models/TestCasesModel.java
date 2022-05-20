package ca.ntro.cards.models;

import ca.ntro.app.models.Model;
import ca.ntro.cards.models.values.TestCasesByCategory;
import ca.ntro.cards.models.values.TestCase;
import ca.ntro.cards.models.values.TestCaseById;

public abstract class      TestCasesModel<CARDS_MODEL extends ExploreCardsModel, 
                                          TEST_CASE extends TestCase<CARDS_MODEL>> 

                implements Model {
	
	private long version = 0;
	
	private TestCaseById<CARDS_MODEL> testCasesById = new TestCaseById<>();

	private TestCasesByCategory<CARDS_MODEL> testCasesByCategory = new TestCasesByCategory<>();

	public TestCaseById<CARDS_MODEL> getTestCasesById() {
		return testCasesById;
	}

	public void setTestCasesById(TestCaseById<CARDS_MODEL> testCasesById) {
		this.testCasesById = testCasesById;
	}

	public TestCasesByCategory<CARDS_MODEL> getTestCasesByCategory() {
		return testCasesByCategory;
	}

	public void setTestCasesByCategory(TestCasesByCategory<CARDS_MODEL> testCasesByCategory) {
		this.testCasesByCategory = testCasesByCategory;
	}

	public void generateFirstVersionIfNeeded() {
		if(version == 0) {
			generateFirstVersion();
			version++;
		}
	}

	protected abstract void generateFirstVersion();

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
