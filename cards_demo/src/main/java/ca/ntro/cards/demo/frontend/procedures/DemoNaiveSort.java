package ca.ntro.cards.demo.frontend.procedures;

import ca.ntro.cards.demo.models.DemoCardsModel;
import ca.ntro.cards.frontend.procedures.NaiveSort;

public class DemoNaiveSort extends NaiveSort {


	public <C extends Comparable<C>> void sort(C[] sourceArray, C[] sortedArray) {
		setIndexOfNextEmptySpace(0);
		registerSimpleOperation();
		
		for(int i = 0; i < sourceArray.length; i++) {

			findIndexOfSmallestElement(sourceArray);
			
			C smallestElement = sourceArray[getIndexOfSmallestElement()];
			
			sortedArray[getIndexOfNextEmptySpace()] = smallestElement;
			
			sourceArray[getIndexOfSmallestElement()] = null;
			
			incrementIndexOfNextEmptySpace();

			registerSimpleOperation();
		}
	}


	private <C extends Comparable<C>>void findIndexOfSmallestElement(C[] sourceArray) {
		setIndexOfSmallestElement(-1);

		C currentSmallest = null;

		for(int i = 0; i < sourceArray.length; i++) {
			
			setIndexOfCandidateSmallestElement(i);
			registerSimpleOperation();
			
			C candidate = sourceArray[i];
			
			if(currentSmallest == null
					|| candidate.compareTo(currentSmallest) < 0) {
				
				currentSmallest = candidate;
				
				setIndexOfSmallestElement(i);
				registerSimpleOperation();
			}
		}
	}



}
