package pruebas;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.CotizacionConfiance;
import PortalProductores.Menu;
import PortalProductores.Quotation;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class CrearCotizacionConfianceTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataConfiance = "..\\Ecosistemas-CNP\\Entrada\\cotizacionesConfiance.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Crear Cotizacion Confiance")
	public void crearCotizacionConfiance(String tipodni, String dni, String fechanacimiento, String apellido, String nombre, String genero, String fechaContrato, String provincia, String estadoCivil, String telefono, String actividad, String mail, String peso, String altura, String presionArterial, String primaPactada, String plazo, String periodoPago, String opcionBeneficio, String fondoConGarantia, String fondoSinGarantia, String aporteAdicional) throws Exception {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Creación de una cotización Confiance - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Creación de una cotización Confiance", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "tipo de dni: " + tipodni + ", dni: " + dni + ", fecha de nacimiento: " + fechanacimiento  + ", nombre: " + nombre  + ", apellido: " + apellido + ", género: " + genero + ", provincia: " + provincia + ", fecha de contrato: " + fechaContrato + ", estadoCivil: " + estadoCivil + ", telefono: " + telefono + ", actividad: " + actividad + ", mail: " + mail + ", peso: " + peso + ", altura: " + altura + ", presion Arterial: " + presionArterial + ", prima Pactada: " + primaPactada + ", plazo: " + plazo + ", periodoPago: " + periodoPago + ", opcionBeneficio: " + opcionBeneficio + ", fondoConGarantia: " + fondoConGarantia + ", fondoSinGarantia: " + fondoSinGarantia + ", aporteAdicional: " + aporteAdicional, 10);
				
		menu.clicOnCotizaciones();
		Utilities.waiter(3);
		Quotation cotizacion = new Quotation(driver);
		Assert.assertTrue(cotizacion.pantallaCotizacionesDisponible());
		
		cotizacion.clicOnNewQuotation();
		Assert.assertTrue(cotizacion.pantallaNuevaCotizacionesDisponible());
		cotizacion.clicOnConfiance();
		
		CotizacionConfiance confiance = new CotizacionConfiance(driver);
		confiance.cargaDatosAsegurado(genero, tipodni, dni, provincia, apellido, nombre, fechanacimiento, estadoCivil, telefono, mail, peso, altura, presionArterial, actividad);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos del asegurado");

		confiance.cargaOpcionesProducto(primaPactada, plazo, periodoPago, opcionBeneficio);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos opciones del producto");

		Utilities.realizarScrollDown(driver);
		confiance.cargarEstrategiaInversion(fondoConGarantia, fondoSinGarantia, aporteAdicional);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos estrategia de inversión");

		confiance.cargaCoberturas();
		
		//
		Utilities.realizarScrollDown(driver);
		Utilities.waiter(4);
		confiance.clicOnCotizar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cotización");
		confiance.clicOnFinalizar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");
		Utilities.waiter(3);
		confiance.clicOnSi();
		Utilities.waiter(2);
		confiance.confirmarMailFinalizar(mail);
	}
	
	@DataProvider(name = "Crear Cotizacion Confiance")
	public Object[][] obtenerDatosEntradaConfiance() throws Exception {
		return DatosExternos.leerCSV(archivoDataConfiance, ',', false);
	}

}
