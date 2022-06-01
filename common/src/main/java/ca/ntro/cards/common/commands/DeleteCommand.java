package ca.ntro.cards.common.commands;

public class DeleteCommand extends KeyCommand {

	@Override
	public boolean isClear() {
		return false;
	}

	@Override
	public boolean isDelete() {
		return true;
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
		throw new RuntimeException("[FATAL] command is not a ClearCommand");
	}

	@Override
	public DeleteCommand delete() {
		return this;
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
