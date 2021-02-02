package pruebas;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.CotizacionCNPVie;
import PortalProductores.Menu;
import PortalProductores.Quotation;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class CrearCotizacionCNPVie {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataCNPVie = "..\\Ecosistemas-CNP\\Entrada\\cotizacionesCNPVie.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	
	@Test(dataProvider = "Crear Cotizacion CNPVie")
	public void crearCotizacionCNPVie(String tipodni, String dni, String fechanacimiento, String apellido, String nombre, String genero, String fechaContrato, String provincia, String estadoCivil, String telefono, String actividad, String mail, String peso, String altura, String presionArterial, String primaPactada, String plazo, String periodoPago, String opcionBeneficio, String fondoConGarantia, String fondoSinGarantia, String aporteAdicional, String moneda) throws Exception {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Creación de una cotización CNP Vie - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Creación de una cotización CNP Vie", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "tipo de dni: " + tipodni + ", dni: " + dni + ", fecha de nacimiento: " + fechanacimiento  + ", nombre: " + nombre  + ", apellido: " + apellido + ", género: " + genero + ", provincia: " + provincia + ", fecha de contrato: " + fechaContrato + ", estadoCivil: " + estadoCivil + ", telefono: " + telefono + ", actividad: " + actividad + ", mail: " + mail + ", peso: " + peso + ", altura: " + altura + ", presion Arterial: " + presionArterial + ", prima Pactada: " + primaPactada + ", plazo: " + plazo + ", periodoPago: " + periodoPago + ", opcionBeneficio: " + opcionBeneficio + ", fondoConGarantia: " + fondoConGarantia + ", fondoSinGarantia: " + fondoSinGarantia + ", aporteAdicional: " + aporteAdicional, 10);
				
		menu.clicOnCotizaciones();
		Utilities.waiter(3);
		Quotation cotizacion = new Quotation(driver);
		Assert.assertTrue(cotizacion.pantallaCotizacionesDisponible());
		
		cotizacion.clicOnNewQuotation();
		Assert.assertTrue(cotizacion.pantallaNuevaCotizacionesDisponible());
		cotizacion.clicOnCNPVie();
		
		CotizacionCNPVie cnpvie = new CotizacionCNPVie(driver);
		Utilities.waiter(5);
		cnpvie.cargaDatosAsegurado(genero, tipodni, dni, provincia, apellido, nombre, fechanacimiento, estadoCivil, telefono, mail);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos del asegurado");

		cnpvie.cargaOpcionesProducto(moneda, plazo, periodoPago);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos opciones del producto");

		cnpvie.cargaCoberturas(aporteAdicional);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos cobertura");
	
		//
		Utilities.realizarScrollDown(driver);
		Utilities.waiter(4);
		cnpvie.clicOnCotizar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cotización");
		cnpvie.clicOnFinalizar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");
		Utilities.waiter(3);
		cnpvie.clicOnSi();
		Utilities.waiter(2);
		cnpvie.confirmarMailFinalizar(mail);
	}
	
	
	@DataProvider(name = "Crear Cotizacion CNPVie")
	public Object[][] obtenerDatosEntradaCNPVie() throws Exception {
		return DatosExternos.leerCSV(archivoDataCNPVie, ',', false);
	}

}
