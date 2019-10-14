package associations;

public class Main {

	public static void main(String[] args) {
		Publisher myPublisher = new Publisher("RBHS", "5151 New Street");
		Author myAuthor = new Author("A J", "AJ@gmail.com", 'M', myPublisher);
		Book myBook = new Book("How I made $6000 from selling books", myAuthor, 6000, "12391309213", myPublisher);
		System.out.println(myBook);
//		System.out.println(myAuthor);
//		System.out.println(myPublisher);
	}

}
