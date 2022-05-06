package ca.ntro.cards.freesort.models.utils;

import ca.ntro.cards.models.values.Card;
import java.util.List;

public class Utils {

	public static boolean cardsAreSorted(List<Card> cards) {
		boolean isSorted = false;
		
		if (cards != null) {
			
			isSorted = true;
			
			if (cards.size() >= 2) {
			
				for (int i = 1; i < cards.size(); i++) {
					Card card = cards.get(i-1);
					Card card2 = cards.get(i);
					
					if (card.compareTo(card2) > 0) { // check later
						isSorted = false;
						break;
					}
					
				}
				
			}
			
		}
		
		return isSorted;
	}
	
}
