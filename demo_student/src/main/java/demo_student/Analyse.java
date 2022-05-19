package demo_student;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.DemoAnalysisApp;

@SuppressWarnings("rawtypes")
public class Analyse extends DemoAnalysisApp<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MonTriNaif> naiveSortClass() {
		return MonTriNaif.class;
	}


}
