// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)
//
// This file is part of Ntro
//
// This is free software: you can redistribute it and/or modify
// it under the terms of the GNU GPL3 General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU GPL3 General Public License for more details.
//
// You should have received a copy of the GNU GPL3 General Public License
// along with ntro.  If not, see <https://www.gnu.org/licenses/>

package ca.ntro.cards.fusionsort.models;

import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.values.cards.NullCard;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.fusionsort.FusionsortConstants;
import ca.ntro.cards.fusionsort.frontend.FusionsortProcedureViewData;
import ca.ntro.cards.fusionsort.frontend.views.FusionsortVariablesView;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortProcedureDrawingOptions;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortProcedureObject2d;
import ca.ntro.cards.fusionsort.models.world2d.FusionsortProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

// TODO: renommer
public class TriFusion<C extends Comparable<C>>

		extends
		ProcedureCardsModel<TriFusion, FusionsortProcedureObject2d, FusionsortProcedureWorld2d, FusionsortProcedureDrawingOptions, FusionsortProcedureViewData, FusionsortVariablesView> {

	public TriFusion<C> gauche;
	public TriFusion<C> droite;
	public C[] tableau = (C[]) new Card[0];
	private int lvl = 1;
	private String side = "null"; // left or right or null // A ENLEVER??
	private boolean tmp = true;

	public boolean getTmp() {
		return tmp;
	}

	public void setTmp(boolean tmp) {
		this.tmp = tmp;
	}

	public TriFusion<C> getGauche() {
		return gauche;
	}

	public void setGauche(TriFusion<C> gauche) {
		this.gauche = gauche;
	}

	public TriFusion<C> getDroite() {
		return droite;
	}

	public void setDroite(TriFusion<C> droite) {
		this.droite = droite;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public C[] getTableau() {
		return tableau;
	}

	public void setTableau(C[] tableau) {
		this.tableau = tableau;
	}

	public TriFusion() {

	}

	public TriFusion(int taille) {
		setTableau((C[]) new Card[taille]);
	}

	@Override
	public void copyDataFrom(TriFusion other) {
		tableau = (C[]) new Card[other.tableau.length];
		for (int i = 0; i < tableau.length; i++) {
			// Card card = (Card) other.tableau[i];
			// Card clone = card.clone();
			tableau[i] = (C) other.tableau[i];
		}
		lvl = other.lvl;
		side = other.side;

		if (other.gauche != null) {
			gauche = new TriFusion<>();
			gauche.copyDataFrom(other.gauche);
		}

		if (other.droite != null) {
			droite = new TriFusion<>();
			droite.copyDataFrom(other.droite);
		}
	}

	@Override
	public boolean acceptManualModel(TriFusion manualModel) {
		boolean accepted = false;

		if (manualModel.tableauValid()) {

			accepted = true;

		}

		return accepted;
	}

	private boolean tableauValid() {
		boolean valid = true;
		
		if (gauche != null && droite != null) {
			
			if (tableau.length != gauche.tableau.length + droite.tableau.length) {
				valid = false;
			} else {
				valid = gauche.tableauValid() && droite.tableauValid();
			}
			
		} else if (gauche == null ^ droite == null) {
			valid = false;
		}
		
		return valid;
	}
	
	@Override
	protected void updateViewDataImpl(FusionsortProcedureViewData cardsViewData) {

		addLevel();
		
		updateViewData(cardsViewData, 0);

	}

	private void updateViewData(FusionsortProcedureViewData cardsViewData, double minWidth) {
		double cardWidth = FusionsortConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = FusionsortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

		// Avancement des cartes par rapport au debut du canvas
		double worldWidth = FusionsortConstants.INITIAL_WORLD_WIDTH / Math.pow(2, lvl - 1);
		double cardsTotalWidth = (cardWidth + cardWidth / 2) * tableau.length + cardWidth;
		double initialLeftSpace = minWidth + (worldWidth - cardsTotalWidth) / 2;

		for (int i = 0; i < tableau.length; i++) {

			double targetTopLeftX = initialLeftSpace + (cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2);
			double targetTopLeftY = (cardHeight * lvl) + ((cardHeight / 2) * (lvl - 1)) - (cardHeight / 2);

			AbstractCard card = (Card) tableau[i];

			if (card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card, targetTopLeftX, targetTopLeftY);

			cardsViewData.displayCardFaceUp(card);

		}

		if (gauche != null) {
			gauche.updateViewData(cardsViewData, minWidth);
		}
		if (droite != null) {
			droite.updateViewData(cardsViewData, minWidth + worldWidth / 2);
		}
	}

	@Override
	public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {
		if (descriptor.testCaseId().equals("ex01")) {

			tableau = (C[]) new Card[] { new Card(2, Suit.CLUBS), new Card(5, Suit.CLUBS), new Card(5, Suit.DIAMONDS),
					new Card(5, Suit.HEARTS), new Card(7, Suit.SPADES), new Card(2, Suit.HEARTS) };

		} else if (descriptor.testCaseId().equals("ex02")) {

			tableau = (C[]) new Card[] { new Card(2, Suit.SPADES), new Card(3, Suit.HEARTS), new Card(7, Suit.DIAMONDS) };

		} else if (descriptor.testCaseId().equals("ex03")) {

			tableau = (C[]) new Card[] { new Card(3, Suit.CLUBS), new Card(9, Suit.SPADES), new Card(3, Suit.DIAMONDS),
					new Card(4, Suit.DIAMONDS), new Card(8, Suit.SPADES), new Card(1, Suit.HEARTS),
					new Card(7, Suit.HEARTS), new Card(7, Suit.CLUBS), new Card(2, Suit.SPADES),
					new Card(6, Suit.DIAMONDS)};

		}

		// TODO: créer les autres cas de test
	}

	@Override
	public int testCaseSize() {
		return tableau.length;
	}

	@Override
	protected Stream<Card> cards() {
		return new StreamNtro<Card>() {
			@Override
			public void forEach_(Visitor<Card> visitor) throws Throwable {
				// TODO: visiter chaque carte
				visitList(visitor, tableau);
			}

			private void visitList(Visitor<Card> visitor, C[] listToVisit) throws Throwable {
				for (C card : listToVisit) {
					visitor.visit((Card) card);
				}
			}
		};
	}

	@Override
	public void run() {
		trier();
	}

	// TODO: renommer
	public void trier() {
	}

	protected C[] nouveauTableau(int size) {
		return (C[]) new Card[size];
	}

	@Override
	public void displayOn(FusionsortVariablesView variablesView) {
		// TODO: afficher les attributs
	}

	public int nbLevel() {
		int nbLvl = -1;

		if (tableau != null) {
			double size = tableau.length;
			nbLvl = 1;

			while (size >= 2) {
				size /= 2;
				size = Math.ceil(size);
				nbLvl++;
			}

		}

		return nbLvl;
	}

	private void addLevel() {
		if (tmp && tableau.length >= 2) {

			tmp = false;
			
			int leftSize = (int) Math.ceil(tableau.length / 2.0);
			C[] leftCards = (C[]) new Card[leftSize];
			C[] rightCards = (C[]) new Card[tableau.length - leftSize];

			for (int i = 0; i < leftCards.length; i++) {
				leftCards[i] = null;
			}

			for (int i = 0; i < rightCards.length; i++) {
				rightCards[i] = null;
			}

			if (gauche == null) {
				gauche = new TriFusion();

				gauche.setTableau(leftCards);
				gauche.setSide("left");
				gauche.setLvl(lvl + 1);
				gauche.addLevel(); // a enlever
			}

			if (droite == null) {
				droite = new TriFusion();

				droite.setTableau(rightCards);
				droite.setSide("right");
				droite.setLvl(lvl + 1);
				droite.addLevel(); // a enlever
			}
		}
	}

}
