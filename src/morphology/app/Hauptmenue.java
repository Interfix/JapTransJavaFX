package morphology.app;


import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class Hauptmenue extends Application {

//Klassenvariablen--------------------------------------------------------------------------------
	
	Stage		window;	
	
	
	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		window.setTitle("Transkription und Analyse");
		
		
		// Main Border Pane
			MakeBoxes.mainBorderPane = new BorderPane();
			MakeBoxes.mainBorderPane.setPadding(new Insets(10, 10, 0, 10));
						
			MakeBoxes.mainBorderPane.setBottom(MakeBoxes.setBottomBox());
			MakeBoxes.mainBorderPane.setTop(MakeBoxes.setTopBox());
					
				// DBConnection Setup
			
				try {				
					DBConnector.registerDriver();
					DBConnector.connectDB();				
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					System.out.println("Kein Datenbanktreiber gefunden.");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Fehler bei der Datenbankverbindung.");
				}	
			
			MakeBoxes.mainBorderPane.setLeft(MakeLeftBoxes.setLeftMain());
			MakeBoxes.mainBorderPane.setRight(MakeRightBoxes.setRightMain());
			MakeBoxes.mainBorderPane.setCenter(MakeCenterBoxes.setCenterMain());

			
			
			
		// Make Boxes
					
			MakeBoxes.centerDBBox = MakeCenterBoxes.setCenterDB();
			MakeBoxes.centerTransBox = MakeCenterBoxes.setCenterTrans();
			MakeBoxes.centerSetBox = MakeCenterBoxes.setCenterSettings();
			
			MakeBoxes.leftDBBox = MakeLeftBoxes.setLeftDB();
			MakeBoxes.leftTransBox = MakeLeftBoxes.setLeftTrans();
			MakeBoxes.leftSetBox = MakeLeftBoxes.setLeftSettings();		
			
			MakeBoxes.rightDBBox = MakeRightBoxes.setRightDB();
			MakeBoxes.rightTransBox = MakeRightBoxes.setRightTrans();
			MakeBoxes.rightSetBox = MakeRightBoxes.setRightSettings();
			

		
		// Main Scene		
			Scene mainScene = new Scene(MakeBoxes.mainBorderPane, Region.USE_PREF_SIZE, 600);
						
		window.setScene(mainScene);
		window.setResizable(false);
		window.show();
	
	}
}