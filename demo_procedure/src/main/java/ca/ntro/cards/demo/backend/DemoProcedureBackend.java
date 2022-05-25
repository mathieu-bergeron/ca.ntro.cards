package ca.ntro.cards.demo.backend;



import ca.ntro.app.tasks.backend.BackendTasks;
import static ca.ntro.app.tasks.backend.BackendTasks.*;
import ca.ntro.cards.backend.ProcedureBackend;
import ca.ntro.cards.demo.backend.tasks.DemoModifyCardsModel;
import ca.ntro.cards.demo.messages.MsgUpdateList;
import ca.ntro.cards.demo.models.TriNaif;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.cards.demo.models.DemoProcedureDashboardModel;
import ca.ntro.cards.demo.models.DemoProcedureSettingsModel;
import ca.ntro.cards.demo.models.DemoTestCasesModel;

public class   DemoProcedureBackend<STUDENT_MODEL extends TriNaif>


       extends ProcedureBackend<TriNaif, // executable model
                                STUDENT_MODEL,
                                TriNaif, // canvas model
                                DemoTestCase,
                                DemoTestCasesModel,
                                DemoProcedureDashboardModel,
                                DemoProcedureSettingsModel> {

	
	

	@Override
	protected void addSubTasksToModifyCardsModel(BackendTasks subTasks) {

		 DemoModifyCardsModel.updateList(subTasks, getExecutableModelClass());

	}

	@Override
	protected void addSubTasksToModifySettingsModel(BackendTasks subTasks) {

	}

	@Override
	protected void addSubTasksToModifyDashboardModel(BackendTasks subTasks) {
		super.addSubTasksToModifyDashboardModel(subTasks);

		subTasks.task("updateListDashboard")
		     .waitsFor(message(MsgUpdateList.class))
		     .thenExecutes(inputs -> {
		    	 
		    	 System.out.println("ModifyDashbaordModel.updateList");
		    	 
		    	 
		     });

	}

	@Override
	protected void createAdditionalTasks(BackendTasks tasks) {
		
	}

	@Override
	protected void addSubTasksToManageThread(BackendTasks subTasks) {
		
	}

	@Override
	protected void addSubTasksToModifyTestCasesModel(BackendTasks subTasks) {

	}




}
