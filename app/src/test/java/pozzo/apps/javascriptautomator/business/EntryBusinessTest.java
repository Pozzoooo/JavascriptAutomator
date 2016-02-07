package pozzo.apps.javascriptautomator.business;

import org.junit.Test;
import org.mockito.Mockito;

import pozzo.apps.javascriptautomator.model.Entry;

import static org.junit.Assert.*;

/**
 * Test {@link EntryBusiness}.
 *
 * @author Luiz Gustavo Pozzo
 * @since 07/02/2016
 */
public class EntryBusinessTest {

	@Test
	public void testParseCommands() throws Exception {
		final String COMMAND_STRING = "\tfirst\n\nsecond   \nthird";
		final int EXPECTED_LENGTH = 3;

		EntryBusiness entryBusiness = new EntryBusiness();
		Entry entry = Mockito.mock(Entry.class);
		Mockito.when(entry.getCommands()).thenCallRealMethod();
		Mockito.doCallRealMethod().when(entry).setCommands(Mockito.anyString());
		entry.setCommands(COMMAND_STRING);
		String[] parsedCommands = entryBusiness.parseCommands(entry);

		assertEquals(EXPECTED_LENGTH, parsedCommands.length);
	}
}