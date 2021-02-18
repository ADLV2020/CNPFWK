package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PortalProductores.AgregarSolicitudAP;
import PortalProductores.AgregarSolicitudVC;
import PortalProductores.AltaVidasPolizaColectiva;
import PortalProductores.CierreComisiones;
import PortalProductores.CotizacionAccidentesPersonales;
import PortalProductores.CotizacionCNPVie;
import PortalProductores.CotizacionConfiance;
import PortalProductores.CotizacionTresor;
import PortalProductores.Login;
import PortalProductores.Menu;
import PortalProductores.ModificacionPolizas;
import PortalProductores.ModificarSolicitudAP;
import PortalProductores.ModificarSolicitudVC;
import PortalProductores.Policy;
import PortalProductores.PolizasModificacionActividadLaboral;
import PortalProductores.PolizasModificacionBeneficiarioAsegurado;
import PortalProductores.PolizasModificacionNombreAsegurado;
import PortalProductores.PolizasModificacionNombreTomador;
import PortalProductores.PolizasModificacionPrima;
import PortalProductores.Quotation;
import PortalProductores.Solicitudes;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class PortalProductoresTest {
	private WebDriver driver;
	//private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String pathDriver = "C:\\Users\\admin\\Documents\\Proyectos\\Proyecto CNP\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataCotizacion = "..\\Ecosistemas-CNP\\Entrada\\cotizacionTitular.csv";
	private String archivoDatosAsegurados = "..\\Ecosistemas-CNP\\Entrada\\cotizacionAsegurados.csv";
	private String archivoDatosAseguradosLCT = "..\\Ecosistemas-CNP\\Entrada\\cotizacionAseguradosLCT.csv";
	private String archivoDataPolizas = "..\\Ecosistemas-CNP\\Entrada\\polizas.csv";
	private String archivoDataModificacionConductoPago = "..\\Ecosistemas-CNP\\Entrada\\modificacionConductoPago.csv";
	private String archivoDataPolizasVigentes = "..\\Ecosistemas-CNP\\Entrada\\polizasVigentes.csv";
	private String archivoDataModificacionDomiclio = "..\\Ecosistemas-CNP\\Entrada\\modificacionDomicilio.csv";
	private String archivoDatosAmbiente = "..\\Ecosistemas-CNP\\Entrada\\ambientePortal.csv";
	private String archivoDataConsultaCotizacion = "..\\Ecosistemas-CNP\\Entrada\\consultaCotizaciones.csv";
	private String archivoDataDescargarCotizacion = "..\\Ecosistemas-CNP\\Entrada\\descargaCotizaciones.csv";
	private String archivoDataAccidentesPersonales = "..\\Ecosistemas-CNP\\Entrada\\accidentesPersonales.csv";
	private String archivoDataConfiance = "..\\Ecosistemas-CNP\\Entrada\\cotizacionesConfiance.csv";
	private String archivoDataCNPVie = "..\\Ecosistemas-CNP\\Entrada\\cotizacionesCNPVie.csv";
	private String archivoDataTresor = "..\\Ecosistemas-CNP\\Entrada\\cotizacionesTresor.csv";
	private String archivoDataConsultaSolicitudes = "..\\Ecosistemas-CNP\\Entrada\\consultaSolicitudes.csv";
	private String archivoDataDescargarCertificadoSolicitud = "..\\Ecosistemas-CNP\\Entrada\\descargaCertificadoSolicitud.csv";
	private String archivoDataModificarAP = "..\\Ecosistemas-CNP\\Entrada\\modificarAP.csv";
	private String archivoDataAgregarAP = "..\\Ecosistemas-CNP\\Entrada\\agregarAP.csv";
	private String archivoDataAgregarVC = "..\\Ecosistemas-CNP\\Entrada\\agregarVC.csv";
	private String archivoDataModificarVC = "..\\Ecosistemas-CNP\\Entrada\\modificarVC.csv";
	private String archivoDataEnviarSolicitud = "..\\Ecosistemas-CNP\\Entrada\\enviarSolicitudes.csv";
	private String archivoDataPolizasIndividuales = "..\\Ecosistemas-CNP\\Entrada\\polizaIndividual.csv";
	private String archivoDataPolizasDescargaEndoso = "..\\Ecosistemas-CNP\\Entrada\\polizaDescargaEndoso.csv";
	private String archivoDataPolizasDescargaEstadoCuenta = "..\\Ecosistemas-CNP\\Entrada\\polizaDescargaEstadoCuenta.csv";
	private String archivoDataModificacionPrima = "..\\Ecosistemas-CNP\\Entrada\\modificacionPrima.csv";
	private String archivoDataModificacionActividadLaboral = "..\\Ecosistemas-CNP\\Entrada\\modificacionActividadLaboral.csv";
	private String archivoDataModificacionBeneficiarioAsegurado = "..\\Ecosistemas-CNP\\Entrada\\modificacionBeneficiarioAsegurado.csv";
	private String archivoDataVerHistorialEndoso = "..\\Ecosistemas-CNP\\Entrada\\verHistorialEndoso.csv";
	private String archivoDataModificaNombreDocumentoAsegurado = "..\\Ecosistemas-CNP\\Entrada\\modificaNombreAsegurado.csv";
	private String archivoDataModificaNombreDocumentoTomador = "..\\Ecosistemas-CNP\\Entrada\\modificaNombreTomador.csv";
	private String archivoDataImprimirPoliza = "..\\Ecosistemas-CNP\\Entrada\\imprimirPoliza.csv";
	private String archivoDataVerPoliza = "..\\Ecosistemas-CNP\\Entrada\\verPoliza.csv";
	private String archivoDataAprobar = "..\\Ecosistemas-CNP\\Entrada\\aprobar.csv";
	private String archivoDataPolizaColectiva = "..\\Ecosistemas-CNP\\Entrada\\polizaColectiva.csv";
	private String archivoDataImpresionDeudaPoliza = "..\\Ecosistemas-CNP\\Entrada\\impresionDeudaPoliza.csv";
	private String archivoDataConstanciaCoberturaPoliza = "..\\Ecosistemas-CNP\\Entrada\\polizaConstanciaCobertura.csv";
	private String archivoDataAltaVidasPoliza = "..\\Ecosistemas-CNP\\Entrada\\polizaAltaVida.csv";
	private String archivoDataDescargarEndososPoliza = "..\\Ecosistemas-CNP\\Entrada\\descargarEndososPolizaColectiva.csv";
	private String archivoDataHistorialRegistrosPoliza = "..\\Ecosistemas-CNP\\Entrada\\historialRegistrosPolizaColectiva.csv";
	private String nombreArchivoEvidencias;
	private String archivoDataComisiones = "..\\Ecosistemas-CNP\\Entrada\\comisiones.csv";
	private Menu menu;
	private int indiceAsegurados=0;
	
	
	@BeforeSuite
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
		
		// El browser se abre dos veces: Revisar, sólo ocurre en la automatización
		login.fillCredentials(datosURL[0][1].toString(), datosURL[0][2].toString());
		login.clicOnButton();
		
		menu = new Menu(driver);
		
		Assert.assertTrue(menu.pantallaPrincipalDisponible(), "Login correcto");
		
		
		nombreArchivoEvidencias = "PortalProductores - Testing - Evidencias " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pruebas automatizadas - Portal de productores", 20);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla Principal");
		
	}
	
	@Test(dataProvider = "Buscar Poliza")
	public void buscarPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		buscarPolizaEvidencia(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta,  ramo);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

	}
	
	
	public void buscarPolizaEvidencia(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda de pólizas", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
	}
	
	
	public void realizaBusqueda(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		String mensajeError = "No existen registros que coincidan con la búsqueda especificada. Por favor, ajuste sus criterios de búsqueda e inténtelo nuevamente.";
		
		menu.clicOnPolizas();
		
		Utilities.waiter(5);
		
		Policy policy = new Policy(driver);
		Assert.assertTrue(policy.pantallaPolizaDisponible());
		policy.enterSearchCriteria(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos para la realización de la Búsqueda");

		policy.clicOnSearch();
		Utilities.realizarScrollDown(driver);
		
		Utilities.waiter(3);
		Utilities.realizarScrollDown(driver);
		Assert.assertTrue(policy.busquedaSinResultado().equals(mensajeError) || policy.resultadoBusquedaDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la Búsqueda");
		Utilities.waiter(5);
	}
	
	
	@Test(dataProvider= "Polizas Vigentes")
	public void detallesPolizaVigente(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pólizas vigentes", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		Assert.assertEquals(policy.esPolizaVigente(), "VIGENTE", "No es una poliza vigente");
		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");
		Utilities.waiter(2);
	}
	
	@Test(dataProvider = "Modificar Conducto Pago")
	public void modificarConductoPagoTomador(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String medioPago, String nroTarjeta, String observaciones, String tipopoliza) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de conducto pago - póliza " + tipopoliza, 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", medioPago: " + medioPago + ", nroTarjeta: " + nroTarjeta + ", observaciones: " + observaciones, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		//Utilities.waiter(3);
		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Sección a modificar");
		
		modificacionpolizas.clicOnConductoPagoTomador();
		modificacionpolizas.modificarMedioDePago(medioPago, nroTarjeta, observaciones);
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaEdicionCamposDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cambios realizados");
		
		modificacionpolizas.clicOnGuardarCambios();
		Utilities.waiter(2);
		modificacionpolizas.clicOnConfirmar();
		Utilities.waiter(3);
		Assert.assertTrue(modificacionpolizas.pantallaConfirmarDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificacionpolizas.clicOnCerrar();
		Utilities.waiter(3);
	}
	
	@Test(dataProvider = "Modificar Domicilio")
	public void modificarDomicilioTomador(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String calle, String altura, String piso, String dto, String localidad, String provincia, String codpostal, String telefono, String mail, String observaciones, String tipopoliza) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de domicilio del tomador - poliza " + tipopoliza, 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		Utilities.waiter(3);
		policy.clicOnDetalles();
		Utilities.waiter(5);
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(3);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		
		modificacionpolizas.clicOnInformacionDomiciliaria();
		Utilities.waiter(5);
		modificacionpolizas.modificaInfoDomicio(calle,  altura, piso, dto, localidad, provincia, codpostal, telefono, mail, observaciones);
		Assert.assertTrue(modificacionpolizas.pantallaEdicionCamposDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cambios realizados");
		
		Utilities.waiter(4);
		modificacionpolizas.clicOnGuardarDomicilio();
		Utilities.waiter(6);
		modificacionpolizas.clicOnConfirmarDomicilio();
		Utilities.waiter(10);
		Assert.assertTrue(modificacionpolizas.pantallaConfirmarDisponible());
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificacionpolizas.clicOnCerrarDomicilio();
		Utilities.realizarScrollUp(driver);
		Utilities.waiter(3);
	}
	
	
	@Test(dataProvider = "Crear Cotizacion Data")
	public void crearCotizacionColectivaCM(String tipodni, String dni, String provincia, String apellido, String nombre, String mail, String genero, String cantidad) throws Exception {
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
		cotizacion.guardar(mail);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirma y finaliza");
		Utilities.realizarScrollUp(driver);
	}
	
	@Test(dataProvider = "Crear Cotizacion Data")
	public void crearCotizacionColectivaLCT(String tipodni, String dni, String provincia, String apellido, String nombre, String mail, String genero, String cantidad) throws Exception {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Creación de una cotización colectiva LCT", 20);
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
		
		cotizacion.seleccionaProductoCM("LCT");
		cotizacion.cargaDatosTitular(tipodni, dni, provincia, apellido, nombre, genero);
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Carga datos del titular");
		int auxiliar = Integer.parseInt(cantidad);
		if(auxiliar > 0) {
			Object[][] datosAseg = DatosExternos.leerCSV(archivoDatosAseguradosLCT, ',', false);
		
			for (int i = indiceAsegurados; i < indiceAsegurados + auxiliar; i++) {
				cotizacion.cargaAseguradosLCT(datosAseg[i][0].toString(), datosAseg[i][1].toString(), datosAseg[i][2].toString(), datosAseg[i][3].toString(), datosAseg[i][4].toString(), datosAseg[i][5].toString(), datosAseg[i][6].toString(), datosAseg[i][7].toString());
				cotizacion.clicOnIncluirAseguradoLCT();
				CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Carga datos de asegurados");
				}
			indiceAsegurados = indiceAsegurados + auxiliar;
		}
		Utilities.realizarScrollDown(driver);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado agregado");

		cotizacion.cotizar();
		//AssertJUnit.assertTrue(cotizacion.pantallaFinalizacionDisponible());
		cotizacion.guardar(mail);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirma y finaliza");
		Utilities.realizarScrollUp(driver);
	}
	
	@Test(dataProvider = "Crear Cotizacion Accidente")
	public void crearCotizacionAccidente(String tipodni, String dni, String fechanacimiento, String apellido, String nombre, String genero, String sueldo, String fechaContrato, String provincia, String vigencia, String actividad, String mail, String muerte, String ityp, String gastosMedicos, String tipoPersona, String razonSocial, String tipoCotizacion, String vigenciaCobertura, String moto, String ambito) throws Exception {
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
	
	@Test(dataProvider = "Consultar Cotizaciones")
	public void consultarCotizaciones(String fechaDesde, String fechaHasta, String ramo, String nombreAsegurado, String apellidoAsegurado, String productor, String codigo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Consulta de cotización", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "fecha desde: " + fechaDesde + ", fecha hasta: " + fechaHasta + ", ramo: " + ramo  + ", nombre: " + nombreAsegurado  + ", apellido: " + apellidoAsegurado + ", productor: " + productor, 10);
		
		menu.clicOnCotizaciones();
		
		Quotation cotizaciones = new Quotation(driver);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla de búsqueda");

		cotizaciones.insertaDatosConsulta(fechaDesde, fechaHasta, ramo, nombreAsegurado, apellidoAsegurado, productor, codigo);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos para la búsqueda");
		
		cotizaciones.clicOnDetalles();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la cotización");
	}
	
	@Test(dataProvider = "Descargar Cotizaciones")
	public void descargarCotizaciones(String fechaDesde, String fechaHasta, String ramo, String nombreAsegurado, String apellidoAsegurado, String productor, String codigo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Descarga de cotización", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "fecha desde: " + fechaDesde + ", fecha hasta: " + fechaHasta + ", ramo: " + ramo  + ", nombre: " + nombreAsegurado  + ", apellido: " + apellidoAsegurado + ", productor: " + productor, 10);
		
		menu.clicOnCotizaciones();
		
		Quotation cotizaciones = new Quotation(driver);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla de búsqueda");

		cotizaciones.insertaDatosConsulta(fechaDesde, fechaHasta, ramo, nombreAsegurado, apellidoAsegurado, productor, codigo);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos para la búsqueda");
		
		cotizaciones.clicOnDescargar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la cotización");
	}
	
	@Test(dataProvider = "Crear Cotizacion Confiance")
	public void crearCotizacionConfiance(String tipodni, String dni, String fechanacimiento, String apellido, String nombre, String genero, String fechaContrato, String provincia, String estadoCivil, String telefono, String actividad, String mail, String peso, String altura, String presionArterial, String primaPactada, String plazo, String periodoPago, String opcionBeneficio, String fondoConGarantia, String fondoSinGarantia, String aporteAdicional) throws Exception {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Creación de una cotización de accidente personal", 20);
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
	
	@Test(dataProvider = "Crear Cotizacion CNPVie")
	public void crearCotizacionCNPVie(String tipodni, String dni, String fechanacimiento, String apellido, String nombre, String genero, String fechaContrato, String provincia, String estadoCivil, String telefono, String actividad, String mail, String peso, String altura, String presionArterial, String primaPactada, String plazo, String periodoPago, String opcionBeneficio, String fondoConGarantia, String fondoSinGarantia, String aporteAdicional, String moneda) throws Exception {
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
	
	@Test(dataProvider = "Crear Cotizacion Tresor")
	public void crearCotizacionTresor(String tipodni, String dni, String fechanacimiento, String apellido, String nombre, String genero, String fechaContrato, String provincia, String estadoCivil, String telefono, String actividad, String mail, String peso, String altura, String presionArterial, String primaPactada, String plazo, String periodoPago, String opcionBeneficio, String fondoConGarantia, String fondoSinGarantia, String aporteAdicional, String moneda) throws Exception {
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
	
	@Test
	public void excluirCotizacion() throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Excluir una cotización", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
				
		menu.clicOnCotizaciones();
		Utilities.waiter(3);
		Quotation cotizacion = new Quotation(driver);
		Assert.assertTrue(cotizacion.pantallaCotizacionesDisponible());
		cotizacion.clicOnBuscar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la búsqueda");

		cotizacion.clicOnExcluir();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");
		
		cotizacion.clicOnConfirmar();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Excluida");

	}
	
	@Test(dataProvider = "Consultar Solicitudes")
	public void consultarSolicitudes(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Consultar solicitud", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "nroSolicitud: " + nroSolicitud + ", fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta  + ", nombreTomador: " + nombreTomador  + ", documentoTomador: " + documentoTomador + ", estado: " + estado + ", nombreAsegurado: " + nombreAsegurado + ", documentoAsegurado: " + documentoAsegurado + ", ramo: " + ramo + ", productor: " + productor, 10);

		
		menu.clicOnSolicitudes();
		
		Solicitudes solicitudes = new Solicitudes(driver);
		
		solicitudes.cargaDatosConsulta(nroSolicitud, fechaDesde, fechaHasta, nombreTomador, documentoTomador, estado, nombreAsegurado, documentoAsegurado, ramo, productor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la búsqueda");

		solicitudes.clicOnBuscar();
		solicitudes.clicOnSolicitud();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la solicitud");
		Utilities.realizarScrollDown(driver);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la solicitud");
	}
	
	@Test(dataProvider = "Descargar Certificado Solicitud")
	public void descargaCertificadoSolicitudes(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor, String razonSocial, String cuil) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Consultar solicitud", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "nroSolicitud: " + nroSolicitud + ", fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta  + ", nombreTomador: " + nombreTomador  + ", documentoTomador: " + documentoTomador + ", estado: " + estado + ", nombreAsegurado: " + nombreAsegurado + ", documentoAsegurado: " + documentoAsegurado + ", ramo: " + ramo + ", productor: " + productor, 10);

		
		menu.clicOnSolicitudes();
		
		Solicitudes solicitudes = new Solicitudes(driver);
		
		solicitudes.cargaDatosConsulta(nroSolicitud, fechaDesde, fechaHasta, nombreTomador, documentoTomador, estado, nombreAsegurado, documentoAsegurado, ramo, productor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la búsqueda");
		Utilities.waiter(3);
		solicitudes.clicOnBuscar();
		solicitudes.clicOnCertificado();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la solicitud");
		solicitudes.impresionCertificado(razonSocial, cuil);
		solicitudes.clicOnIncluir();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la solicitud");
		solicitudes.clicOnDescargar();
	}
	
	
	@Test(dataProvider = "Modificar AP")
	public void modificarAP(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley, String tarjeta, String numTarjeta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificar Solicitud Accidentes Personales", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "nroSolicitud: " + nroSolicitud + ", fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta  + ", nombreTomador: " + nombreTomador  + ", documentoTomador: " + documentoTomador + ", estado: " + estado + ", nombreAsegurado: " + nombreAsegurado + ", documentoAsegurado: " + documentoAsegurado + ", ramo: " + ramo + ", productor: " + productor, 10);

		
		menu.clicOnSolicitudes();
		
		Solicitudes solicitudes = new Solicitudes(driver);
		solicitudes.cargaDatosConsulta(nroSolicitud, fechaDesde, fechaHasta, nombreTomador, documentoTomador, estado, nombreAsegurado, documentoAsegurado, ramo, productor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la búsqueda");
		Utilities.waiter(3);
		solicitudes.clicOnBuscar();
		solicitudes.clicOnEditar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Campos a modificar");
		
		ModificarSolicitudAP modificarSolAP = new ModificarSolicitudAP(driver);
		
		modificarSolAP.cargarDatosPersonales(nacionalidad, estadoCivil, fechaNacimiento, calle, numCalle, piso, dpto, localidad, codPostal, actividad, mail, telEmpresa, celular, politica, ley);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos personales");

		modificarSolAP.cargaMedioPago(tarjeta, numTarjeta);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de medio de pago");

		modificarSolAP.clicOnGuardar();
		modificarSolAP.clicOnSiConfirmar();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");

		modificarSolAP.clicOnCerrar();
	}
	
	@Test(dataProvider = "Agregar AP")
	public void agregarAP(String fechaDesde, String fechaHasta, String nombre, String apellido, String ramo, String productor, String codigo, String genero, String tipoDocumento, String numDocumento, String lugarNacimiento, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numeroCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley, String tarjeta, String numTarjeta, String tipoBeneficiario, String nombreAsegurado, String apellidoAsegurado, String fechaNacimientoAsegurado, String generoAsegurado, String tipoDocumentoAsegurado, String numDocumentoAsegurado) throws InvalidFormatException, IOException, InterruptedException {
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
	
	@Test(dataProvider = "Agregar VC")
	public void agregarVC(String fechaDesde, String fechaHasta, String nombre, String apellido, String ramo, String productor, String codigo, String genero, String tipoDocumento, String numDocumento, String lugarNacimiento, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numeroCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley, String tarjeta, String numTarjeta, String tipoBeneficiario, String nombreAsegurado, String apellidoAsegurado, String fechaNacimientoAsegurado, String generoAsegurado, String tipoDocumentoAsegurado, String numDocumentoAsegurado) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Agregar Solicitud Accidentes Personales", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta + ", nombre: " + nombre  + ", apellido: " + apellido  + ", ramo: " + ramo + ", productor: " + productor + ", codigo: " + codigo + ", genero: " + genero + ", tipoDocumento: " + tipoDocumento + ", numDocumento: " + numDocumento + ", lugarNacimiento: " + lugarNacimiento + ", nacionalidad: " + nacionalidad + ", estadoCivil: " + estadoCivil + ", fechaNacimiento: " + fechaNacimiento + ", calle: " + calle + ", numeroCalle: " + numeroCalle + ", piso: " + piso + ", dpto: " + dpto + ", localidad: " + localidad + ", codPostal: " + codPostal + ", actividad: " + actividad + ", mail: " + mail + ", telEmpresa: " + telEmpresa + ", celular: " + celular + ", politica: " + politica + ", ley: " + ley + ", tarjeta: " + tarjeta + ", numTarjeta: " + numTarjeta + ", tipoBeneficiario: " + tipoBeneficiario + ", nombreAsegurado: " + nombreAsegurado + ", apellidoAsegurado: " + apellidoAsegurado + ", fechaNacimientoAsegurado: " + fechaNacimientoAsegurado + ", generoAsegurado: " + generoAsegurado + ", tipoDocumentoAsegurado: " + tipoDocumentoAsegurado + ", numDocumentoAsegurado: " + numDocumentoAsegurado, 10);		

		menu.clicOnCotizaciones();
		
		Quotation cotizaciones = new Quotation(driver);
		cotizaciones.insertaDatosConsulta(fechaDesde, fechaHasta, ramo, nombre, apellido, productor, codigo);
		//limpiar calendarios
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda");
		cotizaciones.clicOnAgregarSolicitud();
		
		AgregarSolicitudVC agregarSolicitud = new AgregarSolicitudVC(driver);
		Utilities.waiter(3);
		agregarSolicitud.cargaDatosTomador(genero, tipoDocumento, numDocumento, lugarNacimiento, nacionalidad, estadoCivil, fechaNacimiento, numeroCalle, numeroCalle, piso, dpto, localidad, codPostal, actividad, mail, telEmpresa, celular, politica, ley);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos del tomador");
		
		agregarSolicitud.cargaMedioPago(tarjeta, numTarjeta);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Medios de pago");

		agregarSolicitud.clicOnGuardar();
		agregarSolicitud.clicOnSiConfirmar();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");

		agregarSolicitud.clicOnCerrar();
	}
	
	@Test(dataProvider = "Modificar VC")
	public void modificarVC(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley, String tarjeta, String numTarjeta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificar Solicitud Vida Colectiva", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "nroSolicitud: " + nroSolicitud + ", fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta  + ", nombreTomador: " + nombreTomador  + ", documentoTomador: " + documentoTomador + ", estado: " + estado + ", nombreAsegurado: " + nombreAsegurado + ", documentoAsegurado: " + documentoAsegurado + ", ramo: " + ramo + ", productor: " + productor, 10);

		
		menu.clicOnSolicitudes();
		
		Solicitudes solicitudes = new Solicitudes(driver);
		solicitudes.cargaDatosConsulta(nroSolicitud, fechaDesde, fechaHasta, nombreTomador, documentoTomador, estado, nombreAsegurado, documentoAsegurado, ramo, productor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la búsqueda");
		Utilities.waiter(3);
		solicitudes.clicOnBuscar();
		solicitudes.clicOnEditar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Campos a modificar");
		
		ModificarSolicitudVC modificarSolVC = new ModificarSolicitudVC(driver);
		
		modificarSolVC.cargarDatosPersonales(nacionalidad, estadoCivil, fechaNacimiento, calle, numCalle, piso, dpto, localidad, codPostal, actividad, mail, telEmpresa, celular, politica, ley);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos personales");

		modificarSolVC.cargaMedioPago(tarjeta, numTarjeta);
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de medio de pago");

		modificarSolVC.clicOnGuardar();
		modificarSolVC.clicOnSiConfirmar();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");

		modificarSolVC.clicOnCerrar();
	}
	
	@Test(dataProvider = "Enviar Solicitud")
	public void enviarSolicitudes(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley, String tarjeta, String numTarjeta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Enviar Solicitud", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "nroSolicitud: " + nroSolicitud + ", fechaDesde: " + fechaDesde + ", fechaHasta: " + fechaHasta  + ", nombreTomador: " + nombreTomador  + ", documentoTomador: " + documentoTomador + ", estado: " + estado + ", nombreAsegurado: " + nombreAsegurado + ", documentoAsegurado: " + documentoAsegurado + ", ramo: " + ramo + ", productor: " + productor, 10);

		
		menu.clicOnSolicitudes();
		
		Solicitudes solicitudes = new Solicitudes(driver);
		solicitudes.cargaDatosConsulta(nroSolicitud, fechaDesde, fechaHasta, nombreTomador, documentoTomador, estado, nombreAsegurado, documentoAsegurado, ramo, productor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la búsqueda");
		Utilities.waiter(3);
		solicitudes.clicOnBuscar();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la búsqueda");
		solicitudes.clicOnEnviar();
		Utilities.waiter(5);
		solicitudes.clicOnSiConfirmar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Solicitud enviada");
		solicitudes.clicOnCerrar();
	}
	
	
	@Test(dataProvider= "Polizas Individuales")
	public void consultaPolizaIndividual(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String ver) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Póliza individual - Consulta", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", ver: " + ver, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");

		policy.clicOnVer(ver);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, ver);

	}
	
	@Test(dataProvider= "Descarga Endoso")
	public void polizaDescargaEndoso(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String ver) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Póliza individual - Descarga Endoso", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", ver: " + ver, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");

		policy.clicOnVerEndoso();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Endoso");

		policy.clicOnDescargarEndoso();
	}
	
	@Test(dataProvider= "Descarga Estado Cuenta")
	public void polizaDescargaEstadoCuenta(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String ver) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Póliza individual - Descarga Estado de Cuenta", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", ver: " + ver, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");
		Utilities.waiter(3);
		policy.clicOnVerEstadoCuenta();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Estado de cuenta");

		Utilities.realizarScrollDown(driver);
		policy.clicOnDescargarEstadoCuenta();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Descargar Estado de Cuenta");

	}
	
	@Test(dataProvider = "Modificar Prima")
	public void modificarIncrementoDisminucionPrima(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String nuevaPrima, String observaciones, String prioridad) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Incremento/Disminución de Prima", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", prioridad: " + prioridad + ", nueva prima: " + nuevaPrima + ", observaciones: " + observaciones, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Sección a modificar");
		
		
		modificacionpolizas.clicOnModificarPrisma();
		
		PolizasModificacionPrima modificaPrima = new PolizasModificacionPrima(driver);

		modificaPrima.cargaDatosPrima(nuevaPrima, observaciones, prioridad);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cambios realizados");
		
		
		modificaPrima.clicOnGuardarCambios();
		Utilities.waiter(2);
		modificaPrima.clicOnConfirmar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificaPrima.clicOnCerrar();
		Utilities.waiter(3);
		
	}
	
	@Test(dataProvider = "Modificar Actividad Laboral")
	public void modificarActividadLaboralPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String actividad, String observaciones, String prioridad) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de actividad laboral", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", prioridad: " + prioridad + ", actividad laboral: " + actividad + ", observaciones: " + observaciones, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Sección a modificar");
		
		modificacionpolizas.clicOnModificarActividadLaboral();
		
		PolizasModificacionActividadLaboral modificaActividad = new PolizasModificacionActividadLaboral(driver);
		Utilities.waiter(5);
		modificaActividad.cargaDatosActividadLaboral(actividad, observaciones, prioridad);
		
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cambios realizados");
		
		
		modificaActividad.clicOnGuardarActividad();
		Utilities.waiter(2);
		modificaActividad.clicOnConfirmarActividad();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificaActividad.clicOnCerrarActividad();
		Utilities.waiter(3);
	}
	
	@Test(dataProvider = "Modificar Beneficiario Asegurado")
	public void modificarBeneficiarioAseguradoPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String apellidoBeneficiario, String nombreBeneficiario, String tipodni, String dni, String vinculo, String relacion, String designacion, String observaciones) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de actividad laboral", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", observaciones: " + observaciones, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Sección a modificar");
		
		modificacionpolizas.clicOnModificarAsegurado();
		
		PolizasModificacionBeneficiarioAsegurado modificaAsegurado = new PolizasModificacionBeneficiarioAsegurado(driver);
		Utilities.waiter(5);
		modificaAsegurado.eliminarAsegurado();
		modificaAsegurado.cargaDatosBeneficiarioAsegurado(apellidoBeneficiario, nombreBeneficiario, tipodni, dni, vinculo, relacion, designacion);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos del nuevo asegurado");

		modificaAsegurado.clicOnAgregaAsegurado();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado agregado");

		modificaAsegurado.clicOnGuardarAsegurado();
		Utilities.waiter(2);
		modificaAsegurado.clicOnConfirmarAsegurado();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificaAsegurado.clicOnCerrarAsegurado();
		Utilities.waiter(3);
	}
	
	
	@Test(dataProvider = "Ver Historial Endoso Poliza")
	public void verHistorialEndosoPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ver historial endoso", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Endosos disponibles");
		
		modificacionpolizas.clicOnDescargarHistorial();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Hisotiral de Endoso");
		modificacionpolizas.clicnOnSalirHistorial();
	}
	
	
	@Test(dataProvider = "Modifica Nombre y Documento Asegurado")
	public void modificarPNombreDocumentoAseguradoPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String apellidoAsegurado, String nombreAsegurado, String tipoDNI, String dni, String observacion, String prioridad) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de nombre y documento del asegurado", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Endosos disponibles");
		
		modificacionpolizas.clicOnModificarNombreDocumentoAsegurado();
		
		PolizasModificacionNombreAsegurado modificaNombreAsegurado = new PolizasModificacionNombreAsegurado(driver);
		
		modificaNombreAsegurado.cargaDatosNombreAsegurado(apellidoAsegurado, nombreAsegurado, tipoDNI, dni, observacion, prioridad);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Nuevos datos cargados");

		modificaNombreAsegurado.clicOnGuardarAsegurado();
		Utilities.waiter(2);
		modificaNombreAsegurado.clicOnConfirmarAsegurado();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificaNombreAsegurado.clicOnCerrarAsegurado();
		Utilities.waiter(3);
	}
	
	@Test(dataProvider = "Modifica Nombre y Documento Tomador")
	public void modificarPNombreDocumentoTomadorPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String apellidoAsegurado, String nombreAsegurado, String tipoDNI, String dni, String observacion, String prioridad, String tipopoliza) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificación de nombre y documento del tomador - tipo " + tipopoliza, 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Endosos disponibles");
		
		modificacionpolizas.clicOnModificarNombreDocumentoAsegurado();
		
		PolizasModificacionNombreTomador modificaNombreTomador = new PolizasModificacionNombreTomador(driver);
		
		modificaNombreTomador.cargaDatosNombreTomador(apellidoAsegurado, nombreAsegurado, tipoDNI, dni, observacion, prioridad);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Nuevos datos cargados");

		modificaNombreTomador.clicOnGuardarTomador();
		Utilities.waiter(2);
		modificaNombreTomador.clicOnConfirmarTomador();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmación");

		modificaNombreTomador.clicOnCerrarTomador();
		Utilities.waiter(3);
	}
	
	@Test(dataProvider = "Imprimir Poliza")
	public void imprimirPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Imprimir póliza", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Impresiones disponibles");
		
		modificacionpolizas.clicOnImprimirPoliza();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Impresión realizada");
		
	}
	
	@Test(dataProvider = "Ver Poliza")
	public void verPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ver póliza", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Impresiones disponibles");
		
		modificacionpolizas.clicOnVer();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Impresión realizada");
		Utilities.realizarScrollDown(driver);
		modificacionpolizas.clicOnGuardarSalir();
		modificacionpolizas.clicOnConfirmar();
		Utilities.waiter(5);
		modificacionpolizas.clicOnCerrarVerPoliza();
	}
	
	@Test(dataProvider = "Aprobar")
	public void aprobarPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String comentario) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Aprobar solicitud de Endoso", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador, 10);
		
		Policy policy = new Policy(driver);
		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);

		policy.clicOnDetalles();
		Assert.assertTrue(policy.pantallaDetalleDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles de la póliza");

		ModificacionPolizas modificacionpolizas = new ModificacionPolizas(driver);
		Utilities.waiter(2);
		modificacionpolizas.clicOnModificar();
		Utilities.waiter(2);
		Assert.assertTrue(modificacionpolizas.pantallaModificacionDisponible());
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pendientes");
		
		modificacionpolizas.clicOnAprobar();
		Utilities.waiter(3);
		modificacionpolizas.agregarComentarioAprobacion(comentario);
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Comentario realizado");
		modificacionpolizas.clicOnAprobar2();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Aprobación finalizada");
		modificacionpolizas.clicOnConfirmar();
		Utilities.waiter(3);
		modificacionpolizas.clicOnCerrarAprobacion();

	}
	
	@Test(dataProvider = "Poliza Colectiva General")
	public void verPolizaColectivaGeneral(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String ver) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - general", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo + ", ver: " + ver, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");

		policy.clicOnVer(ver);
		Utilities.realizarScrollDown(driver);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, ver);

	}
	
	@Test(dataProvider = "Impresión Libre Deuda Póliza")
	public void imprimirLibreDeudaPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - Imprimir Libre de Deuda", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Impresión de póliza");

		ModificacionPolizas modificacionpoliza = new ModificacionPolizas(driver);
		
		modificacionpoliza.clicOnImprimirLibreDeuda();
	}
	
	@Test(dataProvider = "Poliza Colectiva Constancia Cobertura")
	public void verConstanciaCoberturaPolizaColectiva(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - Constancia de Cobertura", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");

		policy.clicOnAsegurado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado");

		ModificacionPolizas modificacion = new ModificacionPolizas(driver);
		modificacion.clicOnConstanciaCobertura();
		modificacion.clicOnAgregarasegurado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Constancia");

		modificacion.clicOnImprimirConstanciaCobertura();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Confirmar impresión");
		modificacion.clicOnConfirmar();
		
	}
	
	@Test(dataProvider = "Poliza Colectiva Baja Vida")
	public void bajaDeVidaPolizaColectiva(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - Baja de vida", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");

		policy.clicOnAsegurado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado");

		ModificacionPolizas modificacion = new ModificacionPolizas(driver);
		modificacion.clicOnBajaVidas();
		
		modificacion.clicOnAgregarasegurado();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Agregar Asegurado");

		modificacion.clicOnAceptar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Aceptar baja");

		modificacion.clicOnConfirmaBaja();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Finalizar");

		modificacion.clicOnFinalizaBaja();
		
	}
	
	
	@Test(dataProvider = "Poliza Colectiva Alta Vida")
	public void altaDeVidaPolizaColectiva(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo, String tipoDNIAlta, String dniAlta, String nombre, String apellido, String sexo, String fechaNacimiento, String categoria, String salarioBruto, String fechaIngreso) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - Alta de vida", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");

		policy.clicOnAsegurado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado");

		ModificacionPolizas modificacion = new ModificacionPolizas(driver);
		modificacion.clicOnAltaVidas();
		Utilities.waiter(3);

		AltaVidasPolizaColectiva altaVida = new AltaVidasPolizaColectiva(driver);
		
		altaVida.cargarAltaVida(tipoDNIAlta, dniAlta, nombre, apellido, sexo, fechaNacimiento, categoria, salarioBruto, fechaIngreso);
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado");

		altaVida.clicOnAgregar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado");

		altaVida.clicOnProcesar();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado");

		altaVida.clicOnCerrar();
	}
	
	
	@Test(dataProvider = "Impresión Certificado Cobertura Póliza")
	public void imprimirCertificadoCoberturaPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - Imprimir Certificado de Cobertura", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();

		ModificacionPolizas modificacionpoliza = new ModificacionPolizas(driver);
		Utilities.waiter(3);
		modificacionpoliza.clicOnAsegurado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Asegurado");

		Utilities.waiter(3);
		Utilities.realizarScrollDown(driver);
		modificacionpoliza.clicOnImprimirCertificado();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Impresión de Certificado");

	}
	
	@Test(dataProvider = "Descargar Endosos Poliza")
	public void descargarEndososPoliza(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - Descargar Endosos", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");

		ModificacionPolizas modificacionpoliza = new ModificacionPolizas(driver);

		modificacionpoliza.clicOnEndosos();
		Utilities.realizarScrollDown(driver);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Endosos disponibles para descarga");

		modificacionpoliza.clicOnDescargaEndoso();
	}
	
	@Test(dataProvider = "Historial Registros Poliza Colectiva")
	public void verHistorialRegistrosPolizaColectiva(String poliza, String nombretomador, String solicitud, String dnitomador, String nombreasegurado, String dniasegurado, String fechaemision, String fechahasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Poliza colectiva - Ver Historial de Registros", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "póliza: " + poliza + ", nombre tomador: " + nombretomador + ", solicitud: " + solicitud  + ", dni tomador: " + dnitomador  + ", nombre asegurado: " + nombreasegurado + ", dni asegurado: " + dniasegurado + ", fecha emisión: " + fechaemision + ", fecha hasta: " + fechahasta + ", ramo: " + ramo, 10);
		
		menu.clicOnPolizas();
		
		Policy policy = new Policy(driver);

		realizaBusqueda(poliza, nombretomador, solicitud, dnitomador, nombreasegurado, dniasegurado, fechaemision, fechahasta, ramo);
		
		policy.clicOnVerPerfil();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Perfil");

		ModificacionPolizas modificacionpoliza = new ModificacionPolizas(driver);

		modificacionpoliza.clicOnHistorialRegistros();
		Utilities.realizarScrollDown(driver);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Endosos disponibles para descarga");

	}
	
	@Test(dataProvider = "Consulta Cierres Comisiones")
	public void consultaCierresComisiones(String fechaInicio, String fechaFin, String nombreProductor, String codigoProductor) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Consulta de Cierres de comisiones", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "fechaInicio: " + fechaInicio + ", fechaFin: " + fechaFin + ", nombreProductor: " + nombreProductor  + ", codigoProductor: " + codigoProductor, 10);

		menu.clicOnCierres();
		
		CierreComisiones comisiones = new CierreComisiones(driver);
		
		comisiones.cargarDatosBusqueda(fechaInicio, fechaFin);
		comisiones.clicOnLupaProductor();
		comisiones.cargaDatosProductor(nombreProductor, codigoProductor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda de productor");
		comisiones.clicOnSumarProductor();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de búsqueda");

		comisiones.clicOnBuscar();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda de comisiones");
		
		comisiones.clicOnConsultar();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Cierres de comisiones");

	}
	
	@Test(dataProvider = "Consulta Cierres Comisiones")
	public void descargaCierresComisiones(String fechaInicio, String fechaFin, String nombreProductor, String codigoProductor) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Descarga de comisiones", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "fechaInicio: " + fechaInicio + ", fechaFin: " + fechaFin + ", nombreProductor: " + nombreProductor  + ", codigoProductor: " + codigoProductor, 10);

		menu.clicOnCierres();
		
		CierreComisiones comisiones = new CierreComisiones(driver);
		
		comisiones.cargarDatosBusqueda(fechaInicio, fechaFin);
		comisiones.clicOnLupaProductor();
		comisiones.cargaDatosProductor(nombreProductor, codigoProductor);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda de productor");
		comisiones.clicOnSumarProductor();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de búsqueda");

		comisiones.clicOnBuscar();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Búsqueda de comisiones");
		
		comisiones.clicOnDescargarComisiones();
		Utilities.waiter(5);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Comisiones descargadas");

	}
	
	@DataProvider(name= "Consulta Cierres Comisiones")
	public Object[][] obtenerDatosCierresComisiones() throws Exception {
		return DatosExternos.leerCSV(archivoDataComisiones, ',', false);
	}
	
	
	@DataProvider(name= "Historial Registros Poliza Colectiva")
	public Object[][] obtenerDatosHistorialRegistrosPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataHistorialRegistrosPoliza, ',', false);
	}
	
	@DataProvider(name= "Descargar Endosos Poliza")
	public Object[][] obtenerDatosDesacargarEndososPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataDescargarEndososPoliza, ',', false);
	}
	
	@DataProvider(name= "Impresión Certificado Cobertura Póliza")
	public Object[][] obtenerDatosImpresionCertificadoCoberturaPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataImpresionDeudaPoliza, ',', false);
	}
	
	@DataProvider(name= "Poliza Colectiva Alta Vida")
	public Object[][] obtenerDatosAltaVidaPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataAltaVidasPoliza, ',', false);
	}
	
	@DataProvider(name= "Poliza Colectiva Baja Vida")
	public Object[][] obtenerDatosBajaVidaPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataConstanciaCoberturaPoliza, ',', false);
	}
	
	@DataProvider(name= "Poliza Colectiva Constancia Cobertura")
	public Object[][] obtenerDatosConstanciaCoberturaPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataConstanciaCoberturaPoliza, ',', false);
	}
	
	@DataProvider(name= "Impresión Libre Deuda Póliza")
	public Object[][] obtenerDatosImpresionDeudaPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataImpresionDeudaPoliza, ',', false);
	}
	
	@DataProvider(name= "Poliza Colectiva General")
	public Object[][] obtenerDatosPolizaColectiva() throws Exception {
		return DatosExternos.leerCSV(archivoDataPolizaColectiva, ',', false);
	}
	
	@DataProvider(name= "Aprobar")
	public Object[][] obtenerDatosAprobar() throws Exception {
		return DatosExternos.leerCSV(archivoDataAprobar, ',', false);
	}
	
	@DataProvider(name= "Ver Poliza")
	public Object[][] obtenerDatosVerPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataVerPoliza, ',', false);
	}
	
	@DataProvider(name= "Imprimir Poliza")
	public Object[][] obtenerDatosImprimirPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataImprimirPoliza, ',', false);
	}
	
	@DataProvider(name= "Modifica Nombre y Documento Asegurado")
	public Object[][] obtenerDatosModificaNombreDocumentoAseguradoPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificaNombreDocumentoAsegurado, ',', false);
	}
	
	@DataProvider(name= "Modifica Nombre y Documento Tomador")
	public Object[][] obtenerDatosModificaNombreDocumentoTomadorPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificaNombreDocumentoTomador, ',', false);
	}
	
	@DataProvider(name= "Ver Historial Endoso Poliza")
	public Object[][] obtenerDatosVerHistorialEndosoPoliza() throws Exception {
		return DatosExternos.leerCSV(archivoDataVerHistorialEndoso, ',', false);
	}
	
	@DataProvider(name= "Modificar Beneficiario Asegurado")
	public Object[][] obtenerDatosModificarBeneficiarioAsegurado() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificacionBeneficiarioAsegurado, ',', false);
	}
	
	@DataProvider(name= "Modificar Actividad Laboral")
	public Object[][] obtenerDatosModificarActividadLaboral() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificacionActividadLaboral, ',', false);
	}
	
	@DataProvider(name= "Modificar Prima")
	public Object[][] obtenerDatosModificarPrima() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificacionPrima, ',', false);
	}
	
	@DataProvider(name = "Descarga Estado Cuenta")
	public Object[][] obtenerDatosEntradaDescargaEstadoCuenta() throws Exception {
		return DatosExternos.leerCSV(archivoDataPolizasDescargaEstadoCuenta, ',', false);
	}
	
	@DataProvider(name = "Descarga Endoso")
	public Object[][] obtenerDatosEntradaDescargaEndoso() throws Exception {
		return DatosExternos.leerCSV(archivoDataPolizasDescargaEndoso, ',', false);
	}
	
	@DataProvider(name = "Polizas Individuales")
	public Object[][] obtenerDatosEntradaPolizasIndividuales() throws Exception {
		return DatosExternos.leerCSV(archivoDataPolizasIndividuales, ',', false);
	}
	
	@DataProvider(name = "Enviar Solicitud")
	public Object[][] obtenerDatosEntradaEnviarSolicitud() throws Exception {
		return DatosExternos.leerCSV(archivoDataEnviarSolicitud, ',', false);
	}
	
	@DataProvider(name = "Modificar VC")
	public Object[][] obtenerDatosEntradaModificarVC() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificarVC, ',', false);
	}
	
	@DataProvider(name = "Agregar VC")
	public Object[][] obtenerDatosEntradaAgregarVC() throws Exception {
		return DatosExternos.leerCSV(archivoDataAgregarVC, ',', false);
	}
	
	@DataProvider(name = "Agregar AP")
	public Object[][] obtenerDatosEntradaAgregarAP() throws Exception {
		return DatosExternos.leerCSV(archivoDataAgregarAP, ',', false);
	}
	
	@DataProvider(name = "Modificar AP")
	public Object[][] obtenerDatosEntradaModificarAP() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificarAP, ',', false);
	}
	
	@DataProvider(name = "Descargar Certificado Solicitud")
	public Object[][] obtenerDatosEntradaDescargarCertificadoSolicitud() throws Exception {
		return DatosExternos.leerCSV(archivoDataDescargarCertificadoSolicitud, ',', false);
	}
	
	@DataProvider(name = "Consultar Solicitudes")
	public Object[][] obtenerDatosEntradaConsultaSolicitudes() throws Exception {
		return DatosExternos.leerCSV(archivoDataConsultaSolicitudes, ',', false);
	}
	
	@DataProvider(name = "Crear Cotizacion Tresor")
	public Object[][] obtenerDatosEntradaTresor() throws Exception {
		return DatosExternos.leerCSV(archivoDataTresor, ',', false);
	}
	
	@DataProvider(name = "Crear Cotizacion CNPVie")
	public Object[][] obtenerDatosEntradaCNPVie() throws Exception {
		return DatosExternos.leerCSV(archivoDataCNPVie, ',', false);
	}
		
	@DataProvider(name = "Crear Cotizacion Confiance")
	public Object[][] obtenerDatosEntradaConfiance() throws Exception {
		return DatosExternos.leerCSV(archivoDataConfiance, ',', false);
	}
	
	@DataProvider(name = "Crear Cotizacion Accidente")
	public Object[][] obtenerDatosEntradaAccidentesPersonales() throws Exception {
		return DatosExternos.leerCSV(archivoDataAccidentesPersonales, ',', false);
	}
	
	@DataProvider(name = "Descargar Cotizaciones")
	public Object[][] obtenerDatosEntradaDescargarCotizacion() throws Exception {
		return DatosExternos.leerCSV(archivoDataDescargarCotizacion, ',', false);
	}
	
	@DataProvider(name = "Consultar Cotizaciones")
	public Object[][] obtenerDatosEntradaConsultarCotizacion() throws Exception {
		return DatosExternos.leerCSV(archivoDataConsultaCotizacion, ',', false);
	}
	
	@DataProvider(name = "Crear Cotizacion Data")
	public Object[][] obtenerDatosEntradaCotizacion() throws Exception {
		return DatosExternos.leerCSV(archivoDataCotizacion, ',', false);
	}
	
	@DataProvider(name = "Buscar Poliza")
	public Object[][] obtenerDatosEntradaConsultaPolizas() throws Exception {
		return DatosExternos.leerCSV(archivoDataPolizas, ',', false);
	}
	
	@DataProvider(name = "Polizas Vigentes")
	public Object[][] obtenerDatosEntradaPolizasVigentes() throws Exception {
		return DatosExternos.leerCSV(archivoDataPolizasVigentes, ',', false);
	}
	
	@DataProvider(name= "Modificar Conducto Pago")
	public Object[][] obtenerDatosModificar() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificacionConductoPago, ',', false);
	}
	
	@DataProvider(name = "Modificar Domicilio")
	public Object[][] obtenerDatosEntradaModificacionDomicilio() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificacionDomiclio, ',', false);
	}
	
	
}
