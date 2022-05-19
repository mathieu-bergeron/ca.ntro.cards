package demo_student;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.ExploreDemo;

@SuppressWarnings("rawtypes")
public class Explorer extends ExploreDemo<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MonTriNaif> naiveSortClass() {
		return MonTriNaif.class;
	}


}
