package ca.ntro.cards.arraylist.frontend;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureRootView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureSettingsView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistReplayView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistVariablesView;
import ca.ntro.cards.arraylist.frontend.views.fragments.ArraylistProcedureMessageFragment;
import ca.ntro.cards.arraylist.frontend.views.fragments.ArraylistTestCaseFragment;
import ca.ntro.cards.arraylist.models.ListeTableau;
import ca.ntro.cards.frontend.ProcedureFrontend;
import ca.ntro.cards.arraylist.models.ArraylistProcedureDashboardModel;
import ca.ntro.cards.arraylist.models.ArraylistProcedureSettingsModel;
import ca.ntro.cards.common.messages.MsgMessageToUser;
import ca.ntro.cards.arraylist.frontend.views.ArraylistCardsView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureMessagesView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistSelectionsView;
import ca.ntro.cards.arraylist.frontend.views.ArraylistProcedureDashboardView;

public class ArraylistProcedureFrontend<STUDENT_MODEL extends ListeTableau>

       extends ProcedureFrontend<ArraylistProcedureRootView,
                                 ArraylistProcedureSettingsView, 
                                 ArraylistCardsView, 
                                 ArraylistProcedureDashboardView, 
                                 ArraylistSelectionsView,
                                 ArraylistTestCaseFragment,
                                 ArraylistReplayView,
                                 ArraylistVariablesView,
                                 ArraylistProcedureMessagesView,
                                 ArraylistProcedureMessageFragment,
                                 ArraylistProcedureViewData, 
                                 STUDENT_MODEL, // CanvasModel
                                 ArraylistProcedureDashboardModel, 
                                 ArraylistProcedureSettingsModel> {


	@Override
	protected boolean isProd() {
		return false;
	}

	@Override
	protected Class<ArraylistProcedureRootView> rootViewClass() {
		return ArraylistProcedureRootView.class;
	}

	@Override
	protected Class<ArraylistProcedureSettingsView> settingsViewClass() {
		return ArraylistProcedureSettingsView.class;
	}

	@Override
	protected Class<ArraylistCardsView> canvasViewClass() {
		return ArraylistCardsView.class;
	}

	@Override
	protected Class<ArraylistProcedureDashboardView> dashboardViewClass() {
		return ArraylistProcedureDashboardView.class;
	}


	@Override
	protected Class<ArraylistProcedureViewData> viewDataClass() {
		return ArraylistProcedureViewData.class;
	}



	@Override
	protected Class<ArraylistSelectionsView> selectionsViewClass() {
		return ArraylistSelectionsView.class;
	}

	@Override
	protected Class<ArraylistReplayView> replayControlsViewClass() {
		return ArraylistReplayView.class;
	}

	@Override
	protected Class<ArraylistVariablesView> variablesViewClass() {
		return ArraylistVariablesView.class;
	}


	@Override
	protected Class<ArraylistTestCaseFragment> testCaseFragmentClass() {
		return ArraylistTestCaseFragment.class;
	}

	@Override
	protected Class<ArraylistProcedureMessagesView> messagesViewClass() {
		return ArraylistProcedureMessagesView.class;
	}

	@Override
	protected Class<ArraylistProcedureMessageFragment> messageFragmentClass() {
		return ArraylistProcedureMessageFragment.class;
	}

	@Override
	public void execute() {
		super.execute();

		MsgMessageToUser msgMessageToUser = NtroApp.newMessage(MsgMessageToUser.class);
		msgMessageToUser.setResourceKey("welcome");
		msgMessageToUser.send();
	}


}
