package ca.ntro.cards.common.messages;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.common.frontend.views.CommonMessagesView;
import ca.ntro.cards.common.frontend.views.fragments.CommonMessageFragment;

public class MsgMessageToUser extends MessageNtro {
	
	private String resourceKey;

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public void displayOn(CommonMessagesView messageView , 
			              ViewLoader<? extends CommonMessageFragment> viewLoaderMessageFragment) {
		
		CommonMessageFragment messageFragment = viewLoaderMessageFragment.createView();
		
		messageFragment.displayMessageForResourceKey(resourceKey);
		
		messageView.addMessage(messageFragment);

	}
}
