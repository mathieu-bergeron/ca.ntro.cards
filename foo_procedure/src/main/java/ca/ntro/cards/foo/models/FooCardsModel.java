package ca.ntro.cards.foo.models;


import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.foo.frontend.FooProcedureViewData;
import ca.ntro.cards.foo.frontend.views.FooVariablesView;
import ca.ntro.cards.foo.models.world2d.FooProcedureDrawingOptions;
import ca.ntro.cards.foo.models.world2d.FooProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.values.ComparisonReport;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

               // TODO: renommer
public class   FooCardsModel<C extends Comparable<C>> 

       extends ProcedureCardsModel<FooCardsModel, 
                                   FooProcedureWorld2d, 
                                   FooProcedureDrawingOptions, 
                                   FooProcedureViewData,
                                   FooVariablesView> { 

    @Override
    public void copyDataFrom(FooCardsModel other) {
        // TODO: copier les données telles quelles

    }

	@Override
	public boolean isValidNextStep(FooCardsModel manualModel) {
		boolean modified = false;

		// TODO: accepter ou rejeter les modifications manuelles
		//       retourner faux si c'est rejeté

		return modified;
	}

	@Override
	public ComparisonReport compareToSolution(FooCardsModel solution) {
		
		ComparisonReport report = ComparisonReport.emptyReport();
		
		// TODO: compare to a solution and report every error
		//       the testcase is passed if there is no error
		//       to report

		return report;
	}


    @Override
    protected void updateViewDataImpl(FooProcedureViewData cardsViewData) {
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
    public void run() {
    	// TODO: adapter au besoin
        studentMain();
    }

    // TODO: renommer
    public void studentMain() {
    }

    @Override
    public void displayOn(FooVariablesView variablesView) {
        // TODO: afficher les attributs
    }




}
