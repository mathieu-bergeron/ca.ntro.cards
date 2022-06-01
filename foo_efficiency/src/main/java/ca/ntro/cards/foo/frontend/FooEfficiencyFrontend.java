package ca.ntro.cards.foo.frontend;

import ca.ntro.cards.foo.frontend.views.FooEfficiencyDashboardView;
import ca.ntro.cards.foo.frontend.views.FooEfficiencyMessagesView;
import ca.ntro.cards.foo.frontend.views.FooEfficiencyRootView;
import ca.ntro.cards.foo.frontend.views.FooEfficiencySettingsView;
import ca.ntro.cards.foo.frontend.views.FooGraphsView;
import ca.ntro.cards.foo.frontend.views.fragments.FooEfficiencyMessageFragment;
import ca.ntro.cards.foo.models.FooEfficiencyDashboardModel;
import ca.ntro.cards.foo.models.FooEfficiencySettingsModel;
import ca.ntro.cards.foo.models.FooGraphsModel;
import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.messages.MsgMessageToUser;
import ca.ntro.cards.efficiency.frontend.EfficiencyFrontend;

public class FooEfficiencyFrontend 

       extends EfficiencyFrontend<FooEfficiencyRootView,
                                  FooEfficiencySettingsView, 
                                  FooGraphsView, 
                                  FooEfficiencyDashboardView, 
                                  FooEfficiencyMessagesView,
                                  FooEfficiencyMessageFragment,
                                  FooEfficiencyViewData, 
                                  FooGraphsModel, 
                                  FooEfficiencyDashboardModel, 
                                  FooEfficiencySettingsModel> {

	@Override
	protected boolean isProd() {
		return true;
	}

	@Override
	protected Class<FooEfficiencyRootView> rootViewClass() {
		return FooEfficiencyRootView.class;
	}

	@Override
	protected Class<FooEfficiencySettingsView> settingsViewClass() {
		return FooEfficiencySettingsView.class;
	}

	@Override
	protected Class<FooGraphsView> canvasViewClass() {
		return FooGraphsView.class;
	}

	@Override
	protected Class<FooEfficiencyDashboardView> dashboardViewClass() {
		return FooEfficiencyDashboardView.class;
	}


	@Override
	protected Class<FooEfficiencyViewData> viewDataClass() {
		return FooEfficiencyViewData.class;
	}


	@Override
	protected Class<FooEfficiencyMessagesView> messagesViewClass() {
		return FooEfficiencyMessagesView.class;
	}

	@Override
	protected Class<FooEfficiencyMessageFragment> messageFragmentClass() {
		return FooEfficiencyMessageFragment.class;
	}

	@Override
	public void execute() {
		super.execute();
		
		MsgMessageToUser msgMessageToUser = NtroApp.newMessage(MsgMessageToUser.class);
		msgMessageToUser.setMessage("[INFO] Efficiency app started");
		msgMessageToUser.send();
	}

}
