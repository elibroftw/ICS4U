package associations;

public class Book {
	private String name;
	private Author author;
	private double price;
	private String isbn;
	private int qty;
	private Publisher publisher;

	public Book(String name, Author author, double price, String isbn, Publisher publisher) {
		this(name, author, price, isbn, publisher, 1);
	}

	public Book(String name, Author author, double price, String isbn, Publisher publisher, int qty) {
		this.name = name;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.publisher = publisher;
		this.qty = qty;
		publisher.addBook(this);
		author.writeBook(this);
	}

	public String getName() {
		return name;
	}

	public Author getAuthor() {
		return author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book[name=" + name + ", author=" + author.getName() + "publisher=" + publisher.getName() + ", isbn="
				+ isbn + ", price=$" + price + ", qty=" + qty + "]";
	}

}
