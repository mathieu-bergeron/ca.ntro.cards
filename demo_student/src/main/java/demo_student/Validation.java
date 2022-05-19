package demo_student;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.DemoValidationApp;

@SuppressWarnings("rawtypes")
public class Validation extends DemoValidationApp<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MonTriNaif> naiveSortClass() {
		return MonTriNaif.class;
	}


}
