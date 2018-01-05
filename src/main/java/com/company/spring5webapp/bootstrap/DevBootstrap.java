package com.company.spring5webapp.bootstrap;

import com.company.spring5webapp.model.Author;
import com.company.spring5webapp.model.Book;
import com.company.spring5webapp.model.Publisher;
import com.company.spring5webapp.repository.AuthorRepository;
import com.company.spring5webapp.repository.BookRepository;
import com.company.spring5webapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init() {
        Author dan = new Author("Dan", "Braun");
        Publisher bookPublisher = new Publisher("BookPublisher");
        Book inferno = new Book("Inferno", "123456",bookPublisher);
        dan.getBooks().add(inferno);
        inferno.getAuthors().add(dan);
        authorRepository.save(dan);
        publisherRepository.save(bookPublisher);
        bookRepository.save(inferno);

        Author cay = new Author("Cay", "Horstman");
        Publisher libraryPublisher = new Publisher("LibraryPublisher");
        Book javaBook = new Book("Java", "10101010",libraryPublisher);
        cay.getBooks().add(javaBook);
        javaBook.getAuthors().add(cay);
        authorRepository.save(cay);
        publisherRepository.save(libraryPublisher);
        bookRepository.save(javaBook);
    }
}
