package ca.ntro.cards.foo.models;


import ca.ntro.cards.common.models.values.cards.Card;
import ca.ntro.cards.common.test_cases.descriptor.AbstractTestCaseDescriptor;
import ca.ntro.cards.foo.frontend.FooProcedureViewData;
import ca.ntro.cards.foo.frontend.views.FooVariablesView;
import ca.ntro.cards.foo.models.world2d.FooProcedureDrawingOptions;
import ca.ntro.cards.foo.models.world2d.FooProcedureObject2d;
import ca.ntro.cards.foo.models.world2d.FooProcedureWorld2d;
import ca.ntro.cards.models.ProcedureCardsModel;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class   FooCardsModel<C extends Comparable<C>> 

       extends ProcedureCardsModel<FooCardsModel, 
                                   FooProcedureObject2d, 
                                   FooProcedureWorld2d, 
                                   FooProcedureDrawingOptions, 
                                   FooProcedureViewData,
                                   FooVariablesView> { 

	@Override
	public void copyDataFrom(FooCardsModel other) {

	}

	@Override
	public void updateViewData(FooProcedureViewData cardsViewData) {

	}

	@Override
	public void initializeAsTestCase(AbstractTestCaseDescriptor descriptor) {

	}

	@Override
	public int testCaseSize() {
		return 0;
	}
	
	@Override
	protected Card cardById(String cardId) {
		return null;
	}
	
	@Override
	protected Stream<Card> cards() {
		return new StreamNtro<Card>() {
			@Override
			public void forEach_(Visitor<Card> visitor) throws Throwable {

			}
		};
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		format(builder);
		
		return builder.toString();

	}
	
	public void format(StringBuilder builder) {
		cards().forEach(card -> {
			builder.append(System.lineSeparator());
			card.format(builder);
		});
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

	public void studentMain() {
	}

	@Override
	public void displayOn(FooVariablesView variablesView) {

	}

}
