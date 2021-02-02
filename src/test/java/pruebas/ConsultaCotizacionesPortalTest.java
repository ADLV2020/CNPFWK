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

public class ConsultaCotizacionesPortalTest {
	
	private WebDriver driver;
	//private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataConsultaCotizacion = "..\\Ecosistemas-CNP\\Entrada\\consultaCotizaciones.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Consultar Cotizaciones")
	public void consultarCotizaciones(String fechaDesde, String fechaHasta, String ramo, String nombreAsegurado, String apellidoAsegurado, String productor, String codigo) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Consulta de cotizaciones - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Consulta de cotización", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "fecha desde: " + fechaDesde + ", fecha hasta: " + fechaHasta + ", ramo: " + ramo  + ", nombre: " + nombreAsegurado  + ", apellido: " + apellidoAsegurado + ", productor: " + productor, 10);
		
		menu.clicOnCotizaciones();
		
		Quotation cotizaciones = new Quotation(driver);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla de búsqueda");

		cotizaciones.insertaDatosConsulta(fechaDesde, fechaHasta, ramo, nombreAsegurado, apellidoAsegurado, productor, codigo);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos para la búsqueda");
		
		cotizaciones.clicOnDetalles();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la cotización");
	}
	
	@DataProvider(name = "Consultar Cotizaciones")
	public Object[][] obtenerDatosEntradaConsultarCotizacion() throws Exception {
		return DatosExternos.leerCSV(archivoDataConsultaCotizacion, ',', false);
	}

}
