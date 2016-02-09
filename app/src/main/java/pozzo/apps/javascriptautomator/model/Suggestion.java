package pozzo.apps.javascriptautomator.model;

import android.provider.BaseColumns;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * This is a suggestion that may be given in edition activity.
 *
 * @author Luiz Gustavo Pozzo
 * @since 06/02/16
 */
@Table(name = "Suggestion", id = BaseColumns._ID)
public class Suggestion extends BaseModel {
	public interface Col {
	}

	@Column(name = "displayName")
	private String displayName;

	@Column(name = "value")
	private String value;

	@Column(name = "position")
	private int position;

	@Column(name = "cursor")
	private int cursor;

	public Suggestion() {
	}

	public Suggestion(String displayName, String value, int cursor) {
		this.displayName = displayName;
		this.value = value;
		this.cursor = cursor;
		this.position = Integer.MAX_VALUE;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getCursor() {
		return cursor;
	}

	/**
	 * Where cursor should be moved to.
	 */
	public void setCursor(int cursor) {
		this.cursor = cursor;
	}
}
