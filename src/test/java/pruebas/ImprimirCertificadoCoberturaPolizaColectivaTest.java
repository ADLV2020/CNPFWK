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

public class ImprimirCertificadoCoberturaPolizaColectivaTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataImpresionDeudaPoliza = "..\\Ecosistemas-CNP\\Entrada\\impresionDeudaPoliza.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Impresión Certificado Cobertura Póliza")
	public void imprimirCertificadoCoberturaPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Imprimir Certificado de Cobertura - Póliza Colectiva" + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - Imprimir Certificado de Cobertura", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		menu.clicOnPolizas();
		
		buscarPolizaPortal buscar = new buscarPolizaPortal();
		
		Policy policy = new Policy(driver);
		buscar.realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo, driver);

		policy.clicOnVerPerfil();

		ModificacionPolizas modificacionpoliza = new ModificacionPolizas(driver);
		Utilities.waiter(3);
		modificacionpoliza.clicOnAsegurado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado");

		Utilities.waiter(3);
		Utilities.realizarScrollDown(driver);
		modificacionpoliza.clicOnImprimirCertificado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Impresión de Certificado");

	}
	
	@DataProvider(name= "Impresión Certificado Cobertura Póliza")
	public Object[][] obtenerDatosImpresionCertificadoCoberturaPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataImpresionDeudaPoliza, ',', false);
	}

}
