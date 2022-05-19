package ca.ntro.cards.backend.model_history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.DashboardModel;
import ca.ntro.core.initialization.Ntro;

public class ModelHistoryFull<CARDS_MODEL extends CardsModel> 

       implements ModelHistory<CARDS_MODEL> {

	private List<CARDS_MODEL> history = Collections.synchronizedList(new ArrayList<>());
	
	private int current = 0;
	
	@Override
	public void pushReferenceTo(CARDS_MODEL model) {
		history.add(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void pushCopyOf(CARDS_MODEL model) {
		history.add((CARDS_MODEL) Ntro.reflection().clone(model));
	}
	
	public CARDS_MODEL currentModel() {
		CARDS_MODEL currentModel = null;
		
		if(isValidIndex()) {
			currentModel = history.get(current);
		}
		
		return currentModel;
	}

	private boolean isValidIndex() {
		return current >= 0 && current < history.size();
	}
	
	public void stepForward() {
		current++;
		
		if(current >= history.size()) {
			current = history.size() - 1;
		}
	}

	public void stepBackward() {
		current--;
		
		if(current < 0) {
			current = 0;
		}
	}

	@Override
	public void updateDashboard(DashboardModel dashboardModel) {
		dashboardModel.setSimpleOperations(history.size());
	}
}
