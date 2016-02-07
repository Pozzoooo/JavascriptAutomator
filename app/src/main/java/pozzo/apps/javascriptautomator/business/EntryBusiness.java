package pozzo.apps.javascriptautomator.business;

import com.activeandroid.query.Select;

import java.util.List;

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
		//We enforce a protocol before saving, so user can see it when editing
		if(!entry.getAddress().contains("://"))
			entry.setAddress("http://" + entry.getAddress());
		entry.save();
	}

	/**
	 * Related Entry by its local id.
	 */
	public Entry get(long id) {
		return Entry.load(Entry.class, id);
	}

	/**
	 * @return All entries saed on database.
	 */
	public List<Entry> getAll() {
		return new Select().from(Entry.class).orderBy(Entry.Col.NAME).execute();
	}

	/**
	 * @return Commands isolated.
	 */
	public String[] parseCommands(Entry entry) {
		String commands = entry.getCommands();
		if(commands == null)
			return null;

		commands = commands.replaceAll("[\n]{2,}", "\n");
		return commands.split("\n");
	}
}
