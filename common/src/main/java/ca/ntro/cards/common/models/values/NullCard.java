package ca.ntro.cards.common.models.values;

import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.views.controls.canvas.World2dGraphicsContext;
import ca.ntro.cards.common.models.world2d.CommonDrawingOptions;

public class NullCard<OPTIONS extends CommonDrawingOptions> extends AbstractCard<OPTIONS> {
	
	public NullCard() {
		super();
	}

	@Override
	public int compareTo(AbstractCard otherCard) {
		if(otherCard instanceof NullCard) {
			return 0;
			
		}else {

			return +1;
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void drawFaceUp(World2dGraphicsContext gc, 
			                  double topLeftX, 
			                  double topLeftY, 
			                  double width, 
			                  double height, 
			                  int levelOfDetails, 
			                  OPTIONS options) {

			gc.setFill(NtroApp.colorFromString("#ffffff"));

			gc.fillRect(topLeftX, 
					    topLeftY,
					    width,
					    height);

			gc.strokeRect(topLeftX, 
					      topLeftY,
					      width,
					      height);

			/*
			gc.strokeArc(getTopLeftX() + getWidth()/2 - getWidth()/8, 
					     getTopLeftY() + getHeight()/2 - getWidth()/8,
					     getWidth()/4,
					     getWidth()/4,
					     0,
					     360);
					     */
			
			/*
			gc.fillText("null", 
					    getTopLeftX() + getWidth() / 2 - 16, 
					    getTopLeftY() + getHeight() / 2);
			*/

		
	}

	@Override
	public boolean isNullCard() {
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		format(builder);
		
		return builder.toString();
	}

	@Override
	public void format(StringBuilder builder) {
		builder.append("NullCard");
		builder.append("  (");
		super.format(builder);
		builder.append(")");
	}

}
