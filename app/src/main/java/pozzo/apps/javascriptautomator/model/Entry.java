package pozzo.apps.javascriptautomator.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16
 */
@Table(name = "Entry")
public class Entry extends Model implements Parcelable {
	public interface Col {
		String NAME = "name";
	}

	@Column(name = Col.NAME)
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "commands")
	private String commands;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCommands() {
		return commands;
	}

	public void setCommands(String commands) {
		this.commands = commands;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return If this object seems empty.
	 */
	public boolean isEmpty() {
		return (name == null || name.length() == 0)
				&& (address == null || address.length() == 0)
				&& (commands == null || commands.length() == 0);
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Entry entry = (Entry) o;

		if(name != null ? !name.equals(entry.name) : entry.name != null) return false;
		if(address != null ? !address.equals(entry.address) : entry.address != null) return false;
		return !(commands != null ? !commands.equals(entry.commands) : entry.commands != null);
	}

	@Override
	public int hashCode() {
		return 31 + (name != null ? name.hashCode() : 0);
	}

	protected Entry(Parcel in) {
		name = in.readString();
		address = in.readString();
		commands = in.readString();
	}

	public static final Creator<Entry> CREATOR = new Creator<Entry>() {
		@Override
		public Entry createFromParcel(Parcel in) {
			return new Entry(in);
		}

		@Override
		public Entry[] newArray(int size) {
			return new Entry[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(address);
		dest.writeString(commands);
	}
}
