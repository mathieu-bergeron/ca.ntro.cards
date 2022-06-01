package ca.ntro.cards.arraylist;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.arraylist.models.ArraylistCardsModel;
import ca.ntro.cards.arraylist.models.values.ArraylistTestCase;
import ca.ntro.cards.arraylist.test_cases.ArraylistTestCaseDatabase;
import ca.ntro.cards.arraylist.test_cases.execution_trace.ArraylistExecutionTrace;
import ca.ntro.cards.arraylist.test_cases.execution_trace.ArraylistExecutionTraceFull;

public abstract class ArraylistGenerateTestCases<STUDENT_MODEL extends ArraylistCardsModel> 

       extends        GenerateTestCases<ArraylistCardsModel, 
                                        STUDENT_MODEL,
                                        ArraylistTestCase,
                                        ArraylistTestCaseDatabase,
                                        ArraylistExecutionTrace> {

	
	@Override
	protected Class<ArraylistTestCase> testCaseClass(){
		return ArraylistTestCase.class;
	}

	@Override
	protected Class<ArraylistTestCaseDatabase> testCaseDatabaseClass(){
		return ArraylistTestCaseDatabase.class;
	}

	@Override
	protected Class<ArraylistCardsModel> executableModelClass() {
		return ArraylistCardsModel.class;
	}

	@Override
	protected boolean shouldWriteJson() {
		return false;
	}

	@Override
	protected Class<? extends ArraylistExecutionTrace> executionTraceClass() {
		return ArraylistExecutionTraceFull.class;
	}

}
