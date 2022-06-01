package ca.ntro.cards.common.commands;

public class AddCommand extends KeyValueCommand {

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
		return true;
	}

	@Override
	public boolean isGet() {
		return false;
	}

	@Override
	public AddCommand add() {
		return this;
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
	public GetCommand get() {
		throw new RuntimeException("[FATAL] command is not a GetCommand");
	}

	@Override
	public InsertCommand insert() {
		throw new RuntimeException("[FATAL] command is not an InsertCommand");
	}

}
