package pozzo.apps.javascriptautomator.core;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This client will executes javascript commands.
 *
 * @author Luiz Gustavo Pozzo
 * @since 22/01/16.
 */
public class JavascriptRunner extends WebViewClient {
	private ArrayList<String> tasks;
	private Handler uiHandler;
	//Prevents double 100% page load
	private Runnable current;
	private WebView webView;

	public JavascriptRunner(WebView webView) {
		this.webView = webView;
		this.uiHandler = new Handler(Looper.getMainLooper());
		this.tasks = new ArrayList<>();
		webView.setWebViewClient(this);
	}

	/**
	 * Add an entry to the execution queue.
	 */
	public JavascriptRunner add(String task) {
		tasks.add(task);
		return this;
	}

	/**
	 * Just to easyfy the task add.
	 */
	public void addAll(String ...tasks) {
		Collections.addAll(this.tasks, tasks);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		executeSingleValidCommand();
	}

	/**
	 * Start executing tasks.
	 * They will chain if they all load things.
	 */
	public void start() {
		executeSingleValidCommand();
	}

	private void executeSingleValidCommand() {
		//Make sure it is not empty and there is no null element
		while(!tasks.isEmpty() && current == null) {
			final String next = tasks.remove(0);

			if(next != null && next.length() != 0) {
				current = new Runnable() {
					@Override
					public void run() {
						webView.loadUrl(next);
						current = null;
					}
				};
				//300 is good enougth to block dual 100% and is enough to flash screen.
				uiHandler.postDelayed(current, 300);
				break;
			}
		}
	}
}
