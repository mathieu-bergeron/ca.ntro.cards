package ca.ntro.cards.demo.messages;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.messages.MessageNtro;
import ca.ntro.cards.demo.models.NaiveSort;
import common.models.values.Card;

public class MsgUpdateList extends MessageNtro {
	
	List<Card> sourceList = new ArrayList<>();
	List<Card> targetList = new ArrayList<>();

	public List<Card> getSourceList() {
		return sourceList;
	}

	public void setSourceList(List<Card> sourceList) {
		this.sourceList = sourceList;
	}

	public List<Card> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<Card> targetList) {
		this.targetList = targetList;
	}

	public void applyTo(NaiveSort demoModel) {
		demoModel.updateCards(sourceList, targetList);
	}
}
