package ca.ntro.app.views.controls;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class SimpleFlexBox extends StackPane {
	
	private HBox hbox = new HBox();
	private VBox vbox = new VBox();
	private Pane currentPane = this;
	private double gap = 200;
	private double prefWidth = -1;
	
	public SimpleFlexBox() {
		super();
		
		initializeFlexBox();
		initialize();
	}
	
	protected double getGap() {
		return gap;
	}

	protected void setGap(double gap) {
		this.gap = gap;
	}

	protected abstract void initialize();

	private void initializeFlexBox() {
		getChildren().add(vbox);
		getChildren().add(hbox);

		hbox.setAlignment(Pos.CENTER);
		vbox.setAlignment(Pos.CENTER);
		
		widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(oldValue.doubleValue() == 0) {
					prefWidth = 0;
					for(Node child : getChildren()) {
						prefWidth += child.prefWidth(0);
					}
				}
			}
		});
	}

	@Override
	protected void layoutChildren() {
		if(prefWidth == -1) {
			return;
		}
		
		if(currentPane != vbox
				&& getWidth() < prefWidth + gap) {

			toVBox();

		}else if(currentPane != hbox
				&& getWidth() >= prefWidth + gap){

			toHBox();
		}

		super.layoutChildren();
	}
	
	private void moveNodes(Pane from, Pane to) {
		List<Node> toMove = new ArrayList<>();

		for(Node child : from.getChildren()) {
			if(child != hbox 
					&& child != vbox
					&& child != currentPane) {

				toMove.add(child);
			}
		}
		
		for(Node child : toMove) {
			from.getChildren().remove(child);
			to.getChildren().add(child);
		}
	}

	private void toVBox() {
		moveNodes(currentPane, vbox);
		
		vbox.toFront();

		currentPane = vbox;
		
		onVBox();
	}
	
	protected abstract void onVBox();

	private void toHBox() {
		moveNodes(currentPane, hbox);

		hbox.toFront();

		currentPane = hbox;
		
		onHBox();
	}

	protected abstract void onHBox();
}
