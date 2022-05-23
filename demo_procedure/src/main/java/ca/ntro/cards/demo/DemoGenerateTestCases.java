package ca.ntro.cards.demo;

import ca.ntro.cards.common.GenerateTestCases;
import ca.ntro.cards.demo.models.DemoTestCasesModel;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.values.DemoTestCase;

public abstract class DemoGenerateTestCases<STUDENT_MODEL extends TriNaif> 

       extends        GenerateTestCases<TriNaif, 
                                        STUDENT_MODEL,
                                        DemoTestCase,
                                        DemoTestCasesModel<STUDENT_MODEL>> {


}
