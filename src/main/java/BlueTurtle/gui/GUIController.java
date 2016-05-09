package BlueTurtle.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Controller class for the GUI.
 * 
 * @author BlueTurtle.
 *
 */
public class GUIController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="selectButton"
	private Button selectButton; // Value injected by FXMLLoader

	@FXML // fx:id="projectSourcePathText"
	private Text projectSourcePathText; // Value injected by FXMLLoader

	@FXML // fx:id="visualizeButton"
	private Button visualizeButton; // Value injected by FXMLLoader

	/**
	 * Events for the LoadButton.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void selectButtonEvent(MouseEvent event) {

	}

	/**
	 * Events from the VisualizeButton.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void visualizeButtonEvent(MouseEvent event) {

	}

	/**
	 * Initialize the buttons.
	 */
	@FXML
	void initialize() {
		assert selectButton != null : "fx:id=\"LoadButton\" was not injected: check your FXML file 'Menu.fxml'.";
		assert projectSourcePathText != null : "fx:id=\"projectPathText\" was not injected: check your FXML file 'Menu.fxml'.";
		assert visualizeButton != null : "fx:id=\"VisualizeButton\" was not injected: check your FXML file 'Menu.fxml'.";

		selectButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * Event handler for the button.
			 * 
			 * @param event
			 *            the event.
			 */
			@Override
			public void handle(MouseEvent event) {

				DirectoryChooser directoryChooser = new DirectoryChooser();
				File selectedDirectory = directoryChooser.showDialog(new Stage());

				if (selectedDirectory == null) {
					projectSourcePathText.setText("No Directory selected");
				} else {
					projectSourcePathText.setText(selectedDirectory.getAbsolutePath());
				}
			}
		});

		visualizeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * Event handler for the button.
			 * 
			 * @param event
			 *            the event.
			 */
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Visualize button pressed");
			}
		});

	}
}
