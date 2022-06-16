package ca.ntro.cards.naivesort.models.world2d;

import java.util.ArrayList;

import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.CommonConstants;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.naivesort.NaivesortConstants;
import ca.ntro.cards.naivesort.messages.NaivesortMsgAcceptManualModel;
import ca.ntro.cards.naivesort.models.TriNaif;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class NaivesortProcedureWorld2d extends
		ProcedureWorld2d<NaivesortProcedureObject2d, NaivesortProcedureWorld2d, NaivesortProcedureDrawingOptions> {

	private NaivesortMsgAcceptManualModel msgAcceptManualModel = NtroApp
			.newMessage(NaivesortMsgAcceptManualModel.class);

	private NaivesortCard2d lastFlippedCard = null;

	public void registerFlippedCard(NaivesortCard2d card2d) {
		this.lastFlippedCard = card2d;
	}

	@Override
	public void buildAndSendManualModel() {

		List<NaivesortCard2d> topCards2d = new ArrayList<>();
		List<NaivesortCard2d> bottomCards2d = new ArrayList<>();

		for (Object2d object2d : getObjects()) {

			if (object2d instanceof NaivesortCard2d) {

				if (object2d.topLeftY() < CommonConstants.INITIAL_CARD_HEIGHT_MILIMETERS) {

					topCards2d.add((NaivesortCard2d) object2d);

				} else {

					bottomCards2d.add((NaivesortCard2d) object2d);
				}
			}
		}

		topCards2d.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});

		bottomCards2d.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});

		List<Card> sourceList = new ArrayList<>();
		List<Card> targetList = new ArrayList<>();

		int topCardsToWithdraw = 0, bottomCardsToAdd = 0;
		if (topCards2d.size() > bottomCards2d.size()) { // GERER LES CAS OU TOPCARDS A UNE CARTE EN MOINS QUE
														// BOTTOMCARDS
			topCardsToWithdraw++;
			bottomCardsToAdd++;
		}

		int indexOfNextVoid = 0; // AJOUTER

		for (NaivesortCard2d card2d : topCards2d) {
			if (card2d.isNullCard()) {
				if (topCardsToWithdraw > 0) {
					topCardsToWithdraw--;
				} else {
					targetList.add(null);
				}
			} else {
				targetList.add((Card) card2d.getCard());

				indexOfNextVoid++;
			}
		}
		// SOURCE

		int indexOfCandidate = -1;

		for (int i = 0; i < bottomCards2d.size(); i++) {
			NaivesortCard2d card2d = bottomCards2d.get(i);

			double topLeftX = 75 + (i * 25) + (i * NaivesortConstants.INITIAL_CARD_WIDTH_MILIMETERS);

			if (card2d.isNullCard()) {
				sourceList.add(null);
			} else {
				if (bottomCardsToAdd > 0 && topLeftX + 50 < card2d.getTopLeftX()) {
					sourceList.add(null);
					sourceList.add((Card) card2d.getCard());
					bottomCardsToAdd--;
				} else {
					sourceList.add((Card) card2d.getCard());
				}

				if (card2d == this.lastFlippedCard) {
					indexOfCandidate = i;
				}

			}
		}
		if (bottomCardsToAdd > 0) {
			sourceList.add(null);
			bottomCardsToAdd--;
		}

		// double markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndicePlusPetit()
		// * cardWidth * 3 / 2;
		double cardWidth = NaivesortConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = NaivesortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

		Object2d marker2d = objectById("smallestElement");
		int indexOfSmallest = (int) Math
				.round((marker2d.topLeftX() - 10 - cardWidth - cardWidth / 2) * 2 / 3 / cardWidth);

		TriNaif manualModel = new TriNaif();

		Card[] source = new Card[sourceList.size()];
		for (int i = 0; i < sourceList.size(); i++) {
			source[i] = sourceList.get(i);
		}

		Card[] target = new Card[targetList.size()];
		for (int i = 0; i < targetList.size(); i++) {
			target[i] = targetList.get(i);
		}

		manualModel.setSource(source);
		manualModel.setCible(target);
		manualModel.setIndicePlusPetit(indexOfSmallest);
		manualModel.setIndiceCandidat(indexOfCandidate);
		// AJOUTER
		manualModel.setIndiceProchainVide(indexOfNextVoid);

		msgAcceptManualModel.setManualModel(manualModel);
		msgAcceptManualModel.send();
	}

}
