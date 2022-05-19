package student_demo;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.DemoLocalApp;

@SuppressWarnings("rawtypes")
public class App extends DemoLocalApp<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MonTriNaif> naiveSortClass() {
		return MonTriNaif.class;
	}

}
