package morphology.app;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Copyright {

	public static void display(String title, String message)
	{
		Stage crWindow = new Stage();
		crWindow.setTitle(title);
		crWindow.setMinWidth(250);		
		crWindow.initModality(Modality.APPLICATION_MODAL);
		
		Label label = new Label(message);
		Button closeButton = new Button("Accept");
		closeButton.setOnAction(e -> crWindow.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		crWindow.setScene(scene);
		crWindow.showAndWait();
		
	}
}
