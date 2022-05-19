package ca.ntro.cards.messages;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.models.TestCasesModel;

public class MsgGenerateTestCase extends MessageNtro {

	public void applyTo(TestCasesModel testCasesModel) {
		testCasesModel.generateTestCase();
	}


}
