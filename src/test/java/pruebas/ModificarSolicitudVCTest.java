package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.Menu;
import PortalProductores.ModificarSolicitudVC;
import PortalProductores.Solicitudes;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class ModificarSolicitudVCTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataModificarVC = "..\\Ecosistemas-CNP\\Entrada\\modificarVC.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	
	@Test(dataProvider = "Modificar VC")
	public void modificarVC(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley, String tarjeta, String numTarjeta) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Modificar Solicitud Vida Colectiva - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificar Solicitud Vida Colectiva", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "nroSolicitud: " + nroSolicitud + ", fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta  + ", nombreTomador: " + nombreTomador  + ", documentoTomador: " + documentoTomador + ", estado: " + estado + ", nombreAsegurado: " + nombreAsegurado + ", documentoAsegurado: " + documentoAsegurado + ", ramo: " + ramo + ", productor: " + productor, 10);

		
		menu.clicOnSolicitudes();
		
		Solicitudes solicitudes = new Solicitudes(driver);
		solicitudes.cargaDatosConsulta(nroSolicitud, fechaDesde, fechaHasta, nombreTomador, documentoTomador, estado, nombreAsegurado, documentoAsegurado, ramo, productor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la búsqueda");
		Utilities.waiter(3);
		solicitudes.clicOnBuscar();
		solicitudes.clicOnEditar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Campos a modificar");
		
		ModificarSolicitudVC modificarSolVC = new ModificarSolicitudVC(driver);
		
		modificarSolVC.cargarDatosPersonales(nacionalidad, estadoCivil, fechaNacimiento, calle, numCalle, piso, dpto, localidad, codPostal, actividad, mail, telEmpresa, celular, politica, ley);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos personales");

		modificarSolVC.cargaMedioPago(tarjeta, numTarjeta);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de medio de pago");

		modificarSolVC.clicOnGuardar();
		modificarSolVC.clicOnSiConfirmar();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");

		modificarSolVC.clicOnCerrar();
	}
	
	@DataProvider(name = "Modificar VC")
	public Object[][] obtenerDatosEntradaModificarVC() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificarVC, ',', false);
	}

}
