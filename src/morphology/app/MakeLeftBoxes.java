package morphology.app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class MakeLeftBoxes extends MakeBoxes {
	
	public static String lexList;

// Left Box - main ----------------------------------------------------------------------------------------------------
	
	public static VBox setLeftMain() {
		
		leftMainBox = new VBox(8);
		leftMainBox.setPadding(new Insets(10, 10, 10, 0));
		leftMainBox.setStyle("-fx-border-color: transparent black transparent transparent");
		
		Button copyright = new Button("Copyright");
		copyright.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
		leftMainBox.setAlignment(Pos.BOTTOM_LEFT);
			
		copyright.setOnAction(e -> Copyright.display("Copyright Notice", "Copyright @CK"));
			
		leftMainBox.getChildren().add(copyright);
			
		return leftMainBox;
	}
		
		

//Left Box - transcription --------------------------------------------------------------------------------------------
			
	public static VBox setLeftTrans() {
				
		leftTransBox =new VBox(8);
		leftTransBox.setPadding(new Insets(10, 20, 0, 10));
		leftTransBox.setStyle("-fx-border-color: transparent black transparent transparent");

		Label debug = new Label("Debugging output");
		debug.setStyle("-fx-underline: true");
		Label rohEingabe = new Label("Roheingabe: ");
		Label rohTrans = new Label("Rohtranskription: ");
				
		leftTransBox.getChildren().addAll(debug, rohEingabe, rohTrans);
			
		return leftTransBox;
	}
			
			
			
// Left Box - database utility functions ------------------------------------------------------------------------------
		
	public static VBox setLeftDB() {
		
		leftDBBox = new VBox(8);
		leftDBBox.setPadding(new Insets(10, 20, 0, 10));
		leftDBBox.setStyle("-fx-border-color: transparent black transparent transparent");
			
/*Choice Box*/	ChoiceBox<String> chooseLex = new ChoiceBox<>();
				chooseLex.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				chooseLex.getItems().addAll("Verben", "Adjektive");				
				chooseLex.setValue("Verben");
				lexList = chooseLex.getValue().toLowerCase();
				chooseLex.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
					lexList = chooseLex.getValue().toLowerCase();				
					DBUtils.switchDB();					
				});
				
				
/*Separator*/	Separator dbSeparator = new Separator();				
				dbSeparator.setPadding(new Insets(5, 0, 5, 0));
				
/*Button New*/	Button butNew = new Button ("Neuer Eintrag");
				butNew.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				butNew.setOnAction(e -> {
						DBUtils.addEntry(lexList);
				});
				
/*Button Del*/	Button butDel = new Button ("Eintrag löschen");
				butDel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				butDel.setOnAction(e -> {
					if (DBUtils.isItemSelected(lexList) != false){
						DBUtils.deleteEntry(lexList);
					}
					DBUtils.reCount(MakeLeftBoxes.lexList);
				});
				
/*Btn Change*/	Button butChng = new Button ("Eintrag ändern");
				butChng.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				butChng.setOnAction(e -> {
					if (DBUtils.isItemSelected(lexList) != false){
						DBUtils.changeEntry(lexList);
					}
				});
								
/*Btn Search*/	Button butSrch = new Button ("Eintrag suchen/null");		// kommt noch
				butSrch.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				
/*Btn SaveDB*/	Button butSaveDB = new Button ("Datenbank speichern/null");	// wird eine Sicherheitskopie der Datenbank anlegen.
				butSaveDB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);				
				
/*Btn Update*/	Button butUpdateDB = new Button ("Update DB/null");			// verschwindet
				butUpdateDB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				butUpdateDB.setOnAction(e -> {});
				
/*Btn.IDChck*/	Button butChkID = new Button ("ID überprüfen");				//unnötig als aktive Option
				butChkID.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				butChkID.setOnAction(e -> {
					if (DBUtils.isItemSelected(lexList) != false){
						System.out.println(DBUtils.getDBItemID(lexList));
					}
				});
						
		leftDBBox.getChildren().addAll(	chooseLex, 
										dbSeparator, 
										butNew, butDel, butChng, butSrch, butSaveDB, 
										butUpdateDB, butChkID);
			
		return leftDBBox;
	}

				
			
// Left Box - settings ------------------------------------------------------------------------------------------------
		
	public static VBox setLeftSettings() {
			
		leftSetBox = new VBox(8);
		leftSetBox.setPadding(new Insets(10, 20, 0, 10));
		leftSetBox.setStyle("-fx-border-color: transparent black transparent transparent");

		Label setLabel = new Label("Settings under Construction");

		leftSetBox.getChildren().add(setLabel);

		return leftSetBox;
	}
	
}
