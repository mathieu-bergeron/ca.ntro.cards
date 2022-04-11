package ca.ntro.app.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ca.ntro.app.NtroApp;
import ca.ntro.app.models.Model;
import ca.ntro.app.models.Watchable;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.json.JsonObject;
import ca.ntro.core.path.Path;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.observer.Observable;
import ca.ntro.core.reflection.observer.ObservationNtro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class ModelStoreDefault implements ModelStore {
	
	private Map<Class<?>, Object> previousModels = new ConcurrentHashMap<>();
	private Map<Class<?>, Object> currentModels = new ConcurrentHashMap<>();

	@Override
	public <M extends Model> M load(Class<?> modelClass) {
		
		M model = (M) currentModels.get(modelClass);
		
		if(model == null) {

			model = (M) loadFromFile(modelClass);
		}
		
		if(model == null) {
			
			model = (M) Ntro.factory().newInstance(modelClass);
			
		}

		previousModels.put(modelClass, Ntro.reflection().clone(model));
		currentModels.put(modelClass, model);
		
		return model;
	}


	private <M extends Model> M loadFromFile(Class<?> modelClass) {
		M model = null;

		Path filePath = filePathFromClass(modelClass);
		
		if(Ntro.storage().ifFileExists(filePath)) {
			
			String jsonString = Ntro.storage().readTextFile(filePath);
			JsonObject jsonObject = Ntro.json().fromJsonString(jsonString);
			model = (M) Ntro.reflection().graphFromJsonObject(jsonObject).buildObject();
		}

		return model;
	}

	private Path filePathFromClass(Class<?> modelClass) {
		return Path.fromRawPath("/models/" + Ntro.reflection().simpleName(modelClass) + ".json");
	}

	@Override
	public void save(Object model) {
		Object previousModel = previousModels.get(model.getClass());
		
		if(previousModel != null
				&& !Ntro.reflection().graphEquals(previousModel, model)) {
			
			pushObservation(model.getClass(), previousModel, model);

			writeModelFile(model);
		}
	}

	private void writeModelFile(Path filePath, Object model) {

		ObjectGraph graph = Ntro.reflection().graphFromObject(model);

		JsonObject jsonObject = graph.buildJsonObject();
		String jsonString = jsonObject.toJsonString(true);
		Ntro.storage().writeTextFile(filePath, jsonString);

	}

	private void writeModelFile(Object model) {
		writeModelFile(filePathFromClass(model.getClass()), model);
	}

	@Override
	public void writeModelFiles() {
		for(Object model : currentModels.values()) {
			writeModelFile(model);
		}
	}


	@Override
	public void writeGraphs() {
		for(Object model : currentModels.values()) {
			writeModelGraph(model);
		}
	}

	private void writeModelGraph(Object model) {
		ObjectGraph graph = Ntro.reflection().graphFromObject(model);
		graph.write(Ntro.graphWriter());
	}


	@Override
	public void watch(Class<?> modelClass) {
		
		
		Path filePath = filePathFromClass(modelClass);

		Object model = load(modelClass);

		createModelFileIfNeeded(filePath, modelClass, model);
		
		pushFirstObservation(modelClass);

		if(Ntro.reflection().ifClassImplements(modelClass, Watchable.class)) {

			Ntro.storage().watchFile(filePathFromClass(modelClass), () -> {
				observeModelFile(modelClass);
			});
		}
	}

	private void createModelFileIfNeeded(Path filePath, Class<?> modelClass, Object model) {
		if(!Ntro.storage().ifFileExists(filePath)) {
			writeModelFile(filePath, model);
		}
	}

	private void observeModelFile(Class<?> modelClass) {
		Object previousModel = currentModels.get(modelClass);
		Object currentModel = loadFromFile(modelClass);
		
		if(currentModel != null) {
			currentModels.put(modelClass, currentModel);
		}

		if(previousModel != null
				&& currentModel != null
				&& !Ntro.reflection().graphEquals(previousModel, currentModel)) {

			/*
			System.out.println("[INFO] observation from file");
			ObjectGraph previousModelGraph = Ntro.reflection().graphFromObject(previousModel, "previousModel");
			ObjectGraph modelGraph = Ntro.reflection().graphFromObject(currentModel, "model");
			
			previousModelGraph.write(Ntro.graphWriter());
			modelGraph.write(Ntro.graphWriter());
			*/
			
			pushObservation(modelClass, previousModel, currentModel);
		}
	}
	
	private void pushFirstObservation(Class<?> modelClass) {
		Object previousModel = Ntro.factory().newInstance(modelClass);
		Object currentModel = load(modelClass);

		pushObservation(modelClass, previousModel, currentModel);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void pushObservation(Class<?> modelClass, Object previousModel, Object currentModel) {
		currentModels.put(modelClass, currentModel);
		
		ObservationNtro observation = new ObservationNtro<>();
		observation.setPreviousValue((Observable) previousModel);

		// XXX: clone currentModel to simulate that the observation
		//      is received after serialization and deserialization
		observation.setCurrentValue((Observable) Ntro.reflection().clone(currentModel));

		//Revisions revisions = Ntro.reflection().revisionsFromTo(initialModel, currentModel);

		NtroApp.messageService().receiveObservationFromServer(Ntro.reflection().simpleName(modelClass), observation);
		NtroApp.messageService().pushObservationToClients(Ntro.reflection().simpleName(modelClass), observation);
	}


	@Override
	public Stream<Model> modelStream() {
		return new StreamNtro<Model>() {
			@Override
			public void forEach_(Visitor<Model> visitor) throws Throwable {
				for(Object model : currentModels.values()) {
					visitor.visit((Model) model);
				}
			}
		};
	}




}
