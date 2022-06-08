package ca.ntro.cards.naivesort.frontend.views;

import java.net.URL;
import java.util.ResourceBundle;

import ca.ntro.cards.common.frontend.views.CommonSettingsView;
import ca.ntro.cards.frontend.views.ProcedureSettingsView;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public class NaivesortProcedureSettingsView extends ProcedureSettingsView {
	
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

	@FXML
	private ToggleButton useFourCardColorsToggleButton;
	
	@FXML
	private ToggleButton twoTimeSpeedToggleButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Ntro.assertNotNull("leftSpace", leftSpace);
		Ntro.assertNotNull("rightSpace", rightSpace);
		Ntro.assertNotNull("topSpace", topSpace);
		Ntro.assertNotNull("bottomSpace", bottomSpace);
		Ntro.assertNotNull("quitButton", quitButton);
		Ntro.assertNotNull("useFourCardColorsToggleButton", useFourCardColorsToggleButton);
		Ntro.assertNotNull("twoTimeSpeedToggleButton", twoTimeSpeedToggleButton);
		System.out.println(twoTimeSpeedToggleButton.isSelected()==true);
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

	@Override
	protected ToggleButton useFourCardColorsToggleButton() {
		return useFourCardColorsToggleButton;
	}
	
	protected ToggleButton UseTwoTimeSpeedToggleButton() {
		//Cette m�thode sera tr�s utile pour changer la vitesse
		return twoTimeSpeedToggleButton;
	}

}
