
package score.validation;

public class CSVParsingError {

	private int lineNumber;
	private String message;
	
	public CSVParsingError(int l, String m) {
		setLineNumber(l);
		setMessage(m);
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString() {
		return lineNumber + "," + message;
	}
	
}
