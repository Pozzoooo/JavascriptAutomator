package pozzo.apps.javascriptautomator.core.init;

import java.util.ArrayList;
import java.util.List;

import pozzo.apps.javascriptautomator.business.SuggestionBusiness;
import pozzo.apps.javascriptautomator.model.Suggestion;

/**
 * This class creates our initial suggestions to the user.
 *
 * @author Luiz Gustavo Pozzo
 * @since 06/02/16
 */
public class SuggestionsCreator {

	/**
	 * @return True if we need to create the common suggestions.
	 */
	public boolean shouldCreateCommonSuggestions() {
		List<Suggestion> savedSuggestions = new SuggestionBusiness().getAll();
		return savedSuggestions == null || savedSuggestions.isEmpty();
	}

	/**
	 * If this increase, I will need a better check on shouldCreate.
	 *
	 * @return Create some common suggestions.
	 */
	public List<Suggestion> create() {
		ArrayList<Suggestion> suggestions = new ArrayList<>();

		//TODO increase our suggestion database
		Suggestion suggestion = new Suggestion("submit",
				"javascript:document.getElementById(\"\").submit()", 36);

		suggestions.add(suggestion);

		return suggestions;
	}
}
