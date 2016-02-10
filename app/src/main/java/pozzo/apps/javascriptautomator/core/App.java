package pozzo.apps.javascriptautomator.core;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.activeandroid.ActiveAndroid;

import java.util.List;

import pozzo.apps.javascriptautomator.business.SuggestionBusiness;
import pozzo.apps.javascriptautomator.core.init.SampleEntryCreator;
import pozzo.apps.javascriptautomator.core.init.SuggestionsCreator;
import pozzo.apps.javascriptautomator.model.Suggestion;

/**
 * Well, this is the start point of everything...
 *
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16.
 *
 * TODO add mint
 */
public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		ActiveAndroid.initialize(this);
		initializationChecker(this);
	}

	/**
	 * Check if our system are good to go!
	 * With all basic data to our user!
	 *
	 * As our load list also runs on an AsyncTask, it will be loaded only after it finish this
	 * 	initialization.
	 */
	private void initializationChecker(final Context context) {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				checkSampleEntry(context);
				checkSuggestions();
				return null;
			}
		}.execute();
	}

	/**
	 * Check the need to insert a sample entry.
	 * @param context so we can save.
	 */
	private void checkSampleEntry(Context context) {
		SampleEntryCreator sampleEntryCreator = new SampleEntryCreator();
		if(sampleEntryCreator.shouldCreateSample())
			sampleEntryCreator.create(context).save();
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
