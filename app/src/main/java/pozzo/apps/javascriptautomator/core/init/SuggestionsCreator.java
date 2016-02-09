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

		suggestions.add(new Suggestion("getById",
				"javascript:document.getElementById(\"\")", 36));
		suggestions.add(new Suggestion("getByName",
				"javascript:document.getElementsByName(\"\")[0]", 39));
		suggestions.add(new Suggestion("getByClass",
				"javascript:document.getElementsByClassName(\"\")[0]", 44));
		suggestions.add(new Suggestion("submit", ".submit();", 10));
		suggestions.add(new Suggestion("click", ".click();", 9));
		suggestions.add(new Suggestion("setText", ".value=\"\";", 8));
		suggestions.add(new Suggestion("getTitle", "javascript:document.title", 25));

		return suggestions;
	}
}
