package cosmetics.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cosmetics.business.database.Database;

public class DatabaseTest {

	@Test
	public void testDatabase() throws Exception {
		Database database = new Database();
		
		assertTrue(database.getCategories().size() == 6);
		assertTrue(database.getGroups().size() == 3);
		assertTrue(database.getUsers().size() == 10);
		assertTrue(database.getProducts().size() == 11);
	}
	
}
