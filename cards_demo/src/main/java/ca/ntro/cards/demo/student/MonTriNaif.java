package ca.ntro.cards.demo.student;

import ca.ntro.cards.demo.models.NaiveSort;

public class MonTriNaif<C extends Comparable<C>> extends NaiveSort<C> {
	
	@Override
	public void sort() {
		for(int i = 0; i < source.size(); i++) {

			C plusPetitElement = trouverPlusPetit();
			
			target.set(nextEmpty, plusPetitElement);
			
			source.set(smallest, null);
			
			nextEmpty++;

			saveStep();

		}
	}

	private C trouverPlusPetit() {
		C resultat = null;

		for(int i = 0; i < source.size(); i++) {
			
			candidate = i;
			saveStep();
			
			C elementCandidat = source.get(i);
			
			if(resultat == null
					&& elementCandidat != null) {

				smallest = i;
				resultat = elementCandidat;

				saveStep();

			}else if(resultat != null 
					&& elementCandidat != null
					&& elementCandidat.compareTo(resultat) < 0) {
				
				smallest = i;
				resultat = elementCandidat;

				saveStep();
			}

		}
		
		return resultat;
	}

}
