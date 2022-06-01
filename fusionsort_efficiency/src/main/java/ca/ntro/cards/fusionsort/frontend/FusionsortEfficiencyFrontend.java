package ca.ntro.cards.fusionsort.frontend;

import ca.ntro.cards.fusionsort.frontend.views.FusionsortEfficiencyDashboardView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortEfficiencyMessagesView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortEfficiencyRootView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortEfficiencySettingsView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortGraphsView;
import ca.ntro.cards.fusionsort.frontend.views.fragments.FusionsortEfficiencyMessageFragment;
import ca.ntro.cards.fusionsort.models.FusionsortEfficiencyDashboardModel;
import ca.ntro.cards.fusionsort.models.FusionsortEfficiencySettingsModel;
import ca.ntro.cards.fusionsort.models.FusionsortGraphsModel;
import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.messages.MsgMessageToUser;
import ca.ntro.cards.efficiency.frontend.EfficiencyFrontend;

public class FusionsortEfficiencyFrontend 

       extends EfficiencyFrontend<FusionsortEfficiencyRootView,
                                  FusionsortEfficiencySettingsView, 
                                  FusionsortGraphsView, 
                                  FusionsortEfficiencyDashboardView, 
                                  FusionsortEfficiencyMessagesView,
                                  FusionsortEfficiencyMessageFragment,
                                  FusionsortEfficiencyViewData, 
                                  FusionsortGraphsModel, 
                                  FusionsortEfficiencyDashboardModel, 
                                  FusionsortEfficiencySettingsModel> {

	@Override
	protected boolean isProd() {
		return true;
	}

	@Override
	protected Class<FusionsortEfficiencyRootView> rootViewClass() {
		return FusionsortEfficiencyRootView.class;
	}

	@Override
	protected Class<FusionsortEfficiencySettingsView> settingsViewClass() {
		return FusionsortEfficiencySettingsView.class;
	}

	@Override
	protected Class<FusionsortGraphsView> canvasViewClass() {
		return FusionsortGraphsView.class;
	}

	@Override
	protected Class<FusionsortEfficiencyDashboardView> dashboardViewClass() {
		return FusionsortEfficiencyDashboardView.class;
	}


	@Override
	protected Class<FusionsortEfficiencyViewData> viewDataClass() {
		return FusionsortEfficiencyViewData.class;
	}


	@Override
	protected Class<FusionsortEfficiencyMessagesView> messagesViewClass() {
		return FusionsortEfficiencyMessagesView.class;
	}

	@Override
	protected Class<FusionsortEfficiencyMessageFragment> messageFragmentClass() {
		return FusionsortEfficiencyMessageFragment.class;
	}

	@Override
	public void execute() {
		super.execute();
		
		MsgMessageToUser msgMessageToUser = NtroApp.newMessage(MsgMessageToUser.class);
		msgMessageToUser.setResourceKey("welcome");
		msgMessageToUser.send();
	}

}
