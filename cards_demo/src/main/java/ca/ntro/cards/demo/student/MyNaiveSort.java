package ca.ntro.cards.demo.student;

import java.util.List;

import ca.ntro.cards.demo.models.DemoCardsModel;

public class MyNaiveSort<C extends Comparable<C>> extends DemoCardsModel<C> {
	
	@Override
	public void sort() {
		indexOfNextEmptySpace = 0;
		indexOfSmallestElement = -1;
		indexOfCandidateSmallestElement = -1;
		registerSimpleOperation();
		
		for(int i = 0; i < sourceArray.size(); i++) {

			findIndexOfSmallestElement(sourceArray);
			
			C smallestElement = sourceArray.get(indexOfSmallestElement);
			
			targetArray.set(indexOfNextEmptySpace, smallestElement);
			
			sourceArray.set(indexOfSmallestElement, null);

			registerSimpleOperation();

			indexOfSmallestElement = -1;
			indexOfCandidateSmallestElement = -1;
			indexOfNextEmptySpace++;

			registerSimpleOperation();
		}
		
		indexOfNextEmptySpace = 0;
		indexOfCandidateSmallestElement = -1;
		indexOfSmallestElement = -1;
		registerSimpleOperation();
	}


	private void findIndexOfSmallestElement(List<C> sourceArray) {
		C currentSmallest = null;

		for(int i = 0; i < sourceArray.size(); i++) {
			
			indexOfCandidateSmallestElement = i;
			registerSimpleOperation();
			
			C candidate = sourceArray.get(i);
			
			if(candidate != null 
					&& currentSmallest != null
					&& candidate.compareTo(currentSmallest) < 0) {

				currentSmallest = candidate;
				
				indexOfSmallestElement = i;
				registerSimpleOperation();
				
			}else if(candidate != null
					&& currentSmallest == null) {
				
				currentSmallest = candidate;

				indexOfSmallestElement = i;
				registerSimpleOperation();
			}
		}
	}


}
