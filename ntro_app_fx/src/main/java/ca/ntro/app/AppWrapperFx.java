package ca.ntro.app;

import java.io.IOException;


import ca.ntro.app.backend.BackendRegistrarNtro;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.frontend.WindowFx;
import ca.ntro.app.messages.MessageRegistrarNtro;
import ca.ntro.app.models.ModelRegistrarNtro;
import ca.ntro.core.initialization.Ntro;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class AppWrapperFx extends Application {
    
    static Class<? extends NtroClientFx> appClass;
    
    private static BackendRegistrarNtro backendRegistrar;
    private static FrontendRegistrarFx  frontendRegistrar;
    //private static SystemTasksNtro      systemTasksNtro;
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
        NtroClientFx app = Ntro.factory().newInstance(appClass);

        MessageRegistrarNtro messageRegistrar  = new MessageRegistrarNtro();
        ModelRegistrarNtro   modelRegistrar    = new ModelRegistrarNtro();
        backendRegistrar  = new BackendRegistrarNtro();
        frontendRegistrar = new FrontendRegistrarFx();
        //systemTasksNtro   = new SystemTasksNtro();

        WindowFx window = new WindowFx(primaryStage);
        frontendRegistrar.setWindow(window);

        primaryStage.setOnCloseRequest(event -> {
            Platform.runLater(() -> {
                NtroApp.exit(() -> onExit());
            });
        });
        
        backendRegistrar.setMessageRegistrar(messageRegistrar);
        backendRegistrar.setModelRegistrar(modelRegistrar);

        frontendRegistrar.setMessageRegistrar(messageRegistrar);
        frontendRegistrar.setModelRegistrar(modelRegistrar);

        app.registerMessages(messageRegistrar);
        app.registerModels(modelRegistrar);
        app.registerBackend(backendRegistrar);
        app.registerFrontend(frontendRegistrar);

        System.out.println("\n\n\n");
        System.out.println("[INFO] Ntro version 1.0");
        System.out.println("[INFO] Locale: '" + NtroApp.currentLocale() + "'");
        
		if(backendRegistrar.isRemoteBackend()) {

			backendRegistrar.openConnection();

        }else {

			backendRegistrar.prepareToExecuteTasks();

        }
        
        frontendRegistrar.prepareToExecuteTasks();
        
        if(!backendRegistrar.isRemoteBackend()) {

			modelRegistrar.watchModels();
			backendRegistrar.executeTasks();

        }
        	
        frontendRegistrar.executeTasks();
        
        // FIXME: should define some Ntro.threadService
        new Thread() {
            
            @Override
            public void run() {

                System.out.println("[INFO] App running. Press Enter here to exit.");

                try {

                    System.in.read();
                    NtroApp.exit(() -> onExit());

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }.start();

    }

    public static void onExit() {

        System.out.println("\n\n\n[GENERATING GRAPHS]\n\n\n");
        NtroApp.models().writeGraphs();
        
        if(!backendRegistrar.isRemoteBackend()) {
			backendRegistrar.writeGraph();
        }

        frontendRegistrar.writeGraph();
    }
}
