package org.agoncal.quarkus.starting;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {
    @ConfigProperty(name = "books.genre", defaultValue = "Educational")
    String genre;
    public List<Book> getAllBooks() {
        return List.of(
                new Book(1, "Understanding Quarkus","Antonio", 1999, genre),
                new Book(2, "Thinking Quarkus","Antonio", 2000, genre),
                new Book(3, "Effective Quarkus","Antonio", 2022, genre)
        );
    }

    public int countAllBooks(){
        return getAllBooks().size();
    }

    public Optional<Book> getBook(int id){
        return getAllBooks().stream().filter(book -> book.id == id).findFirst();
    }

}