package ca.ntro.cards.freesort;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.freesort.models.TriLibre;
import ca.ntro.cards.freesort.models.values.FreesortTestCase;
import ca.ntro.cards.freesort.test_cases.FreesortTestCaseDatabase;
import ca.ntro.cards.freesort.test_cases.execution_trace.FreesortExecutionTrace;
import ca.ntro.cards.freesort.test_cases.execution_trace.FreesortExecutionTraceFull;

public abstract class FreesortGenerateTestCases<STUDENT_MODEL extends TriLibre> 

       extends        GenerateTestCases<TriLibre, 
                                        STUDENT_MODEL,
                                        FreesortTestCase,
                                        FreesortTestCaseDatabase,
                                        FreesortExecutionTrace> {

	
	@Override
	protected Class<FreesortTestCase> testCaseClass(){
		return FreesortTestCase.class;
	}

	@Override
	protected Class<FreesortTestCaseDatabase> testCaseDatabaseClass(){
		return FreesortTestCaseDatabase.class;
	}

	@Override
	protected Class<TriLibre> executableModelClass() {
		return TriLibre.class;
	}

	@Override
	protected boolean shouldWriteJson() {
		return true;
	}

	@Override
	protected Class<? extends FreesortExecutionTrace> executionTraceClass() {
		return FreesortExecutionTraceFull.class;
	}

}
