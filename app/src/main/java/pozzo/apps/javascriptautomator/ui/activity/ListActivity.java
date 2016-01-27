package pozzo.apps.javascriptautomator.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
 * TODO edit/delete entry
 */
public class ListActivity extends AppCompatActivity {
	private EntryBusiness entryBusiness;

	private EntryAdapter adapter;
	private RecyclerView rvList;

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
				newEntry();
			}
		});

		entryBusiness = new EntryBusiness();
	}

	@Override
	protected void onStart() {
		super.onStart();
		loadList();
	}

	/**
	 * Request a new entry.
	 */
	private void newEntry() {
		Intent intent = new Intent(ListActivity.this, EntryActivity.class);
		startActivity(intent);
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
					adapter = new EntryAdapter(onEntryClick);

				adapter.setEntries(entries);
				rvList.setAdapter(adapter);
			}
		}.execute();
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
}
