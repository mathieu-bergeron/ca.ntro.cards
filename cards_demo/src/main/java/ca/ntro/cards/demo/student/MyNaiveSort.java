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
		C smallestElement = null;

		for(int i = 0; i < sourceArray.size(); i++) {
			
			candidate = i;
			saveStep();
			
			C candidate = sourceArray.get(i);
			
			if(smallestElement == null
					&& candidate != null) {

				smallest = i;
				smallestElement = candidate;

				saveStep();

			}else if(smallestElement != null 
					&& candidate != null
					&& candidate.compareTo(smallestElement) < 0) {
				
				smallest = i;
				smallestElement = candidate;

				saveStep();
			}

		}
	}


}
