package morphology.app;

import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MakeCenterBoxes extends MakeBoxes {
	
	public static TableView<DBShow> verbTable;
	public static TableView<DBShow> adjTable;
	
	
	
// Center Box - main ---------------------------------------------------------------------------------------------------
	
		public static GridPane setCenterMain() {
			centerMainBox = new GridPane();
			
			return centerMainBox;
	}
	
// Center Box - transcription ------------------------------------------------------------------------------------------
	
		public static GridPane setCenterTrans() {
			
			centerTransBox = new GridPane();
			
			centerTransBox.setHgap(5);
			centerTransBox.setVgap(5);
			centerTransBox.setPadding(new Insets(10, 10, 10, 10));
			
			DBCount entries = DBUtils.makeDBCounter();
			
			Label vLbl = new Label("Verben in Datenbank: ");
			vLbl.setStyle("-fx-font-size:12pt;");
			centerTransBox.add(vLbl, 0, 0);
			Label vCountLbl = new Label();
			vCountLbl.setText(Integer.toString(entries.getVCount()));
			vCountLbl.setMinWidth(50);
			vCountLbl.setAlignment(Pos.CENTER_RIGHT);
			vCountLbl.setStyle("-fx-font-size:12pt;");
			centerTransBox.add(vCountLbl, 1, 0);
			
			Label aLbl = new Label("Adjektive in Datenbank: ");
			aLbl.setStyle("-fx-font-size:12pt;");
			centerTransBox.add(aLbl, 0, 1);
			Label aCountLbl = new Label();
			aCountLbl.setText(Integer.toString(entries.getACount()));
			aCountLbl.setMinWidth(50);
			aCountLbl.setAlignment(Pos.CENTER_RIGHT);
			aCountLbl.setStyle("-fx-font-size:12pt;");
			centerTransBox.add(aCountLbl, 1, 1);
			return centerTransBox;
	}
		
// Center Box - database -----------------------------------------------------------------------------------------------
		
		public static StackPane setCenterDB() {					
			
/*			try {				
				DBConnector.registerDriver();
				DBConnector.connectDB();				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Kein Datenbanktreiber gefunden.");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Fehler bei der Datenbankverbindung.");
			}*/
			
			verbTable = new TableView<>();
			verbTable = DBCarpenter.tableMaker("verben");
			adjTable = new TableView<>();
			adjTable = DBCarpenter.tableMaker("adjektive");
			
			centerDBBox = new StackPane();
			centerDBBox.getChildren().addAll(adjTable, verbTable);
			
			return centerDBBox;
	}
		

		
// Center Box - settings -----------------------------------------------------------------------------------------------
		


		public static GridPane setCenterSettings() {
			centerSetBox = new GridPane();
			centerSetBox.setAlignment(Pos.CENTER);
			
			Button btDC = new Button("Disconnect DB");
			btDC.setOnAction(e -> {
			DBConnector.shutdownDBConn();
			});
			
			Button btConn = new Button("Reconnect DB");
			btConn.setOnAction(e -> {
			try {				
				if (DBConnector.conn !=null) {
					DBConnector.connectDB();
				}
			} catch (SQLException e1) {				
				e1.printStackTrace();
				System.out.println("Fehler bei der Datenbankverbindung.");
			}					
			});
			
			VBox connectionButtons = new VBox (8);
			connectionButtons.getChildren().addAll(btDC, btConn);
			centerSetBox.getChildren().addAll(connectionButtons);	
			
			return centerSetBox;
	}			
			
}
