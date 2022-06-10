package ca.ntro.cards.models.values;

public class ErrorReportNtro implements ErrorReport {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public ErrorReportNtro(String message) {
		this.message = message;
	}
	
}
