package pozzo.apps.javascriptautomator.business;

import pozzo.apps.javascriptautomator.model.Entry;

/**
 * Business logic for our Entries.
 *
 * @author Luiz Gustavo Pozzo
 * @since 23/01/16.
 */
public class EntryBusiness {

	/**
	 * Saves a new entry to database.
	 */
	public void save(Entry entry) {
		entry.save();
	}

	/**
	 * Related Entry by its local id.
	 */
	public Entry get(long id) {
		return Entry.load(Entry.class, id);
	}
}
