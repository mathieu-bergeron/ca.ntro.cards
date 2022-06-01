package ca.ntro.cards.fusionsort.models;


import ca.ntro.cards.common.models.enums.Suit;
import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
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
public class   TriFusion<C extends Comparable<C>> 

       extends ProcedureCardsModel<TriFusion, 
                                   FusionsortProcedureObject2d, 
                                   FusionsortProcedureWorld2d, 
                                   FusionsortProcedureDrawingOptions, 
                                   FusionsortProcedureViewData,
                                   FusionsortVariablesView> { 
	
	public TriFusion<C> gauche;
	public TriFusion<C> droite;
	public C[] tableau = (C[]) new Card[0];
	
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
		for(int i = 0; i < tableau.length; i++) {
			tableau[i] = (C) other.tableau[i];
		}
		
		if(other.gauche != null) {
			gauche = new TriFusion<>();
			gauche.copyDataFrom(other.gauche);
		}
		
		if(other.droite != null) {
			droite = new TriFusion<>();
			droite.copyDataFrom(other.droite);
		}
    }

	@Override
	public boolean acceptManualModel(TriFusion manualModel) {
		boolean modified = false;

		// TODO: accepter ou rejeter les modifications manuelles
		//       retourner faux si c'est rejeté

		return modified;
	}

    @Override
    protected void updateViewDataImpl(FusionsortProcedureViewData cardsViewData) {
        // TODO: créer des Carte2d pour afficher les cartes du modèle
    }

    @Override
    public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {
        if(descriptor.testCaseId().equals("ex01")) {

			tableau = (C[]) new Card[] {new Card(2, Suit.CLUBS), 
					                    new Card(5, Suit.CLUBS), 
					                    new Card(5, Suit.DIAMONDS), 
					                    new Card(5, Suit.HEARTS), 
					                    new Card(7, Suit.SPADES), 
					                    new Card(2, Suit.HEARTS)};

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


}
