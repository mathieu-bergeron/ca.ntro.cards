package ca.ntro.cards.naivesort;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.naivesort.models.NaivesortCardsModel;
import ca.ntro.cards.naivesort.models.values.NaivesortTestCase;
import ca.ntro.cards.naivesort.test_cases.NaivesortTestCaseDatabase;
import ca.ntro.cards.naivesort.test_cases.execution_trace.NaivesortExecutionTrace;
import ca.ntro.cards.naivesort.test_cases.execution_trace.NaivesortExecutionTraceFull;

public abstract class NaivesortGenerateTestCases<STUDENT_MODEL extends NaivesortCardsModel> 

       extends        GenerateTestCases<NaivesortCardsModel, 
                                        STUDENT_MODEL,
                                        NaivesortTestCase,
                                        NaivesortTestCaseDatabase,
                                        NaivesortExecutionTrace> {

	
	@Override
	protected Class<NaivesortTestCase> testCaseClass(){
		return NaivesortTestCase.class;
	}

	@Override
	protected Class<NaivesortTestCaseDatabase> testCaseDatabaseClass(){
		return NaivesortTestCaseDatabase.class;
	}

	@Override
	protected Class<NaivesortCardsModel> executableModelClass() {
		return NaivesortCardsModel.class;
	}

	@Override
	protected boolean shouldWriteJson() {
		return false;
	}

	@Override
	protected Class<? extends NaivesortExecutionTrace> executionTraceClass() {
		return NaivesortExecutionTraceFull.class;
	}

}
