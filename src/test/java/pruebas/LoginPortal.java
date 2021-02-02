package pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import PortalProductores.Login;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class LoginPortal {

	private WebDriver driver;
	private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String archivoDatosAmbiente = "..\\Ecosistemas-CNP\\Entrada\\ambientePortal.csv";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String nombreArchivoEvidencias;
	
	@Test
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", pathDriver);
		
		driver = new ChromeDriver();
		
		Object[][] datosURL = DatosExternos.leerCSV(archivoDatosAmbiente, ',', false);
		
		driver.get(datosURL[0][0].toString());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		System.out.println("Inicio de Suite de Pruebas");

		Login login = new Login(driver);
		login.fillCredentials(datosURL[0][1].toString(), datosURL[0][2].toString());
		login.clicOnButton();
		
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Login - " + Utilities.obtenerFechaActual() + ".docx";
		
		// El browser se abre dos veces: Revisar, sólo ocurre en la automatización
		login.fillCredentials(datosURL[0][1].toString(), datosURL[0][2].toString());
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Login");

		login.clicOnButton();
				
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla Principal");
		
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
