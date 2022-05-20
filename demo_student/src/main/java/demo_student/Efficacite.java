package demo_student;

import ca.ntro.app.NtroClientFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.cards.common.backend.CommonBackend;
import ca.ntro.cards.common.frontend.CommonFrontend;
import ca.ntro.cards.demo.EfficaciteTriNaif;

@SuppressWarnings("rawtypes")
public class Efficacite extends EfficaciteTriNaif<MonTriNaif> {

	public static void main(String[] args) {
		NtroClientFx.launch(args);
	}

}
