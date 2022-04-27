package ca.ntro.cards.frontend.views;


import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.models.world2d.World2dCards;

public abstract class TabletopView extends ViewFx {

	public abstract void displayWorld2d(World2dCards world2d);
	public abstract void clearCanvas();
	public abstract void displayFps(String fpsCourant);

}
