package ca.ntro.cards.foo;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.foo.models.FooCardsModel;
import ca.ntro.cards.foo.models.values.FooTestCase;
import ca.ntro.cards.foo.test_cases.FooTestCaseDatabase;
import ca.ntro.cards.foo.test_cases.execution_trace.FooExecutionTrace;
import ca.ntro.cards.foo.test_cases.execution_trace.FooExecutionTraceFull;

public abstract class FooGenerateTestCases<STUDENT_MODEL extends FooCardsModel> 

       extends        GenerateTestCases<FooCardsModel, 
                                        STUDENT_MODEL,
                                        FooTestCase,
                                        FooTestCaseDatabase,
                                        FooExecutionTrace> {

	
	@Override
	protected Class<FooTestCase> testCaseClass(){
		return FooTestCase.class;
	}

	@Override
	protected Class<FooTestCaseDatabase> testCaseDatabaseClass(){
		return FooTestCaseDatabase.class;
	}

	@Override
	protected Class<FooCardsModel> executableModelClass() {
		return FooCardsModel.class;
	}

	@Override
	protected boolean shouldWriteJson() {
		return false;
	}

	@Override
	protected Class<? extends FooExecutionTrace> executionTraceClass() {
		return FooExecutionTraceFull.class;
	}

}
