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
import PortalProductores.PolizasModificacionActividadLaboral;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class PolizaModificarActividadTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataModificacionActividadLaboral = "..\\Ecosistemas-CNP\\Entrada\\modificacionActividadLaboral.csv";
	private String nombreArchivoEvidencias;
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
	}

	
	@Test(dataProvider = "Modificar Actividad Laboral")
	public void modificarActividadLaboralPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String actividad, String observaciones, String prioridad) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Modificación de actividad laboral - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de actividad laboral", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", prioridad: " + prioridad + ", actividad laboral: " + actividad + ", observaciones: " + observaciones, 10);
		
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
		
		modificacionpolizas.clicOnModificarActividadLaboral();
		
		PolizasModificacionActividadLaboral modificaActividad = new PolizasModificacionActividadLaboral(driver);
		Utilities.waiter(5);
		modificaActividad.cargaDatosActividadLaboral(actividad, observaciones, prioridad);
		
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cambios realizados");
		
		
		modificaActividad.clicOnGuardarActividad();
		Utilities.waiter(2);
		modificaActividad.clicOnConfirmarActividad();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificaActividad.clicOnCerrarActividad();
		Utilities.waiter(3);
	}
	
	@DataProvider(name= "Modificar Actividad Laboral")
	public Object[][] obtenerDatosModificarActividadLaboral() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificacionActividadLaboral, ',', false);
	}
}
