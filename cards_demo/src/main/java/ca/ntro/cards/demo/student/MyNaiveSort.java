package ca.ntro.cards.demo.student;

import java.util.List;

import ca.ntro.cards.demo.models.DemoCardsModel;

public class MyNaiveSort<C extends Comparable<C>> extends DemoCardsModel<C> {
	
	@Override
	public void sort() {
		for(int i = 0; i < sourceArray.size(); i++) {

			findIndexOfSmallestElement(sourceArray);
			
			C smallestElement = sourceArray.get(indexOfSmallestElement);
			
			targetArray.set(indexOfNextEmptySpace, smallestElement);
			
			sourceArray.set(indexOfSmallestElement, null);
			
			indexOfNextEmptySpace++;

			synchronize();

		}
	}


	private void findIndexOfSmallestElement(List<C> sourceArray) {
		C currentSmallest = null;

		for(int i = 0; i < sourceArray.size(); i++) {
			
			indexOfCandidateSmallestElement = i;
			synchronize();
			
			C candidate = sourceArray.get(i);
			
			if(currentSmallest == null
					&& candidate != null) {

				indexOfSmallestElement = i;
				currentSmallest = candidate;

				synchronize();

			}else if(currentSmallest != null 
					&& candidate != null
					&& candidate.compareTo(currentSmallest) < 0) {
				
				indexOfSmallestElement = i;
				currentSmallest = candidate;

				synchronize();
			}

		}
	}


}
