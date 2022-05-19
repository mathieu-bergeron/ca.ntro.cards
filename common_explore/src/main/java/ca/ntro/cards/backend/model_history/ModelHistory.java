package ca.ntro.cards.backend.model_history;

import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;

public interface ModelHistory <CARDS_MODEL extends CardsModel> {

	void pushReferenceTo(CARDS_MODEL model);
	void pushCopyOf(CARDS_MODEL model);
	void updateDashboard(DashboardModel dashboardModel);

}
