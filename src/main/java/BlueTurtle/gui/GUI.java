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

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));

			SplitPane splitPane = (SplitPane) loader.load();

			Scene scene = new Scene(splitPane);

			stage.setHeight(600);
			stage.setWidth(1200);
			stage.setResizable(false);
			stage.setScene(scene);
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
