package ca.ntro.cards.common.commands;

public class InsertCommand extends KeyValueCommand {

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
		return true;
	}

	@Override
	public boolean isAdd() {
		return false;
	}

	@Override
	public boolean isGet() {
		return false;
	}

	@Override
	public AddCommand add() {
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
	public GetCommand get() {
		throw new RuntimeException("[FATAL] command is not a GetCommand");
	}

	@Override
	public InsertCommand insert() {
		return this;
	}

}
