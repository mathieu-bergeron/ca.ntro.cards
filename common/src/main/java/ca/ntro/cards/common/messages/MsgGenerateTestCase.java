package ca.ntro.cards.common.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.models.TestCasesModel;

public class MsgGenerateTestCase extends MessageNtro {

	public void applyTo(TestCasesModel testCasesModel) {
		testCasesModel.generateTestCase();
	}


}
