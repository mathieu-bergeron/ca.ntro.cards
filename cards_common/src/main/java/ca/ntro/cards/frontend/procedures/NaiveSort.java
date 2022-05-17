package ca.ntro.cards.frontend.procedures;

import java.util.List;

public class NaiveSort<C extends Comparable<C>> extends Procedure {

	protected int indexOfSmallestElement = -1;
	protected int indexOfCandidateSmallestElement = 0;
	protected int indexOfNextEmptySpace = 0;
	
	protected List<C> sourceArray;
	protected List<C> targetArray;

	public List<C> getSourceArray() {
		return sourceArray;
	}

	public void setSourceArray(List<C> sourceArray) {
		this.sourceArray = sourceArray;
	}

	public List<C> getTargetArray() {
		return targetArray;
	}

	public void setTargetArray(List<C> targetArray) {
		this.targetArray = targetArray;
	}

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
}
