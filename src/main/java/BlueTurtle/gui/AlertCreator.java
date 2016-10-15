package BlueTurtle.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class is for creating alert dialogs.
 * 
 * @author BlueTurtle.
 *
 */
public final class AlertCreator {

	private static AlertCreator alertCreator = null;

	/**
	 * Only this class can instantiate itself.
	 */
	private AlertCreator() {
	}

	/**
	 * Get an instance of this class.
	 * 
	 * @return an instance of the DialogCreator.
	 */
	public static AlertCreator getInstance() {
		if (alertCreator == null) {
			alertCreator = new AlertCreator();
		}
		return alertCreator;
	}

	/**
	 * Create an alert dialog.
	 * 
	 * @param type
	 *            the type of the alert.
	 * @param title
	 *            the title of the dialog box.
	 * @param message
	 *            the message in the dialog box.
	 * @return an alert dialog.
	 */
	public Alert createAlert(AlertType type, String title, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(message);
		return alert;
	}

}
