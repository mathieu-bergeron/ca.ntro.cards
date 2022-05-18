package ca.ntro.cards.backend;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.backend.LocalBackendNtro;
import ca.ntro.app.tasks.backend.BackendTasks;
import ca.ntro.cards.backend.tasks.ModifyCardsModel;
import ca.ntro.cards.backend.tasks.ModifyDashboardModel;
import ca.ntro.cards.backend.tasks.ModifySettingsModel;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.cards.models.SettingsModel;

public abstract class CommonBackend<CARDS_MODEL extends CardsModel,
                                    DASHBOARD_MODEL extends DashboardModel,
                                    SETTINGS_MODEL extends SettingsModel,
                                    MSG_REGISTER_SIMPLE_OPERATION extends MsgRegisterSimpleOperation<CARDS_MODEL, 
                                                                                                     DASHBOARD_MODEL>> 

       extends LocalBackendNtro {
	
	public static int indexCurrentModel = 0;
	
	private Class<CARDS_MODEL> cardsModelClass;
	private Class<DASHBOARD_MODEL> dashboardModelClass;
	private Class<SETTINGS_MODEL> settingsModelClass;
	private Class<? extends MsgRegisterSimpleOperation> msgRegisterSimpleOperationClass;
	
	private List<CARDS_MODEL> modelHistory = new ArrayList<>();

	public Class<CARDS_MODEL> getCardsModelClass() {
		return cardsModelClass;
	}

	public void setCardsModelClass(Class<CARDS_MODEL> cardsModelClass) {
		this.cardsModelClass = cardsModelClass;
	}


	public Class<DASHBOARD_MODEL> getDashboardModelClass() {
		return dashboardModelClass;
	}


	public void setDashboardModelClass(Class<DASHBOARD_MODEL> dashboardModelClass) {
		this.dashboardModelClass = dashboardModelClass;
	}


	public Class<SETTINGS_MODEL> getSettingsModelClass() {
		return settingsModelClass;
	}


	public void setSettingsModelClass(Class<SETTINGS_MODEL> settingsModelClass) {
		this.settingsModelClass = settingsModelClass;
	}

	public Class<? extends MsgRegisterSimpleOperation> getMsgRegisterSimpleOperationClass() {
		return msgRegisterSimpleOperationClass;
	}


	public void setMsgRegisterSimpleOperationClass(Class<? extends MsgRegisterSimpleOperation> msgRegisterSimpleOperationClass) {
		this.msgRegisterSimpleOperationClass = msgRegisterSimpleOperationClass;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void createTasks(BackendTasks tasks) {

		ModifyDashboardModel.createTasks(tasks,
				                         dashboardModelClass,
				                         msgRegisterSimpleOperationClass,

				                          subTasks -> {

				                        	addSubTasksToModifySettingsModel(subTasks);

				                        });
		
		ModifyCardsModel.createTasks(tasks, 
				                     cardsModelClass,
				                     msgRegisterSimpleOperationClass,
				                     modelHistory,
				                     subTasks -> {
				                    	 
				                    	 addSubTasksToModifyCardsModel(subTasks);
				                    	 
				                     });


		ModifySettingsModel.createTasks(tasks,
				                        settingsModelClass,
				                        subTasks -> {
				                        	
				                        	addSubTasksToModifySettingsModel(subTasks);

				                        });
		
		createAdditionalTasks(tasks);
	}
	
	protected abstract void addSubTasksToModifyCardsModel(BackendTasks subTasks);
	protected abstract void addSubTasksToModifyDashboardModel(BackendTasks subTasks);
	protected abstract void addSubTasksToModifySettingsModel(BackendTasks subTasks);
	
	protected abstract void createAdditionalTasks(BackendTasks tasks);


	@Override
	public void execute() {
		
	}

}
