package ca.ntro.cards.intro.backend;



import ca.ntro.app.tasks.backend.BackendTasks;
import static ca.ntro.app.tasks.backend.BackendTasks.*;
import ca.ntro.cards.backend.ProcedureBackend;
import ca.ntro.cards.intro.messages.IntroMsgAcceptManualModel;
import ca.ntro.cards.intro.models.Intro;
import ca.ntro.cards.intro.models.values.IntroTestCase;
import ca.ntro.cards.intro.test_cases.IntroTestCaseDatabase;
import ca.ntro.cards.intro.test_cases.execution_trace.IntroExecutionTrace;
import ca.ntro.cards.messages.ProcedureMsgAcceptManualModel;
import ca.ntro.cards.intro.models.IntroProcedureDashboardModel;
import ca.ntro.cards.intro.models.IntroProcedureSettingsModel;

public class   IntroProcedureBackend<STUDENT_MODEL extends Intro>


       extends ProcedureBackend<Intro,       // ExecutableModel
                                STUDENT_MODEL,
                                STUDENT_MODEL,        // CanvasModel
                                IntroTestCase,
                                IntroTestCaseDatabase,
                                IntroExecutionTrace,
                                IntroProcedureDashboardModel,
                                IntroProcedureSettingsModel,
                                IntroMsgAcceptManualModel> {

	
	


}
