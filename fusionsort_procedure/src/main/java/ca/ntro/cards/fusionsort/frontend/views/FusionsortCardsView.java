// Copyright (C) (2022) (Adrien Josephine-Olivier) (2066267@cmontmorency.qc.ca)

package ca.ntro.cards.fusionsort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.app.NtroApp;
import ca.ntro.app.views.controls.canvas.World2dCanvasFx;
import ca.ntro.app.views.controls.canvas.World2dResizableCanvasFx;
import ca.ntro.cards.fusionsort.FusionsortConstants;
import ca.ntro.cards.fusionsort.frontend.views.controls.FusionsortPreviewCanvas;
import ca.ntro.cards.fusionsort.frontend.events.EvtSaveControlKeyState;
import ca.ntro.cards.fusionsort.frontend.views.controls.FusionsortMainCanvas;
import ca.ntro.cards.frontend.views.ProcedureCanvasView;
import ca.ntro.core.initialization.Ntro;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FusionsortCardsView extends ProcedureCanvasView {

	@FXML
	private VBox cardsViewContainer;

	@FXML
	private FusionsortPreviewCanvas previewCanvas;

	@FXML
	private FusionsortMainCanvas mainCanvas;

	@FXML
	private StackPane dashboardContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Ntro.assertNotNull("cardsViewContainer", cardsViewContainer);
		Ntro.assertNotNull("previewCanvas", previewCanvas);
		Ntro.assertNotNull("mainCanvas", mainCanvas);
		Ntro.assertNotNull("dashboardContainer", dashboardContainer);

		super.initialize(location, resources);
		installEvtSaveControlKeyState();
	}

	protected void installEvtSaveControlKeyState() {
		EvtSaveControlKeyState evtSaveControlKeyState = NtroApp.newEvent(EvtSaveControlKeyState.class);

		mainCanvas().setOnKeyPressed(evtFx -> {

			if (evtFx.getCode().equals(KeyCode.CONTROL)) {

				evtSaveControlKeyState.setControlKeyState("pressed");
				evtSaveControlKeyState.trigger();
			}

		});

		mainCanvas().setOnKeyReleased(evtFx -> {

			if (evtFx.getCode().equals(KeyCode.CONTROL)) {

				evtSaveControlKeyState.setControlKeyState("released");
				evtSaveControlKeyState.trigger();
			}

		});

	}

	@SuppressWarnings("rawtypes")
	@Override
	protected World2dResizableCanvasFx mainCanvas() {
		return mainCanvas;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected World2dCanvasFx previewCanvas() {
		return previewCanvas;
	}

	@Override
	protected Pane dashboardContainer() {
		return dashboardContainer;
	}

	@Override
	protected Pane cardsViewContainer() {
		return cardsViewContainer;
	}

	@Override
	protected double initialWorldHeight() {
		return FusionsortConstants.INITIAL_WORLD_HEIGHT;
	}

	@Override
	protected double initialWorldWidth() {
		return FusionsortConstants.INITIAL_WORLD_WIDTH;
	}

	@Override
	protected double initialPreviewCanvasScreenHeight() {
		return FusionsortConstants.INITIAL_PREVIEW_CANVAS_SCREEN_HEIGHT;
	}

}
