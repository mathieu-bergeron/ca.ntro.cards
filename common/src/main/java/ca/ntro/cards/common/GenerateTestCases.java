package ca.ntro.cards.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ca.ntro.cards.common.models.CommonExecutableModel;
import ca.ntro.cards.common.models.TestCasesModel;
import ca.ntro.cards.common.models.values.test_cases.TestCase;
import ca.ntro.core.NtroJdk;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Path;

public abstract class GenerateTestCases<EXECUTABLE_MODEL extends CommonExecutableModel,
                                        STUDENT_MODEL extends EXECUTABLE_MODEL,
                                        TEST_CASE extends TestCase<EXECUTABLE_MODEL>,
                                        TEST_CASES_MODEL extends TestCasesModel> {

	public void generateTestCases() {

		NtroJdk.initializer().executeBlocking();
		
		System.out.println("\n\n[GENERATING TEST CASES]\n\n\n");
		
		TEST_CASES_MODEL testCasesModel = Ntro.factory().newInstance(testCasesModelClass());
		
		testCasesModel.registerExecutableModelClass(executableModelClass());
		testCasesModel.registerStudentModelClass(studentModelClass());
		
		testCasesModel.generateFirstVersionIfNeeded();
	
		try {

			FileOutputStream fileOutput = new FileOutputStream(new File("db.bin"));
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(testCasesModel);

		} catch (IOException e) {
			
			Ntro.throwException(e);

		}
	}

	protected abstract Class<EXECUTABLE_MODEL> executableModelClass();
	protected abstract Class<STUDENT_MODEL> studentModelClass();
	protected abstract Class<TEST_CASE> testCaseClass();
	protected abstract Class<TEST_CASES_MODEL> testCasesModelClass();
	

}
