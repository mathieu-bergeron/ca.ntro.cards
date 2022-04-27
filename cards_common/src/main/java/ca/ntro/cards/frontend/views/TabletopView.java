package ca.ntro.cards.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import ca.ntro.cards.models.world2d.World2dCards;

public abstract class TabletopView extends ViewFx {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public abstract void displayWorld2d(World2dCards world2d);

}
