package RESTful.library.utility;

import RESTful.library.resources.DataBaseSQLite;

public class Utility {
	private static int MAX_COUNT = 10;
	
	public boolean checkAdd(DataBaseSQLite bookStroe) {
		int count = 0;
		count = bookStroe.getCount();
		if (count > MAX_COUNT)
			return false;
		return true;
	}
}
