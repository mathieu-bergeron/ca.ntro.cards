package demo_student;

import ca.ntro.cards.demo.models.TriNaif;

public class MonTriNaif<C extends Comparable<C>> extends TriNaif<C> {
	
	@Override
	public void trier() {
		/*
		for(int i = 0; i < source.size(); i++) {
			candidate = i;
			saveStep();
		}
		*/
		
		for(int i = 0; i < source.size(); i++) {

			C plusPetitElement = trouverPlusPetit();
			
			cible.set(indiceProchainVide, plusPetitElement);
			
			source.set(indicePlusPetit, null);
			
			indiceProchainVide++;

			ajouterEtape();

		}
	}

	private C trouverPlusPetit() {
		C resultat = null;

		for(int i = 0; i < source.size(); i++) {
			
			indiceCandidate = i;
			ajouterEtape();
			
			C elementCandidat = source.get(i);
			
			if(resultat == null
					&& elementCandidat != null) {

				indicePlusPetit = i;
				resultat = elementCandidat;

				ajouterEtape();

			}else if(resultat != null 
					&& elementCandidat != null
					&& elementCandidat.compareTo(resultat) < 0) {
				
				indicePlusPetit = i;
				resultat = elementCandidat;

				ajouterEtape();
			}

		}
		
		return resultat;
	}

}
