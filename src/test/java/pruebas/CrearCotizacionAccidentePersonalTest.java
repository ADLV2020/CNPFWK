package pruebas;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.CotizacionAccidentesPersonales;
import PortalProductores.Menu;
import PortalProductores.Quotation;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class CrearCotizacionAccidentePersonalTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataAccidentesPersonales = "..\\Ecosistemas-CNP\\Entrada\\accidentesPersonales.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Crear Cotizacion Accidente")
	public void crearCotizacionAccidente(String tipodni, String dni, String fechanacimiento, String apellido, String nombre, String genero, String sueldo, String fechaContrato, String provincia, String vigencia, String actividad, String mail, String muerte, String ityp, String gastosMedicos, String tipoPersona, String razonSocial, String tipoCotizacion, String vigenciaCobertura, String moto, String ambito) throws Exception {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Creación de una cotización de Accidente Personal - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Creación de una cotización de accidente personal", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "tipo de dni: " + tipodni + ", dni: " + dni + ", apellido: " + apellido  + ", nombre: " + nombre  + ", fecha de nacimiento: " + fechanacimiento + ", género: " + genero + ", sueldo: " + sueldo + ", provincia: " + provincia + ", vigencia: " + vigencia + ", actividad: " + actividad + ", mail: " + mail + ", muerte: " + muerte + ", ityp: " + ityp + ", gastosMedicos: " + gastosMedicos + ", tipoPersona: " + tipoPersona + ", razonSocial: " + razonSocial + ", tipoCotizacion: " + tipoCotizacion + ", vigenciaCobertura: " + vigenciaCobertura + ", moto: " + moto + ", ambito: " + ambito, 10);
				
		menu.clicOnCotizaciones();
		Utilities.waiter(3);
		Quotation cotizacion = new Quotation(driver);
		Assert.assertTrue(cotizacion.pantallaCotizacionesDisponible());
		
		cotizacion.clicOnNewQuotation();
		Assert.assertTrue(cotizacion.pantallaNuevaCotizacionesDisponible());
		cotizacion.clicOnAccidentesPersonales();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Selecciona Accidentes");
		
		CotizacionAccidentesPersonales accidentes = new CotizacionAccidentesPersonales(driver);
		accidentes.cargaDatosAsegurado(provincia, apellido, nombre, tipoPersona, razonSocial);
		accidentes.cargaVariablesCotizacion(tipoCotizacion, vigenciaCobertura, moto, ambito);
		Utilities.waiter(5);
		accidentes.cargarVigencia(vigencia);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos cargados");
		accidentes.cargaActividad(actividad, muerte, ityp, gastosMedicos);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos cargados");
		accidentes.clicOnIncluir();
		Utilities.realizarScrollDown(driver);
		Utilities.waiter(4);
		accidentes.clicOnCotizar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cotización");
		accidentes.clicOnFinalizar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");
		Utilities.waiter(3);
		accidentes.clicOnSi();
		Utilities.waiter(2);
		accidentes.confirmarMailFinalizar(mail);
	}
	
	@DataProvider(name = "Crear Cotizacion Accidente")
	public Object[][] obtenerDatosEntradaAccidentesPersonales() throws Exception {
		return DatosExternos.leerCSV(archivoDataAccidentesPersonales, ',', false);
	}

}
