package ca.ntro.cards.common.backend.model_history;

import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;

public class ModelHistorySumOnly<CARDS_MODEL extends CommonCanvasModel> 

       implements ModelHistory<CARDS_MODEL> {
    	   
    	   private int size = 0;
    	   

	@Override
	public void pushReferenceTo(CARDS_MODEL model) {
		size++;
	}

	@Override
	public void pushCopyOf(CARDS_MODEL model) {
		size++;
	}

	@Override
	public void updateDashboard(CommonDashboardModel dashboardModel) {
		dashboardModel.setNumberOfSteps(size);
	}

}
