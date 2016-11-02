package BlueTurtle.gui;

import java.awt.Desktop;
import java.awt.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType; 
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Popup;

import BlueTurtle.TSE.JavaController;
import BlueTurtle.TSE.Main;
import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.gui.GUIController.ASAT;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * Controller class for the GUI.
 * 
 * @author BlueTurtle.
 *
 */
public class GUIController {


	/**
	 * Enums to represent the ASATs.
	 * 
	 * @author BlueTurtle.
	 *
	 */
	public enum ASAT { CheckStyle, PMD, FindBugs; }

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources; //NOPMD - needed for the FXMLLoader.

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location; //NOPMD - needed for the FXMLLoader.

	@FXML // fx:id="selectButton"
	private Button selectButton; // Value injected by FXMLLoader

	@FXML // fx:id="projectSourcePathText"
	private Text projectSourcePathText; // Value injected by FXMLLoader

	@FXML // fx:id="visualizeButton"
	private Button visualizeButton; // Value injected by FXMLLoader
	
	@Getter @Setter private static String projectPath; //NOPMD - warning caused by lombok.

	/**
	 * Event for CheckStyle button.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void selectCheckStyleConfigEvent(MouseEvent event) { }

	/**
	 * Event for PMD button.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void selectPMDConfigEvent(MouseEvent event) { }

	/**
	 * Event for FindBugs button.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void selectFindBugsConfigEvent(MouseEvent event) { }

	/**
	 * Events for the LoadButton.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void selectButtonEvent(MouseEvent event) { }

	/**
	 * Events from the VisualizeButton.
	 * 
	 * @param event
	 *            the event.
	 */
	@FXML
	void visualizeButtonEvent(MouseEvent event) { }

	/**
	 * Initialize the buttons.
	 */
	@FXML
	void initialize() {
		assert visualizeButton != null : "fx:id=\"visualizeButton\" was not injected: check your FXML file 'Menu.fxml'.";
		assert projectSourcePathText != null : "fx:id=\"projectSourcePathText\" was not injected: check your FXML file 'Menu.fxml'.";
		assert selectButton != null : "fx:id=\"selectButton\" was not injected: check your FXML file 'Menu.fxml'.";

		// Set the event handlers for the buttons.
		selectButton.setOnMouseClicked(new SelectButtonEventHandler(projectSourcePathText, visualizeButton));
		visualizeButton.setOnMouseClicked(new VisualizeButtonEventHandler());
	}
	
}

/**
 * EventHandler class for select button of GUI.
 * 
 * @author BlueTurtle.
 *
 */
class SelectButtonEventHandler implements EventHandler<MouseEvent> {

	@Getter private Text sourcePathText;
	@Getter private Button visualizeButton;

	/**
	 * Constructor.
	 * 
	 * @param sourcePathText
	 *            the text field that shows the path of the project.
	 * @param vButton
	 *            the visualize button. This is needed for enabling the button
	 *            after the project folder is selected.
	 */
	public SelectButtonEventHandler(Text sourcePathText, Button vButton) {
		this.sourcePathText = sourcePathText;
		this.visualizeButton = vButton;
	}
	
	/**
	 * Show an reminder to the user, after the user has selected the project folder.
	 */
	public void showAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setResizable(true);
		alert.setContentText("Please make sure that you have run mvn site" + "\n"
						+ "before clicking on the " + '"' + "Visualize" + '"' + " button!");
		alert.showAndWait();
	}

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
			sourcePathText.setText("No Directory selected");
		} else {
			sourcePathText.setText(selectedDirectory.getAbsolutePath());
			GUIController.setProjectPath(sourcePathText.getText());
			showAlert();
			visualizeButton.setDisable(false);
		}
	}

}

/**
 * EventHandler class for visualize button of GUI.
 * 
 * @author BlueTurtle.
 *
 */
class VisualizeButtonEventHandler implements EventHandler<MouseEvent> {

	/**
	 * Event handler for the button.
	 * 
	 * @param event
	 *            the event.
	 */
	@Override
	public void handle(MouseEvent event) {
		setOutputFiles();
		try {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Info");
			alert.setResizable(true);
			alert.setContentText("Collecting the data may take a few minutes for large projects.");
			alert.show();
			
			ProjectInfoFinder pif = new ProjectInfoFinder();
			pif.findFiles(new File(GUIController.getProjectPath()));
			pif.retrieveCodeFiles();
			Main.runVisualization();
			Desktop.getDesktop().browse(new File("visualization/main.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the output files for the JavaController.
	 */
	public void setOutputFiles() {
		JavaController.setASATOutput(ASAT.CheckStyle,
				new File(GUIController.getProjectPath() + "/target/checkstyle-result.xml"));
		JavaController.setASATOutput(ASAT.PMD, new File(GUIController.getProjectPath() + "/target/pmd.xml"));
		JavaController.setASATOutput(ASAT.FindBugs,
				new File(GUIController.getProjectPath() + "/target/findbugs.xml"));
	}
}

