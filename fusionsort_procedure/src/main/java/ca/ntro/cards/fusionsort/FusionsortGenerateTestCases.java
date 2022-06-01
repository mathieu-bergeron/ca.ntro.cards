package ca.ntro.cards.fusionsort;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.fusionsort.models.values.FusionsortTestCase;
import ca.ntro.cards.fusionsort.test_cases.FusionsortTestCaseDatabase;
import ca.ntro.cards.fusionsort.test_cases.execution_trace.FusionsortExecutionTrace;
import ca.ntro.cards.fusionsort.test_cases.execution_trace.FusionsortExecutionTraceFull;

public abstract class FusionsortGenerateTestCases<STUDENT_MODEL extends TriFusion> 

       extends        GenerateTestCases<TriFusion, 
                                        STUDENT_MODEL,
                                        FusionsortTestCase,
                                        FusionsortTestCaseDatabase,
                                        FusionsortExecutionTrace> {

	
	@Override
	protected Class<FusionsortTestCase> testCaseClass(){
		return FusionsortTestCase.class;
	}

	@Override
	protected Class<FusionsortTestCaseDatabase> testCaseDatabaseClass(){
		return FusionsortTestCaseDatabase.class;
	}

	@Override
	protected Class<TriFusion> executableModelClass() {
		return TriFusion.class;
	}

	@Override
	protected boolean shouldWriteJson() {
		return false;
	}

	@Override
	protected Class<? extends FusionsortExecutionTrace> executionTraceClass() {
		return FusionsortExecutionTraceFull.class;
	}

}
