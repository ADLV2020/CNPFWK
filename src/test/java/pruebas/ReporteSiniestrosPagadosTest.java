package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import NovaSiniestros.Login;
import NovaSiniestros.Menu;
import NovaSiniestros.ReporteSiniestrosPagados;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class ReporteSiniestrosPagadosTest {
	private WebDriver driver;
	private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDatosAmbiente = "..\\Ecosistemas-CNP\\Entrada\\ambienteNova.csv";
	private String archivoDataReporteSiniestrosPagados = "..\\Ecosistemas-CNP\\Entrada\\reporteSiniestrosPagados.csv";
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
		nombreArchivoEvidencias = "NovaSiniestros - Testing - Evidencias - Reporte de Siniestros Pagados - " + Utilities.obtenerFechaActual() + ".docx";
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
		//Assert.assertTrue(menu.pantallaMenuDisponible(), "Error al loguearse");
	}
	
	@Test(dataProvider = "Reporte Siniestros Pagados")
	public void reporteSiniestrosPagados(String fechaDesde, String fechaHasta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Reporte de Siniestros Pagados", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha desde: " + fechaDesde + ", Fecha hasta: " + fechaHasta, 10);
		
		menu.clicOnDespliegaReportes();
		menu.clicOnReporteSiniestrosPagados();
		
		ReporteSiniestrosPagados reporte = new ReporteSiniestrosPagados(driver);
		Assert.assertTrue(reporte.pantallaReporteDisponible());

		reporte.ingresaFechas(fechaDesde, fechaHasta);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingreso de fechas para la búsqueda");
		reporte.clicOnBuscar();
		
		Utilities.waiter(10);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado");
		
		reporte.clicOnDescargarPDF();
		reporte.clicOnDescargarExcel();
	}
	
	@DataProvider(name = "Reporte Siniestros Pagados")
	public Object[][] obtenerDatosEntradaReporteSiniestrosPagados() throws Exception {
		return DatosExternos.leerCSV(archivoDataReporteSiniestrosPagados, ',', false);
	}

}
