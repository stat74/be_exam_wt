
package score.data;

public class DataLine {

	private String id;
	private String first;
	private String middle;
	private String last;
	private String phone;
	
	public DataLine() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getMiddle() {
		return middle;
	}
	public void setMiddle(String middle) {
		this.middle = middle;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Person convertToPerson() {
		Name n = new Name(first, middle, last);
		return new Person(Integer.parseInt(id), n, phone);
	}
	
}
