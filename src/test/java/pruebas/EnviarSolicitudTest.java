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

public class EnviarSolicitudTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataEnviarSolicitud = "..\\Ecosistemas-CNP\\Entrada\\enviarSolicitudes.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Enviar Solicitud")
	public void enviarSolicitudes(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley, String tarjeta, String numTarjeta) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Enviar Solicitudes - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Enviar Solicitud", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "nroSolicitud: " + nroSolicitud + ", fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta  + ", nombreTomador: " + nombreTomador  + ", documentoTomador: " + documentoTomador + ", estado: " + estado + ", nombreAsegurado: " + nombreAsegurado + ", documentoAsegurado: " + documentoAsegurado + ", ramo: " + ramo + ", productor: " + productor, 10);

		menu.clicOnSolicitudes();
		
		Solicitudes solicitudes = new Solicitudes(driver);
		solicitudes.cargaDatosConsulta(nroSolicitud, fechaDesde, fechaHasta, nombreTomador, documentoTomador, estado, nombreAsegurado, documentoAsegurado, ramo, productor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la búsqueda");
		Utilities.waiter(3);
		solicitudes.clicOnBuscar();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la búsqueda");
		solicitudes.clicOnEnviar();
		Utilities.waiter(5);
		solicitudes.clicOnSiConfirmar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Solicitud enviada");
		solicitudes.clicOnCerrar();
	}
	
	@DataProvider(name = "Enviar Solicitud")
	public Object[][] obtenerDatosEntradaEnviarSolicitud() throws Exception {
		return DatosExternos.leerCSV(archivoDataEnviarSolicitud, ',', false);
	}

}
