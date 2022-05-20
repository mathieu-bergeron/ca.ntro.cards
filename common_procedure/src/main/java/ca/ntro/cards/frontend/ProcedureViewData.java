package ca.ntro.cards.frontend;

import ca.ntro.app.NtroApp;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.frontend.CommonViewData;
import ca.ntro.cards.messages.MsgExecutionStepForward;

public abstract class ProcedureViewData extends CommonViewData {

	private boolean isThreadRunning = false;
	private double timeSinceLastDashboardUpdate;
	
	private boolean isCodeExecuting = false;
	private double timeSinceLastExecutionStep;
	private MsgExecutionStepForward msgExecutionStepForward = NtroApp.newMessage(MsgExecutionStepForward.class);

	public void onTimePasses(double secondsElapsed) {
		if(isCodeExecuting) {

			timeSinceLastExecutionStep -= secondsElapsed;
			
			if(timeSinceLastExecutionStep < 0) {
				timeSinceLastExecutionStep = CommonConstants.SECONDS_BETWEEN_EXECUTION_STEPS;
				msgExecutionStepForward.send();
			}
		}

		super.onTimePasses(secondsElapsed);
	}

	public void startCodeExecution() {
		isCodeExecuting = true;
		timeSinceLastExecutionStep = CommonConstants.SECONDS_BETWEEN_EXECUTION_STEPS;
	}

	public void stopCodeExecution() {
		isCodeExecuting = false;
	}

}
