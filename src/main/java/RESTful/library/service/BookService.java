package RESTful.library.service;

import java.util.List;

import RESTful.library.model.Books;
import RESTful.library.resources.DataBaseSQLite;

public class BookService {
	
	private static DataBaseSQLite bookStore = new DataBaseSQLite();
	
	public Books addBook(Books book) {
		try {
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
	
	public Books getBook(int ID) {
		// TODO Auto-generated method stub
		return bookStore.get(ID);
	}
	
	public boolean deleteBook(int id){
		return bookStore.delete(id);
	}


		
	

}