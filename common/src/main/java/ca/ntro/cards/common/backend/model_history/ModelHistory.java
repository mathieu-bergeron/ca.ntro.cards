package ca.ntro.cards.common.backend.model_history;

import ca.ntro.cards.common.models.CommonCanvasModel;
import ca.ntro.cards.common.models.CommonDashboardModel;

public interface ModelHistory <CARDS_MODEL extends CommonCanvasModel> {

	void pushReferenceTo(CARDS_MODEL model);
	void pushCopyOf(CARDS_MODEL model);
	void updateDashboard(CommonDashboardModel dashboardModel);

}
