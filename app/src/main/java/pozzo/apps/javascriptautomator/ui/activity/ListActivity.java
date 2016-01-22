package pozzo.apps.javascriptautomator.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import pozzo.apps.javascriptautomator.R;

/**
 * Entry list.
 *
 * @author Luiz Gustavo Pozzo
 * @since 21/01/2016
 */
public class ListActivity extends AppCompatActivity {

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
	}

	/**
	 * Request a new entry.
	 */
	private void newEntry() {
		Intent intent = new Intent(ListActivity.this, EntryActvity.class);
		startActivity(intent);
	}
}
