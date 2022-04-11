package ca.ntro.core.services;

import java.util.List;

public class RandomNull implements Random {

	@Override
	public boolean nextBoolean() {
		return false;
	}

	@Override
	public int nextInt() {
		return 0;
	}

	@Override
	public int nextInt(int bound) {
		return 0;
	}

	@Override
	public <V> V choice(List<V> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V choice(V[] array) {
		// TODO Auto-generated method stub
		return null;
	}

}

