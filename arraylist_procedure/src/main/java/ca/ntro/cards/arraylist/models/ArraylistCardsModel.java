package ca.ntro.cards.arraylist.models;


import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.arraylist.frontend.ArraylistProcedureViewData;
import ca.ntro.cards.arraylist.frontend.views.ArraylistVariablesView;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureDrawingOptions;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureObject2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

               // TODO: renommer
public class   ArraylistCardsModel<C extends Comparable<C>> 

       extends ProcedureCardsModel<ArraylistCardsModel, 
                                   ArraylistProcedureObject2d, 
                                   ArraylistProcedureWorld2d, 
                                   ArraylistProcedureDrawingOptions, 
                                   ArraylistProcedureViewData,
                                   ArraylistVariablesView> { 

    @Override
    public void copyDataFrom(ArraylistCardsModel other) {
        // TODO: copier les données telles quelles

    }

	@Override
	public boolean acceptManualModel(ArraylistCardsModel manualModel) {
		boolean modified = false;

		// TODO: accepter ou rejeter les modifications manuelles
		//       retourner faux si c'est rejeté

		return modified;
	}

    @Override
    protected void updateViewDataImpl(ArraylistProcedureViewData cardsViewData) {
        // TODO: créer des Carte2d pour afficher les cartes du modèle
    }

    @Override
    public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {
        if(descriptor.testCaseId().equals("ex01")) {

            // TODO: créer le case de test ex01

        }

        // TODO: créer les autres cas de test
    }

    @Override
    public int testCaseSize() {
        // TODO: 
    	return 0;
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
    public void onBeforeRunning() {

    }
    
    @Override
    public void run() {
        studentMain();
    }

    @Override
    public void onAfterRunning() {
    }

    // TODO: renommer
    public void studentMain() {
    }

    @Override
    public void displayOn(ArraylistVariablesView variablesView) {
        // TODO: afficher les attributs
    }


}
