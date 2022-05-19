package ca.ntro.cards.demo.student;

import ca.ntro.cards.demo.models.NaiveSort;

public class MyNaiveSort<C extends Comparable<C>> extends NaiveSort<C> {
	
	@Override
	public void sort() {
		for(int i = 0; i < source.size(); i++) {

			C smallestElement = findSmallest();
			
			target.set(nextEmpty, smallestElement);
			
			source.set(smallest, null);
			
			nextEmpty++;

			saveStep();

		}
	}


	private C findSmallest() {
		C result = null;

		for(int i = 0; i < source.size(); i++) {
			
			candidate = i;
			saveStep();
			
			C candidate = source.get(i);
			
			if(result == null
					&& candidate != null) {

				smallest = i;
				result = candidate;

				saveStep();

			}else if(result != null 
					&& candidate != null
					&& candidate.compareTo(result) < 0) {
				
				smallest = i;
				result = candidate;

				saveStep();
			}

		}
		
		return result;
	}


}
