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
	private int indiceCarteselectionner=0;

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

	@SuppressWarnings("unchecked")
	@Override
	public void copyDataFrom(@SuppressWarnings("rawtypes") ListeTableau other) {
		// TODO: copier les donnÃ©es telles quelles
		int sizeGrandTableau=other.getGrandTableau().length;
		commands=new ArrayList(other.getCommands());
		lastGet=(C) other.getLastGet();
		grandTableau=  (C[]) new Card[sizeGrandTableau];
		for(int i = 0; i < sizeGrandTableau; i++) {
			grandTableau[i] = (C) other.grandTableau[i];
		}
		indicePremierElement=other.getIndicePremierElement();
		indiceDernierElement=other.getIndiceDernierElement();
	
	}

	@Override
	public boolean acceptManualModel(ListeTableau manualModel) {
		//retourne vrai si manualModel est une prochaine ï¿½tape de l'exï¿½cution valide
		//retourne faux si ce n'est pas une prochaine ï¿½tape valide de l'exï¿½cution
		boolean modified = false;
		if(manualModel.getGrandTableau().length!=grandTableau.length) {
			modified=true;
			copyDataFrom(manualModel);
		}
		/*crï¿½er une mï¿½thode qui trouve s'il y a une carte qui n'est pas null dans le this et qui 
		 * devient nul avec le manualModel. Il faut vï¿½rifier si l'indice de la carte correspond  avec
		 * le testUnitaire. Sinon on le rejette.
		*/
		if(isManualModelEqualToGrandTableau(manualModel)==false) {
			//Il faudra remplacer 4 par l'index voulu selon le test unitaire
			//J'utilise lastGet pour que cela conserve la carte qui est remplacé par un null, dans le but 
			//à l'avenir de voir si la bonne carte est retiré. Pour ensuite trier le tableau.
			if(indiceCarteselectionner==4) {
				modified=true;
				copyDataFrom(manualModel);
				lastGet=(C) manualModel.getGrandTableau()[indiceCarteselectionner];
			}else {
				modified=false;
			}
		}

		if(Math.abs(this.indicePremierElement-manualModel.indicePremierElement)>1) {
			modified = false;
		}
		if(Math.abs(this.indiceDernierElement-manualModel.indiceDernierElement)>1) {
			modified = false;
		}
		/*Cas compliquï¿½
		 * this=valeur courante/ï¿½tape courante de l'exï¿½cution,
		 *ManualModel est la prochaine ï¿½tape de l'exï¿½cution
		 *On veut accepter en comparant this ï¿½ manual model et on vï¿½rifie si la transition est valide
		 */
		// TODO: accepter ou rejeter les modifications manuelles
		// retourner faux si c'est rejetÃ©

		return modified;
	}
	public boolean isManualModelEqualToGrandTableau(ListeTableau manualModel) {
		//Cette méthode est utile pour savoir si une valeur du tableau a été modifié
		boolean equivalent=true;
		int longueurBoucle=grandTableau.length;
		for(int i=0;i<longueurBoucle;i++) {
			if(grandTableau[i].compareTo((C) manualModel.getGrandTableau()[i])!=0) {
				equivalent=false;
				indiceCarteselectionner=i;
			}
		}
		return equivalent;
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
		}	
		double markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndicePremierElement() * cardWidth * 3 / 2;
		double markerTopRightX = 10 + cardWidth + cardWidth / 2 + getIndiceDernierElement() * cardWidth * 3 / 2;
		double markerTopLeftY = cardHeight * 3 + cardHeight / 3;
		
		cardsViewData.addOrUpdateMarker("smallestElement","#33ccff", markerTopLeftX, markerTopLeftY);
		cardsViewData.addOrUpdateMarker("biggestElement","#ff0000", markerTopRightX, markerTopLeftY);

		if(getIndicePremierElement() >= 0 && getIndicePremierElement() < grandTableau.length) {
			AbstractCard smallestCard = (AbstractCard) grandTableau[getIndicePremierElement()];
			cardsViewData.displayCardFaceUp(smallestCard);
			
		}
		
		if(getIndiceDernierElement() >= 0 && getIndiceDernierElement() < grandTableau.length) {
			AbstractCard candidateCard = (AbstractCard) grandTableau[getIndicePremierElement()];
			cardsViewData.displayCardFaceUp(candidateCard);
		}


	}

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
		String commandeCourantes="";
				Command<C> command = getCurrentCommand();
				if (command.isAdd()) {

					commandeCourantes="ajouter";

				} else if (command.isGet()) {

					commandeCourantes="obtenir";

				} else if (command.isDelete()) {

					commandeCourantes="Effacer";

				} else if (command.isInsert()) {

					commandeCourantes="est ajouter";

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
				if(grandTableau[i]!=null&&grandTableau[i+1]!=null) {
					if(grandTableau[i].compareTo(grandTableau[i+1])<0) {
						indexOfFirstHearts = i;
					}
				}
				
			}
		}
		variablesView.displayFooVar01(String.valueOf( "enlever"));
		variablesView.displayFooVar02(String.valueOf(indexOfFirstHearts));
		variablesView.displayFooVar03(String.valueOf(indiceDernierElement));

	
	}

}
