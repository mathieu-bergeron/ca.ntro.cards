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

		TriFusion manualModel = buildManuelModel(minHeight, maxHeight, minWidth, maxWidth, 1);

		msgAcceptManualModel.setManualModel(manualModel);
		msgAcceptManualModel.send();
	}

	private TriFusion buildManuelModel(double minHeight, double maxHeight, double minWidth, double maxWidth, int lvl) {

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
		if (separator != null && cards2d.size() >= 1) {

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

			triFusion = new TriFusion();

			if (separatorIndex != -1) {
				int newLvl = lvl + 1;
				double cardHeight = FusionsortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

				double newMinHeight = (cardHeight * (newLvl - 1)) + ((cardHeight / 2) * (newLvl - 1));
				double newMaxHeight = newMinHeight + (cardHeight / 2) * 3;

				double worldWidth = FusionsortConstants.INITIAL_WORLD_WIDTH / Math.pow(2, newLvl - 1);

				double newMinWidth = minWidth;
				double newMaxWidth = minWidth + worldWidth;

				triFusion.setGauche(buildManuelModel(newMinHeight, newMaxHeight, newMinWidth, newMaxWidth, newLvl));

				newMinWidth += worldWidth;
				newMaxWidth += worldWidth;

				triFusion.setDroite(buildManuelModel(newMinHeight, newMaxHeight, newMinWidth, newMaxWidth, newLvl));

				if (triFusion.gauche == null && triFusion.droite == null) {

					addLevel(triFusion, cardsList, separatorIndex, newLvl);

				} else {

					int leftSize = 0;
					int rightSize = 0;
					if (triFusion.gauche != null) {
						leftSize = triFusion.gauche.tableau.length;
					}

					if (triFusion.droite != null) {
						rightSize = triFusion.droite.tableau.length;
					}

					if (cardsList.size() != leftSize + rightSize) {

						int divergenceTopBottom = cardsList.size() - (leftSize + rightSize);
						int divergenceLeftRight = leftSize - rightSize;

						if (divergenceTopBottom == 2) {

							if (divergenceLeftRight >= 1) {

								if (rightSize == 0) {
									TriFusion newRight = new TriFusion();
									newRight.setLvl(newLvl);
									triFusion.setDroite(newRight);

								}
								List<Card> cardsRight = cardsArrayToList((Card[]) triFusion.droite.tableau);
								addNullCardToEnd(cardsRight);
								Card[] newCards = cardsListToArray(cardsRight);
								triFusion.droite.setTableau(newCards);

							} else {

								if (leftSize == 0) {
									TriFusion newLeft = new TriFusion();
									newLeft.setLvl(newLvl);
									triFusion.setGauche(newLeft);
								}
								List<Card> cardsLeft = cardsArrayToList((Card[]) triFusion.gauche.tableau);
								addNullCardToEnd(cardsLeft);
								Card[] newCards = cardsListToArray(cardsLeft);
								triFusion.gauche.setTableau(newCards);

							}

							cardsList = withdrawNullCardFrom(cardsList);

						} else if (divergenceTopBottom == -2) {

							if (divergenceLeftRight >= 1) {

								addNullCardTo(cardsList, separatorIndex, true);

								Card[] cards = (Card[]) triFusion.gauche.tableau;
								List<Card> newCards = withdrawNullCardFrom(cards);
								newCards = rearrangedCards(newCards);

								triFusion.gauche.setTableau(cardsListToArray(newCards));

							} else {

								addNullCardTo(cardsList, separatorIndex, false);

								Card[] cards = (Card[]) triFusion.droite.tableau;
								List<Card> newCards = withdrawNullCardFrom(cards);
								newCards = rearrangedCards(newCards);

								triFusion.droite.setTableau(cardsListToArray(newCards));

							}

						}

						separatorIndex = (int) Math.ceil(cardsList.size() / 2.0);
						if (triFusion.gauche != null && triFusion.gauche.getSeparatorIndex() != -1) {
							triFusion.gauche.setSeparatorIndex((int) Math.ceil(triFusion.gauche.tableau.length / 2.0));
						}
						if (triFusion.droite != null && triFusion.droite.getSeparatorIndex() != -1) {
							triFusion.droite.setSeparatorIndex((int) Math.ceil(triFusion.droite.tableau.length / 2.0));
						}

					}

				}
			}

			Card[] cards = cardsListToArray(cardsList);

			triFusion.setTableau(cards);
			triFusion.setSeparatorIndex(separatorIndex);
			triFusion.setLvl(lvl);

		}

		return triFusion;
	}

	private void addNullCardTo(List<Card> cardsList, int separatorIndex, boolean beforeSeparator) {
		if (beforeSeparator) {
			cardsList.add(0, null);
		} else {
			cardsList.add(separatorIndex, null);
		}
	}

	private void addNullCardToEnd(List<Card> cardsList) {
		if (cardsList.size() > 0) {
			cardsList.add(cardsList.size() - 1, null);
		} else {
			cardsList.add(null);
		}

	}

	private List<Card> withdrawNullCardFrom(Card[] cards) {
		List<Card> newCards = new ArrayList<>();

		int cardsToWithdraw = 1;
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] == null && cardsToWithdraw >= 1) {
				cardsToWithdraw--;
			} else {
				newCards.add(cards[i]);
			}

		}
		return newCards;
	}

	private List<Card> withdrawNullCardFrom(List<Card> cards) {
		List<Card> newCards = new ArrayList<>();

		int cardsToWithdraw = 1;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i) == null && cardsToWithdraw >= 1) {
				cardsToWithdraw--;
			} else {
				newCards.add(cards.get(i));
			}

		}
		return newCards;
	}

	private void addLevel(TriFusion triFusion, List<Card> tableau, int separatorIndex, int newLvl) {
		if (tableau.size() >= 2) {

			int leftSize = separatorIndex;
			Card[] leftCards = new Card[leftSize];
			Card[] rightCards = new Card[tableau.size() - leftSize];

			for (int i = 0; i < leftCards.length; i++) {
				leftCards[i] = null;
			}

			for (int i = 0; i < rightCards.length; i++) {
				rightCards[i] = null;
			}

			if (triFusion.gauche == null) {
				triFusion.gauche = new TriFusion();

				triFusion.gauche.setTableau(leftCards);
				triFusion.gauche.setLvl(newLvl);

			}

			if (triFusion.droite == null) {
				triFusion.droite = new TriFusion();

				triFusion.droite.setTableau(rightCards);
				triFusion.droite.setLvl(newLvl);

			}
		}
	}

	private Card[] cardsListToArray(List<Card> cardsList) {
		Card[] cards = new Card[cardsList.size()];
		for (int i = 0; i < cards.length; i++) {
			cards[i] = cardsList.get(i);
		}
		return cards;
	}

	private List<Card> cardsArrayToList(Card[] cardsArray) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < cardsArray.length; i++) {
			cards.add(cardsArray[i]);
		}
		return cards;
	}

	private List<Card> rearrangedCards(List<Card> cards) {
		List<Card> arrangedCards = new ArrayList<>();

		for (Card card : cards) {
			if (card != null) {
				arrangedCards.add(card);
			}
		}

		while (arrangedCards.size() != cards.size()) {
			arrangedCards.add(null);
		}
		return arrangedCards;
	}

}
