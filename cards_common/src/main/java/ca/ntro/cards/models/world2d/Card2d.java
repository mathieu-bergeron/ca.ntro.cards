package ca.ntro.cards.models.world2d;

import ca.ntro.app.views.controls.canvas.GraphicsContextFx;
import ca.ntro.cards.models.values.Card;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Card2d extends Object2dCards {
	
	private Card card;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public void initialize() {
		setWidth(100);
		setHeight(100);
	}

	@Override
	protected boolean onMouseEvent(MouseEvent evtFx, double worldX, double worldY) {
		return false;
	}

	@Override
	public void draw(GraphicsContextFx gc) {
		gc.getRawGraphicsContext().setFill(Color.CORAL);
		gc.fillRect(getTopLeftX(), 
				    getTopLeftY(),
				    getWidth(), 
				    getHeight());
	}


}
