package ca.ntro.cards.backend.model_history;

import ca.ntro.cards.models.ExploreCardsModel;
import ca.ntro.cards.models.DashboardModel;

public class ModelHistorySumOnly<CARDS_MODEL extends ExploreCardsModel> 

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
	public void updateDashboard(DashboardModel dashboardModel) {
		dashboardModel.setNumberOfSteps(size);
	}

}
