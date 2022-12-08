package org.agoncal.quarkus.starting;

import org.jboss.logging.Logger;
import org.jboss.logging.annotations.Param;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookRepository repository;

    @Inject
    Logger logger;

    @GET
    public List<Book> getAllBooks() {
        logger.info("Return all books");
        return repository.getAllBooks();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countAllBooks(){
        logger.info("Returns number of books");
        return repository.countAllBooks();
    }

    @GET
    @Path("{id}")
    public Optional<Book> getBook(@PathParam("id") int id){
        logger.info("Return a single book with id " + id);
        return repository.getBook(id);
    }

}