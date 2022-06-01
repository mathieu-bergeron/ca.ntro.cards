package ca.ntro.cards.common.commands;

public abstract class Command {
	
	public abstract boolean isClear();
	public abstract boolean isDelete();
	public abstract boolean isInsert();
	public abstract boolean isAdd();
	public abstract boolean isGet();

	public abstract AddCommand add();
	public abstract ClearCommand clear();
	public abstract DeleteCommand delete();
	public abstract GetCommand get();
	public abstract InsertCommand insert();


}
