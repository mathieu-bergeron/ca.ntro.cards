package ca.ntro.cards.intro.frontend;

import ca.ntro.cards.intro.frontend.views.IntroEfficiencyDashboardView;
import ca.ntro.cards.intro.frontend.views.IntroEfficiencyMessagesView;
import ca.ntro.cards.intro.frontend.views.IntroEfficiencyRootView;
import ca.ntro.cards.intro.frontend.views.IntroEfficiencySettingsView;
import ca.ntro.cards.intro.frontend.views.IntroGraphsView;
import ca.ntro.cards.intro.frontend.views.fragments.IntroEfficiencyMessageFragment;
import ca.ntro.cards.intro.models.IntroEfficiencyDashboardModel;
import ca.ntro.cards.intro.models.IntroEfficiencySettingsModel;
import ca.ntro.cards.intro.models.IntroGraphsModel;
import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.messages.MsgMessageToUser;
import ca.ntro.cards.efficiency.frontend.EfficiencyFrontend;

public class IntroEfficiencyFrontend 

       extends EfficiencyFrontend<IntroEfficiencyRootView,
                                  IntroEfficiencySettingsView, 
                                  IntroGraphsView, 
                                  IntroEfficiencyDashboardView, 
                                  IntroEfficiencyMessagesView,
                                  IntroEfficiencyMessageFragment,
                                  IntroEfficiencyViewData, 
                                  IntroGraphsModel, 
                                  IntroEfficiencyDashboardModel, 
                                  IntroEfficiencySettingsModel> {

	@Override
	protected boolean isProd() {
		return true;
	}

	@Override
	protected Class<IntroEfficiencyRootView> rootViewClass() {
		return IntroEfficiencyRootView.class;
	}

	@Override
	protected Class<IntroEfficiencySettingsView> settingsViewClass() {
		return IntroEfficiencySettingsView.class;
	}

	@Override
	protected Class<IntroGraphsView> canvasViewClass() {
		return IntroGraphsView.class;
	}

	@Override
	protected Class<IntroEfficiencyDashboardView> dashboardViewClass() {
		return IntroEfficiencyDashboardView.class;
	}


	@Override
	protected Class<IntroEfficiencyViewData> viewDataClass() {
		return IntroEfficiencyViewData.class;
	}


	@Override
	protected Class<IntroEfficiencyMessagesView> messagesViewClass() {
		return IntroEfficiencyMessagesView.class;
	}

	@Override
	protected Class<IntroEfficiencyMessageFragment> messageFragmentClass() {
		return IntroEfficiencyMessageFragment.class;
	}

	@Override
	public void execute() {
		super.execute();
		
		MsgMessageToUser msgMessageToUser = NtroApp.newMessage(MsgMessageToUser.class);
		msgMessageToUser.setResourceKey("welcome");
		msgMessageToUser.send();
	}

}
