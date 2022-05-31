package ca.ntro.cards.demo;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;
import ca.ntro.cards.demo.test_cases.execution_trace.DemoExecutionTrace;
import ca.ntro.cards.demo.test_cases.execution_trace.DemoExecutionTraceFull;

public abstract class DemoGenerateTestCases<STUDENT_MODEL extends DemoCardsModel> 

       extends        GenerateTestCases<DemoCardsModel, 
                                        STUDENT_MODEL,
                                        DemoTestCase,
                                        DemoTestCaseDatabase,
                                        DemoExecutionTrace> {

	
	@Override
	protected Class<DemoTestCase> testCaseClass(){
		return DemoTestCase.class;
	}

	@Override
	protected Class<DemoTestCaseDatabase> testCaseDatabaseClass(){
		return DemoTestCaseDatabase.class;
	}

	@Override
	protected Class<DemoCardsModel> executableModelClass() {
		return DemoCardsModel.class;
	}

	@Override
	protected boolean shouldWriteJson() {
		return false;
	}

	@Override
	protected Class<? extends DemoExecutionTrace> executionTraceClass() {
		return DemoExecutionTraceFull.class;
	}

}
