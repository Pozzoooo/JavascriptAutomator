package pozzo.apps.javascriptautomator.business;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.List;

import pozzo.apps.javascriptautomator.model.Suggestion;

/**
 * This handle the Suggestion business.
 *
 * @author Luiz Gustavo Pozzo
 * @since 06/02/16
 *
 * TODO add position aware
 */
public class SuggestionBusiness {

	/**
	 * @return All saved suggestions.
	 */
	public List<Suggestion> getAll() {
		return new Select().from(Suggestion.class).execute();
	}

	/**
	 * Saves the given suggestion.
	 */
	public void save(Suggestion... suggestions) {
		if(suggestions == null || suggestions.length == 0)
			return;

		ActiveAndroid.beginTransaction();
		for(Suggestion it : suggestions)
			it.save();
		ActiveAndroid.setTransactionSuccessful();
		ActiveAndroid.endTransaction();
	}
}
