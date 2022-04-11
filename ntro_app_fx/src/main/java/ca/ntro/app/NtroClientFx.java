package ca.ntro.app;

import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.services.LocaleServiceJdk;
import ca.ntro.app.services.MessageServiceNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.reflection.observer.Observation;
import ca.ntro.core.reflection.observer.ObservationNtro;
import javafx.application.Application;
import ntro.core.NtroFx;
import services.ExitServiceFx;
import services.MessageServiceFx;

public interface NtroClientFx extends App<FrontendRegistrarFx> {
    
    private static void checkJavaVersion() {
        String javaVersion = System.getProperty("java.version");
        if(!javaVersion.startsWith("11")) {
            System.err.println("[FATAL ERROR]: java-11 is required (provided: " + javaVersion + ")");
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    public static void launch(String[] args) {

        initialize();
        
        Class<? extends NtroClientFx> callerClass = (Class<? extends NtroClientFx>) Ntro.stackAnalyzer().callerClass();
        
        launchImpl(callerClass, args);
    }

    private static void initialize() {
        checkJavaVersion();

        NtroApp.registerLocaleService(new LocaleServiceJdk());
        NtroApp.registerExitService(new ExitServiceFx());
        NtroApp.registerMessageService(new MessageServiceFx());

        try {

        	NtroFx.initializer().executeBlocking();

        } catch (Throwable e) {

            throw new RuntimeException(e);
        }
    }

    public static void launch(Class<? extends NtroClientFx> callerClass, String[] args) {

        initialize();
        
        launchImpl(callerClass, args);
    }

    private static void launchImpl(Class<? extends NtroClientFx> callerClass, String[] args) {

        AppWrapperFx.appClass = callerClass;

        Application.launch(AppWrapperFx.class, args);
    }
}
