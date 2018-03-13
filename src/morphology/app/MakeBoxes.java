package morphology.app;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

public class MakeBoxes {

	static Button						butMain, butTrans, butDBase, butSet;
	static VBox							leftMainBox, leftDBBox, leftTransBox, leftSetBox,
										rightMainBox, rightDBBox, rightTransBox, rightSetBox;								
	static GridPane						centerMainBox, centerTransBox, centerSetBox;
	static StackPane					centerDBBox, connectionStack;
	public static BorderPane			mainBorderPane;
	public static boolean				dbConnection;
	public static ImageView 			vImageFail, vImageConn;
	public static ObservableList<Node>	xy;
	public static TableView<DBShow> 	dbTable;
	
	
// Top Box - switch between scenes ------------------------------------------------------------------------------------
	public static TilePane setTopBox () {
	
	TilePane boxOben = new TilePane(Orientation.HORIZONTAL);
	boxOben.setPadding(new Insets(0, 0, 10, 0));
	boxOben.setHgap(10.0);
	boxOben.setStyle("-fx-border-color: transparent transparent black transparent;");
	
				
		butMain = new Button("Hauptmenü");
		butMain.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		butMain.setStyle("-fx-font-size: 14pt;");
		butMain.setDisable(true);
		butMain.setOnAction(e -> {						
			butMain.setDisable(true);
			butTrans.setDisable(false);
			butDBase.setDisable(false);
			butSet.setDisable(false);
			mainBorderPane.setLeft(leftMainBox);
			mainBorderPane.setRight(rightMainBox);
			mainBorderPane.setCenter(centerMainBox);
			System.out.println(mainBorderPane.getChildren());
		});
		
		butTrans = new Button ("Analyse");
		butTrans.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		butTrans.setStyle("-fx-font-size: 14pt;");
		butTrans.setOnAction(e -> {						
			butTrans.setDisable(true);
			butMain.setDisable(false);
			butDBase.setDisable(false);
			butSet.setDisable(false);
			mainBorderPane.setLeft(leftTransBox);
			mainBorderPane.setRight(rightTransBox);
			mainBorderPane.setCenter(centerTransBox);			
			System.out.println(mainBorderPane.getChildren());
		});
		
		butDBase = new Button ("Datenbank");
		butDBase.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		butDBase.setStyle("-fx-font-size: 14pt;");
		butDBase.setOnAction(e -> {						
			butDBase.setDisable(true);
			butMain.setDisable(false);
			butTrans.setDisable(false);						
			butSet.setDisable(false);
			mainBorderPane.setLeft(leftDBBox);
			mainBorderPane.setRight(rightDBBox);
			mainBorderPane.setCenter(centerDBBox);
			System.out.println(mainBorderPane.getChildren());
		});
	
		butSet = new Button ("Einstellungen");
		butSet.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		butSet.setStyle("-fx-font-size: 14pt;");
		butSet.setOnAction(e -> {						
			butSet.setDisable(true);
			butMain.setDisable(false);
			butTrans.setDisable(false);
			butDBase.setDisable(false);
			mainBorderPane.setLeft(leftSetBox);
			mainBorderPane.setRight(rightSetBox);
			mainBorderPane.setCenter(centerSetBox);
			System.out.println(mainBorderPane.getChildren());
		});
		
		Button butExit = new Button ("Exit");
		butExit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		butExit.setStyle("-fx-font-size: 14pt;");
		butExit.setOnAction(e -> Platform.exit());
		
	boxOben.getChildren().addAll(butMain, butTrans, butDBase, butSet, butExit);
	
	return boxOben;
	}

	

// Bottom Box - database connection indicator -------------------------------------------------------------------------

	public static AnchorPane setBottomBox () {
		AnchorPane dbConnInd = new AnchorPane();
		
		Label dbConnLabel = new Label("Datenbankverbindung ");
		dbConnLabel.setPadding(new Insets(5, 5, 5, 5));
		dbConnInd.setStyle("-fx-border-color: black transparent transparent transparent; -fx-font-size: 11pt;");
		AnchorPane.setRightAnchor(dbConnLabel, 38.0);
		
		Image imgFail = new Image(MakeCenterBoxes.class.getResourceAsStream("/img/Network-Connection-Failed-icon.png"));		
		vImageFail = new ImageView(imgFail);
				
		Image imgConn = new Image(MakeCenterBoxes.class.getResourceAsStream("/img/Network-Connection-icon.png"));		
		vImageConn = new ImageView(imgConn);
		
		connectionStack = new StackPane();
		connectionStack.getChildren().addAll(vImageConn,vImageFail);
		
		AnchorPane.setRightAnchor(connectionStack, 10.0);
		connectionStack.setTranslateY(3);
				
		dbConnInd.getChildren().addAll(dbConnLabel, connectionStack);
				
		return dbConnInd;
	}
}

