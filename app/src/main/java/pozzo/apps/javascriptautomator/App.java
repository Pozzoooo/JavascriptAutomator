package pozzo.apps.javascriptautomator;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by sarge on 21/01/16.
 */
public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		ActiveAndroid.initialize(this);
	}
}
