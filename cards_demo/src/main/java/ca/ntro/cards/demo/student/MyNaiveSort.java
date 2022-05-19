package ca.ntro.cards.demo.student;

import java.util.List;

import ca.ntro.cards.demo.models.NaiveSort;

public class MyNaiveSort<C extends Comparable<C>> extends NaiveSort<C> {
	
	@Override
	public void sort() {
		for(int i = 0; i < source.size(); i++) {

			findIndexOfSmallestElement(source);
			
			C smallestElement = source.get(smallest);
			
			target.set(nextEmpty, smallestElement);
			
			source.set(smallest, null);
			
			nextEmpty++;

			saveStep();

		}
	}


	private void findIndexOfSmallestElement(List<C> sourceArray) {
		C currentSmallest = null;

		for(int i = 0; i < sourceArray.size(); i++) {
			
			candidate = i;
			saveStep();
			
			C candidate = sourceArray.get(i);
			
			if(currentSmallest == null
					&& candidate != null) {

				smallest = i;
				currentSmallest = candidate;

				saveStep();

			}else if(currentSmallest != null 
					&& candidate != null
					&& candidate.compareTo(currentSmallest) < 0) {
				
				smallest = i;
				currentSmallest = candidate;

				saveStep();
			}

		}
	}


}
