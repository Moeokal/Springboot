package com.example.springboot.BootStrap;

import com.example.springboot.Reopsitories.AuthorRepository;
import com.example.springboot.Reopsitories.BookRepository;
import com.example.springboot.Reopsitories.PublisherRepository;
import com.example.springboot.domain.Author;
import com.example.springboot.domain.Book;
import com.example.springboot.domain.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

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

        System.out.println("Started in Bootstrap");

        Publisher publisher=new Publisher();
        publisher.setCity("magdeburg");
        publisher.setName("moayad");
        publisher.setState("SachsenAnhalt");

        publisherRepository.save(publisher);

        System.out.println("Publishers: "+ publisherRepository.count());


        Author eric = new Author("Eric","Evans");
        Book ddd= new Book("Book1","3333333333");

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod","Johnson");
        Book dddd= new Book("Book2","3333333334");

        rod.getBooks().add(dddd);
        dddd.getAuthors().add(rod);

        dddd.setPublisher(publisher);
        publisher.getBooks().add(dddd);

        authorRepository.save(rod);
        bookRepository.save(dddd);
        publisherRepository.save(publisher);

        System.out.println("Books: "+ bookRepository.count());
        System.out.println("Publisher_Books: "+ publisher.getBooks().size());

    }
}
