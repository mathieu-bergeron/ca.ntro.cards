package demo_student;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.AnalyzeDemo;
import ca.ntro.cards.demo.models.DemoTestCasesModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;

@SuppressWarnings("rawtypes")
public class Analyser extends AnalyzeDemo<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MonTriNaif> naiveSortClass() {
		return MonTriNaif.class;
	}




}
