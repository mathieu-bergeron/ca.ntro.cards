package ca.ntro.cards.models.values;

import java.io.Serializable;

import ca.ntro.app.models.Value;
import ca.ntro.core.stream.Stream;

public interface ComparisonReport extends Value, Serializable {
	
	boolean isSolution();
	
	Stream<ErrorReport> errors();

	static ComparisonReport emptyReport() {
		return null;
	}


}
