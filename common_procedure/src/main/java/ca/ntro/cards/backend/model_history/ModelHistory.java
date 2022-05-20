package ca.ntro.cards.backend.model_history;

import ca.ntro.cards.common.models.CommonDashboardModel;
import ca.ntro.cards.models.ProcedureCardsModel;

public interface ModelHistory <CARDS_MODEL extends ProcedureCardsModel> {

	void pushReferenceTo(CARDS_MODEL model);
	void pushCopyOf(CARDS_MODEL model);
	void updateDashboard(CommonDashboardModel dashboardModel);

}
