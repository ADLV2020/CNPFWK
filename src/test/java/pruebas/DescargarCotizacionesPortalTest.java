package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.Menu;
import PortalProductores.Quotation;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class DescargarCotizacionesPortalTest {
	private WebDriver driver;
	//private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataDescargarCotizacion = "..\\Ecosistemas-CNP\\Entrada\\descargaCotizaciones.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Descargar Cotizaciones")
	public void descargarCotizaciones(String fechaDesde, String fechaHasta, String ramo, String nombreAsegurado, String apellidoAsegurado, String productor, String codigo) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Descarga de cotizaciones - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Descarga de cotización", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "fecha desde: " + fechaDesde + ", fecha hasta: " + fechaHasta + ", ramo: " + ramo  + ", nombre: " + nombreAsegurado  + ", apellido: " + apellidoAsegurado + ", productor: " + productor, 10);
		
		menu.clicOnCotizaciones();
		
		Quotation cotizaciones = new Quotation(driver);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla de búsqueda");

		cotizaciones.insertaDatosConsulta(fechaDesde, fechaHasta, ramo, nombreAsegurado, apellidoAsegurado, productor, codigo);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos para la búsqueda");
		
		cotizaciones.clicOnDescargar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la cotización");
	}
	
	@DataProvider(name = "Descargar Cotizaciones")
	public Object[][] obtenerDatosEntradaDescargarCotizacion() throws Exception {
		return DatosExternos.leerCSV(archivoDataDescargarCotizacion, ',', false);
	}

}
