package RESTful.library.service;

import java.util.List;

import RESTful.library.model.Books;
import RESTful.library.resources.DataBaseSQLite;
import RESTful.library.utility.Utility;

public class BookService {
	
	private static DataBaseSQLite bookStore = new DataBaseSQLite();
	private static Utility control = new Utility();
	
	public Books addBook(Books book) {
		try {
			if (!control.checkAdd(bookStore))
				return null;

			int ID = bookStore.put(book.getName(), book.getAuthor(), book.getYear(), book.getPublisher());
			book.setId(ID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	
	public List<Books> getAllBooks(){
		List<Books> allBooks = bookStore.query();
		return allBooks;		
	}
	
	public List<Books> getAllBooksByYear(int year){
		List<Books> booksByYear = bookStore.queryByYear(year);
		return booksByYear;		
	}
	
	public List<Books> getAllBooksByAuthor(String author){
		List<Books> booksByAuthor = bookStore.queryByAuthor(author);
		return booksByAuthor;		
	}
	
	public List<Books> getAllBooksByPublisher(String publisher){
		List<Books> booksByPublisher = bookStore.queryByPublisher(publisher);
		return booksByPublisher;		
	}
	
	public Books getBook(int ID) {
		// TODO Auto-generated method stub
		return bookStore.queryByID(ID);
	}
	
	public boolean deleteBook(int id){
		return bookStore.delete(id);
	}
	
	public boolean deleteBook(String name){
		return bookStore.delete(name);
	}
	
	public boolean deleteBookYear(int year){
		return bookStore.deleteYear(year);
	}
	
	public boolean deleteBookAuthor(String author){
		return bookStore.deleteAuthor(author);
	}
	
	public boolean deleteBookPublisher(String publisher){
		return bookStore.deletePublisher(publisher);
	}

	public Books findBook(String name) {
		return bookStore.find(name);
	}
	
	

}