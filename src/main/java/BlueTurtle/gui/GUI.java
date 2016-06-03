package BlueTurtle.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class the GUI component of the system.
 * 
 * @author BlueTurtle.
 *
 */
public class GUI extends Application {

	/**
	 * Start the GUI.
	 */
	public void startGUI() {
		Application.launch(GUI.class, (java.lang.String[]) null);
	}

	/**
	 * Start the scene.
	 * 
	 * @param stage
	 *            the stage.
	 */
	@Override
	public void start(Stage stage) {
		try {
			stage.setTitle("BlueTurtle Visualizer");
			stage.getIcons().add(new Image(GUI.class.getResourceAsStream("/blueturtlelogo.png")));
			stage.setHeight(420);
			stage.setWidth(1083);
			stage.setResizable(false);
			stage.setScene(new Scene((SplitPane) new FXMLLoader(getClass().getResource("/Menu.fxml")).load()));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stop the application / GUI.
	 */
	public void stop() {
		Platform.exit();
	}

}
