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

public class modificarConductoPagoTomadorPortal {
	
	private WebDriver driver;
	//private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataModificacionConductoPago = "..\\Ecosistemas-CNP\\Entrada\\modificacionConductoPago.csv";
	private String nombreArchivoEvidencias;

	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
	}
	
	
	@Test(dataProvider = "Modificar Conducto Pago")
	public void modificarConductoPagoTomador(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String medioPago, String nroTarjeta, String observaciones, String tipopoliza) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Modificación de conducto de pago del tomador - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de conducto pago - póliza " + tipopoliza, 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", medioPago: " + medioPago + ", nroTarjeta: " + nroTarjeta + ", observaciones: " + observaciones, 10);
		
		buscarPolizaPortal buscar = new buscarPolizaPortal();

		Policy policy = new Policy(driver);
		buscar.realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo, driver);
		//Utilities.waiter(3);
		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(5);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Sección a modificar");
		
		modificacionpolizas.clicOnConductoPagoTomador();
		Utilities.waiter(5);

		modificacionpolizas.modificarMedioDePago(medioPago, nroTarjeta, observaciones);
		Utilities.waiter(3);
		Assert.assertTrue(modificacionpolizas.pantallaEdicionCamposDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cambios realizados");
		
		Utilities.waiter(4);
		modificacionpolizas.clicOnGuardarCambios();
		Utilities.waiter(2);
		modificacionpolizas.clicOnConfirmar();
		Utilities.waiter(3);
		Assert.assertTrue(modificacionpolizas.pantallaConfirmarDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificacionpolizas.clicOnCerrar();
		Utilities.waiter(3);
	}
	
	@DataProvider(name= "Modificar Conducto Pago")
	public Object[][] obtenerDatosModificar() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificacionConductoPago, ',', false);
	}
}
