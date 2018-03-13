package morphology.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBShow {
	
	
//Klassenvariablen-----------------------------------------------------------------------------------------------------
	
	//private SimpleStringProperty vID;
	private SimpleStringProperty vStamm;
	private SimpleStringProperty vBasis;
	private SimpleStringProperty vKlasse;
	private SimpleStringProperty vPartizip;
	private SimpleStringProperty vAdj;
	
	
// Constructors -------------------------------------------------------------------------------------------------------	
	
	public DBShow(){
		
	}
	
	public DBShow(String stamm, String basis, String klasse, String partizip) {
		
		//vID = new SimpleStringProperty(id);
		vStamm = new SimpleStringProperty(stamm);
		vBasis = new SimpleStringProperty(basis);
		vKlasse = new SimpleStringProperty(klasse);
		vPartizip = new SimpleStringProperty(partizip);
	}
	
	public DBShow(String adj) {
		//vID = new SimpleStringProperty(id);
		vAdj = new SimpleStringProperty(adj);
	}	
	
// Class Methods -----------------------------------------------------------------------------------------------------
	
	public static ObservableList<DBShow> getDBShowFromDB(String dbList) {
					
		ObservableList<DBShow> vList = FXCollections.observableArrayList();
				
		try {
				String query = ("SELECT * FROM " + dbList);
				PreparedStatement pst = DBConnector.conn.prepareStatement(query);				
				ResultSet rs = pst.executeQuery();
				
				if (dbList.equals("verben")){
				while(rs.next())
					vList.add(new DBShow(
						//rs.getString("id"),
						rs.getString("Stamm"),
						rs.getString("Basis"),
						rs.getString("Klasse"),
						rs.getString("Partizip")
						));
				}			
				if (dbList.equals("adjektive")){
				while(rs.next())
					vList.add(new DBShow(
						//rs.getString("id"),
						rs.getString("Adj")
						));
				}
				pst.close();
				rs.close();
			} catch (Exception e2) {
				System.err.println(e2);
			}
		return vList;
}

	
		
// Getters and Setters -----------------------------------------------------------------------------------------------

/*	public String getID() {
		return vID.get();
	}*/
	
/*	public void setID(String id) {
		vID.set(id);
	}*/
	
	public String getStamm() {
		return vStamm.get();
	}

	public void setStamm(String stamm) {
		vStamm.set(stamm);
	}

	public String getBasis() {
		return vBasis.get();
	}

	public void setBasis(String basis) {
		vBasis.set(basis);
	}

	public String getKlasse() {
		return vKlasse.get();
	}

	public void setKlasse(String klasse) {
		vKlasse.set(klasse);
	}

	public String getPartizip() {
		return vPartizip.get();
	}

	public void setPartizip(String partizip) {
		vPartizip.set(partizip);
	}
		
	public String getAdj() {
		return vAdj.get();
	}

	public void setAdj(String adj) {
		vAdj.set(adj);
	}	
}
