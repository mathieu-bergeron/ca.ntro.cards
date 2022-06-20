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
		double maxWidth = FusionsortConstants.INITIAL_WORLD_WIDTH * 1.5;

		double minHeight = 0;
		double maxHeight = FusionsortConstants.INITIAL_CARD_HEIGHT_MILIMETERS * 1.5;

		TriFusion manualModel = buildManuelModel(minHeight, maxHeight, minWidth, maxWidth, 1, "null");

		interpretManualModel(manualModel);

		msgAcceptManualModel.setManualModel(manualModel);
		msgAcceptManualModel.send();
	}

	private TriFusion buildManuelModel(double minHeight, double maxHeight, double minWidth, double maxWidth, int lvl,
			String side) {

		TriFusion triFusion = null;
		FusionsortSeparator2d separator = null;

		List<FusionsortCard2d> cards2d = new ArrayList<>();

		for (Object2d object2d : getObjects()) {
			if (object2d.topLeftY() > minHeight && object2d.topLeftY() < maxHeight) {
				if (object2d.topLeftX() > minWidth && object2d.topLeftX() < maxWidth) {

					if (object2d instanceof FusionsortCard2d) {

						cards2d.add((FusionsortCard2d) object2d);

					} else if (object2d instanceof FusionsortSeparator2d) {

						separator = (FusionsortSeparator2d) object2d;
					}
				}
			}
		}

		cards2d.sort((obj01, obj02) -> {
			return Double.compare(obj01.getTopLeftX(), obj02.getTopLeftX());
		});

		int separatorIndex = -1;
		if (separator != null && cards2d.size() >= 2) {
			for (int i = 0; i < cards2d.size(); i++) {

				if (separator.topLeftX() < cards2d.get(i).getTopLeftX()) {
					separatorIndex = i;
					break;
				}
			}
			if (separatorIndex == -1) {
				separatorIndex = cards2d.size();
			}
		}

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
			triFusion.setSeparatorIndex(separatorIndex);

			int newLvl = lvl + 1;
			double cardHeight = FusionsortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

			double newMinHeight = (cardHeight * (newLvl - 1)) + ((cardHeight / 2) * (newLvl - 1));
			double newMaxHeight = newMinHeight + (cardHeight / 2) * 3;

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

		if (manualModel.gauche == null && manualModel.droite == null) {
			if (manualModel.getSeparatorIndex() != -1) {
				manualModel.addLevel();

			}
		} else {

			if (manualModel.getSeparatorIndex() == -1) { // NOT WORKING
				manualModel.gauche = null;
				manualModel.droite = null;
			} else {
				interpretManualModel(manualModel.gauche);
				interpretManualModel(manualModel.droite);
			}

		}

	}

}
