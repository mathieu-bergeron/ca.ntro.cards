package ca.ntro.cards.demo.models;

import ca.ntro.cards.common.models.TestCaseDescriptor;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.demo.models.values.DemoTestCase;
import ca.ntro.core.initialization.Ntro;

public class DemoTestCasesModel<STUDENT_MODEL extends TriNaif> extends TestCasesModel<TriNaif, STUDENT_MODEL, DemoTestCase>{

	@Override
	protected void generateFirstVersion(Class<TriNaif> studentModelClass) {
		

		TestCaseDescriptor descriptor = TestCaseDescriptor.create()
				                                          .category("examples")
				                                          .testCaseId("ex01");

		TriNaif model = Ntro.factory().newInstance(studentModelClass);
		model.generateTestCase(descriptor);
		
		addTestCase(descriptor, model);
		
		for(int i = 0; i < 10; i++) {
			
			int size = 10 + Ntro.random().nextInt(20);
			

			descriptor = TestCaseDescriptor.create()
					                       .random(size);

			model = Ntro.factory().newInstance(studentModelClass);
			model.generateTestCase(descriptor);

			addTestCase(descriptor, model);
		}
	}

	@Override
	public DemoTestCase emptyTestCase() {
		return new DemoTestCase();
	}

	@Override
	public STUDENT_MODEL emptyStudentModel() {
		return null;
	}

}
