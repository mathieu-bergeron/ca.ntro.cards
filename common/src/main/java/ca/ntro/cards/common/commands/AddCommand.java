package ca.ntro.cards.common.commands;

import ca.ntro.cards.common.models.values.cards.Card;

public class AddCommand<C extends Comparable<C>> extends ValueCommand<C> {

	public AddCommand(Card card) {
		super(card);
	}

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
	public AddCommand<C> add() {
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
	public GetCommand<C> get() {
		throw new RuntimeException("[FATAL] command is not a GetCommand");
	}

	@Override
	public InsertCommand<C >insert() {
		throw new RuntimeException("[FATAL] command is not an InsertCommand");
	}

}
