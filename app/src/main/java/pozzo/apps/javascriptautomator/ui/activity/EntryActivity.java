package pozzo.apps.javascriptautomator.ui.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
 * TODO Delete option
 */
public class EntryActivity extends AppCompatActivity {
	public static final String PARAM_ENTRY_ID = "entry";

	private EditText eCommands;
	private EditText eName;
	private EditText eAddress;
	private Button bDone;

	private Entry entry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_entry);
		eCommands = (EditText) findViewById(R.id.eCommands);
		eName = (EditText) findViewById(R.id.eName);
		eAddress = (EditText) findViewById(R.id.eAddress);
		bDone = (Button) findViewById(R.id.bDone);

		bDone.setOnClickListener(save);

		if(handleParam(savedInstanceState))
			showEntry(entry);
	}

	/**
	 * Saves the entry to our local storage.
	 */
	private void saveEntry() {
		new AsyncTask<Void, Void, Void>() {
			Entry entry;
			ProgressDialog progressDialog;

			@Override
			protected void onPreExecute() {
				progressDialog = ProgressDialog.show(
						EntryActivity.this, "", getString(R.string.loading));
				entry = updateEntry();
			}

			@Override
			protected Void doInBackground(Void... params) {
				if(!entry.isEmpty())
					new EntryBusiness().save(entry);
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				progressDialog.dismiss();
				finish();
			}
		}.execute();
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

	@Override
	public void onBackPressed() {
		saveEntry();
	}

	/**
	 * When user is done entring data.
	 */
	private View.OnClickListener save = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			saveEntry();
		}
	};
}
