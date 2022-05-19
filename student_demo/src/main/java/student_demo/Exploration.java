package student_demo;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.DemoExplorationApp;

@SuppressWarnings("rawtypes")
public class Exploration extends DemoExplorationApp<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MonTriNaif> naiveSortClass() {
		return MonTriNaif.class;
	}


}
