package ca.ntro.cards.intro;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.intro.models.values.IntroTestCase;
import ca.ntro.cards.intro.test_cases.IntroTestCaseDatabase;
import ca.ntro.cards.intro.test_cases.execution_trace.IntroExecutionTrace;
import ca.ntro.cards.intro.test_cases.execution_trace.IntroExecutionTraceFull;

public abstract class IntroGenerateTestCases<STUDENT_MODEL extends Intro> 

       extends        GenerateTestCases<Intro, 
                                        STUDENT_MODEL,
                                        IntroTestCase,
                                        IntroTestCaseDatabase,
                                        IntroExecutionTrace> {

	
	@Override
	protected Class<IntroTestCase> testCaseClass(){
		return IntroTestCase.class;
	}

	@Override
	protected Class<IntroTestCaseDatabase> testCaseDatabaseClass(){
		return IntroTestCaseDatabase.class;
	}

	@Override
	protected Class<Intro> executableModelClass() {
		return Intro.class;
	}

	@Override
	protected boolean shouldWriteJson() {
		return false;
	}

	@Override
	protected Class<? extends IntroExecutionTrace> executionTraceClass() {
		return IntroExecutionTraceFull.class;
	}

}
