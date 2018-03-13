package morphology.app;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MakeRightBoxes extends MakeBoxes {

// Right Box - main ----------------------------------------------------------------------------------------------------
	
	public static VBox setRightMain() {
		
		rightMainBox = new VBox (8);
		rightMainBox.setPadding(new Insets(10, 20, 0, 10));
		rightMainBox.setStyle("-fx-border-color: transparent transparent transparent black");
				
		Label setLabel1 = new Label ("Welcome to the");
		Label setLabel2 = new Label ("most awesome");
		Label setLabel3 = new Label ("transcription");
		Label setLabel4 = new Label ("program!");
		
		rightMainBox.getChildren().addAll(setLabel1, setLabel2, setLabel3, setLabel4);
		
		return rightMainBox;
	}
	
// Right Box - transcription -------------------------------------------------------------------------------------------
	
	public static VBox setRightTrans() {
	rightTransBox = new VBox ();
	rightTransBox.setPadding(new Insets(10, 20, 0, 10));
	rightTransBox.setStyle("-fx-border-color: transparent transparent transparent black");
	
	Label dbConnection = new Label("Transkriptions-Utilities");
	
	rightTransBox.getChildren().add(dbConnection);
	return rightTransBox;
}
	
// Right Box - database ------------------------------------------------------------------------------------------------
	
	public static VBox setRightDB() {
		
		rightDBBox = new VBox ();
	
		
		return rightDBBox;
	}
		
// Right Box - settings ------------------------------------------------------------------------------------------------
	
	public static VBox setRightSettings() {
		
		rightSetBox = new VBox (8);
		rightSetBox.setPadding(new Insets(10, 20, 0, 10));
		rightSetBox.setStyle("-fx-border-color: transparent transparent transparent black");
				
		Label setLabel1 = new Label ("Web Viewer");
		Label setLabel2 = new Label ("Debug Fenster");
		
		rightSetBox.getChildren().addAll(setLabel1, setLabel2);
		
		return rightSetBox;
	}
	
}
