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

public class DBUtilsAddVerb extends DBUtils {
		
	public static void setAddWindowForVerb() { 
		
/* baut das neue Fenster für die Eingabe neuer Datenbankeinträge
 * 
 */
	
	Stage addVerbWindow = new Stage();
	addVerbWindow.setOnCloseRequest(e -> 
		{
			e.consume();
			addVerbWindow.close();			
		});
	addVerbWindow.setResizable(false);
	addVerbWindow.initModality(Modality.APPLICATION_MODAL);
	addVerbWindow.setTitle("Eintrag hinzufügen");
	
	//------------------------------------------------------------------------------------------------------------
	
	GridPane addGrid = new GridPane();
	addGrid.setHgap(5);
	addGrid.setVgap(5);
	addGrid.setPadding(new Insets(10, 10, 10, 10));
	
	Label addEntry = new Label("Verb hinzufügen: ");
	addEntry.setStyle("-fx-font-size:12pt;");				
	addGrid.add(addEntry,0,0);
		
	Label lblStamm = new Label(" Verbstamm: ");
	addGrid.add(lblStamm,0,3);
	Label lblBasis = new Label(" Verbbasis: ");
	addGrid.add(lblBasis,2,3);
	
	//------------------------------------------------------------------------------------------------------------			
						
	ChoiceBox<String> inpMethod = new ChoiceBox<>();
	inpMethod.getItems().addAll("Stamm/Basis", "Kana");
	inpMethod.setValue("Stamm/Basis");
	addGrid.add(inpMethod,2,0);
	
	//------------------------------------------------------------------------------------------------------------
	
	TextField txtStamm = new TextField();
	txtStamm.setPromptText("Stamm...");
	addGrid.add(txtStamm,0,4);				
	
	TextField txtBasis = new TextField();
	addGrid.add(txtBasis,2,4);
	txtBasis.setPromptText("Basis...");
	
	//------------------------------------------------------------------------------------------------------------	
	

/* es wird nur getestet, ob ausschließlich Text eingegeben wurde, nicht
 * ob die Eingabe ein tatsächlich existierendes Wort ist
 */
		
	Button btnOKEx = new Button("OK");
	btnOKEx.setOnAction(e -> {
	if (!txtStamm.getText().isEmpty()) {
		//if (!hasEntry()) //TODO könnte über eine modifizierte Suche realisiert werden
		chkVal(txtStamm, txtBasis);		
	}
	reCount(MakeLeftBoxes.lexList);
	addVerbWindow.close();
	});

	
	Button btnExit = new Button("Exit");
	btnExit.setOnAction(e -> addVerbWindow.close());

	
	Button btnOK = new Button("Eintragen");
	btnOK.setOnAction(e -> {
	if (!txtStamm.getText().isEmpty()) {
		chkVal(txtStamm, txtBasis);		
	}
	reCount(MakeLeftBoxes.lexList);
	});
	
	HBox btnRow = new HBox(8);
	btnRow.getChildren().addAll(btnOKEx, btnExit, btnOK);
	btnRow.setAlignment(Pos.BASELINE_RIGHT);
	addGrid.add(btnRow, 2, 5);
	
	
	Scene scene = new Scene(addGrid, Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
	
	
	addVerbWindow.setScene(scene);
	addVerbWindow.showAndWait();
	
//------------------------------------------------------------------------------------------------------------			
					
	}	
	
	private static void chkVal(TextField txtStamm, TextField txtBasis){
		boolean stammChkVal = false;					
		for (int i=0; i<= txtStamm.getLength()-1;i++) {
			if (Character.isLetter(txtStamm.getText().charAt(i))) {
				stammChkVal = true;
			} else {
				stammChkVal = false;
				break;
			}	
		}
	boolean basisChkVal = false;
		for (int i=0; i<= txtBasis.getLength()-1;i++) {
			if (Character.isLetter(txtBasis.getText().charAt(i))) {
				basisChkVal = true;
			} else {							
			basisChkVal = false;
			break;
			}
		}
		if (MakeLeftBoxes.lexList.equals("verben") && stammChkVal == true && basisChkVal == true) {	
			writeVerbToDB(txtStamm.getText(), txtBasis.getText());
			txtStamm.clear();
			txtBasis.clear();
		}
	}
}
