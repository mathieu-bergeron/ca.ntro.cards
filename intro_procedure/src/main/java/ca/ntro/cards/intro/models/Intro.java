package ca.ntro.cards.intro.models;


import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.intro.frontend.IntroProcedureViewData;
import ca.ntro.cards.intro.frontend.views.IntroVariablesView;
import ca.ntro.cards.intro.models.world2d.IntroProcedureDrawingOptions;
import ca.ntro.cards.intro.models.world2d.IntroProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.cards.models.values.ComparisonReport;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class   Intro

       extends ProcedureCardsModel<Intro, 
                                   IntroProcedureWorld2d, 
                                   IntroProcedureDrawingOptions, 
                                   IntroProcedureViewData,
                                   IntroVariablesView> { 
	
	private String exampleName;
	private int indiceDerniereCarte;

    public String getExampleName() {
		return exampleName;
	}

	public void setExampleName(String exampleName) {
		this.exampleName = exampleName;
	}
	
	public int getIndiceDerniereCarte() {
		return indiceDerniereCarte;
	}

	public void setIndiceDerniereCarte(int indiceDerniereCarte) {
		this.indiceDerniereCarte = indiceDerniereCarte;
	}

	@Override
    public void copyDataFrom(Intro other) {
        // TODO: copier les données telles quelles

    }

	@Override
	public boolean isValidNextStep(Intro manualModel) {
		boolean modified = false;

		// TODO: accepter ou rejeter les modifications manuelles
		//       retourner faux si c'est rejeté

		return modified;
	}

	@Override
	public ComparisonReport compareToSolution(Intro solution) {
		
		ComparisonReport report = ComparisonReport.emptyReport();
		
		// TODO: compare to a solution and report every error
		//       the testcase is passed if there is no error
		//       to report

		return report;
	}


    @Override
    protected void updateViewDataImpl(IntroProcedureViewData cardsViewData) {
        // TODO: créer des Carte2d pour afficher les cartes du modèle
    }

    @Override
    public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {
    	setExampleName(descriptor.testCaseId());
    	setIndiceDerniereCarte(-1);
    }

    @Override
    public int testCaseSize() {
    	int testCaseSize = 1;
    	if(exampleName.equals("ex01")) {

    		testCaseSize = 1;

    	}else if(exampleName.equals("ex02")) {

    		testCaseSize = 2;

    	}else if(exampleName.equals("ex03")) {

    		testCaseSize = 3;

    	}

    	return testCaseSize;
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
    	if(exampleName.equals("ex01")) {

			ex01();

    	}else if(exampleName.equals("ex02")) {

			ex02();

    	}else if(exampleName.equals("ex03")) {

			ex03();

    	}
    }

    public void ex01() {
    }

    public void ex02() {
    }

    public void ex03() {
    }

    @Override
    public void displayOn(IntroVariablesView variablesView) {
        // TODO: afficher les attributs
    }




}
