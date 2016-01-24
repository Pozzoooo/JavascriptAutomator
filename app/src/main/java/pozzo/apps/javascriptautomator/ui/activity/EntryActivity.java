package pozzo.apps.javascriptautomator.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import pozzo.apps.javascriptautomator.R;
import pozzo.apps.javascriptautomator.business.EntryBusiness;
import pozzo.apps.javascriptautomator.model.Entry;

/**
 * Where you can view/edit an entry.
 *
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16.
 *
 * TODO Done discand interface
 * TODO rotation support
 */
public class EntryActivity extends AppCompatActivity {
	public static final String PARAM_ENTRY_ID = "entry";

	private EditText eCommands;
	private EditText eName;
	private EditText eAddress;

	private Entry entry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_entry);
		eCommands = (EditText) findViewById(R.id.eCommands);
		eName = (EditText) findViewById(R.id.eName);
		eAddress = (EditText) findViewById(R.id.eAddress);

		if(handleParam(getIntent().getExtras()))
			showEntry(entry);
	}

	/**
	 * Saves the entry to our local storage.
	 */
	private void saveEntry() {
		//TODO async
		Entry entry = updateEntry();
		new EntryBusiness().save(entry);
	}

	/**
	 * Update our entry object with view data.
	 */
	private Entry updateEntry() {
		if(entry == null)
			entry = new Entry();
		entry.setCommands(eCommands.getText().toString());
		entry.setAddress(eAddress.getText().toString());
		entry.setName(eName.getText().toString());
		return entry;
	}

	/**
	 * Shows given entry to the user.
	 */
	private void showEntry(Entry entry) {
		eCommands.setText(entry.getCommands());
		eAddress.setText(entry.getAddress());
		eName.setText(entry.getName());
	}

	/**
	 * @param extras to be taken.
	 * @return false if nothing taken.
	 */
	private boolean handleParam(Bundle extras) {
		if(extras == null || !extras.containsKey(PARAM_ENTRY_ID))
			return false;

		long entryId = extras.getLong(PARAM_ENTRY_ID);
		entry = new EntryBusiness().get(entryId);
		return entry != null;
	}
}
