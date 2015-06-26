package RESTful.library.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import RESTful.library.service.BookService;
import RESTful.library.model.Books;


/** This class will implement all the request on the resource: 
 * GET
 * PUT
 * DELETE
 * POST
 */
@Path("/books")
public class BookResource {
    
	DataBaseSQLite db = new DataBaseSQLite();
	BookService bookService = new BookService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)	
	public List<Books> getBooks(){
		List<Books> books = db.query();		
		return books;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Books addBook(Books book) {
		return bookService.addBook(book);
	}
}
