package BlueTurtle.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BlueTurtle.TSE.JavaController;
import BlueTurtle.TSE.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller class for the GUI.
 * 
 * @author BlueTurtle.
 *
 */
public class GUIController {
	
	public enum ASAT {
		Checkstyle,
		PMD,
		Findbugs;
	}

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

	@FXML // fx:id="pmdButton"
	private Button pmdButton; // Value injected by FXMLLoader

	@FXML // fx:id="checkStyleButton"
	private Button checkStyleButton; // Value injected by FXMLLoader

	@FXML // fx:id="findbugsButton"
	private Button findbugsButton; // Value injected by FXMLLoader

	@FXML // fx:id="findbugsConfigText"
	private Text findbugsConfigText; // Value injected by FXMLLoader

	@FXML // fx:id="checkStyleConfigText"
	private Text checkStyleConfigText; // Value injected by FXMLLoader

	@FXML // fx:id="pmdConfigText"
	private Text pmdConfigText; // Value injected by FXMLLoader

	/**
	 * Event for CheckStyle button.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void selectCheckStyleConfigEvent(MouseEvent event) {

	}

	/**
	 * Event for PMD button.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void selectPMDConfigEvent(MouseEvent event) {

	}

	/**
	 * Event for FindBugs button.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void selectFindBugsConfigEvent(MouseEvent event) {

	}

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
		assert visualizeButton != null : "fx:id=\"visualizeButton\" was not injected: check your FXML file 'Menu.fxml'.";
		assert checkStyleButton != null : "fx:id=\"checkStyleButton\" was not injected: check your FXML file 'Menu.fxml'.";
		assert projectSourcePathText != null : "fx:id=\"projectSourcePathText\" was not injected: check your FXML file 'Menu.fxml'.";
		assert findbugsConfigText != null : "fx:id=\"findbugsConfigText\" was not injected: check your FXML file 'Menu.fxml'.";
		assert checkStyleConfigText != null : "fx:id=\"checkStyleConfigText\" was not injected: check your FXML file 'Menu.fxml'.";
		assert pmdConfigText != null : "fx:id=\"pmdConfigText\" was not injected: check your FXML file 'Menu.fxml'.";
		assert findbugsButton != null : "fx:id=\"findbugsButton\" was not injected: check your FXML file 'Menu.fxml'.";
		assert selectButton != null : "fx:id=\"selectButton\" was not injected: check your FXML file 'Menu.fxml'.";
		assert pmdButton != null : "fx:id=\"pmdButton\" was not injected: check your FXML file 'Menu.fxml'.";

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
					enableAllOtherButtons();
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

		checkStyleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * Event handler for the button.
			 * 
			 * @param event
			 *            the event.
			 */
			@Override
			public void handle(MouseEvent event) {
				chooseFile(checkStyleConfigText, ASAT.Checkstyle);
			}
		});

		pmdButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * Event handler for the button.
			 * 
			 * @param event
			 *            the event.
			 */
			@Override
			public void handle(MouseEvent event) {
				chooseFile(pmdConfigText, ASAT.PMD);
			}
		});

		findbugsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			/**
			 * Event handler for the button.
			 * 
			 * @param event
			 *            the event.
			 */
			@Override
			public void handle(MouseEvent event) {
				chooseFile(findbugsConfigText, ASAT.Findbugs);
			}
		});
		
		visualizeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				try {
					Main.runVisualization();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Enable all other buttons in the GUI. This is used after a source folder
	 * is selected.
	 */
	public void enableAllOtherButtons() {
		checkStyleButton.setDisable(false);
		pmdButton.setDisable(false);
		findbugsButton.setDisable(false);
		visualizeButton.setDisable(false);
	}

	/**
	 * Choose configuration file for ASAT.
	 * 
	 * @param configText
	 *            the text in the GUI for the config file.
	 */
	public void chooseFile(Text configText, ASAT asat) {

		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(new Stage());
		JavaController.setASATOutput(asat, file);
		if (file != null) {
			configText.setText(file.getAbsolutePath());
		}
	}

}
