package pozzo.apps.javascriptautomator.core;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

import java.util.List;

import pozzo.apps.javascriptautomator.business.SuggestionBusiness;
import pozzo.apps.javascriptautomator.core.init.SampleEntryCreator;
import pozzo.apps.javascriptautomator.core.init.SuggestionsCreator;
import pozzo.apps.javascriptautomator.model.Suggestion;

/**
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16.
 *
 * TODO Test what?!
 */
public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		ActiveAndroid.initialize(this);

		/* Trigger this on db creation would be probably a better approach, but I dont think I can
		 		do it with AA.
		   And if you know a better way to avoid this IO, pleas tell me.

		   TODO Maybe in background?
		*/
		SampleEntryCreator sampleEntryCreator = new SampleEntryCreator();
		if(sampleEntryCreator.shouldCreateSample())
			sampleEntryCreator.create(this).save();
		checkSuggestions();
	}

	/**
	 * Check suggestions to be saved.
	 */
	private void checkSuggestions() {
		SuggestionsCreator suggestionsCreator = new SuggestionsCreator();
		if(suggestionsCreator.shouldCreateCommonSuggestions()) {
			SuggestionBusiness suggestionBusiness = new SuggestionBusiness();
			List<Suggestion> suggestions = suggestionsCreator.create();
			suggestionBusiness.save(suggestions.toArray(new Suggestion[suggestions.size()]));
		}
	}
}
