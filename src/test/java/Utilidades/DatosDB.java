package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatosDB {
	public static Object[][] consultarDB(String dbName, String queryDB) throws  ClassNotFoundException, SQLException {													
		//Connection URL MySQL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		//Connection URL SQL Server Syntax: jdbc:sqlserver://<server>:<port>;databaseName=AdventureWorks;user=<user>;password=<password>
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;					

		//Database Username, no se usa en SQL Server
		String username = "root";	
        
		//Database Password, no se usa en SQL Server
		String password = "";				

		//Query to Execute		
		String query = queryDB;	
        
 	    //Load mysql jdbc driver: com.mysql.jdbc.Driver	
		//Load SQL Server driver: com.microsoft.sqlserver.jdbc.SQLServerDriver
   	    Class.forName("com.mysql.jdbc.Driver");			
   
   		//Create Connection to DB
   	    // SQL Server: Connection con = DriverManager.getConnection(dbUrl,username,password);
    	Connection con = DriverManager.getConnection(dbUrl,username,password);
  
  		//Create Statement Object		
	   Statement stmt = con.createStatement();					

		// Execute the SQL Query. Store results in ResultSet		
 		ResultSet rs= stmt.executeQuery(query);							
 		
 		ResultSetMetaData rsmd = rs.getMetaData();

 		int columnas = rsmd.getColumnCount();
 		
 		int filas = 0;
 		
 		boolean ultimo = rs.last();
 		    
 		if (ultimo) { 
 			filas = rs.getRow();
 		}
 		
 		Object[][] datos = new Object[filas][columnas];
 		
 		// While Loop to iterate through all data and print results		
 		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				datos[i][j] = rs.getObject(j + 1);
				System.out.println("datosDB[" + i + "][" + j + "] = " + datos[i][j]);
			}
        }		
		
		// closing DB Connection		
		con.close();	
		
		return datos;
}
}
