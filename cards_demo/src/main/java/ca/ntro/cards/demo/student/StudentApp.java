package ca.ntro.cards.demo.student;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.DemoLocalApp;

@SuppressWarnings("rawtypes")
public class StudentApp extends DemoLocalApp<MyNaiveSort> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MyNaiveSort> naiveSortClass() {
		return MyNaiveSort.class;
	}

}
