package ca.ntro.cards.common.commands;

public abstract class KeyValueCommand<C extends Comparable<C>> extends KeyCommand {
	
	private C value;

	public C getValue() {
		return value;
	}

	public void setValue(C value) {
		this.value = value;
	}

}
