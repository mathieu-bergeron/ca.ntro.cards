package ca.ntro.cards.frontend.events;

import ca.ntro.app.frontend.events.EventNtro;
import javafx.scene.input.MouseEvent;

public class MouseEvtOnViewport extends EventNtro {
	
	private MouseEvent evtFx;
	private double viewportX;
	private double viewportY;
	private double worldX;
	private double worldY;

	public MouseEvent getEvtFx() {
		return evtFx;
	}

	public void setEvtFx(MouseEvent evtFx) {
		this.evtFx = evtFx;
	}

	public double getViewportX() {
		return viewportX;
	}

	public void setViewportX(double viewportX) {
		this.viewportX = viewportX;
	}

	public double getViewportY() {
		return viewportY;
	}

	public void setViewportY(double viewportY) {
		this.viewportY = viewportY;
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
	
	



}
