package ca.ntro.cards.common.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.CommonTestCasesModel;

public class MsgGenerateTestCase extends MessageNtro {

	public void applyTo(CommonTestCasesModel testCasesModel) {
		testCasesModel.generateTestCase();
	}


}
