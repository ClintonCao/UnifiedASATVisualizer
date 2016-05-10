package BlueTurtle.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

			stage.setHeight(500);
			stage.setWidth(1000);
			stage.setResizable(false);
			stage.setScene(scene);
			
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});
			
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
