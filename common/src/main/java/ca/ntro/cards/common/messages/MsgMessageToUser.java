package ca.ntro.cards.common.messages;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.frontend.views.CommonMessagesView;
import ca.ntro.cards.common.frontend.views.fragments.CommonMessageFragment;

public class MsgMessageToUser extends MessageNtro {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void displayOn(CommonMessagesView messageView , 
			              ViewLoader<? extends CommonMessageFragment> viewLoaderMessageFragment) {
		
		CommonMessageFragment messageFragment = viewLoaderMessageFragment.createView();
		
		messageFragment.displayMessage(message);
		
		messageView.addMessage(messageFragment);

	}
}
