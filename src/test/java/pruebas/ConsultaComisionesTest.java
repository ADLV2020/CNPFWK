package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.CierreComisiones;
import PortalProductores.Menu;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class ConsultaComisionesTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataComisiones = "..\\Ecosistemas-CNP\\Entrada\\comisiones.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Consulta Cierres Comisiones")
	public void consultaCierresComisiones(String fechaInicio, String fechaFin, String nombreProductor, String codigoProductor) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Consulta de Comisiones - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Consulta de Cierres de comisiones", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "fechaInicio: " + fechaInicio + ", fechaFin: " + fechaFin + ", nombreProductor: " + nombreProductor  + ", codigoProductor: " + codigoProductor, 10);

		menu.clicOnCierres();
		
		CierreComisiones comisiones = new CierreComisiones(driver);
		
		comisiones.cargarDatosBusqueda(fechaInicio, fechaFin);
		comisiones.clicOnLupaProductor();
		comisiones.cargaDatosProductor(nombreProductor, codigoProductor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda de productor");
		comisiones.clicOnSumarProductor();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de búsqueda");

		comisiones.clicOnBuscar();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda de comisiones");
		
		comisiones.clicOnConsultar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cierres de comisiones");

	}
	
	@DataProvider(name= "Consulta Cierres Comisiones")
	public Object[][] obtenerDatosCierresComisiones() throws Exception {
		return DatosExternos.leerCSV(archivoDataComisiones, ',', false);
	}


}
