package ca.ntro.cards.intro.frontend;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.intro.frontend.views.IntroProcedureRootView;
import ca.ntro.cards.intro.frontend.views.IntroProcedureSettingsView;
import ca.ntro.cards.intro.frontend.views.IntroReplayView;
import ca.ntro.cards.intro.frontend.views.IntroVariablesView;
import ca.ntro.cards.intro.frontend.views.fragments.IntroProcedureMessageFragment;
import ca.ntro.cards.intro.frontend.views.fragments.IntroTestCaseFragment;
import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.frontend.ProcedureFrontend;
import ca.ntro.cards.intro.models.IntroProcedureDashboardModel;
import ca.ntro.cards.intro.models.IntroProcedureSettingsModel;
import ca.ntro.cards.common.messages.MsgMessageToUser;
import ca.ntro.cards.intro.frontend.views.IntroCardsView;
import ca.ntro.cards.intro.frontend.views.IntroProcedureMessagesView;
import ca.ntro.cards.intro.frontend.views.IntroSelectionsView;
import ca.ntro.cards.intro.frontend.views.IntroProcedureDashboardView;

public class IntroProcedureFrontend<STUDENT_MODEL extends Intro>

       extends ProcedureFrontend<IntroProcedureRootView,
                                 IntroProcedureSettingsView, 
                                 IntroCardsView, 
                                 IntroProcedureDashboardView, 
                                 IntroSelectionsView,
                                 IntroTestCaseFragment,
                                 IntroReplayView,
                                 IntroVariablesView,
                                 IntroProcedureMessagesView,
                                 IntroProcedureMessageFragment,
                                 IntroProcedureViewData, 
                                 STUDENT_MODEL, // CanvasModel
                                 IntroProcedureDashboardModel, 
                                 IntroProcedureSettingsModel> {


	@Override
	protected boolean isProd() {
		return false;
	}

	@Override
	protected Class<IntroProcedureRootView> rootViewClass() {
		return IntroProcedureRootView.class;
	}

	@Override
	protected Class<IntroProcedureSettingsView> settingsViewClass() {
		return IntroProcedureSettingsView.class;
	}

	@Override
	protected Class<IntroCardsView> canvasViewClass() {
		return IntroCardsView.class;
	}

	@Override
	protected Class<IntroProcedureDashboardView> dashboardViewClass() {
		return IntroProcedureDashboardView.class;
	}


	@Override
	protected Class<IntroProcedureViewData> viewDataClass() {
		return IntroProcedureViewData.class;
	}



	@Override
	protected Class<IntroSelectionsView> selectionsViewClass() {
		return IntroSelectionsView.class;
	}

	@Override
	protected Class<IntroReplayView> replayControlsViewClass() {
		return IntroReplayView.class;
	}

	@Override
	protected Class<IntroVariablesView> variablesViewClass() {
		return IntroVariablesView.class;
	}


	@Override
	protected Class<IntroTestCaseFragment> testCaseFragmentClass() {
		return IntroTestCaseFragment.class;
	}

	@Override
	protected Class<IntroProcedureMessagesView> messagesViewClass() {
		return IntroProcedureMessagesView.class;
	}

	@Override
	protected Class<IntroProcedureMessageFragment> messageFragmentClass() {
		return IntroProcedureMessageFragment.class;
	}

	@Override
	public void execute() {
		super.execute();

		MsgMessageToUser msgMessageToUser = NtroApp.newMessage(MsgMessageToUser.class);
		msgMessageToUser.setResourceKey("welcome");
		msgMessageToUser.send();
	}


}
