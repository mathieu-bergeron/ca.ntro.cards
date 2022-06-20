// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.fusionsort.frontend;

import static ca.ntro.app.tasks.frontend.FrontendTasks.created;
import static ca.ntro.app.tasks.frontend.FrontendTasks.modified;
import static ca.ntro.app.tasks.frontend.FrontendTasks.event;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.tasks.SimpleTaskCreator;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureRootView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureSettingsView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortReplayView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortVariablesView;
import ca.ntro.cards.fusionsort.frontend.views.fragments.FusionsortProcedureMessageFragment;
import ca.ntro.cards.fusionsort.frontend.views.fragments.FusionsortTestCaseFragment;
import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.core.reflection.observer.Modified;
import ca.ntro.cards.frontend.ProcedureFrontend;
import ca.ntro.cards.fusionsort.models.FusionsortProcedureDashboardModel;
import ca.ntro.cards.fusionsort.models.FusionsortProcedureSettingsModel;
import ca.ntro.cards.common.messages.MsgMessageToUser;
import ca.ntro.cards.fusionsort.frontend.events.EvtSaveControlKeyState;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortCardsView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureMessagesView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortSelectionsView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureDashboardView;

public class FusionsortProcedureFrontend<STUDENT_MODEL extends TriFusion>

       extends ProcedureFrontend<FusionsortProcedureRootView,
                                 FusionsortProcedureSettingsView, 
                                 FusionsortCardsView, 
                                 FusionsortProcedureDashboardView, 
                                 FusionsortSelectionsView,
                                 FusionsortTestCaseFragment,
                                 FusionsortReplayView,
                                 FusionsortVariablesView,
                                 FusionsortProcedureMessagesView,
                                 FusionsortProcedureMessageFragment,
                                 FusionsortProcedureViewData, 
                                 STUDENT_MODEL, // CanvasModel
                                 FusionsortProcedureDashboardModel, 
                                 FusionsortProcedureSettingsModel> {


	@Override
	protected boolean isProd() {
		return false;
	}

	@Override
	protected Class<FusionsortProcedureRootView> rootViewClass() {
		return FusionsortProcedureRootView.class;
	}

	@Override
	protected Class<FusionsortProcedureSettingsView> settingsViewClass() {
		return FusionsortProcedureSettingsView.class;
	}

	@Override
	protected Class<FusionsortCardsView> canvasViewClass() {
		return FusionsortCardsView.class;
	}

	@Override
	protected Class<FusionsortProcedureDashboardView> dashboardViewClass() {
		return FusionsortProcedureDashboardView.class;
	}


	@Override
	protected Class<FusionsortProcedureViewData> viewDataClass() {
		return FusionsortProcedureViewData.class;
	}



	@Override
	protected Class<FusionsortSelectionsView> selectionsViewClass() {
		return FusionsortSelectionsView.class;
	}

	@Override
	protected Class<FusionsortReplayView> replayControlsViewClass() {
		return FusionsortReplayView.class;
	}

	@Override
	protected Class<FusionsortVariablesView> variablesViewClass() {
		return FusionsortVariablesView.class;
	}


	@Override
	protected Class<FusionsortTestCaseFragment> testCaseFragmentClass() {
		return FusionsortTestCaseFragment.class;
	}

	@Override
	protected Class<FusionsortProcedureMessagesView> messagesViewClass() {
		return FusionsortProcedureMessagesView.class;
	}

	@Override
	protected Class<FusionsortProcedureMessageFragment> messageFragmentClass() {
		return FusionsortProcedureMessageFragment.class;
	}

	@Override
	public void execute() {
		super.execute();

		MsgMessageToUser msgMessageToUser = NtroApp.newMessage(MsgMessageToUser.class);
		msgMessageToUser.setResourceKey("welcome");
		msgMessageToUser.send();
	}
	
	@Override
	protected void registerAdditionnalEvents(EventRegistrar registrar) {
		registrar.registerEvent(EvtSaveControlKeyState.class);
	}
	
	@Override
	protected void addSubTasksToCards(FrontendTasks subTasks) {
		
		subTasks.task("saveControlKeyState")
		
	     .waitsFor(event(EvtSaveControlKeyState.class))
	     
	     .waitsFor(created(FusionsortProcedureViewData.class))
	     
	     .thenExecutes(inputs -> {
	    	 
	    	 EvtSaveControlKeyState evtSaveControlKeyState = inputs.get(event(EvtSaveControlKeyState.class));
	    	 FusionsortProcedureViewData fusionsortProcedureViewData = inputs.get(created(viewDataClass()));
	    	 
	    	 fusionsortProcedureViewData.setControlKeyState(evtSaveControlKeyState.getControlKeyState());
	    	 
	     });
	}


}
