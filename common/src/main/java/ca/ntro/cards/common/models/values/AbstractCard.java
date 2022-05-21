package ca.ntro.cards.common.models.values;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.app.models.Value;
import ca.ntro.cards.common.models.identifyers.IdFactory;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;
import ca.ntro.core.identifyers.Identifiable;

public abstract class AbstractCard<OPTIONS extends CommonDrawingOptions> implements Value, Identifiable, Comparable<AbstractCard> {

	private long id = -1;
	private boolean faceUp = true;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getFaceUp() {
		return faceUp;
	}

	public void setFaceUp(boolean facesUp) {
		this.faceUp = facesUp;
	}

	@Override
	public String id() {
		return String.valueOf(getId());
	}

	public AbstractCard() {
		setId(IdFactory.nextId());
	}

	public void flip() {
		this.faceUp = !this.faceUp;
	}

	public boolean hasId(String id) {
		return String.valueOf(this.id).equals(id);
	}

	@SuppressWarnings("rawtypes")
	public void draw(World2dGraphicsContext gc, 
			         double topLeftX, 
			         double topLeftY, 
			         double width, 
			         double height, 
			         int levelOfDetails, 
			         OPTIONS options) {
		
		if(faceUp) {

			drawFaceUp(gc, topLeftX, topLeftY, width, height, levelOfDetails, options);

		}else {

			drawFaceDown(gc, topLeftX, topLeftY, width, height, levelOfDetails, options);
			
		}
	}

	@SuppressWarnings("rawtypes")
	protected void drawFaceDown(World2dGraphicsContext gc, 
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


	protected abstract void drawFaceUp(World2dGraphicsContext gc, 
			                           double topLeftX, 
			                           double topLeftY, 
			                           double width, 
			                           double height, 
			                           int levelOfDetails, 
			                           OPTIONS options);

	public abstract boolean isNullCard();

	public void format(StringBuilder builder) {
		builder.append("id:" + id);
		builder.append(", ");
		builder.append("faceUp: " + faceUp);
	}

}
