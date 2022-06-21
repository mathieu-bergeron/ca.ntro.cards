// Copyright (C) (2022) (Marlond Augustin) (marlondjra@gmail.com)
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
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU GPL3 General Public License for more details.
//
// You should have received a copy of the GNU GPL3 General Public License
// along with aquiletour. If not, see <https://www.gnu.org/licenses/>

package ca.ntro.cards.arraylist.models;

import ca.ntro.cards.common.commands.AddCommand;
import ca.ntro.cards.common.commands.Command;
import ca.ntro.cards.common.commands.DeleteCommand;
import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.values.cards.NullCard;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import java.util.ArrayList;
import java.util.List;

import ca.ntro.cards.arraylist.ArraylistConstants;
import ca.ntro.cards.arraylist.frontend.ArraylistProcedureViewData;
import ca.ntro.cards.arraylist.frontend.views.ArraylistVariablesView;
import ca.ntro.cards.arraylist.models.world2d.ArraylistCard2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureDrawingOptions;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureObject2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.world2d.ProcedureObject2d;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class ListeTableau<C extends Comparable<C>>

		extends
		ProcedureCardsModel<ListeTableau, ArraylistProcedureObject2d, ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions, ArraylistProcedureViewData, ArraylistVariablesView> {

	private List<Command<C>> commands = new ArrayList<>();

	private C lastGet = null;

	protected C[] grandTableau = (C[]) new Card[1];
	protected C[] nouveauGrandTableau = null;

	protected int indicePremierElement = 0;
	protected int indiceDernierElement = 0;
	protected int indiceCarteARemplacer = 0;

	public C[] getGrandTableau() {
		return grandTableau;
	}

	public void setGrandTableau(C[] grandTableau) {
		this.grandTableau = grandTableau;
	}

	public int getIndicePremierElement() {
		return indicePremierElement;
	}

	public void setIndicePremierElement(int indicePremierElement) {
		this.indicePremierElement = indicePremierElement;
	}

	public int getIndiceDernierElement() {
		return indiceDernierElement;
	}

	public void setIndiceDernierElement(int indiceDernierElement) {
		this.indiceDernierElement = indiceDernierElement;
	}

	public List<Command<C>> getCommands() {
		return commands;
	}

	public void setCommands(List<Command<C>> commands) {
		this.commands = commands;
	}

	public C getLastGet() {
		return lastGet;
	}

	public void setLastGet(C lastGet) {
		this.lastGet = lastGet;
	}

	public int getIndiceCarteARemplacer() {
		return indiceCarteARemplacer;
	}

	public void setIndiceCarteARemplacer(int indiceCarteARemplacer) {
		this.indiceCarteARemplacer = indiceCarteARemplacer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void copyDataFrom(ListeTableau other) {
		// TODO: copier les données telles quelles
		int sizeGrandTableau = other.getGrandTableau().length;
		commands = new ArrayList(other.getCommands());
		lastGet = (C) other.getLastGet();
		grandTableau = (C[]) new Card[sizeGrandTableau];

		for (int i = 0; i < sizeGrandTableau; i++) {
			grandTableau[i] = (C) other.grandTableau[i];
		}

		indicePremierElement = other.getIndicePremierElement();
		indiceDernierElement = other.getIndiceDernierElement();
		indiceCarteARemplacer = other.getIndiceCarteARemplacer();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean acceptManualModel(ListeTableau manualModel) {
		boolean modified = false;
		CardsModelDiff diff = new CardsModelDiff();
		if (getCurrentCommandIndex() != -1) {
			diff = CardsModelDiff.diff(this, manualModel, (indicePremierElement + getCurrentCommandIndex()),getStringCommandCourante());
		} else {
			// J'ai du créer indiceCarteARemplacer, car lorsque la personne enlève la carte,
			// l'index devient -1
			// Donc donnne à indiceCarteARemplacer l'ancinne valeur d'index.
			diff = CardsModelDiff.diff(this, manualModel, this.getIndiceCarteARemplacer(), getStringCommandCourante());
		}
		/*
		 * cr�er une m�thode qui trouve s'il y a une carte qui n'est pas null dans le
		 * this et qui devient nul avec le manualModel. Il faut v�rifier si l'indice de
		 * la carte correspond avec le testUnitaire. Sinon on le rejette.
		 */
		if (diff.getEquivalent() == false) {
			if (diff.getIndexOfDeletedCard() == (indicePremierElement + getCurrentCommandIndex()) && getStringCommandCourante().contains("Retirer à l'indice ") == true) {
				modified = true;
				manualModel.setIndiceCarteARemplacer(indicePremierElement + getCurrentCommandIndex());
			}
			if (getStringCommandCourante().contains("Trier la carte suivante ") == true && diff.getTrieCarte() == true) {
				modified = true;
				manualModel.setIndiceCarteARemplacer(this.getIndiceCarteARemplacer()+1);
			} 
		}
		if (Math.abs(this.indicePremierElement - manualModel.indicePremierElement) == 1) {
			modified = true;
		}
		if (Math.abs(this.indiceDernierElement - manualModel.indiceDernierElement) == 1) {
			modified = true;
		}

		if (Math.abs(this.indicePremierElement - manualModel.indicePremierElement) > 1) {
			modified = false;
		}
		if (Math.abs(this.indiceDernierElement - manualModel.indiceDernierElement) > 1) {
			modified = false;
		}
		if (modified) {
			copyDataFrom(manualModel);
		}
		return modified;
	}

	@Override
	protected void updateViewDataImpl(ArraylistProcedureViewData cardsViewData) {
		double cardWidth = ArraylistConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = ArraylistConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

		for (int i = 0; i < grandTableau.length; i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight * 2;

			AbstractCard card = (Card) grandTableau[i];

			if (card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card, targetTopLeftX, targetTopLeftY);

			cardsViewData.displayCardFaceUp(card);
		}
		double markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndicePremierElement() * cardWidth * 3 / 2;
		double markerTopRightX = 10 + cardWidth + cardWidth / 2 + getIndiceDernierElement() * cardWidth * 3 / 2;
		double markerTopLeftY = cardHeight * 3 + cardHeight / 3;

		cardsViewData.addOrUpdateMarker("smallestElement", "#33ccff", markerTopLeftX, markerTopLeftY);
		cardsViewData.addOrUpdateMarker("biggestElement", "#ff0000", markerTopRightX, markerTopLeftY);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {
		if (descriptor.testCaseId().equals("ex01")) {

			grandTableau = (C[]) new Card[] { null, null, new Card(1, Suit.HEARTS), new Card(3, Suit.CLUBS),
					new Card(5, Suit.SPADES), new Card(8, Suit.DIAMONDS), new Card(2, Suit.DIAMONDS), null, null };

			indicePremierElement = 2;
			indiceDernierElement = 6;

			commands.add(new DeleteCommand(2));

		}

		else if (descriptor.testCaseId().equals("ex02")) {

			grandTableau = (C[]) new Card[] { null, null, new Card(6, Suit.DIAMONDS), new Card(2, Suit.CLUBS),
					new Card(4, Suit.HEARTS), new Card(7, Suit.HEARTS), new Card(3, Suit.DIAMONDS),
					new Card(1, Suit.SPADES), null, null };

			indicePremierElement = 2;
			indiceDernierElement = 7;

			commands.add(new DeleteCommand(1));

		}
	}

	@Override
	public int testCaseSize() {
		// TODO:
		// return commands.size();
		return grandTableau.length;
	}

	@Override
	protected Stream<Card> cards() {
		return new StreamNtro<Card>() {
			@Override
			public void forEach_(Visitor<Card> visitor) throws Throwable {
				if (grandTableau != null) {
					for (C card : grandTableau) {
						visitor.visit((Card) card);
					}
				}

				if (nouveauGrandTableau != null) {
					for (C card : nouveauGrandTableau) {
						visitor.visit((Card) card);
					}
				}
			}
		};
	}

	@Override
	public void run() {
		while (!commands.isEmpty()) {
			Command<C> command = getCurrentCommand();
			removeCurrentCommand();

			if (command.isAdd()) {

				ajouter(command.add().getValue());

			} else if (command.isGet()) {

				lastGet = obtenir(command.get().index());

			} else if (command.isDelete()) {

				retirer(command.delete().index());

			} else if (command.isInsert()) {

				inserer(command.insert().index(), command.insert().getValue());

			}
		}

	}

	private String getStringCommandCourante() {
		String commandeCourantes = "";
		Command<C> command = getCurrentCommand();
		if (command != null) {
			if (command.isAdd()) {

				commandeCourantes = "ajouter";

			} else if (command.isGet()) {

				commandeCourantes = "obtenir à l'indice " + command.get().index();

			} else if (command.isDelete()) {

				commandeCourantes = "Retirer à l'indice " + command.delete().index();

			} else if (command.isInsert()) {

				commandeCourantes = "est ajouter";

			}
		}else {
			//Vu qu'il faut trier les cartes, j'indique à l'usager qu'elle carte il faut remplacer
			commandeCourantes = "Trier la carte suivante "+(this.getIndiceCarteARemplacer()-1);

		}
		return commandeCourantes;
	}

	private Command<C> getCurrentCommand() {

		Command<C> command = null;
		if (!commands.isEmpty()) {
			command = commands.get(0);
		}
		return command;
	}

	private int getCurrentCommandIndex() {
		// Il y a un problème, lorsqu'on enlève la carte, on perd l'index et en
		// conséquence pour CardsModelDiff, il y aura des erreurs car l'index changera
		// de valeur.
		int index = -1;
		Command<C> command = getCurrentCommand();
		if (command != null) {
			if (command.isDelete()) {
				index = command.delete().index();
			} else if (command.isGet()) {

				index = command.get().index();

			} else if (command.isDelete()) {

				index = command.delete().index();

			} else if (command.isInsert()) {

				index = command.insert().index();

			}

		}
		return index;
	}

	private void removeCurrentCommand() {
		commands = commands.subList(1, commands.size());
	}

	public void ajouter(C valeur) {
	}

	public C obtenir(int index) {
		return null;
	}

	public void retirer(int index) {
	}

	public void inserer(int index, C valeur) {
	}

	protected C[] creerGrandTableau(int size) {
		return (C[]) new Card[size];
	}

	@Override
	public void displayOn(ArraylistVariablesView variablesView) {
		// TODO: afficher les attributs
		boolean firstElementTrouver = false;
		boolean dernierElementTrouver = false;

		variablesView.displayFooVar01(String.valueOf(getStringCommandCourante()));
		variablesView.displayFooVar02(String.valueOf(indicePremierElement));
		variablesView.displayFooVar03(String.valueOf(indiceDernierElement));

	}

}
