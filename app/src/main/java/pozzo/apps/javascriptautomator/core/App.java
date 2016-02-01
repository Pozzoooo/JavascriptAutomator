package pozzo.apps.javascriptautomator.core;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16.
 *
 * TODO Test what?!
 * TODO Translate to portuguese
 * TODO Create a launcher
 */
public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		ActiveAndroid.initialize(this);

		/* Trigger this on db creation would be probably a better approach, but I dont think I can
		 		do it with AA.
		   And if you know a better way to avoid this IO, pleas tell me.
		  */
		SamepleEntry samepleEntry = new SamepleEntry();
		if(samepleEntry.shouldCreateExample())
			samepleEntry.create(this).save();
	}
}
