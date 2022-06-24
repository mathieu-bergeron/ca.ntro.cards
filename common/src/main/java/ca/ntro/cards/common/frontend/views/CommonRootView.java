// Copyright (C) (2022) (Zein El Abdin Hazimeh) (2037668@cmontmorency.qc.ca)
//
// This file is part of Ntro
//
// This is free software: you can redistribute it and/or modify
// it under the terms of the GNU GPL3 General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU GPL3 General Public License for more details.
//
// You should have received a copy of the GNU GPL3 General Public License
// along with aquiletour.  If not, see <https://www.gnu.org/licenses/>

package ca.ntro.cards.common.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.views.ViewFx;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public abstract class CommonRootView extends ViewFx {
	
	private CommonSettingsView menuView;
	private CommonCanvasView canvasView;
	private CommonMessagesView messagesView;
	
	
	
	private Timeline animationSetting = new Timeline();
	private Timeline animationMessage = new Timeline();
	
	 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void registerSettingsView(CommonSettingsView menuView) {
		this.menuView = menuView;
		
		animationSetting.getKeyFrames()
		.add(new KeyFrame(Duration.seconds(0.3), new KeyValue(menuView.rootNode().translateYProperty(),0,Interpolator.EASE_OUT)));
	
		
	}
	
	public void registerCanvasView(CommonCanvasView canvasView) {
		this.canvasView = canvasView;
	}
	
	public void installSubViews() {
		rootNode().getChildren().add(canvasView.rootNode());
		rootNode().getChildren().add(menuView.rootNode());
		rootNode().getChildren().add(messagesView.rootNode());
		
		menuView.rootNode().setVisible(false);
		messagesView.rootNode().setVisible(false);
		 
		hideMenu();


		
		hideMessages();
	}

	public void showMenu() {
		menuView.rootNode().setVisible(true);
		menuView.rootNode().translateYProperty().set(-200);
		animationSetting.playFromStart();
	
	}

	public void hideMenu() {


    animationSetting.stop();
    menuView.rootNode().translateYProperty().set(-200);
    
	}

	public void showMessages() {
		messagesView.rootNode().setVisible(true);
		messagesView.rootNode().translateYProperty().set(-200);
		animationMessage.playFromStart();
		 
	}

	public void hideMessages() {
		animationMessage.stop();
		messagesView.rootNode().translateYProperty().set(-200);
	}

	public void registerMessagesView(CommonMessagesView messagesView) {
		this.messagesView = messagesView;
		
		animationMessage.getKeyFrames()
		.add(new KeyFrame(Duration.seconds(0.3), new KeyValue(messagesView.rootNode().translateYProperty(),0,Interpolator.EASE_OUT)));
	
				
	}

}