package ca.ntro.cards.demo.models;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.cards.demo.DemoConstants;
import ca.ntro.cards.demo.frontend.procedures.DemoNaiveSort;
import ca.ntro.cards.frontend.views.data.CardsViewData;
import ca.ntro.cards.models.CardsModel;
import ca.ntro.cards.models.enums.Suit;
import ca.ntro.cards.models.values.AbstractCard;
import ca.ntro.cards.models.values.Card;
import ca.ntro.cards.models.values.NullCard;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class DemoCardsModel extends CardsModel {
	
	private DemoNaiveSort naiveSort;

	private List<Card> sourceList = new ArrayList<>();
	private List<Card> targetList = new ArrayList<>();

	public DemoNaiveSort getNaiveSort() {
		return naiveSort;
	}

	public void setNaiveSort(DemoNaiveSort naiveSort) {
		this.naiveSort = naiveSort;
	}

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

	@Override
	protected void updateViewDataImpl(CardsViewData cardsViewData) {

		double cardWidth = DemoConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = DemoConstants.INITIAL_CARD_HEIGHT_MILIMETERS;
		
		for(int i = 0; i < sourceList.size(); i++) {

			double targetTopLeftX = cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight / 2;
			
			AbstractCard card = sourceList.get(i);
			
			if(card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card,
					                      targetTopLeftX,
					                      targetTopLeftY);
		}

		for(int i = 0; i < targetList.size(); i++) {

			double targetTopLeftX = cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight * 2;
			
			AbstractCard card = targetList.get(i);
			
			if(card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card,
					                      targetTopLeftX,
					                      targetTopLeftY);
		}
	}

	public void updateCards(List<Card> sourceList, List<Card> targetList) {
		setSourceList(sourceList);
		setTargetList(targetList);
		incrementVersion();
	}

	@Override
	public void createFirstVersion() {
		sourceList.add(new Card(3, Suit.HEARTS));
		sourceList.add(new Card(6, Suit.CLUBS));
		sourceList.add(new Card(4, Suit.SPADES));
		sourceList.add(new Card(10, Suit.DIAMONDS));
		sourceList.add(new Card(5, Suit.HEARTS));
		
		for(int i = 0; i < sourceList.size(); i++) {
			targetList.add(null);
		}
	}
	
	@Override
	protected Card cardById(String cardId) {
		Card result = null;
		
		for(Card candidate : sourceList) {
			if(candidate.hasId(cardId)){
				result = candidate;
				break;
			}
		}
		
		if(result == null) {
			for(Card candidate : targetList) {
				if(candidate.hasId(cardId)){
					result = candidate;
					break;
				}
			}
		}

		
		return result;
	}

	@Override
	protected Stream<Card> cards() {
		return new StreamNtro<Card>() {
			@Override
			public void forEach_(Visitor<Card> visitor) throws Throwable {
				for(Card card : sourceList) {
					visitor.visit(card);
				}
				for(Card card : targetList) {
					visitor.visit(card);
				}
			}
		};
	}

	@Override
	protected void addCardImpl(Card card) {
		targetList.add(card);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		format(builder);
		
		return builder.toString();

	}
	
	public void format(StringBuilder builder) {
		for(Card card : targetList) {
			builder.append(System.lineSeparator());
			card.format(builder);
		}
	}

}
