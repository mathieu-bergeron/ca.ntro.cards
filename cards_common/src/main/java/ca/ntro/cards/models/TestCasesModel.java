package ca.ntro.cards.models;

import java.util.HashMap;
import java.util.Map;

public class TestCasesModel<CARDS_MODEL extends CardsModel> {
	
	private Map<String, CARDS_MODEL> testCases = new HashMap<>();
	
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
