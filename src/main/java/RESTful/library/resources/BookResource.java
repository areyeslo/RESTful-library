package RESTful.library.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
		List<Books> books = bookService.getAllBooks();		
		return books;
	}
	
	@GET
	@Path("/{bookID}")
	@Produces(MediaType.APPLICATION_XML)
	public Books getBook(@PathParam("bookID") int ID){
		return bookService.getBook(ID);
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Books addBook(Books book) {
		if (book != null) 
			return bookService.addBook(book);
		return null;
	}
	
	@DELETE
	@Path("/{bookID}")
	@Produces(MediaType.APPLICATION_XML)
	public String removeBook(@PathParam("bookID") int ID){
		boolean removed= bookService.deleteBook(ID);
		String answer="Removed successfully";
		if(removed = false){
			answer="Not removed";
		}
		return answer;
	}
	
	@GET
	@Path("/name/{bookName}")
	@Produces(MediaType.APPLICATION_XML)
	public Books findBook(@PathParam("bookName") String name) {
		if (name != null)
			return bookService.findBook(name);
		return null;
		
	}
}
