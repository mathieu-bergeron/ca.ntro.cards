package ca.ntro.cards.backend.model_history;

import ca.ntro.cards.models.ExploreCardsModel;
import ca.ntro.cards.models.DashboardModel;

public interface ModelHistory <CARDS_MODEL extends ExploreCardsModel> {

	void pushReferenceTo(CARDS_MODEL model);
	void pushCopyOf(CARDS_MODEL model);
	void updateDashboard(DashboardModel dashboardModel);

}
