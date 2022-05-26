package ca.ntro.cards.demo;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.test_cases.DemoTestCaseDatabase;

public abstract class DemoGenerateTestCases<STUDENT_MODEL extends TriNaif> 

       extends        GenerateTestCases<TriNaif, 
                                        STUDENT_MODEL,
                                        DemoTestCase,
                                        DemoTestCaseDatabase> {

	
	@Override
	protected Class<DemoTestCase> testCaseClass(){
		return DemoTestCase.class;
	}

	@Override
	protected Class<DemoTestCaseDatabase> testCasesModelClass(){
		return DemoTestCaseDatabase.class;
	}

	@Override
	protected Class<TriNaif> executableModelClass() {
		return TriNaif.class;
	}

	@Override
	protected boolean shouldWriteJson() {
		return true;
	}

}
