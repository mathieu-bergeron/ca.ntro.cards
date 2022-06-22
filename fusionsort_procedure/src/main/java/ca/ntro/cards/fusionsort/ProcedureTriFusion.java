// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.fusionsort;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.ProcedureApp;
import ca.ntro.cards.fusionsort.backend.FusionsortProcedureBackend;
import ca.ntro.cards.fusionsort.frontend.FusionsortProcedureFrontend;
import ca.ntro.cards.fusionsort.frontend.FusionsortProcedureViewData;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortCardsView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureMessagesView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureDashboardView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureRootView;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortProcedureSettingsView;
import ca.ntro.cards.fusionsort.frontend.views.fragments.FusionsortProcedureMessageFragment;
import ca.ntro.cards.fusionsort.messages.FusionsortMsgAcceptManualModel;
import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.fusionsort.models.values.FusionsortTestCase;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortSeparator2d;
import ca.ntro.cards.fusionsort.test_cases.FusionsortTestCaseDatabase;
import ca.ntro.cards.fusionsort.test_cases.descriptor.FusionsortTestCaseDescriptor;
import ca.ntro.cards.fusionsort.test_cases.execution_trace.FusionsortExecutionTrace;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.test_cases.descriptor.ProcedureTestCaseDescriptor;
import ca.ntro.cards.fusionsort.models.FusionsortProcedureDashboardModel;
import ca.ntro.cards.fusionsort.models.FusionsortProcedureSettingsModel;

public abstract class ProcedureTriFusion<STUDENT_MODEL extends TriFusion>

		extends ProcedureApp<TriFusion, // executable model
				STUDENT_MODEL, STUDENT_MODEL, // canvas model
				FusionsortTestCase, FusionsortTestCaseDescriptor, FusionsortTestCaseDatabase, FusionsortExecutionTrace, FusionsortProcedureDashboardModel, FusionsortProcedureSettingsModel, FusionsortMsgAcceptManualModel, FusionsortProcedureBackend<STUDENT_MODEL>, FusionsortProcedureRootView, FusionsortCardsView, FusionsortProcedureDashboardView, FusionsortProcedureSettingsView, FusionsortProcedureMessagesView, FusionsortProcedureMessageFragment, FusionsortProcedureViewData, FusionsortProcedureFrontend<STUDENT_MODEL>> {

	private String[] args;

	@Override
	public void registerArgs(String[] args) {
		this.args = args;
	}

	@Override
	protected Class<TriFusion> executableModelClass() {
		return TriFusion.class;
	}

	// TODO: renommer
	protected abstract Class<STUDENT_MODEL> studentClass();

	@Override
	protected Class<FusionsortTestCase> testCaseClass() {
		return FusionsortTestCase.class;
	}

	@Override
	protected Class<FusionsortTestCaseDatabase> testCasesModelClass() {
		return FusionsortTestCaseDatabase.class;
	}

	@Override
	protected Class<FusionsortProcedureDashboardModel> dashboardModelClass() {
		return FusionsortProcedureDashboardModel.class;
	}

	@Override
	protected Class<FusionsortProcedureSettingsModel> settingsModelClass() {
		return FusionsortProcedureSettingsModel.class;
	}

	@Override
	protected FusionsortProcedureFrontend createFrontend() {
		return new FusionsortProcedureFrontend();
	}

	@Override
	protected FusionsortProcedureBackend createBackend() {
		return new FusionsortProcedureBackend();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Class<STUDENT_MODEL> canvasModelClass() {
		return (Class<STUDENT_MODEL>) studentClass();
	}

	@Override
	protected Class<STUDENT_MODEL> studentModelClass() {
		return studentClass();
	}

	@Override
	protected Class<FusionsortTestCaseDescriptor> testCaseDescriptorClass() {
		return FusionsortTestCaseDescriptor.class;
	}

	@Override
	protected Class<FusionsortMsgAcceptManualModel> msgAcceptManualModelClass() {
		return FusionsortMsgAcceptManualModel.class;
	}

	@Override
	protected void registerAdditionnalModels(ModelRegistrar registrar) {
		registrar.registerValue(FusionsortSeparator2d.class);
	}

}
