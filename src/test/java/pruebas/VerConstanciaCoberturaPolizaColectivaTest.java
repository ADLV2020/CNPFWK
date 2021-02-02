package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.Menu;
import PortalProductores.ModificacionPolizas;
import PortalProductores.Policy;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class VerConstanciaCoberturaPolizaColectivaTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataConstanciaCoberturaPoliza = "..\\Ecosistemas-CNP\\Entrada\\polizaConstanciaCobertura.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	
	@Test(dataProvider = "Poliza Colectiva Constancia Cobertura")
	public void verConstanciaCoberturaPolizaColectiva(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Ver Constancia de Cobertura - Póliza Colectiva - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - Constancia de Cobertura", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		menu.clicOnPolizas();
		
		buscarPolizaPortal buscar = new buscarPolizaPortal();
		
		Policy policy = new Policy(driver);
		buscar.realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo, driver);

		policy.clicOnVerPerfil();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");

		policy.clicOnAsegurado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado");

		ModificacionPolizas modificacion = new ModificacionPolizas(driver);
		modificacion.clicOnConstanciaCobertura();
		modificacion.clicOnAgregarasegurado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Constancia");

		modificacion.clicOnImprimirConstanciaCobertura();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmar impresión");
		modificacion.clicOnConfirmar();
		
	}
	
	@DataProvider(name= "Poliza Colectiva Constancia Cobertura")
	public Object[][] obtenerDatosConstanciaCoberturaPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataConstanciaCoberturaPoliza, ',', false);
	}

}
