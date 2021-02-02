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
import PortalProductores.PolizasModificacionBeneficiarioAsegurado;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class PolizaModificarBeneficiarioAseguradoTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataModificacionBeneficiarioAsegurado = "..\\Ecosistemas-CNP\\Entrada\\modificacionBeneficiarioAsegurado.csv";
	private String nombreArchivoEvidencias;
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
	}
	
	@Test(dataProvider = "Modificar Beneficiario Asegurado")
	public void modificarBeneficiarioAseguradoPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String apellidoBeneficiario, String nombreBeneficiario, String tipodni, String dni, String vinculo, String relacion, String designacion, String observaciones) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Modificación de Beneficiario Asegurado - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de actividad laboral", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", observaciones: " + observaciones, 10);
		
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
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Sección a modificar");
		
		modificacionpolizas.clicOnModificarAsegurado();
		
		PolizasModificacionBeneficiarioAsegurado modificaAsegurado = new PolizasModificacionBeneficiarioAsegurado(driver);
		Utilities.waiter(5);
		modificaAsegurado.eliminarAsegurado();
		modificaAsegurado.cargaDatosBeneficiarioAsegurado(apellidoBeneficiario, nombreBeneficiario, tipodni, dni, vinculo, relacion, designacion);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos del nuevo asegurado");

		modificaAsegurado.clicOnAgregaAsegurado();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado agregado");

		modificaAsegurado.clicOnGuardarAsegurado();
		Utilities.waiter(2);
		modificaAsegurado.clicOnConfirmarAsegurado();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificaAsegurado.clicOnCerrarAsegurado();
		Utilities.waiter(3);
	}
	
	@DataProvider(name= "Modificar Beneficiario Asegurado")
	public Object[][] obtenerDatosModificarBeneficiarioAsegurado() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificacionBeneficiarioAsegurado, ',', false);
	}

}
