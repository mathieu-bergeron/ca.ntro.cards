package ca.ntro.cards.demo.messages;

import ca.ntro.cards.demo.frontend.procedures.DemoNaiveSort;
import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.demo.models.DemoDashboardModel;
import ca.ntro.cards.messages.MsgRegisterSimpleOperation;

public class DemoMsgRegisterSimpleOperation extends MsgRegisterSimpleOperation<DemoCardsModel, DemoDashboardModel> {
	
	private DemoNaiveSort naiveSort;

	public DemoNaiveSort getNaiveSort() {
		return naiveSort;
	}

	public void setNaiveSort(DemoNaiveSort naiveSort) {
		this.naiveSort = naiveSort;
	}
	
	

	@Override
	public void applyTo(DemoCardsModel cardsModel) {
		cardsModel.setNaiveSort(naiveSort);
	}

	@Override
	public void applyTo(DemoDashboardModel dashboardModel) {
		dashboardModel.incrementSimpleOperations();
	}
	
	
	

}
