package ca.ntro.cards.naivesort.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.models.values.cards.NullCard;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.naivesort.NaivesortConstants;
import ca.ntro.cards.naivesort.frontend.NaivesortProcedureViewData;
import ca.ntro.cards.naivesort.frontend.views.NaivesortVariablesView;
import ca.ntro.cards.naivesort.models.world2d.NaivesortProcedureDrawingOptions;
import ca.ntro.cards.naivesort.models.world2d.NaivesortProcedureObject2d;
import ca.ntro.cards.naivesort.models.world2d.NaivesortProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class TriNaif<C extends Comparable<C>>

		extends
		ProcedureCardsModel<TriNaif, NaivesortProcedureObject2d, NaivesortProcedureWorld2d, NaivesortProcedureDrawingOptions, NaivesortProcedureViewData, NaivesortVariablesView> {

	protected int indicePlusPetit = -1;
	protected int indiceCandidat = -1;
	protected int indiceProchainVide = 0;

	protected C[] source = (C[]) new Card[0];
	protected C[] cible = (C[]) new Card[0];

	public int getIndicePlusPetit() {
		return indicePlusPetit;
	}

	public void setIndicePlusPetit(int indicePlusPetit) {
		this.indicePlusPetit = indicePlusPetit;
	}

	public int getIndiceCandidat() {
		return indiceCandidat;
	}

	public void setIndiceCandidat(int indiceCandidat) {
		this.indiceCandidat = indiceCandidat;
	}

	public int getIndiceProchainVide() {
		return indiceProchainVide;
	}

	public void setIndiceProchainVide(int indiceProchainVide) {
		this.indiceProchainVide = indiceProchainVide;
	}

	public C[] getSource() {
		return source;
	}

	public void setSource(C[] source) {
		this.source = source;
	}

	public C[] getCible() {
		return cible;
	}

	public void setCible(C[] cible) {
		this.cible = cible;
	}

	@Override
	public void copyDataFrom(TriNaif other) {

		int sizeSource = other.source.length;
		int sizeCible = other.cible.length;

		source = (C[]) new Card[sizeSource];
		cible = (C[]) new Card[sizeCible];

		for (int i = 0; i < sizeSource; i++) {
			source[i] = (C) other.source[i];
		}

		for (int i = 0; i < sizeCible; i++) {
			cible[i] = (C) other.cible[i];
		}

		indicePlusPetit = other.indicePlusPetit;
		indiceCandidat = other.indiceCandidat;
		indiceProchainVide = other.indiceProchainVide;

	}

	@Override
	public boolean acceptManualModel(TriNaif manualModel) {
		boolean accepted = false;
		// FIXME: reject some updates

		// CORRECTION

		if (indiceProchainVide == manualModel.indiceProchainVide) {

			accepted = true;
			// interdire de bouger les cartes de cible
			for (int i = 0; i < cible.length; i++) {

				if (cible[i] != null && manualModel.cible[i] != null) {
					if (!cible[i].equals(manualModel.cible[i])) {
						accepted = false;
						break;
					}

				} else if (cible[i] == null ^ manualModel.cible[i] == null) {
					accepted = false;
					break;
				}

			}
		} else {

			int smallestCardIndex = getSourceIndexOfSmallest();
			if (smallestCardIndex != -1) {
				if (manualModel.cible[indiceProchainVide] != null
						&& manualModel.cible[indiceProchainVide].compareTo(source[smallestCardIndex]) == 0) {
					accepted = true;
				}
			}

		}

		return accepted;
	}

	@Override
	protected void updateViewDataImpl(NaivesortProcedureViewData cardsViewData) {
		double cardWidth = NaivesortConstants.INITIAL_CARD_WIDTH_MILIMETERS;
		double cardHeight = NaivesortConstants.INITIAL_CARD_HEIGHT_MILIMETERS;

		List<AbstractCard> topCards = new ArrayList<>();
		List<AbstractCard> bottomCards = new ArrayList<>();

		for (int i = 0; i < source.length; i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight * 2;

			AbstractCard card = (Card) source[i];

			if (card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card, targetTopLeftX, targetTopLeftY);

			bottomCards.add(card);
			cardsViewData.displayCardFaceDown(card);
		}

		for (int i = 0; i < cible.length; i++) {

			double targetTopLeftX = cardWidth + cardWidth / 2 + i * cardWidth * 3 / 2;
			double targetTopLeftY = cardHeight / 2;

			AbstractCard card = (Card) cible[i];

			if (card == null) {
				card = new NullCard();
			}

			cardsViewData.addOrUpdateCard(card, targetTopLeftX, targetTopLeftY);
			topCards.add(card);
		}

		double markerTopLeftX = 10 + cardWidth + cardWidth / 2 + getIndicePlusPetit() * cardWidth * 3 / 2;
		double markerTopLeftY = cardHeight * 3 + cardHeight / 3;

		cardsViewData.addOrUpdateMarker("smallestElement", markerTopLeftX, markerTopLeftY);

		if (getIndicePlusPetit() >= 0 && getIndicePlusPetit() < bottomCards.size()) {
			AbstractCard smallestCard = bottomCards.get(getIndicePlusPetit());
			cardsViewData.displayCardFaceUp(smallestCard);

		}

		if (getIndiceCandidat() >= 0 && getIndiceCandidat() < bottomCards.size()) {
			AbstractCard candidateCard = bottomCards.get(getIndiceCandidat());
			cardsViewData.displayCardFaceUp(candidateCard);
		}

		if (getIndiceProchainVide() >= 0 && getIndiceProchainVide() < topCards.size()) {
			for (int i = getIndiceProchainVide() + 1; i < topCards.size(); i++) {
				AbstractCard futureCard = topCards.get(i);
				cardsViewData.displayCardFaceDown(futureCard);
			}

		}

	}

	@Override
	public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {

		if (descriptor.testCaseId().equals("ex01")) {

			source = (C[]) new Card[] { new Card(2, Suit.CLUBS), new Card(5, Suit.CLUBS), new Card(5, Suit.DIAMONDS),
					new Card(5, Suit.HEARTS), new Card(7, Suit.SPADES), new Card(2, Suit.HEARTS) };

		} else {

			source = (C[]) randomArrayOfUniqueCards(descriptor.inputSize());

		}

		cible = (C[]) new Card[source.length];

		for (int i = 0; i < source.length; i++) {
			cible[i] = null;
		}
	}

	@Override
	public int testCaseSize() {
		return source.length;
	}

	@Override
	protected Stream<Card> cards() {
		return new StreamNtro<Card>() {
			@Override
			public void forEach_(Visitor<Card> visitor) throws Throwable {
				visitList(visitor, source);
				visitList(visitor, cible);
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

	public void trier() {
	}

	@Override
	public void displayOn(NaivesortVariablesView variablesView) {
		variablesView.displayIndexOfSmallest(String.valueOf(indicePlusPetit));
		variablesView.displayIndexOfCandidate(String.valueOf(indiceCandidat));
		variablesView.displayIndexOfNextEmpty(String.valueOf(indiceProchainVide));
	}

	private void copySourceFrom(C[] other) { // AJOUTER
		int size = other.length;

		source = (C[]) new Card[size];

		for (int i = 0; i < size; i++) {
			source[i] = (C) other[i];
		}
	}

	private C[] getSourceSorted() { // AJOUTER
		C[] src = (C[]) new Card[source.length];

		for (int i = 0; i < source.length; i++) {
			src[i] = source[i];
		}

		Arrays.sort(src);

		return src;
	}

	private int getSourceIndexOfSmallest() { // AJOUTER
		int smallest = -1;

		if (source.length == 1 && source[0] != null) {
			smallest = 0;
		} else if (source.length >= 2) {

			if (source[0] != null) {
				smallest = 0;
			}

			for (int i = 1; i < source.length; i++) {

				if (smallest == -1) {
					if (source[i] != null) {
						smallest = i;
					}
				} else if (source[i] != null) {
					if (source[smallest].compareTo(source[i]) > 0) {
						smallest = i;
					}
				}
			}
		}

		return smallest;
	}

}
