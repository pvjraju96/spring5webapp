package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner  {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("Penguin");
        publisher.setAddressLine1("Add");
        publisher.setCity("Newyork");
        publisherRepository.save(publisher);
        Author steve = new Author("Steve", "Covey");
        Book book1 = new Book("7 Habits of highly effective people", "123123");
        steve.getBooks().add(book1);
        book1.getAuthor().add(steve);
        book1.setPublisher(publisher);
        authorRepository.save(steve);
        bookRepository.save(book1);

        Author rowling = new Author("Rowling", "JK");
        Book book2 = new Book("Harry Potter", "121212");
        rowling.getBooks().add(book2);
        book2.getAuthor().add(rowling);
        book2.setPublisher(publisher);
        authorRepository.save(rowling);
        bookRepository.save(book2);
        publisher.getBooks().add(book1);
        publisher.getBooks().add(book2);
        publisherRepository.save(publisher);
        System.out.println("Started in bootstrap");
        System.out.println("Number of Books : " + bookRepository.count());
        System.out.println("Number of Authors : " + authorRepository.count());
        System.out.println("Number of Publishers : "+ publisherRepository.count());
        System.out.println("Number of books of publisher : "+ publisher.getName()+" are :: "+ publisher.getBooks().size());
    }
}
