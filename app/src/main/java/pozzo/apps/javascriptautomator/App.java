package pozzo.apps.javascriptautomator;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16.
 */
public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		ActiveAndroid.initialize(this);
	}
}
