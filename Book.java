
public class Book {
	
	private String title;
	private double price;
	private String author;
	

	public Book(String title, String author, double price) {
		this.setTitle(title);
		this.setAuthor(author);
		this.setPrice(price);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public double getPrice() {
		return this.price;
	}
	@Override
	public String toString() {
		
		return String.format("\"%s\" by %s",this.getTitle(), this.getAuthor());
	}
}
