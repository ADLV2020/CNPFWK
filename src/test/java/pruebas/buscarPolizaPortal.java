package pruebas;


import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import PortalProductores.Menu;
import PortalProductores.Policy;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class buscarPolizaPortal {

	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataPolizas = "..\\Ecosistemas-CNP\\Entrada\\polizas.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		
	}
	
	@Test(dataProvider = "Buscar Poliza")
	public void buscarPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		menu = new Menu(driver);
		buscarPolizaEvidencia(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta,  ramo);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo, driver);

	}
	
	
	public void buscarPolizaEvidencia(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Buscar póliza - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda de pólizas", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
	}
	
	
	public void realizaBusqueda(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, WebDriver driver) throws InvalidFormatException, IOException, InterruptedException {
		String mensajeError = "No existen registros que coincidan con la búsqueda especificada. Por favor, ajuste sus criterios de búsqueda e inténtelo nuevamente.";
		menu = new Menu(driver);
		menu.clicOnPolizas();
		
		Utilities.waiter(5);
		
		Policy policy = new Policy(driver);
		Assert.assertTrue(policy.pantallaPolizaDisponible());
		policy.enterSearchCriteria(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos para la realización de la Búsqueda");

		policy.clicOnSearch();
		Utilities.realizarScrollDown(driver);
		
		Utilities.waiter(3);
		Utilities.realizarScrollDown(driver);
		Assert.assertTrue(policy.busquedaSinResultado().equals(mensajeError) || policy.resultadoBusquedaDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la Búsqueda");
		Utilities.waiter(5);
	}
	
	
	@DataProvider(name = "Buscar Poliza")
	public Object[][] obtenerDatosEntradaConsultaPolizas() throws Exception {
		return DatosExternos.leerCSV(archivoDataPolizas, ',', false);
	}
}
