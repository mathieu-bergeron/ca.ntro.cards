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
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureDrawingOptions;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureObject2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class ListeTableau<C extends Comparable<C>>

		extends
		ProcedureCardsModel<ListeTableau, ArraylistProcedureObject2d, ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions, ArraylistProcedureViewData, ArraylistVariablesView> {

	// Copyright (C) (2022) (Marlond Augustin) (202043906@cmontmorency.qc.ca)
			//
			// This file is part of Ntro
			//
			// This is free software: you can redistribute it and/or modify
			// it under the terms of the GNU Affero General Public License as published by
			// the Free Software Foundation, either version 3 of the License, or
			// (at your option) any later version.
			//
			// This is distributed in the hope that it will be useful,
			// but WITHOUT ANY WARRANTY; without even the implied warranty of
			// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
			// GNU Affero General Public License for more details.
			//
			// You should have received a copy of the GNU Affero General Public License
			// along with aquiletour.  If not, see <https://www.gnu.org/licenses/>
	
	private List<Command<C>> commands = new ArrayList<>();

	private C lastGet = null;

	protected C[] grandTableau = (C[]) new Card[1];
	protected C[] nouveauGrandTableau = null;

	protected int indicePremierElement = 0;
	protected int indiceDernierElement = 0;

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

	@Override
	public void copyDataFrom(ListeTableau other) {
		// TODO: copier les données telles quelles
		commands=other.getCommands();
		lastGet=(C) other.getLastGet();
		grandTableau=(C[]) other.getGrandTableau();
		indicePremierElement=other.getIndicePremierElement();
		indiceDernierElement=other.getIndiceDernierElement();
	
	}

	@Override
	public boolean acceptManualModel(ListeTableau manualModel) {
		boolean modified = false;
		if(manualModel.getGrandTableau().length!=grandTableau.length) {
			modified=true;
			copyDataFrom(manualModel);
		}
		// TODO: accepter ou rejeter les modifications manuelles
		// retourner faux si c'est rejeté

		return modified;
	}

	@Override
	protected void updateViewDataImpl(ArraylistProcedureViewData cardsViewData) {
		double cardWidth =ArraylistConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = ArraylistConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

		for(int i = 0; i < grandTableau.length; i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight * 2;
			
			AbstractCard card = (Card) grandTableau[i];
			
			if(card == null) {
				card = new NullCard();
			}
			
			cardsViewData.addOrUpdateCard(card,
					                      targetTopLeftX,
					                      targetTopLeftY);

			cardsViewData.displayCardFaceUp(card);
		}	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {
		if (descriptor.testCaseId().equals("ex01")) {

			grandTableau = (C[]) new Card[] { 
					null, null,
					new Card(1, Suit.HEARTS), new Card(3, Suit.CLUBS),
					new Card(5, Suit.SPADES), new Card(8, Suit.DIAMONDS),
					new Card(2, Suit.DIAMONDS),
					null, null };

			indicePremierElement = 2;
			indiceDernierElement = 6;

			commands.add(new DeleteCommand(4));

		}

		else if (descriptor.testCaseId().equals("ex02")) {

			grandTableau = (C[]) new Card[] { null, null,
					new Card(6, Suit.DIAMONDS), new Card(2, Suit.CLUBS),
					new Card(4, Suit.HEARTS), new Card(7, Suit.HEARTS), 
					new Card(3, Suit.DIAMONDS),	new Card(1, Suit.SPADES),
					null, null };

			indicePremierElement = 2;
			indiceDernierElement = 7;

			commands.add(new DeleteCommand(1));

		}
	}

	@Override
	public int testCaseSize() {
		// TODO:
		//return commands.size();
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
		String commandeCourantes="Bonjour";
			while (!commands.isEmpty()) {
				Command<C> command = getCurrentCommand();
				System.out.println(command);
				if (command.isAdd()) {

					commandeCourantes="ajouter";

				} else if (command.isGet()) {

					commandeCourantes="obtenir";

				} else if (command.isDelete()) {

					commandeCourantes="Effacer";

				} else if (command.isInsert()) {

					commandeCourantes="est ajouter";

				}
			}
			return commandeCourantes;
	}
	private Command<C> getCurrentCommand() {
		Command<C> command = commands.get(0);
		return command;
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
		int indexOfFirstHearts = 0;
		for(int i = 0; i < grandTableau.length; i++) {
			if(i<grandTableau.length-1) {
				if(grandTableau[i].compareTo(grandTableau[i+1])<0) {
					indexOfFirstHearts = i;
				}
			}
		}
		variablesView.displayFooVar01(String.valueOf(getStringCommandCourante()));
		variablesView.displayFooVar02(String.valueOf(indexOfFirstHearts));
		variablesView.displayFooVar03(String.valueOf(indiceDernierElement));

	
	}

}
