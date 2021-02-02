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
import PortalProductores.PolizasModificacionNombreTomador;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class modificarNombreDNITomadorPoliza {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataModificaNombreDocumentoTomador = "..\\Ecosistemas-CNP\\Entrada\\modificaNombreTomador.csv";
	private String nombreArchivoEvidencias;
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
	}
	
	@Test(dataProvider = "Modifica Nombre y Documento Tomador")
	public void modificarPNombreDocumentoTomadorPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String apellidoAsegurado, String nombreAsegurado, String tipoDNI, String dni, String observacion, String prioridad) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Modificación de nombre y documento del tomador - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de nombre y documento del tomador", 20);
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
		
		modificacionpolizas.clicOnModificarNombreDocumentoAsegurado();
		
		PolizasModificacionNombreTomador modificaNombreTomador = new PolizasModificacionNombreTomador(driver);
		
		modificaNombreTomador.cargaDatosNombreTomador(apellidoAsegurado, nombreAsegurado, tipoDNI, dni, observacion, prioridad);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Nuevos datos cargados");

		modificaNombreTomador.clicOnGuardarTomador();
		Utilities.waiter(2);
		modificaNombreTomador.clicOnConfirmarTomador();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificaNombreTomador.clicOnCerrarTomador();
		Utilities.waiter(3);
	}
	
	@DataProvider(name= "Modifica Nombre y Documento Tomador")
	public Object[][] obtenerDatosModificaNombreDocumentoTomadorPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificaNombreDocumentoTomador, ',', false);
	}

}
