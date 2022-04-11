package ca.ntro.app.models;

public interface ModelRegistrar {
	
	<M extends Model> void registerModel(Class<M> modelClass);
	<V extends Value> void registerValue(Class<V> valueClass);

}
