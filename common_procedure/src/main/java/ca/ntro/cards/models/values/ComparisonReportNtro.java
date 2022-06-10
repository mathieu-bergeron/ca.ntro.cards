package ca.ntro.cards.models.values;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class ComparisonReportNtro implements ComparisonReport {

	private static final long serialVersionUID = -349958575428922109L;
	
	private List<ErrorReport> errors = new ArrayList<>();

	@Override
	public boolean isSolution() {
		return errors.isEmpty();
	}

	@Override
	public Stream<ErrorReport> errors() {
		return new StreamNtro<ErrorReport>() {
			@Override
			public void forEach_(Visitor<ErrorReport> visitor) throws Throwable {
				for(ErrorReport error : errors) {
					visitor.visit(error);
				}
			}
		};
	}

	@Override
	public void addError(String message) {
		errors.add(new ErrorReportNtro(message));
	}

}
