package com.company.spring5webapp.bootstrap;

import com.company.spring5webapp.model.Author;
import com.company.spring5webapp.model.Book;
import com.company.spring5webapp.repository.AuthorRepository;
import com.company.spring5webapp.repository.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init() {
        Author dan = new Author("Dan", "Braun");
        Book inferno = new Book("Inferno", "123456","publisher");
        dan.getBooks().add(inferno);
        inferno.getAuthors().add(dan);
        authorRepository.save(dan);
        bookRepository.save(inferno);

        Author cay = new Author("Cay", "Horstman");
        Book javaBook = new Book("Java", "10101010","library");
        cay.getBooks().add(javaBook);
        javaBook.getAuthors().add(cay);
        authorRepository.save(cay);
        bookRepository.save(javaBook);
    }
}
