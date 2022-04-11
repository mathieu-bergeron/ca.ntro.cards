package ca.ntro.app.world2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class Object2dFx<OBJECT2D extends Object2dFx<OBJECT2D, WORLD2D>,
                                 WORLD2D extends World2dFx<OBJECT2D, WORLD2D>> 
       
      extends Object2d<GraphicsContext, 
                       OBJECT2D,
                       WORLD2D> {
	
	protected abstract boolean onMouseEvent(MouseEvent evtFx, 
			                                double worldX, 
			                                double worldY);

}
