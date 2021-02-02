package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import NovaSiniestros.Denuncias;
import NovaSiniestros.Login;
import NovaSiniestros.Menu;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class creacionDenunciaNova {
	
	private WebDriver driver;
	private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataCrearSiniestros = "..\\Ecosistemas-CNP\\Entrada\\datosCrearSiniestros.csv";
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
	
	
	@Test(dataProvider = "Crea Nuevo Siniestro")
	public void cargarDenuncia(String titulo, String denunciante, String dni, String fechaNotificacion, String fechaSiniestro, String horaSiniestro, String ramo, String causa, String tipoAccidente, String cobertura, String hechoGenerador, String tipoLesion, String sucursal, String estado, String subEstado, String moneda) throws InvalidFormatException, IOException, InterruptedException{
		nombreArchivoEvidencias = "NovaSiniestros - Testing - Evidencias - Creación de nueva denuncia - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, titulo, 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "denunciante: " + denunciante + ", dni: " + dni + ", fecha de notificación: " + fechaNotificacion + ", fecha del siniestro: " + fechaSiniestro  + ", hora del siniestro: " + horaSiniestro  + ", ramo: " + ramo + ", causa: " + causa + ", tipo de accidente: " + tipoAccidente + ", cobertura: " + cobertura + ", hecho generador: " + hechoGenerador + ", tipo de lesión: " + tipoLesion + ", sucursal: " + sucursal + ", estado: " + estado + ", sub estado: " + subEstado + ", moneda: " + moneda, 10);
		Utilities.waiter(2);
		
		menu.clicOnDenuncias();
		
		Denuncias denuncias = new Denuncias(driver);
		
		Utilities.waiter(1);
		Assert.assertTrue(denuncias.paginaDenunciasSiniestrosDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Crear nueva denuncia");
		
		denuncias.clicOnNuevoRegistro();
		denuncias.ingresaDenunciante(denunciante);
		Utilities.waiter(3);
		denuncias.ingresarDatosBusqueda(dni);
		Utilities.waiter(9);
		denuncias.seleccionaResultadoBusquedaDNI(denunciante, dni);
		denuncias.ingresaFechaSiniestro(fechaSiniestro);
		denuncias.ingresaFechaNotificacion(fechaNotificacion);
		denuncias.ingresarHoraSiniestro(horaSiniestro);
		
		Assert.assertTrue(denuncias.paginaNuevaDenunciaDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos del siniestro");
		
		denuncias.clicOnAgregarAsegurado();
		denuncias.clicOnGuardarDenuncia();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Siniestro guardado");
		
		denuncias.clicOnLupa();
		Assert.assertTrue(denuncias.paginaSiniestrosDisponible());
		
		denuncias.seleccionarPoliza();
		
		Assert.assertTrue(denuncias.paginaSeleccionaPolizasDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Selecciona una póliza");
		
		denuncias.clicOnGuardarPolizaSeleccionada();
		Utilities.waiter(5);
		
		denuncias.seleccionarSucursal(ramo, sucursal);
		denuncias.seleccionarCausaSiniestro(causa);
		denuncias.seleccionarTipoAccidente(tipoAccidente);
		Utilities.waiter(3);
		denuncias.clicOnDesplegarSubReclamos();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Despliega subreclamo");

		Utilities.waiter(3);
		Utilities.realizarScrollDown(driver);
		denuncias.clicOnAgregarSubReclamo();
		denuncias.cargaCamposSubreclamo(estado, subEstado, moneda, cobertura, hechoGenerador, tipoLesion);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Campos del subreclamo");
		Utilities.waiter(3);
		
		denuncias.guardaCamposSubreclamo();
		denuncias.clicOnGuardarDenuncia();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Número de siniestro");
		denuncias.aceptarSubReclamoAgregado();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Denuncia finalizada");

		
	}
	
	@DataProvider(name = "Crea Nuevo Siniestro")
	public Object[][] obtenerDatosEntradaSiniestro() throws Exception {
		return DatosExternos.leerCSV(archivoDataCrearSiniestros, ',', false);
	}

}
