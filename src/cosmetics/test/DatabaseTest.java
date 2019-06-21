package cosmetics.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cosmetics.business.BusinessException;
import cosmetics.business.database.Database;

public class DatabaseTest {

	@Test
	public void testDatabase() throws BusinessException {
		Database database = new Database();
		
		// Check that all insertions happened correctly
		assertTrue(database.getCategories().size() == 6);
		assertTrue(database.getGroups().size() == 3);
		assertTrue(database.getUsers().size() == 10);
		assertTrue(database.getProducts().size() == 11);
		
		// Check that SPF B and SPF C are allocated and SPF A not
		assertFalse(database.getGroup("SPF A").isAllocated());
		assertTrue(database.getGroup("SPF B").isAllocated());
		assertTrue(database.getGroup("SPF C").isAllocated());
	}
	
}
