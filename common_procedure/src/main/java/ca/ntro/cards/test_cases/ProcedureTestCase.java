package ca.ntro.cards.test_cases;

import ca.ntro.cards.common.models.enums.Mode;
import ca.ntro.cards.common.test_cases.CommonTestCase;
import ca.ntro.cards.models.ProcedureCardsModel;

public class   ProcedureTestCase<EXECUTABLE_MODEL extends ProcedureCardsModel,
                                 STUDENT_MODEL    extends EXECUTABLE_MODEL> 

       extends CommonTestCase<EXECUTABLE_MODEL, STUDENT_MODEL> {

	public void updateCardsModel(EXECUTABLE_MODEL cardsModel) {
		executionTraceByMode(Mode.MANUAL).copyDataTo(cardsModel);
	}

}
