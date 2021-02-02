package Utilidades;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader; 

public class DatosExternos {
	public static Object[][] leerCSV(String ruta, char separator, boolean inicioPrimeraFila) throws Exception {
		 char QUOTE = '"';
		 Object[][] dataValues = null;
		 CSVReader reader = null;
	          
	     try {
	    	 reader = new CSVReader(new FileReader(ruta), separator, QUOTE);
	         List<String[]> datos = reader.readAll();
	         String[] registro;
	         
	         if (datos.size() > 0) {
	        	 registro = datos.get(0);
	        	 dataValues = new Object[datos.size() - (inicioPrimeraFila ? 0 : 1)][registro.length];
		         
		         for (int i = (inicioPrimeraFila ? 0 : 1); i < datos.size(); i++) {
		        	 registro = datos.get(i);
		        	 
		        	 for (int j = 0; j < registro.length; j++) {
		        		  dataValues[i - (inicioPrimeraFila ? 0 : 1)][j] = registro[j];
		        		  //System.out.println("datosCSV[" + (i - (inicioPrimeraFila ? 0 : 1)) + "][" + j + "] = " + dataValues[i - (inicioPrimeraFila ? 0 : 1)][j]);
		        	 }
		         }
	         }
	      } catch (Exception e) {
	         System.out.println(e);
	      } finally {
	         if (null != reader) {
	            reader.close();
	         } 
	      }
	     
	     return dataValues;
	   }
}
