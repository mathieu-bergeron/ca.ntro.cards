package ca.ntro.cards.frontend.events;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.cards.frontend.views.GameView;
import javafx.scene.input.MouseEvent;

public class MouseEvtOnTabletop extends EventNtro {
	
	private MouseEvent evtFx;
	private double worldX;
	private double worldY;

	public MouseEvent getEvtFx() {
		return evtFx;
	}

	public void setEvtFx(MouseEvent evtFx) {
		this.evtFx = evtFx;
	}

	public double getWorldX() {
		return worldX;
	}

	public void setWorldX(double worldX) {
		this.worldX = worldX;
	}

	public double getWorldY() {
		return worldY;
	}

	public void setWorldY(double worldY) {
		this.worldY = worldY;
	}


	public void applyTo(GameView gameView) {
		gameView.mouseEvtOnTabletop(evtFx, worldX, worldY);
	}

}
