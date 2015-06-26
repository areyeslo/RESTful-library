package RESTful.library.service;

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

}