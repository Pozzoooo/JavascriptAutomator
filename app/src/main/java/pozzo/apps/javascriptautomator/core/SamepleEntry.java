package pozzo.apps.javascriptautomator.core;

import android.content.Context;

import java.util.List;

import pozzo.apps.javascriptautomator.R;
import pozzo.apps.javascriptautomator.business.EntryBusiness;
import pozzo.apps.javascriptautomator.model.Entry;

/**
 * This will creates example to the user.
 *
 * @author Luiz Gustavo Pozzo
 * @since 31/01/2016.
 */
public class SamepleEntry {
	private EntryBusiness business;

	public SamepleEntry() {
		business = new EntryBusiness();
	}

	/**
	 * @return If false, you should not create an example.
	 */
	public boolean shouldCreateExample() {
		List<Entry> allEntries = business.getAll();
		return allEntries == null || allEntries.isEmpty();
	}

	/**
	 * And inserts a brand new example.
	 */
	public Entry create(Context context) {
		Entry entry = new Entry();
		entry.setAddress("http://google.com");
		entry.setName(context.getString(R.string.sampleTitle));

		String command = ""
				+ "javascript:document.getElementById(\"lst-ib\").value = \"Luiz Gustavo Pozzo\";"
				+ "javascript:document.getElementById(\"tsf\").submit();";
		entry.setCommands(command);
		return entry;
	}
}
