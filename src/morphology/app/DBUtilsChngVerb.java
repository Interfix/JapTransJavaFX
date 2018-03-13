package morphology.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DBUtilsChngVerb extends DBUtils {

	public static void setChngWindowForVerb(String[] selItem) {
		
		Stage chngVerbWindow = new Stage();
		chngVerbWindow.setOnCloseRequest(e -> 
			{
				e.consume();
				chngVerbWindow.close();			
			});
		chngVerbWindow.setResizable(false);
		chngVerbWindow.initModality(Modality.APPLICATION_MODAL);
		chngVerbWindow.setTitle("Eintrag ändern");
		
//-------------------------------------------------------------------------------------------------------------------
		
		GridPane chngGrid = new GridPane();
		chngGrid.setHgap(5);
		chngGrid.setVgap(5);
		chngGrid.setPadding(new Insets(10, 10, 10, 10));
		
		Label chngEntry = new Label("Verb ändern: ");
		chngEntry.setStyle("-fx-font-size:12pt;");				
		chngGrid.add(chngEntry,0,0);
		
		
		
//-------------------------------------------------------------------------------------------------------------------

		
		TextField txtStamm = new TextField();
		txtStamm.setPromptText(selItem[0].toString());
		chngGrid.add(txtStamm, 0, 1);
		
		TextField txtBasis = new TextField();
		txtBasis.setPromptText(selItem[1].toString());
		chngGrid.add(txtBasis, 1, 1);
		
		TextField txtKlasse = new TextField();
		txtKlasse.setPromptText(selItem[2].toString());
		chngGrid.add(txtKlasse, 2, 1);
		
		TextField txtPartizip = new TextField();
		txtPartizip.setPromptText(selItem[3].toString());
		chngGrid.add(txtPartizip, 3, 1);
		

					
		
		
//-------------------------------------------------------------------------------------------------------------------
		
		
		Button btnOKEx = new Button("OK");
		btnOKEx.setOnAction(e -> {
			updateVerbDB(txtStamm, txtBasis, txtKlasse, txtPartizip);
			chngVerbWindow.close();
		});
		
		
		Button btnExit = new Button("Exit");
		btnExit.setOnAction(e -> chngVerbWindow.close());

		
		Button btnOK = new Button("Ändern");
		btnOK.setOnAction(e -> {
			updateVerbDB(txtStamm, txtBasis, txtKlasse, txtPartizip);
		});

		
		
		
		HBox btnRow = new HBox(8);
		btnRow.getChildren().addAll(btnOKEx, btnExit, btnOK);
		btnRow.setAlignment(Pos.BASELINE_RIGHT);
		chngGrid.add(btnRow, 3, 5);
		
		
		Scene scene = new Scene(chngGrid, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		
		
		chngVerbWindow.setScene(scene);
		chngVerbWindow.showAndWait();}
		
//--------------------------------------------------------------------------------------------------------------------
	
}

