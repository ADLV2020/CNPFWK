package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.AgregarSolicitudAP;
import PortalProductores.Menu;
import PortalProductores.Quotation;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class AgregarSolicitudAPTest {
	
	private WebDriver driver;
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataAgregarAP = "..\\Ecosistemas-CNP\\Entrada\\agregarAP.csv";
	private String nombreArchivoEvidencias;
	private Menu menu;
	
	
	@BeforeSuite
	public void setUp() throws Exception {
		LoginPortal setUp = new LoginPortal();
		setUp.setUp();
		driver = setUp.getDriver();
		menu = new Menu(driver);
	}
	
	@Test(dataProvider = "Agregar AP")
	public void agregarAP(String fechaDesde, String fechaHasta, String nombre, String apellido, String ramo, String productor, String codigo, String genero, String tipoDocumento, String numDocumento, String lugarNacimiento, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numeroCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley, String tarjeta, String numTarjeta, String tipoBeneficiario, String nombreAsegurado, String apellidoAsegurado, String fechaNacimientoAsegurado, String generoAsegurado, String tipoDocumentoAsegurado, String numDocumentoAsegurado) throws InvalidFormatException, IOException, InterruptedException {
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias - Agregar Solicitud Accidentes Personales - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Agregar Solicitud Accidentes Personales", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta + ", nombre: " + nombre  + ", apellido: " + apellido  + ", ramo: " + ramo + ", productor: " + productor + ", codigo: " + codigo + ", genero: " + genero + ", tipoDocumento: " + tipoDocumento + ", numDocumento: " + numDocumento + ", lugarNacimiento: " + lugarNacimiento + ", nacionalidad: " + nacionalidad + ", estadoCivil: " + estadoCivil + ", fechaNacimiento: " + fechaNacimiento + ", calle: " + calle + ", numeroCalle: " + numeroCalle + ", piso: " + piso + ", dpto: " + dpto + ", localidad: " + localidad + ", codPostal: " + codPostal + ", actividad: " + actividad + ", mail: " + mail + ", telEmpresa: " + telEmpresa + ", celular: " + celular + ", politica: " + politica + ", ley: " + ley + ", tarjeta: " + tarjeta + ", numTarjeta: " + numTarjeta + ", tipoBeneficiario: " + tipoBeneficiario + ", nombreAsegurado: " + nombreAsegurado + ", apellidoAsegurado: " + apellidoAsegurado + ", fechaNacimientoAsegurado: " + fechaNacimientoAsegurado + ", generoAsegurado: " + generoAsegurado + ", tipoDocumentoAsegurado: " + tipoDocumentoAsegurado + ", numDocumentoAsegurado: " + numDocumentoAsegurado, 10);		
		
		menu.clicOnCotizaciones();
		
		Quotation cotizaciones = new Quotation(driver);
		cotizaciones.insertaDatosConsulta(fechaDesde, fechaHasta, ramo, nombre, apellido, productor, codigo);
		//limpiar calendarios
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda");
		cotizaciones.clicOnAgregarSolicitud();
		
		AgregarSolicitudAP agregarSolicitud = new AgregarSolicitudAP(driver);
		Utilities.waiter(3);
		agregarSolicitud.cargaDatosTomador(genero, tipoDocumento, numDocumento, lugarNacimiento, nacionalidad, estadoCivil, fechaNacimiento, numeroCalle, numeroCalle, piso, dpto, localidad, codPostal, actividad, mail, telEmpresa, celular, politica, ley);

		agregarSolicitud.cargaDesignacion(tipoBeneficiario);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos del tomador y tipo de beneficiario");

		agregarSolicitud.cargaMedioPago(tarjeta, numTarjeta);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Medios de pago");

		agregarSolicitud.cargaDatosAsegurado(nombreAsegurado, apellidoAsegurado, fechaNacimientoAsegurado, generoAsegurado, tipoDocumentoAsegurado, numDocumentoAsegurado);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos del asegurado");

		agregarSolicitud.clicOnGuardar();
		agregarSolicitud.clicOnSiConfirmar();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");

		agregarSolicitud.clicOnCerrar();
	}
	
	@DataProvider(name = "Agregar AP")
	public Object[][] obtenerDatosEntradaAgregarAP() throws Exception {
		return DatosExternos.leerCSV(archivoDataAgregarAP, ',', false);
	}

}
