package morphology.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DBUtilsAddAdj extends DBUtils {
	
	public static void setAddWindowForAdj() {
		
/* baut das neue Fenster für die Eingabe neuer Datenbankeinträge
 * 
 */
		
	Stage addAdjWindow = new Stage();
	addAdjWindow.setOnCloseRequest(e -> 
		{
			e.consume();
			addAdjWindow.close();			
		});
	addAdjWindow.setResizable(false);
	addAdjWindow.initModality(Modality.APPLICATION_MODAL);
	addAdjWindow.setTitle("Eintrag hinzufügen");
	
	//------------------------------------------------------------------------------------------------------------
	
	GridPane addGrid = new GridPane();
	addGrid.setHgap(5);
	addGrid.setVgap(5);
	addGrid.setPadding(new Insets(10, 10, 10, 10));
	
	Label addEntry = new Label("Adjektiv hinzufügen: ");
	addEntry.setStyle("-fx-font-size:12pt;");				
	addGrid.add(addEntry,0,0);
		
	Label lblStamm = new Label(" Adjektivstamm: ");
	addGrid.add(lblStamm,0,3);
	
	//------------------------------------------------------------------------------------------------------------			
						
	ChoiceBox<String> inpMethod = new ChoiceBox<>();
	inpMethod.getItems().addAll("Stamm/Basis", "Kana");
	inpMethod.setValue("Stamm/Basis");
	addGrid.add(inpMethod,2,0);
	
	//------------------------------------------------------------------------------------------------------------
	
	TextField txtStamm = new TextField();
	txtStamm.setPromptText("Stamm...");
	addGrid.add(txtStamm,0,4);			
	
	//------------------------------------------------------------------------------------------------------------	

/* es wird nur getestet, ob ausschließlich Text eingegeben wurde, nicht
 * ob die Eingabe ein tatsächlich existierendes Wort ist
 */
	
	Button btnOK = new Button("OK");
	btnOK.setOnAction(e -> {
	if (!txtStamm.getText().isEmpty())	{
		boolean stammChkVal = false;					
		for (int i=0; i<= txtStamm.getLength()-1;i++) {
			if (Character.isLetter(txtStamm.getText().charAt(i))) {
				stammChkVal = true;
			} else {
				stammChkVal = false;
				break;
			}	
		}

		if (MakeLeftBoxes.lexList.equals("adjektive") && stammChkVal == true) {			
			writeAdjToDB(txtStamm.getText());
			txtStamm.clear();
		}
	}
	});
	
		
//------------------------------------------------------------------------------------------------------------
	
	Button btnExit = new Button("Exit");
	btnExit.setOnAction(e -> addAdjWindow.close());
	
	
	HBox btnRow = new HBox(8);
	btnRow.getChildren().addAll(btnOK, btnExit);
	btnRow.setAlignment(Pos.BASELINE_RIGHT);
	addGrid.add(btnRow, 2, 5);
	
	
	Scene scene = new Scene(addGrid, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
	
	
	addAdjWindow.setScene(scene);
	addAdjWindow.showAndWait();
	
//------------------------------------------------------------------------------------------------------------	
	
	}
}
