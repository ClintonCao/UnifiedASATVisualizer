package BlueTurtle.gui;

import java.net.URL;
import java.util.ResourceBundle;

import BlueTurtle.TSE.App;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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

	@FXML // fx:id="LoadButton"
	private Button LoadButton; // Value injected by FXMLLoader

	@FXML // fx:id="projectPathText"
	private Text projectPathText; // Value injected by FXMLLoader

	@FXML // fx:id="VisualizeButton"
	private Button VisualizeButton; // Value injected by FXMLLoader

	/**
	 * Events for the LoadButton.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void LoadButtonEvent(MouseEvent event) {

	}

	/**
	 * Events from the VisualizeButton.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void VisualizeButtonEvent(MouseEvent event) {

	}

	/**
	 * Initialize the buttons.
	 */
	@FXML
	void initialize() {
		assert LoadButton != null : "fx:id=\"LoadButton\" was not injected: check your FXML file 'Menu.fxml'.";
		assert projectPathText != null : "fx:id=\"projectPathText\" was not injected: check your FXML file 'Menu.fxml'.";
		assert VisualizeButton != null : "fx:id=\"VisualizeButton\" was not injected: check your FXML file 'Menu.fxml'.";

		LoadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * Event handler for the button.
			 * 
			 * @param event
			 *            the event.
			 */
			@Override
			public void handle(MouseEvent event) {
				// projectPathText.setText("TestPoject");
				System.out.println("Hello World");
			}
		});

		VisualizeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

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
