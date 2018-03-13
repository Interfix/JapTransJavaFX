package morphology.app;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DBCarpenter {


	public static TableView<DBShow> tableMaker (String dbList) {
		
		TableView<DBShow> table = new TableView<DBShow>();
		

		if (dbList == "verben") {

			TableColumn<DBShow, String> idColumn = new TableColumn<DBShow, String>("ID");
			idColumn.setMinWidth(141);
			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			
			TableColumn<DBShow, String> stammColumn = new TableColumn<DBShow, String>("Stamm");
			stammColumn.setMinWidth(141);
			stammColumn.setCellValueFactory(new PropertyValueFactory<>("stamm"));
			
			TableColumn<DBShow, String> basisColumn = new TableColumn<DBShow, String>("Basis");
			basisColumn.setMinWidth(141);
			basisColumn.setCellValueFactory(new PropertyValueFactory<>("basis"));
			
			TableColumn<DBShow, String> klasseColumn = new TableColumn<DBShow, String>("Verbklasse");
			klasseColumn.setMinWidth(141);
			klasseColumn.setCellValueFactory(new PropertyValueFactory<>("klasse"));
			
			TableColumn<DBShow, String> partizipColumn = new TableColumn<DBShow, String>("Partizip");
			partizipColumn.setMinWidth(141);
			partizipColumn.setCellValueFactory(new PropertyValueFactory<>("partizip"));	
			
			table.getColumns().add(stammColumn);
			table.getColumns().add(basisColumn);
			table.getColumns().add(klasseColumn);
			table.getColumns().add(partizipColumn);
				
			table.setItems(DBShow.getDBShowFromDB(dbList));
		}
		
		if (dbList == "adjektive") {
			
			TableColumn<DBShow, String> adjColumn = new TableColumn<DBShow, String>("Adjektiv");
			adjColumn.setMinWidth(564);
			adjColumn.setCellValueFactory(new PropertyValueFactory<>("adj"));	
			
			table.getColumns().add(adjColumn);
		
		
			table.setItems(DBShow.getDBShowFromDB(dbList));
		}
		
		return table;
	}
		
}
