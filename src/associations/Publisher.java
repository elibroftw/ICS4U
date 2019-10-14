package associations;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
	private List<Author> authors;
	private String name;
	private String address;
	private double revenue;
	private List<Book> books;

	public Publisher(String name, String address) {
		this(name, address, new ArrayList<Author>());
	}

	public Publisher(String name, String address, List<Author> authors) {
		this.name = name;
		this.address = address;
		this.authors = authors;
		this.revenue = 0;
		this.books = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public List<Book> getBooks() {
		return books;
	}

	public double getRevenue() {
		return revenue;
	}

	public boolean addAuthor(Author newAuthor) {
		if (authors.contains(newAuthor)) {
			return false;
		}
		authors.add(newAuthor);
		return true;
	}

	public boolean addBook(Book newBook) {
		if (books.contains(newBook)) {
			return false;
		}
		books.add(newBook);
		return true;
	}
	
	/**
	 * 
	 * @param book 
	 * @param qty int
	 * @return profit double
	 */
	public double sellBooks(Book book, int qty) {
		double profit = book.getPrice() * qty;
		revenue += profit;
		return profit;
	}
	
	@Override
	public String toString() {
		String books = "";
		for (Book book : this.books) {
			books += book.getName()+" ";
		}
		String authors = "";
		for (Author author : this.authors) {
			authors += author.getName()+" ";
		}
		return "Publisher[name=" + name + ", address=" + address + ", revenue=" + revenue + ", authors=" + authors
				+ ", books=" + books + "]";
	}
}
