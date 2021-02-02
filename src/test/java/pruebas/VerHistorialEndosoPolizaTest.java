package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.ModificacionPolizas;
import PortalProductores.Policy;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class VerHistorialEndosoPolizaTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataVerHistorialEndoso = "..\\Ecosistemas-CNP\\Entrada\\verHistorialEndoso.csv";
	private String nombreArchivoEvidencias;
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
	}
	
	@Test(dataProvider = "Ver Historial Endoso Poliza")
	public void verHistorialEndosoPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Ver Historial de Endosos - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ver historial endoso", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador, 10);
		
		buscarPolizaPortal buscar = new buscarPolizaPortal();
		
		Policy policy = new Policy(driver);
		buscar.realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo, driver);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Endosos disponibles");
		
		modificacionpolizas.clicOnDescargarHistorial();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Hisotiral de Endoso");
		modificacionpolizas.clicnOnSalirHistorial();
	}
	
	@DataProvider(name= "Ver Historial Endoso Poliza")
	public Object[][] obtenerDatosVerHistorialEndosoPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataVerHistorialEndoso, ',', false);
	}

}
