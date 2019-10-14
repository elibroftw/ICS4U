package associations;

import java.util.ArrayList;
import java.util.List;

public class Author {
	private String name;
	private String email;
	private char gender;
	private Publisher publisher;
	private List<Book> books;
	
	public Author(String name, String email, char gender, Publisher publisher){
		this.name = name;
		this.email = email;
		this.gender = Character.toUpperCase(gender);
		this.publisher = publisher;
		publisher.addAuthor(this);
		books = new ArrayList<>();
	}
	public String getName(){
		return name;
	}
	public String getEmail() {
		return email;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher newPublisher) {
		publisher = newPublisher;
	}
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	public char getGender() {
		return gender;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void writeBook(Book newBook) {
		books.add(newBook);
	}
	@Override
	public String toString() {
		String books = "";
		for (Book book : this.books) {
			books += book.getName()+" ";
		}
		return "Author[name: " + name + ", email: " + email + ", gender: " + gender + ", publisher=" + publisher.getName() + ", books: " + books + "]";
	}
}
