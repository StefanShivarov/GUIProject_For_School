import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookStore {
	
	private ArrayList <Book>books;
	private ArrayList<String> bookTitles;
	private ArrayList<Book> boughtBooks;
	private double sum;
	
	public BookStore() {
		books = new ArrayList<>();
		bookTitles = new ArrayList<>();
	}
	
	public Book getBookByTitle(String title) {

		for(Book b : this.books) {
			if(b.getTitle().equals(title)) {
				return b;
			}
		}
		return null;
	}
	
	public void fillBooksInfo(File booksFile, File pricesFile) {
		try {
			Scanner booksReader = new Scanner(booksFile);
			Scanner pricesReader = new Scanner(pricesFile);
			while(booksReader.hasNextLine()||pricesReader.hasNextLine()) {
				String line = booksReader.nextLine();
				String[]booksArr = line.split(" - ");
				String[]pricesArr = pricesReader.nextLine().split(" - ");
				String title = booksArr[0];
				String author = booksArr[1];
				double price = Double.parseDouble(pricesArr[2]);
				this.books.add(new Book(title, author, price));
				this.bookTitles.add(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getBookTitles(){
		return this.bookTitles;
	}
	
	public ArrayList<Book> getBoughtBooks(){
		return this.boughtBooks;
	}
	
	
	
	
}
