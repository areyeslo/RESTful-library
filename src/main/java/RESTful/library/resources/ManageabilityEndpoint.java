package RESTful.library.resources;
import RESTful.library.resources.*;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import RESTful.library.model.Books;

@Path("BookResource.class")
public interface ManageabilityEndpoint {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public abstract List<Books> getBooks();

	@GET
	@Path("/{bookID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Books getBook(int ID);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Books addBook(Books book);

	@DELETE
	@Path("/{bookID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeBook(int ID);

	@DELETE
	@Path("/name/{bookName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeBook(String name);

	@DELETE
	@Path("/year/{bookYear}")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeBookYear(int year);

	@DELETE
	@Path("/author/{bookAuthor}")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeBookAuthor(String author);

	@DELETE
	@Path("/publisher/{bookPublisher}")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeBookPublisher(String publisher);

	@GET
	@Path("/name/{bookName}")
	@Produces(MediaType.APPLICATION_XML)
	public Books findBook(String name);

	@GET
	@Path("/year/{bookYear}")
	@Produces(MediaType.APPLICATION_JSON)	
	public List<Books> getBooksByYear(int year);

	@GET
	@Path("/author/{bookAuthor}")
	@Produces(MediaType.APPLICATION_JSON)	
	public List<Books> getBooksByAuthor(String author);

	@GET
	@Path("/publisher/{bookPublisher}")
	@Produces(MediaType.APPLICATION_JSON)	
	public List<Books> getBooksByPublisher(String publisher);

}