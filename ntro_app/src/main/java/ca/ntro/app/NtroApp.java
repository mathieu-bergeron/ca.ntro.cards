package ca.ntro.app;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.app.messages.MessageNtro;
import ca.ntro.app.services.EventService;
import ca.ntro.app.services.EventServiceNtro;
import ca.ntro.app.services.ExitHandler;
import ca.ntro.app.services.ExitService;
import ca.ntro.app.services.ExitServiceDefault;
import ca.ntro.app.services.LocaleService;
import ca.ntro.app.services.LocaleServiceNull;
import ca.ntro.app.services.MessageService;
import ca.ntro.app.services.MessageServiceNtro;
import ca.ntro.app.services.ModelStore;
import ca.ntro.app.services.ModelStoreDefault;

public class NtroApp {

	/* <Locale> */

	private static LocaleService localeService = new LocaleServiceNull();

	static void registerLocaleService(LocaleService localeService){
		NtroApp.localeService = localeService;
	}

	public static Locale currentLocale() {
		return NtroApp.localeService.currentLocale();
	}

	public static Locale locale(String language){
		return NtroApp.localeService.newLocale(language);
	}

	public static Locale locale(String language, String country){
		return NtroApp.localeService.newLocale(language, country);
	}

	public static Locale locale(String language, String country, String variant){
		return NtroApp.localeService.newLocale(language, country, variant);
	}

	/* </Locale> */

	
	
	/* <Events> */

	private static EventService eventService = new EventServiceNtro();

	static void registerEventService(EventService eventService){
		NtroApp.eventService = eventService;
	}
	
	public static EventService eventService() {
		return NtroApp.eventService;
	}

	public static <E extends EventNtro> E newEvent(Class<E> eventClass) {
		return eventService.newEvent(eventClass);
	}

	/* </Events> */
	
	
	
	
	/* <Messages> */

	private static MessageService messageService = new MessageServiceNtro();

	static void registerMessageService(MessageService messageService){
		NtroApp.messageService = messageService;
	}

	public static MessageService messageService() {
		return NtroApp.messageService;
	}

	public static <MSG extends MessageNtro> MSG newMessage(Class<MSG> messageClass) {
		return messageService.newMessage(messageClass);
	}


	/* </Messages> */
	

	/* <ModelStore> */

	private static ModelStore modelStore = new ModelStoreDefault();

	static void registerModelStore(ModelStore modelStore){
		NtroApp.modelStore = modelStore;
	}

	public static ModelStore models() {
		return NtroApp.modelStore;
	}


	/* </ModelStore> */
	
	
	

	/* <ExitService> */

	private static ExitService exitService = new ExitServiceDefault();

	static void registerExitService(ExitService exitService){
		NtroApp.exitService = exitService;
	}

	public static void exit(ExitHandler exitHandler) {
		NtroApp.exitService.exit(exitHandler);
	}


	/* </ExitService> */

}
