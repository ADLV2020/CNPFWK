package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.Menu;
import PortalProductores.ModificarSolicitudAP;
import PortalProductores.Solicitudes;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class ModificarSolicitudAPTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataModificarAP = "..\\Ecosistemas-CNP\\Entrada\\modificarAP.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Modificar AP")
	public void modificarAP(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley, String tarjeta, String numTarjeta) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Modificar Solicitud Accidentes Personales - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificar Solicitud Accidentes Personales", 20);
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
		
		ModificarSolicitudAP modificarSolAP = new ModificarSolicitudAP(driver);
		
		modificarSolAP.cargarDatosPersonales(nacionalidad, estadoCivil, fechaNacimiento, calle, numCalle, piso, dpto, localidad, codPostal, actividad, mail, telEmpresa, celular, politica, ley);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos personales");

		modificarSolAP.cargaMedioPago(tarjeta, numTarjeta);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de medio de pago");

		modificarSolAP.clicOnGuardar();
		modificarSolAP.clicOnSiConfirmar();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");

		modificarSolAP.clicOnCerrar();
	}
	
	@DataProvider(name = "Modificar AP")
	public Object[][] obtenerDatosEntradaModificarAP() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificarAP, ',', false);
	}
	

}
