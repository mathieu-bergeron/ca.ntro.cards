package ca.ntro.cards.common.commands;

public class GetCommand<C extends Comparable<C>> extends KeyCommand {

	@Override
	public boolean isClear() {
		return false;
	}

	@Override
	public boolean isDelete() {
		return false;
	}

	@Override
	public boolean isInsert() {
		return false;
	}

	@Override
	public boolean isAdd() {
		return false;
	}

	@Override
	public boolean isGet() {
		return true;
	}

	@Override
	public AddCommand<C> add() {
		throw new RuntimeException("[FATAL] command is not an AddCommand");
	}

	@Override
	public ClearCommand clear() {
		throw new RuntimeException("[FATAL] command is not a ClearCommand");
	}

	@Override
	public DeleteCommand delete() {
		throw new RuntimeException("[FATAL] command is not a DeleteCommand");
	}

	@Override
	public GetCommand<C> get() {
		return this;
	}

	@Override
	public InsertCommand<C> insert() {
		throw new RuntimeException("[FATAL] command is not an InsertCommand");
	}

}
