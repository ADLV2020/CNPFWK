package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import NovaSiniestros.BuscarSiniestroSolicitudPagos;
import NovaSiniestros.Login;
import NovaSiniestros.Menu;
import NovaSiniestros.ModificarItemSolicitudPagos;
import NovaSiniestros.NuevaSolicitudPagos;
import NovaSiniestros.SolicitudPagos;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class creacionSolicitudPagoNova {
	
	private WebDriver driver;
	private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDatosAmbiente = "..\\Ecosistemas-CNP\\Entrada\\ambienteNova.csv";
	private String archivoDataSolicitudPago = "..\\Ecosistemas-CNP\\Entrada\\solicitudPago.csv";
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
	
	@Test(dataProvider = "Solicitud Pago")
	public void solicitudPagos(String pagarA,String sucursal,String fechaRegistro,String fechaContable,String fechaEstimPago, String ramo,String nombreAsegurado,String tipoDocumento,String nroDocumento,String moneda,String nombreCobrador,String impCambio,String impTotalPagar,String desc, String cbu, String nroSiniestroBuscar, String tipoPago, String clasePago, String conceptoPago, String impAPagar) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "NovaSiniestros - Testing - Evidencias - Creación de solicitud de pago - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Solicitud de Pago", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pagar A: " + pagarA + ", Sucursal: " + sucursal + ", Fecha Registro: " + fechaRegistro  + ", Fecha Contable: " + fechaContable  + ", Fecha Estim Pago: " + fechaEstimPago + ", Ramo: " + ramo + ", Nombre asegurado: " + nombreAsegurado + ", tipo Documento: " + tipoDocumento + ", número Documento: " + nroDocumento + ", moneda: " + moneda + ", Nombre Cobrador: " + nombreCobrador + ", impCambio: " + impCambio + ", importe TotalPagar: " + impTotalPagar + ", descripción: " + desc + ", cbu: " + cbu + ", número Siniestro: " + nroSiniestroBuscar + ", tipo Pago: " + tipoPago + ", clase Pago: " + clasePago + ", concepto Pago: " + conceptoPago + ", Importe a Pagar: " + impAPagar, 10);
		
		menu.clicOnSolicitudPagos();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla Solicitud de Pagos");
		SolicitudPagos solicitudPagos = new SolicitudPagos(driver);
		Assert.assertTrue(solicitudPagos.pantallaDisponible());
		
		solicitudPagos.clicOnNuevaSolicitudPago();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla Nueva Solicitud de Pagos");
		NuevaSolicitudPagos nuevaSolicitud = new NuevaSolicitudPagos(driver);
		Assert.assertTrue(nuevaSolicitud.pantallaDisponible());
		
		Utilities.waiter(4);
		nuevaSolicitud.cargarCamposCabecera(pagarA, sucursal);
		nuevaSolicitud.cargarCamposFechas(fechaRegistro, fechaContable, fechaEstimPago);
		nuevaSolicitud.cargarCamposPersonales(ramo, nombreAsegurado, tipoDocumento, nroDocumento, moneda, nombreCobrador, impCambio, impTotalPagar, desc, cbu);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Campos cargados");

		nuevaSolicitud.clicOnAgregarSiniestro();
		
		BuscarSiniestroSolicitudPagos buscarSiniestros = new BuscarSiniestroSolicitudPagos(driver);
		buscarSiniestros.pantallaDisponible();
		buscarSiniestros.buscarSiniestro(nroSiniestroBuscar);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Siniestro seleccionado");
		
		Utilities.waiter(3);
		Utilities.realizarScrollDown(driver);
		buscarSiniestros.guardaSiniestro();
		
		nuevaSolicitud.clicOnEditar();
		
		ModificarItemSolicitudPagos modificarItem = new ModificarItemSolicitudPagos(driver);
		modificarItem.clicOnTipoPago(tipoPago);
		Utilities.waiter(3);
		modificarItem.modificarItem(clasePago, conceptoPago, impAPagar);
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificaciones");
		Utilities.waiter(3);
		modificarItem.clicOnAceptar();
		Utilities.waiter(3);
		Utilities.realizarScrollDown(driver);
		Utilities.waiter(3);
		nuevaSolicitud.clicOnGuardar();
		Utilities.waiter(6);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizado");
	}
	
	
	@DataProvider(name = "Solicitud Pago")
	public Object[][] obtenerDatosEntradaSolicitudPago() throws Exception {
		return DatosExternos.leerCSV(archivoDataSolicitudPago, ',', false);
	}

}
