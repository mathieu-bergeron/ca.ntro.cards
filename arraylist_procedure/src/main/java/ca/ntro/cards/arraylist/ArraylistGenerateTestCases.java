package ca.ntro.cards.arraylist;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.arraylist.models.values.ArraylistTestCase;
import ca.ntro.cards.arraylist.test_cases.ArraylistTestCaseDatabase;
import ca.ntro.cards.arraylist.test_cases.execution_trace.ArraylistExecutionTrace;
import ca.ntro.cards.arraylist.test_cases.execution_trace.ArraylistExecutionTraceFull;

public abstract class ArraylistGenerateTestCases<STUDENT_MODEL extends ListeTableau> 

       extends        GenerateTestCases<ListeTableau, 
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
	protected Class<ListeTableau> executableModelClass() {
		return ListeTableau.class;
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
