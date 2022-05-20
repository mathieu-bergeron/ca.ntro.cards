package ca.ntro.cards.models;

import ca.ntro.cards.common.models.CommonTestCasesModel;
import ca.ntro.cards.models.values.ProcedureTestCase;

public abstract class   ProcedureTestCasesModel<CARDS_MODEL extends ProcedureCardsModel, 
                                                TEST_CASE   extends ProcedureTestCase<CARDS_MODEL>> 

                extends CommonTestCasesModel<CARDS_MODEL, TEST_CASE> {

}
