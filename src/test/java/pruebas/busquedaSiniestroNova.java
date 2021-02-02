package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import NovaSiniestros.Busqueda;
import NovaSiniestros.Login;
import NovaSiniestros.Menu;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class busquedaSiniestroNova {
	private WebDriver driver;
	private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataBusqueda = "..\\Ecosistemas-CNP\\Entrada\\datosBusqueda.csv";
	private String archivoDatosAmbiente = "..\\Ecosistemas-CNP\\Entrada\\ambienteNova.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	@BeforeSuite
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", pathDriver);
		
		driver = new ChromeDriver();
		
		Object[][] datosURL = DatosExternos.leerCSV(archivoDatosAmbiente, ',', false);
		
		driver.get(datosURL[0][0].toString());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		System.out.println("Inicio de Suite de Pruebas");
		nombreArchivoEvidencias = "NovaSiniestros - Testing - Evidencias - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Nova Siniestros – Evidencia de Pruebas", 20);

		Login login = new Login(driver);
		login.ingresaAzure();		
		login.ingresarMail(datosURL[0][1].toString());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingresa email");

		Utilities.waiter(2);
		login.ingresaPassword(datosURL[0][2].toString());
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingresa password");
		login.clicOnLogin();
		Utilities.waiter(2);
		login.clicOnNoMantenerSesion();
		
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Datos Busqueda")
	public void buscarSiniestro(String titulo, String nroSiniestro, String nroPoliza, String nroDNI, String fechaDesde, String fechaHasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "NovaSiniestros - Testing - Evidencias - Búsqueda de siniestros - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, titulo, 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "número de siniestro: " + nroSiniestro + ", dni: " + nroDNI + ", fecha desde: " + fechaDesde  + ", fecha hasta: " + fechaHasta  + ", ramo: " + ramo, 10);
		
		menu.clicOnBusqueda();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla de búsqueda");
		
		Busqueda busqueda = new Busqueda(driver);
		Assert.assertTrue(busqueda.pantallaBusquedaDisponible());
		busqueda.ingresarDatosBusqueda(nroSiniestro, nroPoliza, nroDNI, fechaDesde, fechaHasta, ramo);
		Utilities.waiter(2);

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos para la realización de la Búsqueda");
		busqueda.clicOnBuscar();
		Utilities.waiter(2);

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la búsqueda");
		
		busqueda.clicOnDetalles();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles del siniestro");
		Assert.assertTrue(busqueda.pantallaSiniestroDisponible());
		
	}
	
	@DataProvider(name = "Datos Busqueda")
	public Object[][] obtenerDatosEntradaBusqueda() throws Exception {
		return DatosExternos.leerCSV(archivoDataBusqueda, ',', false);	
	}

}
