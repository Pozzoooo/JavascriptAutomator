package pozzo.apps.javascriptautomator.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
 * TODO Add some help button as sugestions
 */
public class EntryActivity extends AppCompatActivity {
	public static final String PARAM_ENTRY_ID = "entryId";
	public static final String PARAM_ENTRY = "entry";
	public static final String RES_DELETED_ENTRY = "delEntry";

	private EditText eCommands;
	private EditText eName;
	private EditText eAddress;

	private Entry entry;
	private EntryBusiness entryBusiness;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_entry);
		eCommands = (EditText) findViewById(R.id.eCommands);
		eName = (EditText) findViewById(R.id.eName);
		eAddress = (EditText) findViewById(R.id.eAddress);

		entryBusiness = new EntryBusiness();
		if(handleParam(savedInstanceState))
			showEntry(entry);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.entry, menu);

		if(entry == null || entry.getId() == 0)
			menu.findItem(R.id.mDelete).setVisible(false);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.mDone:
				saveEntry();
				return true;
			case R.id.mDiscard:
				finish();
				return true;
			case R.id.mDelete:
				deleteEntry(entry);
				return true;
			default:
				return false;
		}
	}

	/**
	 * Saves the entry to our local storage.
	 */
	private void saveEntry() {
		new AsyncTask<Void, Void, Void>() {
			ProgressDialog progressDialog;

			@Override
			protected void onPreExecute() {
				progressDialog = ProgressDialog.show(
						EntryActivity.this, "", getString(R.string.loading));
				entry = updateEntry(entry);
			}

			@Override
			protected Void doInBackground(Void... params) {
				if(!entry.isEmpty())
					entryBusiness.save(entry);
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
	private Entry updateEntry(Entry entry) {
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
	 * Deletes given entry.
	 */
	private void deleteEntry(Entry entry) {
		if(entry != null) {
			entry.delete();
			Intent data = new Intent();
			data.putExtra(RES_DELETED_ENTRY, entry);
			setResult(RESULT_OK, data);
		}
		finish();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		if(entry != null)
			outState.putParcelable(PARAM_ENTRY, entry);
		super.onSaveInstanceState(outState);
	}

	/**
	 * @param savedInstanceState to be taken.
	 * @return false if nothing taken.
	 */
	private boolean handleParam(Bundle savedInstanceState) {
		if(savedInstanceState == null)
			savedInstanceState = getIntent().getExtras();

		if(savedInstanceState == null || (!savedInstanceState.containsKey(PARAM_ENTRY)
					&& !savedInstanceState.containsKey(PARAM_ENTRY_ID)))
			return false;

		if(savedInstanceState.containsKey(PARAM_ENTRY))
			entry = savedInstanceState.getParcelable(PARAM_ENTRY);

		if(entry == null) {
			long entryId = savedInstanceState.getLong(PARAM_ENTRY_ID);
			entry = entryBusiness.get(entryId);
		}
		return entry != null;
	}

	@Override
	public void onBackPressed() {
		Entry editedEntry = updateEntry(new Entry());
		if(editedEntry.equals(entry)) {
			super.onBackPressed();
			return;
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setMessage(R.string.quitSaving)
				.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//Does nothing... just stays on the screen
					}
				})
				.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EntryActivity.super.onBackPressed();
					}
				}).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						saveEntry();
					}
				});
		builder.create().show();
	}
}
