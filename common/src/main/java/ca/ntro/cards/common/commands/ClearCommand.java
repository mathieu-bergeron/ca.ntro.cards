package ca.ntro.cards.common.commands;

public class ClearCommand extends Command {

	@Override
	public boolean isClear() {
		return true;
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
		return false;
	}


	@Override
	public AddCommand add() {
		throw new RuntimeException("[FATAL] command is not an AddCommand");
	}

	@Override
	public ClearCommand clear() {
		return this;
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
