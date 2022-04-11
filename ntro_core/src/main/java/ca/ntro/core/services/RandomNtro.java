package ca.ntro.core.services;

import java.util.List;

public abstract class RandomNtro implements Random {

	@Override
	public <V> V choice(List<V> list) {
		V result = null;

		if(!list.isEmpty()) {
			result = list.get(nextInt(list.size()));
		}
		
		return result;
	}

	@Override
	public <V> V choice(V[] array) {
		V result = null;

		if(array.length > 0) {
			result = array[nextInt(array.length)];
		}
		
		return result;
	}

}
