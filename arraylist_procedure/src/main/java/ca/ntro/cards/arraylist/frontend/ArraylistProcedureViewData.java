package ca.ntro.cards.arraylist.frontend;

import ca.ntro.cards.common.frontend.views.CommonCanvasView;
import ca.ntro.cards.common.frontend.views.CommonDashboardView;
import ca.ntro.cards.common.models.values.cards.AbstractCard;
import ca.ntro.app.world2d.Object2d;
import ca.ntro.cards.arraylist.frontend.views.ArraylistCardsView;
import ca.ntro.cards.arraylist.models.world2d.ArraylistCard2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistMarker2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureDrawingOptions;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureObject2d;
import ca.ntro.cards.arraylist.models.world2d.ArraylistProcedureWorld2d;
import ca.ntro.cards.frontend.ProcedureViewData;
import ca.ntro.cards.models.world2d.ProcedureCard2d;
import ca.ntro.cards.models.world2d.ProcedureMarker2d;
import ca.ntro.cards.models.world2d.ProcedureObject2d;

public class ArraylistProcedureViewData extends ProcedureViewData<ArraylistProcedureObject2d, ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions> {

	@Override
	protected ArraylistProcedureWorld2d newWorld2d() {
		return new ArraylistProcedureWorld2d();
	}

	@Override
	protected ProcedureCard2d newCard2d(AbstractCard card) {
		return new ArraylistCard2d(card);
	}
	/*
	public void addOrUpdateMarker(String markerId, double topLeftX, double topLeftY,String couleur) {
		
		ProcedureMarker2d<ArraylistProcedureObject2d, ArraylistProcedureWorld2d, ArraylistProcedureDrawingOptions> marker2d = null;

		if(world2d().objectById(markerId)==null) {
			marker2d = new ArraylistMarker2d(markerId,couleur);
			world2d().addObject2d(marker2d);
		}
		
		marker2d.setTopLeftX(topLeftX);
		marker2d.setTopLeftY(topLeftY);
		
	}*/
	public void addOrUpdateMarker(String markerId, double topLeftX, double topLeftY,String couleur) {

		ProcedureObject2d marker2d = world2d().objectById(markerId);



		if(marker2d == null) {
		marker2d = new ArraylistMarker2d(markerId,couleur);
		world2d().addObject2d((ArraylistProcedureObject2d) marker2d);
		}

		marker2d.setTopLeftX(topLeftX);
		marker2d.setTopLeftY(topLeftY);

		}
	@Override
	protected ArraylistProcedureDrawingOptions defaultDrawingOptions() {
		return new ArraylistProcedureDrawingOptions() {
			@Override
			public boolean useFourCardColors() {
				return true;
			}
		};
	}
	@Override
	public void displayOn(CommonCanvasView canvasView, 
            CommonDashboardView dashboardView) {
		super.displayOn(canvasView, dashboardView);
		ArraylistCardsView cardsView=(ArraylistCardsView) canvasView;
		cardsView.displayRecycleBin();
	}


}
