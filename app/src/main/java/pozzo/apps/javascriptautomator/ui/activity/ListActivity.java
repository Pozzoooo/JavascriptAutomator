package pozzo.apps.javascriptautomator.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pozzo.apps.javascriptautomator.R;
import pozzo.apps.javascriptautomator.business.EntryBusiness;
import pozzo.apps.javascriptautomator.model.Entry;
import pozzo.apps.javascriptautomator.ui.adapter.EntryAdapter;

/**
 * Entry list.
 *
 * @author Luiz Gustavo Pozzo
 * @since 21/01/2016
 *
 * TODO Fix list layout
 * TODO Add some click feedback
 */
public class ListActivity extends AppCompatActivity {
	private static final int ST_EDIT_ENTRY = 0x18;

	private EntryBusiness entryBusiness;

	private EntryAdapter adapter;
	private RecyclerView rvList;
	private ViewGroup vgMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				editEntry(null);
			}
		});

		entryBusiness = new EntryBusiness();
		vgMain = (ViewGroup) findViewById(R.id.vgMain);
	}

	@Override
	protected void onStart() {
		super.onStart();
		loadList();
	}

	/**
	 * Request a new entry.
	 *
	 * @param entry If null, request a new entry =].
	 */
	private void editEntry(Entry entry) {
		Intent intent = new Intent(ListActivity.this, EntryActivity.class);
		if(entry != null)
			intent.putExtra(EntryActivity.PARAM_ENTRY_ID, entry.getId());
		startActivityForResult(intent, ST_EDIT_ENTRY);
	}

	/**
	 * Loads and display our saved entries.
	 */
	private void loadList() {
		new AsyncTask<Void, Void, List<Entry>>() {
			@Override
			protected List<Entry> doInBackground(Void... params) {
				return entryBusiness.getAll();
			}

			@Override
			protected void onPostExecute(List<Entry> entries) {
				if(isFinishing())
					return;

				if(rvList == null) {
					rvList = (RecyclerView) findViewById(R.id.rvList);
					rvList.setLayoutManager(new LinearLayoutManager(ListActivity.this));
				}
				if(adapter == null)
					adapter = new EntryAdapter(onEntryClick, onLongEntryClick);

				adapter.setEntries(entries);
				rvList.setAdapter(adapter);
			}
		}.execute();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case ST_EDIT_ENTRY:
				if(data == null)
					break;
				Bundle extra = data.getExtras();
				if(extra == null || !extra.containsKey(EntryActivity.RES_DELETED_ENTRY))
					break;
				Entry deletedEntry = extra.getParcelable(EntryActivity.RES_DELETED_ENTRY);
				secondChanceSnackDeletion(deletedEntry);
				break;
			default:
				super.onActivityResult(requestCode, resultCode, data);
		}
	}

	/**
	 * Gives user a second chance to undo deleted entry.
	 */
	private void secondChanceSnackDeletion(final Entry deletedEntry) {
		if(deletedEntry == null)
			return;

		Snackbar.make(vgMain, R.string.deleted, Snackbar.LENGTH_LONG)
				.setAction(R.string.undo, new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						deletedEntry.save();
						loadList();
					}
				}).show();
	}

	/**
	 * Users wants to do something with the entry...
	 */
	private View.OnClickListener onEntryClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Entry entry = (Entry) v.getTag();
			if(entry != null) {
				Intent intent = new Intent(ListActivity.this, RunnerActivity.class);
				intent.putExtra(RunnerActivity.PARAM_ENTRY_ID, entry.getId());
				startActivity(intent);
			}
		}
	};

	/**
	 * To not depends on actionMode (because of api 7) we use long as direct context menu.
	 */
	private View.OnLongClickListener onLongEntryClick = new View.OnLongClickListener() {
		@Override
		public boolean onLongClick(View v) {
			Entry entry = (Entry) v.getTag();
			if(entry != null) {
				editEntry(entry);
				return true;
			}
			return false;
		}
	};
}
