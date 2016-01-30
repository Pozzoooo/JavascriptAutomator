package pozzo.apps.javascriptautomator.model;

import com.activeandroid.Model;
import com.splunk.mint.Mint;

import java.lang.reflect.Field;

/**
 * Our baisc model.
 *
 * @author Luiz Gustavo Pozzo
 * @since 30/01/2016
 */
public class BaseModel extends Model {

	/**
	 * Bypass para setarmos o ID no ActiveAndroid.
	 */
	public void setId(Long mId) {
		try {
			Class clas = getClass().getSuperclass().getSuperclass();
			while(!clas.getName().equals(Model.class.getName()))
				clas = clas.getSuperclass();

			Field mIdField = clas.getDeclaredField("mId");
			mIdField.setAccessible(true);
			mIdField.set(this, mId);
		} catch (NoSuchFieldException e) {
			//Estas duas excessoes nao devem ocorrer, jamais!
			Mint.logException(e);
		} catch (IllegalAccessException e) {
			Mint.logException(e);
		}
	}
}
