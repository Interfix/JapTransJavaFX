package morphology.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DBUtils {


	
	
	
//--------------------------------------------------------------------------------------------------------------------
	
public static void addEntry(String lexList) {
	
/* 
 * ruft das Eingabefenster für neue Wörter auf (DBUtilsAddAdj/Verb) 
 */
	
	
	if (lexList.equals("verben")) {
		DBUtilsAddVerb.setAddWindowForVerb();
	}
	if (lexList.equals("adjektive")) {
		DBUtilsAddAdj.setAddWindowForAdj();
	}	
}


protected static void writeVerbToDB(String txtStamm, String txtBasis) {
	
/* - benötigt Datenbankzugriff -
 * Fügt der Datenbank einen neuen Verb-Eintrag hinzu und
 * aktualisiert die Listenanzeige im Datenbankreiter 
 */
	
	DBShow show;
	String txtKlasse = new String();
	String txtPartizip = new String();
	
	if (txtStamm.equals(txtBasis)){
		txtKlasse = "Vv";
		txtPartizip = txtStamm;
	} else {
		String[] verbFormen = getVerbForms(txtStamm, txtBasis);
		txtKlasse = verbFormen[0];
		txtPartizip = verbFormen[1];
	}
	
	try {
		String query =	("INSERT INTO " +
						MakeLeftBoxes.lexList.toLowerCase() +
						" (Stamm, Basis, Klasse, Partizip) " +
						"VALUES ('" + txtStamm + "', '" + txtBasis + "', '" + txtKlasse + "', '" + txtPartizip + "');");
		PreparedStatement pst = DBConnector.conn.prepareStatement(query);				
		pst.execute();				
		pst.close();		
	} catch (Exception e) {
		System.err.println(e);
	}
	
	show = new DBShow(txtStamm, txtBasis, txtKlasse, txtPartizip);
	show.setStamm(txtStamm);
	show.setBasis(txtBasis);
	show.setKlasse(txtKlasse);
	show.setPartizip(txtPartizip);	
	MakeCenterBoxes.verbTable.getItems().add(show);	  
}


private static String[] getVerbForms(String txtStamm, String txtBasis) {

/*  
 * Bestimmt die Verbklasse eines neuen Eintrags und sein Partizip 
 */
	
	String[] verbForm = new String[2];
	String consonantFinal;
	
	String klasseVc = "Vc/";
	if (txtStamm.endsWith("a") || txtStamm.endsWith("e") || txtStamm.endsWith("i") || txtStamm.endsWith("o") || txtStamm.endsWith("u")) {
		consonantFinal = "w";
	} else {
		consonantFinal = txtStamm.substring(txtStamm.length()-1);
	}
	
	verbForm[0] = klasseVc.concat(consonantFinal);
		
	String txtPartizip = "";
	
	switch (verbForm[0]) {
	
		case "Vc/s":
			txtPartizip = txtBasis;
			break;
		case "Vc/t":
			txtPartizip = txtStamm;
			break;
		case "Vc/r":
			txtPartizip = txtStamm.substring(0, txtStamm.length()-1); 
			txtPartizip = txtPartizip.concat("t");
			break;
		case "Vc/k":
			txtPartizip = txtStamm.substring(0, txtStamm.length()-1); 
			txtPartizip = txtPartizip.concat("i");
			break;
		case "Vc/g": 
			txtPartizip = txtStamm.substring(0, txtStamm.length()-1); 
			txtPartizip = txtPartizip.concat("i");
			break;
		case "Vc/b": 
			txtPartizip = txtStamm.substring(0, txtStamm.length()-1); 
			txtPartizip = txtPartizip.concat("ñ");
			break;
		case "Vc/m": 
			txtPartizip = txtStamm.substring(0, txtStamm.length()-1); 
			txtPartizip = txtPartizip.concat("ñ");
			break;
		case "Vc/n": 
			txtPartizip = txtStamm.substring(0, txtStamm.length()-1); 
			txtPartizip = txtPartizip.concat("ñ");
			break;
		case "Vc/w":
			txtPartizip = txtBasis.substring(0, txtBasis.length()-1); 
			txtPartizip = txtPartizip.concat("t");
			break;
	}

	verbForm[1] = txtPartizip;

	return verbForm;
}


protected static void writeAdjToDB(String txtAdj) {
	
/* - benötigt Datenbankzugriff -
 * Fügt der Datenbank einen neuen Adjektiv-Eintrag hinzu und
 * aktualisiert die Listenanzeige im Datenbankreiter 
 */	
	
	DBShow show;
		
	try {
		String query =	("INSERT INTO " +
						MakeLeftBoxes.lexList.toLowerCase() +
						" (Adj) " +
						"VALUES ('" + txtAdj + "');");
		PreparedStatement pst = DBConnector.conn.prepareStatement(query);				
		pst.execute();				
		pst.close();		
	} catch (Exception e) {
		System.err.println(e);
	}
	
	show = new DBShow(txtAdj); 
	show.setAdj(txtAdj);	
	MakeCenterBoxes.adjTable.getItems().add(show);
	}



//--------------------------------------------------------------------------------------------------------------------
	
public static void deleteEntry(String lexList) {
		
/* Löscht einen angewählten Eintrag aus der Datenbank und 
 * aktualisiert die Listenanzeige im Datenbankreiter
 */
		ObservableList<DBShow> selectList, fullList;
				
		if (lexList.equals("verben")){
			
			String selectedItem = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItem().getStamm();

			try {
				String query = ("DELETE FROM " + MakeLeftBoxes.lexList + " WHERE Stamm = '" + selectedItem + "';");
				PreparedStatement pst = DBConnector.conn.prepareStatement(query);			
				pst.execute();				

				pst.close();
			} catch (Exception e2) {
				System.err.println(e2);
			}
			fullList = MakeCenterBoxes.verbTable.getItems();
			selectList = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItems();		
			
			selectList.forEach(fullList::remove);
		} else if (lexList.equals("adjektive")) {
			
			String selectedItem = MakeCenterBoxes.adjTable.getSelectionModel().getSelectedItem().getAdj();

			try {
				String query = ("DELETE FROM " + MakeLeftBoxes.lexList + " WHERE Adj = '" + selectedItem + "';");
				PreparedStatement pst = DBConnector.conn.prepareStatement(query);			
				pst.execute();		

				pst.close();
			} catch (Exception e2) {
				System.err.println(e2);
			}
			fullList = MakeCenterBoxes.adjTable.getItems();
			selectList = MakeCenterBoxes.adjTable.getSelectionModel().getSelectedItems();
			
			selectList.forEach(fullList::remove); // :: = Methodenreferenz 
		}
	}
			
//----------------------------------------------------------------------------------------------------------------------
			
public static void changeEntry(String lexList) {
	
/* Ruft das Fenster zum Ändern eines Eintrags auf. Übergibt den Inhalt der markierten Zeile.
 * 	
 */
	String[] selItem = new String[4];
	if (lexList.equals("verben")) {
		selItem[0] = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItem().getStamm();
		selItem[1] = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItem().getBasis();
		selItem[2] = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItem().getKlasse();
		selItem[3] = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItem().getPartizip();		
	
		DBUtilsChngVerb.setChngWindowForVerb(selItem);
	}
	if (lexList.equals("adjektive")) {
		selItem[0] = MakeCenterBoxes.adjTable.getSelectionModel().getSelectedItem().getAdj();
		
		DBUtilsChngAdj.setChngWindowForAdj(selItem);
	}	
}	
	

protected static void updateVerbDB(TextField txtStamm, TextField txtBasis, TextField txtKlasse, TextField txtPartizip) {
	
/* ändert den angewählten Eintrag in der Datenbank und
 * aktualisiert die Listenanzeige im Datenbankreiter
 */

	TextField[] chngInput = new TextField[] {txtStamm, txtBasis, txtKlasse, txtPartizip};
	
	for(TextField x:chngInput){
		if (x.getText().equals(""))
		System.out.println(x.promptTextProperty().get());
		else
		System.out.println(x.getText());

/*
		
		ObservableList<DBShow> selItem = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItems(); 
		
		Label lStamm, lBasis, lPartizip, lKlasse;
				
		String stamm = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItem().getStamm();
		String basis = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItem().getBasis();
		String klasse = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItem().getKlasse();
		String partizip = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItem().getPartizip();
		
		String[] items = {stamm,basis,klasse,partizip};
		
		for (String string : items) {
			
		}
		
		lStamm = new Label(stamm);
		lBasis = new Label(basis);
		lKlasse = new Label(klasse);
		lPartizip = new Label(partizip);
		
		*/
		
		
	}

}
			

protected static void updateAdjDB() {

	// Code um die Adjektivdatenbank und die Listenanzeige im Datenbankreiter zu aktualisieren.
	}

//----------------------------------------------------------------------------------------------------------------------
		
public static String searchVEntry(String stamm, String basis) {
	String vEntry ="";
	//ObservableList<DBShow> selItem = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItems();
	return vEntry;

	}

public static String searchAEntry(String adj) {
	String aEntry = "";
	//ObservableList<DBShow> selItem = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItems();
	return aEntry;

	}
			
//----------------------------------------------------------------------------------------------------------------------

/*
 * liest die ID eines Eintrags aus der Datenbank aus und stellt den Wert als Integer zur Verfügung
 */

public static int getDBItemID(String lexList) {
	
	DBShow item = null;
	
	if (lexList.equals("verben")){
		ObservableList<DBShow> selItem = MakeCenterBoxes.verbTable.getSelectionModel().getSelectedItems();
		item = selItem.get(0);
	} else if (lexList.equals("adjektive")){
		ObservableList<DBShow> selItem = MakeCenterBoxes.adjTable.getSelectionModel().getSelectedItems();
		item = selItem.get(0);
	}
	
	int id = 0;
	String conc = "";
		
	try {
	String queryID = ("SELECT id FROM " + MakeLeftBoxes.lexList + " WHERE ");
	
		
		if (lexList.equals("verben")){
			conc = ("Stamm = '" + item.getStamm() + "' AND Basis = '" + item.getBasis() + "';)"); 
		} else if (lexList.equals("adjektive")){
			conc = ("Adj = '" + item.getAdj() + "';)");
		}
			
	queryID = queryID.concat(conc);
	

	PreparedStatement pst = DBConnector.conn.prepareStatement(queryID);
	ResultSet rs = pst.executeQuery();
	
	id = rs.getInt(1);
	
	pst.close();
	rs.close();
	
	} catch (Exception e) {
		System.err.println(e);
	}
	return id;
}


//----------------------------------------------------------------------------------------------------------------------

public static void switchDB() {

/*
* Schiebt die angewählte Datenbank in den Hintergrund.
*/
		
		ObservableList<Node> xy = MakeCenterBoxes.centerDBBox.getChildren();
		if (xy.size() > 1) {
			Node topNode = xy.get(xy.size()-1);
			topNode.toBack();
		}  		
	}			
			
//----------------------------------------------------------------------------------------------------------------------

public static boolean isItemSelected(String lexList) {
	
/*
 * Testet, ob eine Zelle in der Verb- oder Adjektivtaelle ausgewählt ist.
 */
	
		ReadOnlyIntegerProperty chkSelect = null;
		if (lexList.equals("verben")){
			chkSelect = MakeCenterBoxes.verbTable.getSelectionModel().selectedIndexProperty();
		} else if (lexList.equals("adjektive")){
			chkSelect = MakeCenterBoxes.adjTable.getSelectionModel().selectedIndexProperty();
		}
		if (chkSelect.get() == -1 || chkSelect == null) {
			return false;
		} else return true;
	}

//------------------------------------------------------------------------------------------------------

public static DBCount makeDBCounter(){
	
	int[] count = {0,0};
	count[0] = DBUtils.entryCount("verben");
	count[1] = DBUtils.entryCount("adjektive");
	
	DBCount entries = new DBCount(count);
	
	return entries;
	}


public static int entryCount(String lexList) {
		
	/*
	 * Gibt die Anzahl der Einträge in einer der Datenbanken zurück.
	 */
	int count = 0;
	
		System.out.println(lexList);
	try {	
	
		String queryCount = ("SELECT COUNT (DISTINCT id) FROM " + lexList + ";");
		
		PreparedStatement pst = DBConnector.conn.prepareStatement(queryCount);
		ResultSet rs = pst.executeQuery();
	
		if (lexList.equals("verben")){			
			count = rs.getInt(1);			
		} else if (lexList.equals("adjektive")){
			count = rs.getInt(1);
		}
					
		pst.close();
		rs.close();
		
	} catch (Exception e) {
		System.err.println(e);
	}
	if (!lexList.equals("")){
		return count;
	} else return -1;
	}


//------------------------------------------------------------------------------------------------------
	
	public static void reCount(String lexList){ 
		if (lexList.equals("verben")) {
			Node countNd = MakeCenterBoxes.centerTransBox.getChildren().get(1);
			Label countLbl = (Label)countNd;
			countLbl.setText(Integer.toString(DBUtils.entryCount(lexList)));
		} else if (lexList.equals("adjektive")) {
			Node countNd = MakeCenterBoxes.centerTransBox.getChildren().get(3);
			Label countLbl = (Label)countNd;
			countLbl.setText(Integer.toString(DBUtils.entryCount(lexList)));
		}
	}

//------------------------------------------------------------------------------------------------------
	
	public static boolean hasVEntry(String stamm, String basis){
		if (searchVEntry(stamm, basis) != "") {
			return true;
		} else return false;
	}
	
	public static boolean hasAEntry(String adj){
		if (searchAEntry(adj) != "") {
			return true;
		} else return false;
	}
}
