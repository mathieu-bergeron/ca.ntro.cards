package demo_student;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.ProcedureTriNaif;

@SuppressWarnings("rawtypes")
public class Procedure extends ProcedureTriNaif<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MonTriNaif> classeTriNaif() {
		return MonTriNaif.class;
	}


}
