package ca.ntro.app.views.controls.canvas;

import ca.ntro.app.world2d.World2dFx;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;

public abstract class World2dCanvas extends ResizableCanvas {
	
	private double worldWidth = 0;
	private double worldHeight = 0;
	
	private Pos position = Pos.CENTER;

	public Pos getPosition() {
		return position;
	}

	public void setPosition(Pos position) {
		this.position = position;
	}

	public double getWorldWidth() {
		return worldWidth;
	}

	public void setWorldWidth(double worldWidth) {
		this.worldWidth = worldWidth;
	}

	public double getWorldHeight() {
		return worldHeight;
	}

	public void setWorldHeight(double worldHeight) {
		this.worldHeight = worldHeight;
	}

	@Override
	protected void onInitialSize(double initialWidth, double initialHeight) {
		globalTransform(initialWidth, initialHeight);
	}

	@Override
	protected void onNewSize(double oldWidth, double oldHeight, double newWidth, double newHeight) {
		globalTransform(newWidth, newHeight);
	}
	
	private void globalTransform(double canvasWidth, double canvasHeight) {
		double canvasAspectRatio = canvasWidth / canvasHeight;
		double worldAspectRatio = worldWidth / worldHeight;
		
		double scaleX = 1.0;
		double scaleY = 1.0;
		double shearX = 0;
		double shearY = 0;
		double translateX = 0;
		double translateY = 0;
		
		if(canvasAspectRatio > worldAspectRatio) {

			scaleY = canvasHeight / worldHeight;
			scaleX = scaleY;

		} else if(canvasAspectRatio <= worldAspectRatio) {

			scaleX = canvasWidth / worldWidth; 
			scaleY = scaleX;
		}
		
		double worldWidthScaled = worldWidth * scaleX;
		double worldHeightScaled = worldHeight * scaleY;

		if(worldWidthScaled <  canvasWidth
				&& position == Pos.CENTER) {
			translateX = ((canvasWidth - worldWidthScaled) / 2);

		}else if(worldWidthScaled <  canvasWidth
				&& position == Pos.CENTER_RIGHT) {

			translateX = canvasWidth - worldWidthScaled;
		}

		if(worldHeightScaled <  canvasHeight) {
			translateY = ((canvasHeight - worldHeightScaled) / 2);
		}

		getGc().setTransform(scaleX, shearX, shearY, scaleY, translateX, translateY);
	}
	
	public void displayFps(String fps) {
		getGc().save();
		getGc().setTransform(1.0, 0, 0, 1.0, 1.0, 1.0); 
		getGc().fillText(fps, 0, 12);
		getGc().restore();
	}

	public void displayWorld2d(World2dFx world2d) {

		getGc().save();
		getGc().beginPath();
		getGc().rect(0, 0, worldWidth, worldHeight);
		getGc().clip();
		
		world2d.draw(getGc());
		
		getGc().restore();
	}

	public void clearCanvas() {
		getGc().save();
		getGc().setTransform(1.0, 0, 0, 1.0, 1.0, 1.0); 
		getGc().clearRect(0, 0, getWidth(), getHeight());
		getGc().restore();
	}
	
	public <T extends MouseEvent> void addMouseEventFilter(EventType<T> eventType, MouseEventHandler handler) {
		addEventFilter(eventType, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double x = event.getX();
				double y = event.getY();

				double mxx = getGc().getTransform().getMxx();
				double myy = getGc().getTransform().getMyy();
				double tx = getGc().getTransform().getTx();
				double ty = getGc().getTransform().getTy();
				
				double worldX = (x - tx) / mxx;
				double worldY = (y - ty) / myy;
				
				handler.handle(event, worldX, worldY);
			}
		});
	}
}
