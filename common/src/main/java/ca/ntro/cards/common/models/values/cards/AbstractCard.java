package ca.ntro.cards.common.models.values.cards;

import java.io.Serializable;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.identifyers.IdFactory;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.core.identifyers.Identifiable;

public abstract class AbstractCard<OPTIONS extends CommonDrawingOptions> 


       implements Value, Identifiable, Comparable<AbstractCard>, Serializable {

	private static final long serialVersionUID = 7116211713513539219L;

	@SuppressWarnings("rawtypes")
	public void drawFaceDown(World2dGraphicsContext gc, 
			                    double topLeftX, 
			                    double topLeftY, 
			                    double width, 
			                    double height, 
			                    int levelOfDetails, 
			                    OPTIONS options) {

		if(levelOfDetails > 5) {

			drawFaceDownHighDetails(gc, topLeftX, topLeftY, width, height, options);

		}else {

			drawFaceDownLowDetails(gc, topLeftX, topLeftY, width, height, options);

		}
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void drawFaceDownLowDetails(World2dGraphicsContext gc, 
			                            double topLeftX, 
			                            double topLeftY, 
			                            double width, 
			                            double height, 
			                            OPTIONS options) {
		
		gc.setFill(NtroApp.colorFromString("#aaaaaa"));
		gc.fillRect(topLeftX, 
					topLeftY,
					width, 
					height);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void drawFaceDownHighDetails(World2dGraphicsContext gc, 
			                  double topLeftX, 
			                  double topLeftY, 
			                  double width, 
			                  double height, 
			                  OPTIONS options) {
		
		gc.setFill(NtroApp.colorFromString("#999999"));
		gc.fillRect(topLeftX, 
					topLeftY,
					width, 
					height);

		gc.setStroke(NtroApp.colorFromString("#000000"));
		gc.strokeRect(topLeftX, 
					  topLeftY,
					  width, 
					  height);
	}


	public abstract void drawFaceUp(World2dGraphicsContext gc, 
			                           double topLeftX, 
			                           double topLeftY, 
			                           double width, 
			                           double height, 
			                           int levelOfDetails, 
			                           OPTIONS options);

	public abstract boolean isNullCard();

	@Override
	public boolean hasId(String id) {
		return String.valueOf(this.id()).equals(id);
	}

	public abstract void format(StringBuilder builder);

}
