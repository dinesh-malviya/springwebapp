package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
@Component
public class BootStrapData implements CommandLineRunner {
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Publisher publisher = new Publisher();
		publisher.setName("Malviya Publishing");
		publisher.setState("Karnataka");
		publisher.setCity("Bangalore");
		publisherRepository.save(publisher);
		System.out.println("Publisher count: "+publisherRepository.count());
		Author author = new Author("Dinesh", "Malviya");
		Book book = new Book("System design", "12345");

		author.getBooks().add(book);
		book.setPublisher(publisher);
		publisher.getBooks().add(book);
		
		authorRepository.save(author);
		bookRepository.save(book);
		
		Author author2 = new Author("Priya", "Khedekar");
		Book book2 = new Book("Product Managment", "67890");
		book2.setPublisher(publisher);
		author2.getBooks().add(book2);
		publisher.getBooks().add(book2);

		authorRepository.save(author2);
		bookRepository.save(book2);
		publisherRepository.save(publisher);
		
		System.out.println("BootStrap Data Started");
		System.out.println("Number of Bookes: " + bookRepository.count());
		System.out.println("Publisher Number of Bookes: " + publisher.getBooks().size());
	}

}
