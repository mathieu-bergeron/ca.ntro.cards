package ca.ntro.cards.backend;

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
	
	private Class<CARDS_MODEL> cardsModelClass;
	private Class<DASHBOARD_MODEL> dashboardModelClass;
	private Class<SETTINGS_MODEL> settingsModelClass;
	private Class<MSG_REGISTER_SIMPLE_OPERATION> msgRegisterSimpleOperationClass;

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

	public Class<MSG_REGISTER_SIMPLE_OPERATION> getMsgRegisterSimpleOperationClass() {
		return msgRegisterSimpleOperationClass;
	}


	public void setMsgRegisterSimpleOperationClass(Class<MSG_REGISTER_SIMPLE_OPERATION> msgRegisterSimpleOperationClass) {
		this.msgRegisterSimpleOperationClass = msgRegisterSimpleOperationClass;
	}


	@Override
	public void createTasks(BackendTasks tasks) {
		
		ModifyCardsModel.createTasks(tasks, 
				                     cardsModelClass,
				                     msgRegisterSimpleOperationClass,
				                     subTasks -> {
				                    	 
				                    	 addSubTasksToModifyCardsModel(subTasks);
				                    	 
				                     });

		ModifyDashboardModel.createTasks(tasks,
				                         dashboardModelClass,
				                         msgRegisterSimpleOperationClass,

				                          subTasks -> {

				                        	addSubTasksToModifySettingsModel(subTasks);

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
