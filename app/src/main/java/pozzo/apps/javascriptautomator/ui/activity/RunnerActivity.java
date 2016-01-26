package pozzo.apps.javascriptautomator.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import pozzo.apps.javascriptautomator.JavascriptRunner;
import pozzo.apps.javascriptautomator.R;
import pozzo.apps.javascriptautomator.business.EntryBusiness;
import pozzo.apps.javascriptautomator.model.Entry;

/**
 * Where the magic happens.
 *
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16.
 */
public class RunnerActivity extends AppCompatActivity {
	public static final String PARAM_ENTRY_ID = "entry";

	private WebView webView;
	private Entry entry;
	private JavascriptRunner javascriptRunner;
	private EntryBusiness entryBusiness;

	/**
	 * Run the entry!
	 */
	private void runEntry(Entry entry) {
		String[] commands = entryBusiness.parseCommands(entry);
		javascriptRunner.addAll(commands);
		javascriptRunner.start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_runner);

		webView = (WebView) findViewById(R.id.webView);
		javascriptRunner = new JavascriptRunner(webView);
		entryBusiness = new EntryBusiness();
		handleParam(getIntent().getExtras());
		//TODO add a log for empty param
		runEntry(entry);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		if(entry != null)
			outState.putLong(PARAM_ENTRY_ID, entry.getId());
		super.onSaveInstanceState(outState);
	}

	/**
	 * @param savedInstanceState to be taken.
	 * @return false if nothing taken.
	 */
	private boolean handleParam(Bundle savedInstanceState) {
		if(savedInstanceState == null)
			savedInstanceState = getIntent().getExtras();

		if(savedInstanceState == null || !savedInstanceState.containsKey(PARAM_ENTRY_ID))
			return false;

		long entryId = savedInstanceState.getLong(PARAM_ENTRY_ID);
		entry = new EntryBusiness().get(entryId);
		return entry != null;
	}
}
