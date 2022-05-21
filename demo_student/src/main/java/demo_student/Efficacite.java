package demo_student;

import ca.ntro.app.NtroClientFx;
import ca.ntro.cards.demo.EfficaciteTriNaif;

@SuppressWarnings("rawtypes")
public class Efficacite extends EfficaciteTriNaif<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

	@Override
	protected Class<MonTriNaif> classeTriNaif() {
		return MonTriNaif.class;
	}

}
