package ca.ntro.cards.frontend.procedures;

public class NaiveSort extends Procedure {

	private int indexOfSmallestElement = -1;
	private int indexOfCandidateSmallestElement = 0;
	private int indexOfNextEmptySpace = 0;

	public int getIndexOfSmallestElement() {
		return indexOfSmallestElement;
	}

	public void setIndexOfSmallestElement(int indexOfSmallestElement) {
		this.indexOfSmallestElement = indexOfSmallestElement;
	}

	public int getIndexOfNextEmptySpace() {
		return indexOfNextEmptySpace;
	}

	public void setIndexOfNextEmptySpace(int indexOfNextEmptySpace) {
		this.indexOfNextEmptySpace = indexOfNextEmptySpace;
	}

	public int getIndexOfCandidateSmallestElement() {
		return indexOfCandidateSmallestElement;
	}

	public void setIndexOfCandidateSmallestElement(int indexOfCandidateSmallestElement) {
		this.indexOfCandidateSmallestElement = indexOfCandidateSmallestElement;
	}

	protected void incrementIndexOfNextEmptySpace() {
		this.indexOfSmallestElement++;
	}

}
