package ca.ntro.cards.arraylist.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.efficiency.frontend.views.EfficiencySettingsView;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public class ArraylistEfficiencySettingsView extends EfficiencySettingsView {

	@FXML
	private Pane leftSpace;

	@FXML
	private Pane rightSpace;

	@FXML
	private Pane topSpace;

	@FXML
	private Pane bottomSpace;

	@FXML
	private Button quitButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("leftSpace", leftSpace);
		Ntro.assertNotNull("rightSpace", rightSpace);
		Ntro.assertNotNull("topSpace", topSpace);
		Ntro.assertNotNull("bottomSpace", bottomSpace);
		Ntro.assertNotNull("quitButton", quitButton);
		
		super.initialize(location, resources);
	}

	@Override
	protected Stream<Pane> spaces() {
		return new StreamNtro<Pane>() {
			@Override
			public void forEach_(Visitor<Pane> visitor) throws Throwable {
				visitor.visit(leftSpace);
				visitor.visit(rightSpace);
				visitor.visit(topSpace);
				visitor.visit(bottomSpace);
			}
		};
	}

	@Override
	protected Button quitButton() {
		return quitButton;
	}

}
