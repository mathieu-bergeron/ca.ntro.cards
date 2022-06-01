package ca.ntro.cards.arraylist.frontend;

import ca.ntro.cards.arraylist.frontend.views.ArraylistEfficiencyDashboardView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistEfficiencyMessagesView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistEfficiencyRootView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistEfficiencySettingsView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistGraphsView;
import ca.ntro.cards.arraylist.frontend.views.fragments.ArraylistEfficiencyMessageFragment;
import ca.ntro.cards.arraylist.models.ArraylistEfficiencyDashboardModel;
import ca.ntro.cards.arraylist.models.ArraylistEfficiencySettingsModel;
import ca.ntro.cards.arraylist.models.ArraylistGraphsModel;
import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.messages.MsgMessageToUser;
import ca.ntro.cards.efficiency.frontend.EfficiencyFrontend;

public class ArraylistEfficiencyFrontend 

       extends EfficiencyFrontend<ArraylistEfficiencyRootView,
                                  ArraylistEfficiencySettingsView, 
                                  ArraylistGraphsView, 
                                  ArraylistEfficiencyDashboardView, 
                                  ArraylistEfficiencyMessagesView,
                                  ArraylistEfficiencyMessageFragment,
                                  ArraylistEfficiencyViewData, 
                                  ArraylistGraphsModel, 
                                  ArraylistEfficiencyDashboardModel, 
                                  ArraylistEfficiencySettingsModel> {

	@Override
	protected boolean isProd() {
		return true;
	}

	@Override
	protected Class<ArraylistEfficiencyRootView> rootViewClass() {
		return ArraylistEfficiencyRootView.class;
	}

	@Override
	protected Class<ArraylistEfficiencySettingsView> settingsViewClass() {
		return ArraylistEfficiencySettingsView.class;
	}

	@Override
	protected Class<ArraylistGraphsView> canvasViewClass() {
		return ArraylistGraphsView.class;
	}

	@Override
	protected Class<ArraylistEfficiencyDashboardView> dashboardViewClass() {
		return ArraylistEfficiencyDashboardView.class;
	}


	@Override
	protected Class<ArraylistEfficiencyViewData> viewDataClass() {
		return ArraylistEfficiencyViewData.class;
	}


	@Override
	protected Class<ArraylistEfficiencyMessagesView> messagesViewClass() {
		return ArraylistEfficiencyMessagesView.class;
	}

	@Override
	protected Class<ArraylistEfficiencyMessageFragment> messageFragmentClass() {
		return ArraylistEfficiencyMessageFragment.class;
	}

	@Override
	public void execute() {
		super.execute();
		
		MsgMessageToUser msgMessageToUser = NtroApp.newMessage(MsgMessageToUser.class);
		msgMessageToUser.setResourceKey("welcome");
		msgMessageToUser.send();
	}

}
