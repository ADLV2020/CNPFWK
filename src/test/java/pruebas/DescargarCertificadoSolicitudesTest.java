package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.Menu;
import PortalProductores.Solicitudes;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class DescargarCertificadoSolicitudesTest {
	
	private WebDriver driver;
	//private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataDescargarCertificadoSolicitud = "..\\Ecosistemas-CNP\\Entrada\\descargaCertificadoSolicitud.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	
	@Test(dataProvider = "Descargar Certificado Solicitud")
	public void descargaCertificadoSolicitudes(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor, String razonSocial, String cuil) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Descargar certificado de solicitud - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Descargar certificado de solicitud", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "nroSolicitud: " + nroSolicitud + ", fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta  + ", nombreTomador: " + nombreTomador  + ", documentoTomador: " + documentoTomador + ", estado: " + estado + ", nombreAsegurado: " + nombreAsegurado + ", documentoAsegurado: " + documentoAsegurado + ", ramo: " + ramo + ", productor: " + productor, 10);

		
		menu.clicOnSolicitudes();
		
		Solicitudes solicitudes = new Solicitudes(driver);
		
		solicitudes.cargaDatosConsulta(nroSolicitud, fechaDesde, fechaHasta, nombreTomador, documentoTomador, estado, nombreAsegurado, documentoAsegurado, ramo, productor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la búsqueda");
		Utilities.waiter(3);
		solicitudes.clicOnBuscar();
		solicitudes.clicOnCertificado();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la solicitud");
		solicitudes.impresionCertificado(razonSocial, cuil);
		solicitudes.clicOnIncluir();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Descarga");
		solicitudes.clicOnDescargar();
	}
	
	@DataProvider(name = "Descargar Certificado Solicitud")
	public Object[][] obtenerDatosEntradaDescargarCertificadoSolicitud() throws Exception {
		return DatosExternos.leerCSV(archivoDataDescargarCertificadoSolicitud, ',', false);
	}

}
