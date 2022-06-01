package ca.ntro.cards.common.commands;

public abstract class KeyCommand extends Command {
	
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public int getIndex() {
		return Integer.valueOf(key);
	}

	public void setIndex(int index) {
		this.key = String.valueOf(index);
	}

}
