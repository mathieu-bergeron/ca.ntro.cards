package ca.ntro.cards.common.backend.model_history;

import ca.ntro.cards.common.models.CommonCardsModel;
import ca.ntro.cards.common.models.CommonDashboardModel;

public interface ModelHistory <CARDS_MODEL extends CommonCardsModel> {

	void pushReferenceTo(CARDS_MODEL model);
	void pushCopyOf(CARDS_MODEL model);
	void updateDashboard(CommonDashboardModel dashboardModel);

}
