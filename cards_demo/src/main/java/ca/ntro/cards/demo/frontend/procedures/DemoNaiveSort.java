package ca.ntro.cards.demo.frontend.procedures;

import java.util.List;

import ca.ntro.cards.frontend.procedures.NaiveSort;

public class DemoNaiveSort<C extends Comparable<C>> extends NaiveSort<C> {

	public void sort() {
		indexOfNextEmptySpace = 0;
		
		for(int i = 0; i < sourceArray.size(); i++) {

			findIndexOfSmallestElement(sourceArray);
			
			C smallestElement = sourceArray.get(indexOfSmallestElement);
			
			targetArray.add(indexOfNextEmptySpace, smallestElement);
			
			sourceArray.add(indexOfSmallestElement, null);
			
			indexOfNextEmptySpace++;

			registerSimpleOperation();
		}
	}


	private void findIndexOfSmallestElement(List<C> sourceArray) {
		indexOfSmallestElement = -1;

		C currentSmallest = null;

		for(int i = 0; i < sourceArray.size(); i++) {
			
			indexOfCandidateSmallestElement = i;
			registerSimpleOperation();
			
			C candidate = sourceArray.get(i);
			
			if(currentSmallest == null
					|| candidate.compareTo(currentSmallest) < 0) {
				
				currentSmallest = candidate;
				
				indexOfSmallestElement = i;
				registerSimpleOperation();
			}
		}
	}
}
