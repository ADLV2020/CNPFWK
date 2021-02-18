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

public class modificarDomicilioTomadorPortal {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataModificacionDomiclio = "..\\Ecosistemas-CNP\\Entrada\\modificacionDomicilio.csv";
	private String nombreArchivoEvidencias;
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
	}

	@Test(dataProvider = "Modificar Domicilio")
	public void modificarDomicilioTomador(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String calle, String altura, String piso, String dto, String localidad, String provincia, String codpostal, String telefono, String mail, String observaciones, String tipopoliza) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Modificación de domicilio del tomador - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de domicilio del tomador - poliza " + tipopoliza, 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		buscarPolizaPortal buscar = new buscarPolizaPortal();
		
		Policy policy = new Policy(driver);
		buscar.realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo, driver);
		Utilities.waiter(3);
		policy.clicOnDetalles();
		Utilities.waiter(5);
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(3);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		
		modificacionpolizas.clicOnInformacionDomiciliaria();
		Utilities.waiter(5);
		modificacionpolizas.modificaInfoDomicio(calle,  altura, piso, dto, localidad, provincia, codpostal, telefono, mail, observaciones);
		Assert.assertTrue(modificacionpolizas.pantallaEdicionCamposDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cambios realizados");
		
		Utilities.waiter(4);
		modificacionpolizas.clicOnGuardarDomicilio();
		Utilities.waiter(6);
		modificacionpolizas.clicOnConfirmarDomicilio();
		Utilities.waiter(10);
		Assert.assertTrue(modificacionpolizas.pantallaConfirmarDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificacionpolizas.clicOnCerrarDomicilio();
		Utilities.realizarScrollUp(driver);
		Utilities.waiter(3);
	}
	
	@DataProvider(name = "Modificar Domicilio")
	public Object[][] obtenerDatosEntradaModificacionDomicilio() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificacionDomiclio, ',', false);
	}
}
