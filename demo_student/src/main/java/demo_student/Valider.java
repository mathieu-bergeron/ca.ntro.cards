package demo_student;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.ValidateDemo;
import ca.ntro.cards.demo.models.DemoTestCasesModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;

@SuppressWarnings("rawtypes")
public class Valider extends ValidateDemo<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MonTriNaif> naiveSortClass() {
		return MonTriNaif.class;
	}



}
