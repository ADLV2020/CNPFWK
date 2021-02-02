package pruebas;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.CotizacionTresor;
import PortalProductores.Menu;
import PortalProductores.Quotation;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class CrearCotizacionTresorTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataTresor = "..\\Ecosistemas-CNP\\Entrada\\cotizacionesTresor.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}

	
	@Test(dataProvider = "Crear Cotizacion Tresor")
	public void crearCotizacionTresor(String tipodni, String dni, String fechanacimiento, String apellido, String nombre, String genero, String fechaContrato, String provincia, String estadoCivil, String telefono, String actividad, String mail, String peso, String altura, String presionArterial, String primaPactada, String plazo, String periodoPago, String opcionBeneficio, String fondoConGarantia, String fondoSinGarantia, String aporteAdicional, String moneda) throws Exception {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Creación de cotización Tresor - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Creación de una cotización Tresor", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "tipo de dni: " + tipodni + ", dni: " + dni + ", fecha de nacimiento: " + fechanacimiento  + ", nombre: " + nombre  + ", apellido: " + apellido + ", género: " + genero + ", provincia: " + provincia + ", fecha de contrato: " + fechaContrato + ", estadoCivil: " + estadoCivil + ", telefono: " + telefono + ", actividad: " + actividad + ", mail: " + mail + ", peso: " + peso + ", altura: " + altura + ", presion Arterial: " + presionArterial + ", prima Pactada: " + primaPactada + ", plazo: " + plazo + ", periodoPago: " + periodoPago + ", opcionBeneficio: " + opcionBeneficio + ", fondoConGarantia: " + fondoConGarantia + ", fondoSinGarantia: " + fondoSinGarantia + ", aporteAdicional: " + aporteAdicional, 10);
				
		menu.clicOnCotizaciones();
		Utilities.waiter(3);
		Quotation cotizacion = new Quotation(driver);
		Assert.assertTrue(cotizacion.pantallaCotizacionesDisponible());
		
		cotizacion.clicOnNewQuotation();
		Assert.assertTrue(cotizacion.pantallaNuevaCotizacionesDisponible());
		cotizacion.clicOnTresor();
		
		CotizacionTresor tresor = new CotizacionTresor(driver);
		
		tresor.seleccionaMoneda();
		
		tresor.cargaDatosAsegurado(genero, tipodni, dni, provincia, apellido, nombre, fechanacimiento, estadoCivil, telefono, mail);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos del asegurado");
		
		tresor.cargaImportesYPlazos(primaPactada, plazo, periodoPago, opcionBeneficio);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Importes y plazos");
		
		tresor.cargarEstrategiaInversion(fondoConGarantia, fondoSinGarantia, aporteAdicional);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Aporte adicional");

		//
		Utilities.realizarScrollDown(driver);
		Utilities.waiter(4);
		tresor.clicOnCotizar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cotización");
		tresor.clicOnFinalizar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");
		Utilities.waiter(3);
		tresor.clicOnSi();
		Utilities.waiter(2);
		tresor.confirmarMailFinalizar(mail);
	}
	
	@DataProvider(name = "Crear Cotizacion Tresor")
	public Object[][] obtenerDatosEntradaTresor() throws Exception {
		return DatosExternos.leerCSV(archivoDataTresor, ',', false);
	}
	
}
