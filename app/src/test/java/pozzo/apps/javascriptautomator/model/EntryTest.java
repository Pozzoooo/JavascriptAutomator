package pozzo.apps.javascriptautomator.model;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Just making sure our entity is working as expected.
 *
 * @author Luiz Gustavo Pozzo
 * @since 08/02/2016.
 */
public class EntryTest {

	@Test
	public void testIsEmpty() throws Exception {
		Entry entry = Mockito.mock(Entry.class);
		Mockito.doCallRealMethod().when(entry).isEmpty();
		assertTrue(entry.isEmpty());
	}

	@Test
	public void testIsNotEmpty() throws Exception {
		Entry entry = Mockito.mock(Entry.class);
		Mockito.doCallRealMethod().when(entry).setName(Mockito.anyString());
		Mockito.doCallRealMethod().when(entry).isEmpty();

		entry.setName("Nome");
		assertFalse(entry.isEmpty());
	}
}
