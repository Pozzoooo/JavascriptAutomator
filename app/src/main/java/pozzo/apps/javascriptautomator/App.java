package pozzo.apps.javascriptautomator;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16.
 *
 * TODO Fix theme
 * TODO Add some help button as sugestions
 * TODO Add an example
 * TODO Give names to screens
 */
public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		ActiveAndroid.initialize(this);
	}
}
