// Copyright (C) (2020) (Mathieu Bergeron) (mathieu.bergeron@cmontmorency.qc.ca)
//
// This file is part of ntro4f5
//
// ntro4f5 is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// ntro4f5 is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with aquiletour.  If not, see <https://www.gnu.org/licenses/>

package ca.ntro.app.views.controls.canvas;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public abstract class ResizableCanvas extends Pane {
	
	private Canvas canvas;
	private GraphicsContext gc;
	private double epsilon = 1;

	public GraphicsContext getGc() {
		return gc;
	}

	protected double getEpsilon() {
		return epsilon;
	}

	protected void setEpsilon(int epsilon) {
		this.epsilon = epsilon;
	}
	
	protected void setCanvasWidth(double width) {
		canvas.setWidth(width);
	}

	protected void setCanvasHeight(double height) {
		canvas.setHeight(height);
	}

	public ResizableCanvas() {
		initialize();
		initializeCanvas();
	}

	protected abstract void initialize();

	private void initializeCanvas() {
		installCanvas();
		
		installWidthObserver();
		installHeightObserver();
	}

	private void installCanvas() {
		canvas = new Canvas();
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
	}
	
	private void installWidthObserver() {
		
		this.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				resizeCanvas();
			}
		});
	}

	private void resizeCanvas() {
		
		if(Math.abs(getWidth() - canvas.getWidth()) > epsilon
				|| Math.abs(getHeight() - canvas.getHeight()) > epsilon) {
			
			double oldCanvasWidth = canvas.getWidth();
			double oldCanvasHeight = canvas.getHeight();
			double newCanvasWidth = getWidth();
			double newCanvasHeight = getHeight();
			
			if(oldCanvasWidth > 0
					&& oldCanvasHeight > 0) {

				onNewSize(oldCanvasWidth, oldCanvasHeight, newCanvasWidth, newCanvasHeight);
				
			}else if(newCanvasWidth > 0
					&& newCanvasHeight > 0) {

				onInitialSize(newCanvasWidth, newCanvasHeight);
			}

			canvas.setWidth(newCanvasWidth);
			canvas.setHeight(newCanvasHeight);
		}
	}

	private void installHeightObserver() {

		this.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				resizeCanvas();
			}
		});
	}

	protected abstract void onInitialSize(double initialWidth, double initialHeight);
	protected abstract void onNewSize(double oldWidth, 
			                          double oldHeight, 
			                          double newWidth,
			                          double newHeight);

}
