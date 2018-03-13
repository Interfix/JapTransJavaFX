package morphology.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {


//Klassenvariablen------------------------------------------------------------------------------------------------------
	
		public static Connection conn = null;
		
		private static final String driverName = "org.sqlite.JDBC";
		private static final String driver = "jdbc:sqlite";
		private static final String dbFile = "Lexeme.sqlite";
			
		
//Klassenmethoden-------------------------------------------------------------------------------------------------------

		public static void registerDriver() throws ClassNotFoundException
		{
			Class.forName(driverName);
			System.out.println("register");
		}
		
//----------------------------------------------------------------------------------------------------------------------
		
		public static Connection connectDB() throws SQLException
		{
			try {
				if (DBConnector.conn == null || DBConnector.conn.isClosed())
					{
						conn = DriverManager.getConnection(driver+":"+dbFile);
						SwitchButton.switchImage();
						System.out.println("connect");
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
				
						
			return conn;
		}
		
//----------------------------------------------------------------------------------------------------------------------
		
		public static void shutdownDBConn()
		{
			try {
				if (!DBConnector.conn.isClosed()) {
					DBConnector.conn.close();
					SwitchButton.switchImage();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		
		
//Konstruktoren---------------------------------------------------------------------------------------------------------
		
		public DBConnector()
		{
			
		}
		
		public DBConnector (String driverName)
		{
			
		}
	
	
	
}
