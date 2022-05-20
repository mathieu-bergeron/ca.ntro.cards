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
		
		for(int i = 0; i < listeSource.size(); i++) {

			C plusPetitElement = trouverPlusPetit();

			listeSource.set(indicePlusPetit, null);
			
			listeCible.set(indiceProchainVide, plusPetitElement);
			
			indiceProchainVide++;

			signalerEtape();

		}
	}

	private C trouverPlusPetit() {
		C resultat = null;

		for(int i = 0; i < listeSource.size(); i++) {
			
			indiceCandidat = i;
			signalerEtape();
			
			C elementCandidat = listeSource.get(i);
			
			if(resultat == null
					&& elementCandidat != null) {

				indicePlusPetit = i;
				resultat = elementCandidat;

				signalerEtape();

			}else if(resultat != null 
					&& elementCandidat != null
					&& elementCandidat.compareTo(resultat) < 0) {
				
				indicePlusPetit = i;
				resultat = elementCandidat;

				signalerEtape();
			}

		}
		
		return resultat;
	}

}
