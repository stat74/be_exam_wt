
package score.data;

public class Name {

	private String first;
	private String middle;
	private String last;
	
	public Name() {
		
	}
	
	public Name(String f, String m, String l) {
		setFirst(f);
		setMiddle(m);
		setLast(l);
	}
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String firstName) {
		this.first = firstName;
	}
	public String getMiddle() {
		return middle;
	}
	public void setMiddle(String middleName) {
		this.middle = middleName;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String lastName) {
		this.last = lastName;
	}
	
}
