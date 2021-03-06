package pruebas;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.Menu;
import PortalProductores.Quotation;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class crearCotizacionColectCMPortal {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataCotizacion = "..\\Ecosistemas-CNP\\Entrada\\cotizacionTitular.csv";
	private String archivoDatosAsegurados = "..\\Ecosistemas-CNP\\Entrada\\cotizacionAsegurados.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	private int indiceAsegurados=0;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}

	
	@Test(dataProvider = "Crear Cotizacion Data")
	public void crearCotizacionColectivaCM(String tipodni, String dni, String provincia, String apellido, String nombre, String mail, String genero, String cantidad) throws Exception {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Crear cotización colectiva CM - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Creación de una cotización colectiva CM", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "tipo de dni: " + dni + ", provincia: " + provincia + ", apellido: " + apellido  + ", nombre: " + nombre  + ", mail: " + mail + ", género: " + genero + ", cantidad de asegurados: " + cantidad, 10);
		
		menu.clicOnCotizaciones();
		Utilities.waiter(3);
		Quotation cotizacion = new Quotation(driver);
		Assert.assertTrue(cotizacion.pantallaCotizacionesDisponible());
		
		cotizacion.clicOnNewQuotation();
		Assert.assertTrue(cotizacion.pantallaNuevaCotizacionesDisponible());
		cotizacion.clicOnVidaColectiva();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Selecciona Vida Colectivo");
		
		cotizacion.seleccionaProductoCM("CM");
		cotizacion.cargaDatosTitular(tipodni, dni, provincia, apellido, nombre, genero);
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Carga datos del titular");
		int auxiliar = Integer.parseInt(cantidad);
		if(auxiliar > 0) {
			Object[][] datosAseg = DatosExternos.leerCSV(archivoDatosAsegurados, ',', false);
		
			for (int i = indiceAsegurados; i < indiceAsegurados + auxiliar; i++) {
				cotizacion.cargaAsegurados(datosAseg[i][0].toString(), datosAseg[i][1].toString(), datosAseg[i][2].toString(), datosAseg[i][3].toString(), datosAseg[i][4].toString(), datosAseg[i][5].toString());
				cotizacion.clicOnIncluirAsegurado();
				CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Carga datos de asegurados");
				}
			indiceAsegurados = indiceAsegurados + auxiliar;
		}
		Utilities.realizarScrollDown(driver);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado agregado");

		cotizacion.cotizar();
		//AssertJUnit.assertTrue(cotizacion.pantallaFinalizacionDisponible());
		Utilities.waiter(5);
		cotizacion.guardar(mail);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirma y finaliza");
		Utilities.realizarScrollUp(driver);
		Utilities.waiter(3);
	}
	
	
	@DataProvider(name = "Crear Cotizacion Data")
	public Object[][] obtenerDatosEntradaCotizacion() throws Exception {
		return DatosExternos.leerCSV(archivoDataCotizacion, ',', false);
	}
}
