package ca.ntro.cards.naivesort.models.world2d;

import java.util.ArrayList;

import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.values.cards.NullCard;
import ca.ntro.cards.naivesort.NaivesortConstants;
import ca.ntro.cards.naivesort.messages.NaivesortMsgAcceptManualModel;
import ca.ntro.cards.naivesort.models.TriNaif;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class NaivesortProcedureWorld2d extends ProcedureWorld2d<NaivesortProcedureObject2d, NaivesortProcedureWorld2d, NaivesortProcedureDrawingOptions> {
	
	private NaivesortMsgAcceptManualModel msgAcceptManualModel = NtroApp.newMessage(NaivesortMsgAcceptManualModel.class);
	
	private NaivesortCard2d lastFlippedCard = null;

	private NaivesortCard2d draggedCard = null;
	private double draggedCardOriginalTopLeftX;
	private double draggedCardOriginalTopLeftY;

	@Override
	public void registerDraggedObject2d(ProcedureObject2d object2d) {
		if(object2d instanceof NaivesortCard2d) {
			draggedCard = (NaivesortCard2d) object2d;
			draggedCardOriginalTopLeftX = draggedCard.getTopLeftX();
			draggedCardOriginalTopLeftY = draggedCard.getTopLeftY();
		}

		super.registerDraggedObject2d(object2d);
	}
	
	public void registerFlippedCard(NaivesortCard2d card2d) {
		this.lastFlippedCard = card2d;
	}

	@Override
	public void buildAndSendManualModel() {

		List<NaivesortCard2d> cards = collectCardsExceptDraggedCard();
		
		if(draggedCard != null) {

			addNullCardAtDraggedCardOriginalLocation(cards);

			insertDraggedCardAtFinalLocation(cards);

			forgetDraggedCard();
		}

		List<NaivesortCard2d> topCards2d = new ArrayList<>();
		List<NaivesortCard2d> bottomCards2d = new ArrayList<>();

		splitTopBottomCards(cards, topCards2d, bottomCards2d);
		
		orderCardsLeftToRight(topCards2d);
		orderCardsLeftToRight(bottomCards2d);

		Card[] source = new Card[bottomCards2d.size()];
		
		int indexOfCandidate = -1;

		for(int i = 0; i < bottomCards2d.size(); i++) {
			NaivesortCard2d card2d = bottomCards2d.get(i);

			if(card2d == this.lastFlippedCard) {
				indexOfCandidate = i;
			}

			if(card2d.isNullCard()) {
				source[i] = null;
			}else {
				source[i] = (Card) card2d.getCard();
				
			}
		}

		Card[] target = new Card[topCards2d.size()];
		int indexOfNextEmpty = 0;

		for(int i = 0; i < topCards2d.size(); i++) {
			NaivesortCard2d card2d = topCards2d.get(i);

			if(card2d.isNullCard()) {
				target[i] = null;
			}else {
				target[i] = (Card) card2d.getCard();
				indexOfNextEmpty = i + 1;
			}
		}

		//double markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndicePlusPetit() * cardWidth * 3 / 2;
		double cardWidth = NaivesortConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = NaivesortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

		Object2d marker2d = objectById("smallestElement");
		int indexOfSmallest = (int) Math.round((marker2d.topLeftX() - 10 - cardWidth - cardWidth / 2) * 2 / 3 / cardWidth);
		
		TriNaif manualModel = new TriNaif();

		manualModel.setSource(source);
		manualModel.setCible(target);
		manualModel.setIndicePlusPetit(indexOfSmallest);
		manualModel.setIndiceCandidat(indexOfCandidate);
		manualModel.setIndiceProchainVide(indexOfNextEmpty);

		msgAcceptManualModel.setManualModel(manualModel);
		msgAcceptManualModel.send();
	}

	private List<NaivesortCard2d> collectCardsExceptDraggedCard(){
		List<NaivesortCard2d> cards = new ArrayList<>();

		for(Object2d object2d : getObjects()) {
			if(object2d instanceof NaivesortCard2d
					&& object2d != draggedCard) {
				cards.add((NaivesortCard2d) object2d);
			}
		}
		
		return cards;
	}

	private void splitTopBottomCards(List<NaivesortCard2d> cards, 
			                         List<NaivesortCard2d> topCards2d, 
			                         List<NaivesortCard2d> bottomCards2d) {

		for(NaivesortCard2d card : cards) {

			if(card.topLeftY() < CommonConstants.INITIAL_CARD_HEIGHT_MILIMETERS) {
				
				topCards2d.add(card);

			}else {

				bottomCards2d.add(card);
			}
		}
	}

	private void addNullCardAtDraggedCardOriginalLocation(List<NaivesortCard2d> cards) {
		NaivesortCard2d draggedCardLocation = new NaivesortCard2d(new NullCard());
		draggedCardLocation.setTopLeftX(draggedCardOriginalTopLeftX);
		draggedCardLocation.setTopLeftY(draggedCardOriginalTopLeftY);

		cards.add(draggedCardLocation);
	}

	private void insertDraggedCardAtFinalLocation(List<NaivesortCard2d> cards) {
		int indexOfClosestCard = indexOfClosestCard(cards, draggedCard);
		cards.set(indexOfClosestCard, draggedCard);
	}

	private int indexOfClosestCard(List<NaivesortCard2d> cards, NaivesortCard2d draggedCard) {
		double distance = Double.MAX_VALUE;
		int index = -1;
		
		for(int i = 0; i < cards.size(); i++) {
			NaivesortCard2d card = cards.get(i);

			double distanceThisCard = distance(card, draggedCard);
			
			if(distanceThisCard < distance) {
				distance = distanceThisCard;
				index = i;
			}
		}
		
		return index;
	}

	private double distance(NaivesortCard2d cardA, NaivesortCard2d cardB) {
		return Math.sqrt(Math.pow(cardA.topLeftX() - cardB.topLeftX(),2) + Math.pow(cardA.topLeftY() - cardB.topLeftY(), 2));
	}

	private void orderCardsLeftToRight(List<NaivesortCard2d> topCards2d) {
		topCards2d.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});
	}

	private void forgetDraggedCard() {
		draggedCard = null;
	}

}
