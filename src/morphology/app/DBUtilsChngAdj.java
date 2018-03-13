package morphology.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DBUtilsChngAdj extends DBUtils {

	public static void setChngWindowForAdj(String[] selItem) {
		
		Stage chngAdjWindow = new Stage();
		chngAdjWindow.setOnCloseRequest(e -> 
			{
				e.consume();
				chngAdjWindow.close();			
			});
		chngAdjWindow.setResizable(false);
		chngAdjWindow.initModality(Modality.APPLICATION_MODAL);
		chngAdjWindow.setTitle("Eintrag ändern");
		
//-------------------------------------------------------------------------------------------------------------------
		
		GridPane chngGrid = new GridPane();
		chngGrid.setHgap(5);
		chngGrid.setVgap(5);
		chngGrid.setPadding(new Insets(10, 10, 10, 10));
		
		Label chngEntry = new Label("Adjektiv ändern: ");
		chngEntry.setStyle("-fx-font-size:12pt;");				
		chngGrid.add(chngEntry,0,0);
		
		
//-------------------------------------------------------------------------------------------------------------------
		
		// TextFelder setzen und füllen mit Eingaben/Vorgaben
		
//-------------------------------------------------------------------------------------------------------------------

		Button btnOKEx = new Button("OK");
		btnOKEx.setOnAction(e -> {
			updateAdjDB();
			chngAdjWindow.close();
		});
		
		Button btnExit = new Button("Exit");
		btnExit.setOnAction(e -> chngAdjWindow.close());
		
		Button btnOK = new Button("Ändern");
		btnOK.setOnAction(e -> {
			updateAdjDB();
		});
		
		
		HBox btnRow = new HBox(8);
		btnRow.getChildren().addAll(btnOK, btnExit);
		btnRow.setAlignment(Pos.BASELINE_RIGHT);
		chngGrid.add(btnRow, 2, 5);
		
		
		Scene scene = new Scene(chngGrid, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		
		
		chngAdjWindow.setScene(scene);
		chngAdjWindow.showAndWait();
		
	//------------------------------------------------------------------------------------------------------------	

	}
}
