// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.fusionsort.models.world2d;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.app.NtroApp;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.fusionsort.FusionsortConstants;
import ca.ntro.cards.fusionsort.messages.FusionsortMsgAcceptManualModel;
import ca.ntro.cards.fusionsort.models.TriFusion;
import ca.ntro.cards.models.world2d.ProcedureWorld2d;

public class FusionsortProcedureWorld2d extends
		ProcedureWorld2d<FusionsortProcedureObject2d, FusionsortProcedureWorld2d, FusionsortProcedureDrawingOptions> {

	private FusionsortMsgAcceptManualModel msgAcceptManualModel = NtroApp
			.newMessage(FusionsortMsgAcceptManualModel.class);

	@Override
	public void buildAndSendManualModel() {

		double minWidth = 0;
		double maxWidth = FusionsortConstants.INITIAL_WORLD_WIDTH;

		double minHeight = 0;
		double maxHeight = FusionsortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

		TriFusion manualModel = buildManuelModel(minHeight, maxHeight, minWidth, maxWidth, 1, "null");

		msgAcceptManualModel.setManualModel(manualModel);
		msgAcceptManualModel.send();
	}

	private TriFusion buildManuelModel(double minHeight, double maxHeight, double minWidth, double maxWidth, int lvl,
			String side) {

		TriFusion triFusion = null;

		List<FusionsortCard2d> cards2d = new ArrayList<>();

		for (Object2d object2d : getObjects()) {

			if (object2d instanceof FusionsortCard2d) {

				if (object2d.topLeftY() > minHeight && object2d.topLeftY() < maxHeight) {
					if (object2d.topLeftX() > minWidth && object2d.topLeftX() < maxWidth) {
						cards2d.add((FusionsortCard2d) object2d);
					}
				}

			}
		}

		cards2d.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});

		if (cards2d.size() != 0) {

			List<Card> cardsList = new ArrayList<>();

			for (FusionsortCard2d card2d : cards2d) {
				if (card2d.isNullCard()) {
					cardsList.add(null);
				} else {
					cardsList.add((Card) card2d.getCard());
				}
			}

			Card[] cards = new Card[cards2d.size()];
			for (int i = 0; i < cards.length; i++) {
				cards[i] = cardsList.get(i);
			}

			triFusion = new TriFusion();
			triFusion.setTableau(cards);
			triFusion.setLvl(lvl);
			triFusion.setSide(side);

			int newLvl = lvl + 1;
			double cardHeight = FusionsortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

			double newMinHeight = (cardHeight * (newLvl - 1)) + ((cardHeight / 2) * (newLvl - 1));
			double newMaxHeight = cardHeight + (cardHeight * (newLvl - 1)) + ((cardHeight / 2) * (newLvl - 1));

			double worldWidth = FusionsortConstants.INITIAL_WORLD_WIDTH / Math.pow(2, newLvl - 1);

			double newMinWidth = minWidth;
			double newMaxWidth = minWidth + worldWidth;

			triFusion.setGauche(buildManuelModel(newMinHeight, newMaxHeight, newMinWidth, newMaxWidth, newLvl, "left"));

			newMinWidth += worldWidth;
			newMaxWidth += worldWidth;

			triFusion
					.setDroite(buildManuelModel(newMinHeight, newMaxHeight, newMinWidth, newMaxWidth, newLvl, "right"));

		}

		return triFusion;
	}

	// A MODIFIER
	private void interpretManualModel(TriFusion manualModel) {

		if (manualModel.gauche != null && manualModel.droite != null) {

			if (manualModel.tableau.length > manualModel.gauche.tableau.length + manualModel.droite.tableau.length) { // carte
																														// ajouter
																														// au
																														// tableau

			} else if (manualModel.tableau.length < manualModel.gauche.tableau.length
					+ manualModel.droite.tableau.length) { // carte retirer au tableau

			}

		}

	}

}
