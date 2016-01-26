package pozzo.apps.javascriptautomator.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16
 */
@Table(name = "Entry")
public class Entry extends Model {
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
}
