package pozzo.apps.javascriptautomator.util;

import android.widget.TextView;

/**
 * Some useful methods to manipulate text.
 *
 * @author Luiz Gustavo Pozzo
 * @since 08/02/16
 */
public class TextUtil {

	/**
	 * Inserts a text snippet in the current cursor position or in the start if cursor is missing.
	 *
	 * @param insert Where will be inserted, usually an EditText.
	 * @param text to be inserted.
	 */
	public static void insertIntoCursorPosition(TextView insert, String text) {
		if(insert == null)
			return;
		int position = insert.getSelectionStart();
		String currentText = insert.getText().toString();
		if(position < 0 || position >= currentText.length())
			position = 0;
		String resultText = currentText.substring(0, position);
		resultText += text;
		resultText += currentText.substring(position);
		insert.setText(resultText);
	}
}
