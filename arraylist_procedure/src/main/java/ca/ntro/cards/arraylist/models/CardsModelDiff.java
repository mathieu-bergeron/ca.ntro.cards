package ca.ntro.cards.arraylist.models;

import ca.ntro.cards.common.models.values.cards.Card;
//Copyright (C) (2022) (Marlond Augustin) (marlondjra@gmail.com)
//
//This file is part of Ntro
//
//This is free software: you can redistribute it and/or modify
//it under the terms of the GNU GPL3 General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//GNU GPL3 General Public License for more details.
//
//You should have received a copy of the GNU GPL3 General Public License
//along with aquiletour. If not, see <https://www.gnu.org/licenses/>
//cette méthode prend en note les différences entre les deux tableaux

public class CardsModelDiff {
	private int indexOfDeletedCard = -1;
	private int indexOfAddedCard = -1;
	private Card deletedCard = null;
	private boolean trieDeletedCarte = false;
	private boolean equivalent = false;

	public boolean getEquivalent() {
		return equivalent;
	}

	public void setEquivalent(boolean equivalent) {
		this.equivalent = equivalent;
	}

	public int getIndexOfDeletedCard() {
		return indexOfDeletedCard;
	}

	public void setIndexOfDeletedCard(int indexOfDeletedCard) {
		this.indexOfDeletedCard = indexOfDeletedCard;
	}

	public int getIndexOfAddedCard() {
		return indexOfAddedCard;
	}

	public void setIndexOfAddedCard(int indexOfAddedCard) {
		this.indexOfAddedCard = indexOfAddedCard;
	}

	public Card getDeletedCard() {
		return deletedCard;
	}

	public void setDeletedCard(Card deletedCard) {
		this.deletedCard = deletedCard;
	}

	public boolean getTrieCarte() {
		return trieDeletedCarte;
	}

	public void setTrieCarte(boolean trieDeletedCarte) {
		this.trieDeletedCarte = trieDeletedCarte;
	}

	@SuppressWarnings("unchecked")
	public static CardsModelDiff diff(ListeTableau before, ListeTableau after, int index, String commandeActuelle) {
		CardsModelDiff result = new CardsModelDiff();
		int longueurBoucle = before.getIndiceDernierElement();
		if (commandeActuelle.contains("Retirer à l'indice ") == true) {
			for (int i = index; i < longueurBoucle; i++) {
				if (before.getGrandTableau()[i] != null && after.getGrandTableau()[i] == null) {
					// Utile pour savoir si les données une donneée a été modifié
					result.equivalent = false;
					result.indexOfDeletedCard = i;
					result.setDeletedCard((Card) after.grandTableau[i]);
				}
			}
		} if(commandeActuelle.contains("Trier la carte suivante ")==true) {
			int nbCarteInchanger=0;
			for(int i=0;i<index;i++) {
				//J'ai créer nbCarteInchange dans le but de s'assurer que les cartes précédent l'index ne changent pas
				//Si les cartes n'ont pas changé le nbCarteInchanger devrait être équivalent à l'index
				if ((before.getGrandTableau()[i] != null && after.getGrandTableau()[i] != null)) {
					if (after.getGrandTableau()[i].compareTo(before.getGrandTableau()[i]) == 0) {
						nbCarteInchanger++;
					}
				}
				if ((before.getGrandTableau()[i] == null && after.getGrandTableau()[i] == null)) {
					nbCarteInchanger++;
				}
			}
			//System.out.println(nbCarteInchanger);
			//System.out.println(index);
			//System.out.println("after modif " + after.getGrandTableau()[index]);
			//System.out.println("before +1 modif " + before.getGrandTableau()[index + 1]);
			//System.out.println("after +1 modif " + after.getGrandTableau()[index + 2]);
			//System.out.println("before +2 modif " + before.getGrandTableau()[index + 2]);
			if ((before.getGrandTableau()[index + 1] != null && after.getGrandTableau()[index] != null)&& nbCarteInchanger==index) {
				/*
				 * On compare la valeur de before.getGrandTableau()[index + 1] et after.getGrandTableau()[index], car la réponse qu'on
				 * veut accepter était originelement l'ancienne valeur de l'index+1
				 */
				if (after.getGrandTableau()[index].compareTo(before.getGrandTableau()[index + 1]) == 0) {
					System.out.println("fonctionnel");
					result.equivalent = false;
					result.indexOfDeletedCard = index;
					result.setTrieCarte(true);
				}
			}
		}
		if ((before.getGrandTableau()[index + 2] == null && after.getGrandTableau()[index + 2] != null)) {

		}

		return result;
	}

}
