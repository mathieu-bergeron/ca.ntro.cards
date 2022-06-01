package ca.ntro.cards.common.commands;

public abstract class Command<C extends Comparable<C>> {
	
	public abstract boolean isClear();
	public abstract boolean isDelete();
	public abstract boolean isInsert();
	public abstract boolean isAdd();
	public abstract boolean isGet();

	public abstract AddCommand<C> add();
	public abstract ClearCommand clear();
	public abstract DeleteCommand delete();
	public abstract GetCommand<C> get();
	public abstract InsertCommand<C> insert();


}
