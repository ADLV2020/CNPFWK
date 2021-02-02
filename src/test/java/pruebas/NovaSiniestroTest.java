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
import NovaSiniestros.Busqueda;
import NovaSiniestros.Denuncias;
import NovaSiniestros.Login;
import NovaSiniestros.Menu;
import NovaSiniestros.ModificarItemSolicitudPagos;
import NovaSiniestros.NuevaSolicitudPagos;
import NovaSiniestros.ReporteAuditoriaSiniestrosPagados;
import NovaSiniestros.ReporteIndicadoresSiniestros;
import NovaSiniestros.ReporteProductorRamo;
import NovaSiniestros.ReporteSiniestros;
import NovaSiniestros.ReporteSiniestrosPagados;
import NovaSiniestros.ReporteSiniestrosPrescriptos;
import NovaSiniestros.ReporteSolicitudesPago;
import NovaSiniestros.ReporteVencimientoSiniestros;
import NovaSiniestros.SolicitudPagos;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class NovaSiniestroTest {
	private WebDriver driver;
	private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String archivoDataCrearSiniestros = "..\\Ecosistemas-CNP\\Entrada\\datosCrearSiniestros.csv";
	private String archivoDataBusqueda = "..\\Ecosistemas-CNP\\Entrada\\datosBusqueda.csv";
	private String archivoDatosAmbiente = "..\\Ecosistemas-CNP\\Entrada\\ambienteNova.csv";
	private String archivoDataSolicitudPago = "..\\Ecosistemas-CNP\\Entrada\\solicitudPago.csv";
	private String archivoDataReporteSiniestros = "..\\Ecosistemas-CNP\\Entrada\\reporteSiniestros.csv";
	private String archivoDataReporteSolicitudesPago = "..\\Ecosistemas-CNP\\Entrada\\reporteSolicitudesPago.csv";
	private String archivoDataReporteIndicadoresSiniestros = "..\\Ecosistemas-CNP\\Entrada\\reporteIndicadoresSiniestros.csv";
	private String archivoDataModificarAgregaSubreclamo = "..\\Ecosistemas-CNP\\Entrada\\modificarAgregaSubreclamo.csv";
	private String archivoDataReporteSiniestrosPagados = "..\\Ecosistemas-CNP\\Entrada\\reporteSiniestrosPagados.csv";
	private String archivoDataReporteSiniestrosPrescriptos = "..\\Ecosistemas-CNP\\Entrada\\reporteSiniestrosPrescriptos.csv";
	private String archivoDataReporteProductorRamo = "..\\Ecosistemas-CNP\\Entrada\\reporteProductorRamo.csv";
	private String archivoDataAuditoriaSiniestrosPagados = "..\\Ecosistemas-CNP\\Entrada\\auditoriaSiniestrosPagados.csv";
	private String archivoDataReporteVencimientoSiniestros = "..\\Ecosistemas-CNP\\Entrada\\reporteVencimientoSiniestros.csv";
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
		//Assert.assertTrue(menu.pantallaMenuDisponible(), "Error al loguearse");
	}
	
	@Test(dataProvider = "Crea Nuevo Siniestro")
	public void cargarDenuncia(String titulo, String denunciante, String dni, String fechaNotificacion, String fechaSiniestro, String horaSiniestro, String ramo, String causa, String tipoAccidente, String cobertura, String hechoGenerador, String tipoLesion, String sucursal, String estado, String subEstado, String moneda) throws InvalidFormatException, IOException, InterruptedException{
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
	
	
	@Test(dataProvider = "Datos Busqueda")
	public void buscarSiniestro(String titulo, String nroSiniestro, String nroPoliza, String nroDNI, String fechaDesde, String fechaHasta, String ramo) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, titulo, 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "número de siniestro: " + nroSiniestro + ", dni: " + nroDNI + ", fecha desde: " + fechaDesde  + ", fecha hasta: " + fechaHasta  + ", ramo: " + ramo, 10);
		
		menu.clicOnBusqueda();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla de búsqueda");
		
		Busqueda busqueda = new Busqueda(driver);
		Assert.assertTrue(busqueda.pantallaBusquedaDisponible());
		busqueda.ingresarDatosBusqueda(nroSiniestro, nroPoliza, nroDNI, fechaDesde, fechaHasta, ramo);
		Utilities.waiter(2);

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos para la realización de la Búsqueda");
		busqueda.clicOnBuscar();
		Utilities.waiter(2);

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la búsqueda");
		
		busqueda.clicOnDetalles();
		Utilities.waiter(2);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Detalles del siniestro");
		Assert.assertTrue(busqueda.pantallaSiniestroDisponible());
		
	}
	
	
	
	@Test(dataProvider = "Solicitud Pago")
	public void solicitudPagos(String pagarA,String sucursal,String fechaRegistro,String fechaContable,String fechaEstimPago, String ramo,String nombreAsegurado,String tipoDocumento,String nroDocumento,String moneda,String nombreCobrador,String impCambio,String impTotalPagar,String desc, String cbu, String nroSiniestroBuscar, String tipoPago, String clasePago, String conceptoPago, String impAPagar) throws InvalidFormatException, IOException, InterruptedException {
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
	
	@Test(dataProvider = "Modificacion Agrega Subreclamo")
	public void modificarAgregaSubreclamo(String fechaDesde, String fechaHasta, String denunciante, String cobertura, String hechoGenerador, String tipoLesion, String estado, String subEstado, String moneda) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Modificar: agregar subreclamo", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha desde: " + fechaDesde + ", Fecha hasta: " + fechaHasta + ", denunciante: " + denunciante + ", cobertura: " + cobertura + ", hechoGenerador: " + hechoGenerador + ", tipoLesion: " + tipoLesion + ", estado: " + estado + ", subEstado: " + subEstado + ", moneda: " + moneda, 10);

		
		menu.clicOnDenuncias();
		
		Denuncias denuncia = new Denuncias(driver);
		
		denuncia.ingresaFechasBusqueda(fechaDesde, fechaHasta);
		denuncia.clicOnBuscarSiniestros();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la búsqueda");
		//denuncia.clicOnLapizEdicion();
		
		denuncia.clicOnLapizCorrecto("LEZCANO GRACIELA ELENA");
		
		denuncia.clicOnGuardarDenuncia();
		denuncia.clicOnLupa();
		
		denuncia.clicOnDesplegarSubReclamos();
		denuncia.clicOnAgregarSubReclamo();
		
		denuncia.cargaCamposSubreclamo(estado, subEstado, moneda, cobertura, hechoGenerador, tipoLesion);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Campos del subreclamo");
		Utilities.waiter(3);
		
		denuncia.guardaCamposSubreclamo();
		denuncia.clicOnGuardarDenuncia();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Número de siniestro");
		denuncia.aceptarSubReclamoAgregado();
		Utilities.waiter(3);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Denuncia finalizada");

	}
	
	
	
	@Test(dataProvider = "Reporte Siniestros")
	public void reporteSiniestros(String fechaDesde, String fechaHasta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Reporte de Siniestros", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha desde: " + fechaDesde + ", Fecha hasta: " + fechaHasta, 10);
		
		menu.clicOnDespliegaReportes();
		menu.clicOnReporteSiniestro();
		
		ReporteSiniestros reporte = new ReporteSiniestros(driver);
		Assert.assertTrue(reporte.pantallaReporteDisponible());
		
		reporte.ingresaFechas(fechaDesde, fechaHasta);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingreso de fechas para la búsqueda");
		reporte.clicOnBuscar();
		
		Utilities.waiter(4);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado");
		
		reporte.clicOnDescargarPDF();
		reporte.clicOnDescargarExcel();
	}
	
	
	@Test(dataProvider = "Reporte Solicitudes Pago")
	public void reporteSolicitudesPago(String fechaDesde, String fechaHasta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Reporte de Solicitudes de Pago", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha desde: " + fechaDesde + ", Fecha hasta: " + fechaHasta, 10);
		
		menu.clicOnDespliegaReportes();
		menu.clicOnReporteSolicitudesPago();
		
		ReporteSolicitudesPago reporte = new ReporteSolicitudesPago(driver);
		Assert.assertTrue(reporte.pantallaReporteDisponible());
		
		reporte.ingresaFechas(fechaDesde, fechaHasta);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingreso de fechas para la búsqueda");
		reporte.clicOnBuscar();
		
		Utilities.waiter(4);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado");

		reporte.clicOnDescargarPDF();
		reporte.clicOnDescargarExcel();
	}
	
	@Test(dataProvider = "Reporte Indicadores Siniestros")
	public void reporteIndicadoresSiniestros(String fechaDesde, String fechaHasta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Reporte de Indicadores de Siniestros", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha desde: " + fechaDesde + ", Fecha hasta: " + fechaHasta, 10);
		
		menu.clicOnDespliegaReportes();
		menu.clicOnReporteIndicadoresSiniestros();
		
		ReporteIndicadoresSiniestros reporte = new ReporteIndicadoresSiniestros(driver);
		Assert.assertTrue(reporte.pantallaReporteDisponible());
		
		reporte.ingresaFechas(fechaDesde, fechaHasta);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingreso de fechas para la búsqueda");

		reporte.clicOnBuscar();
		
		Utilities.waiter(4);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado");

		reporte.clicOnDescargarPDF();
		reporte.clicOnDescargarExcel();
	}
	
	@Test(dataProvider = "Reporte Siniestros Pagados")
	public void reporteSiniestrosPagados(String fechaDesde, String fechaHasta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Reporte de Siniestros Pagados", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha desde: " + fechaDesde + ", Fecha hasta: " + fechaHasta, 10);
		
		menu.clicOnDespliegaReportes();
		menu.clicOnReporteSiniestrosPagados();
		
		ReporteSiniestrosPagados reporte = new ReporteSiniestrosPagados(driver);
		Assert.assertTrue(reporte.pantallaReporteDisponible());
		
		reporte.ingresaFechas(fechaDesde, fechaHasta);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingreso de fechas para la búsqueda");
		reporte.clicOnBuscar();
		
		Utilities.waiter(10);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado");
		
		reporte.clicOnDescargarPDF();
		reporte.clicOnDescargarExcel();
	}
	
	@Test(dataProvider = "Reporte Siniestros Prescriptos")
	public void reportesSiniestrosPrescriptos(String fechaDesde, String fechaHasta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Reporte de Siniestros Prescriptos", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha desde: " + fechaDesde + ", Fecha hasta: " + fechaHasta, 10);
		
		menu.clicOnDespliegaReportes();
		menu.clicOnReporteSiniestrosPrescriptos();
		
		ReporteSiniestrosPrescriptos reporte = new ReporteSiniestrosPrescriptos(driver);
		Assert.assertTrue(reporte.pantallaReporteDisponible());
		
		reporte.ingresaFechas(fechaDesde, fechaHasta);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingreso de fechas para la búsqueda");
		reporte.clicOnBuscar();
		
		Utilities.waiter(10);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado");
		
		reporte.clicOnDescargarPDF();
		reporte.clicOnDescargarExcel();
	}
	
	@Test(dataProvider = "Reporte Productor Y Ramo")
	public void reporteProductorYRamo(String fechaDesde, String fechaHasta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Registro de Siniestros Ingresados por Productor y Ramo", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha desde: " + fechaDesde + ", Fecha hasta: " + fechaHasta, 10);
		
		menu.clicOnDespliegaReportes();
		menu.clicOnReporteProductorRamo();
		
		ReporteProductorRamo reporte = new ReporteProductorRamo(driver);
		
		Assert.assertTrue(reporte.pantallaReporteDisponible());
		
		reporte.ingresaFechas(fechaDesde, fechaHasta);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingreso de fechas para la búsqueda");
		reporte.clicOnBuscar();
		
		Utilities.waiter(10);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado");
		
		reporte.clicOnDescargarPDF();
		reporte.clicOnDescargarExcel();
	}
	
	@Test(dataProvider = "Reporte Auditoria Siniestros Pagados")
	public void reporteAuditoriaSiniestrosPagados(String fechaDesde, String fechaHasta) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Registro de Auditoria de Siniestros Pagados", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha desde: " + fechaDesde + ", Fecha hasta: " + fechaHasta, 10);
		
		menu.clicOnDespliegaReportes();
		menu.clicOnAuditoriaSiniestrosPagados();
		
		ReporteAuditoriaSiniestrosPagados reporte = new ReporteAuditoriaSiniestrosPagados(driver);
		
		Assert.assertTrue(reporte.pantallaReporteDisponible());

		reporte.ingresaFechas(fechaDesde, fechaHasta);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingreso de fechas para la búsqueda");
		reporte.clicOnBuscar();
		
		Utilities.waiter(10);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado");
		
		reporte.clicOnDescargarPDF();
		reporte.clicOnDescargarExcel();
	}
	
	@Test(dataProvider = "Reporte Vencimiento Siniestros")
	public void reporteVencimientoSiniestros(String fechaVigencia, String nroSiniestro, String ramo, String esconderEnEspera) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Registro de Vencimiento de Siniestros", 20);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada", 12);
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Fecha de Vigencia: " + fechaVigencia + ", Número de Siniestro: " + nroSiniestro + ", Ramo: " + ramo + ", Esconder en espera: " + esconderEnEspera, 10);
		
		menu.clicOnDespliegaReportes();
		menu.clicOnReporteVencimientoSiniestros();
		
		ReporteVencimientoSiniestros reporte = new ReporteVencimientoSiniestros(driver);
		
		Assert.assertTrue(reporte.pantallaReporteDisponible());
		
		reporte.ingresaDatosBusqueda(fechaVigencia, nroSiniestro, ramo, esconderEnEspera);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Ingreso de fechas para la búsqueda");
		
		Utilities.waiter(5);
		reporte.clicOnBuscar();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado");
		
		reporte.clicOnDescargarPDF();
		reporte.clicOnDescargarExcel();
		
		menu.irAHome();
	}
	
	@DataProvider(name = "Reporte Vencimiento Siniestros")
	public Object[][] obtenerDatosEntradaReporteVencimientoSiniestros() throws Exception {
		return DatosExternos.leerCSV(archivoDataReporteVencimientoSiniestros, ',', false);
	}
	
	@DataProvider(name = "Reporte Auditoria Siniestros Pagados")
	public Object[][] obtenerDatosEntradaAuditoriaSiniestrosPagados() throws Exception {
		return DatosExternos.leerCSV(archivoDataAuditoriaSiniestrosPagados, ',', false);
	}
	
	@DataProvider(name = "Reporte Productor Y Ramo")
	public Object[][] obtenerDatosEntradaReporteProductorRamo() throws Exception {
		return DatosExternos.leerCSV(archivoDataReporteProductorRamo, ',', false);
	}
	
	@DataProvider(name = "Reporte Siniestros Prescriptos")
	public Object[][] obtenerDatosEntradaReporteSiniestrosPrescriptos() throws Exception {
		return DatosExternos.leerCSV(archivoDataReporteSiniestrosPrescriptos, ',', false);
	}
	
	@DataProvider(name = "Reporte Siniestros Pagados")
	public Object[][] obtenerDatosEntradaReporteSiniestrosPagados() throws Exception {
		return DatosExternos.leerCSV(archivoDataReporteSiniestrosPagados, ',', false);
	}
	
	@DataProvider(name = "Modificacion Agrega Subreclamo")
	public Object[][] obtenerDatosEntradaModificarAgregaSubreclamo() throws Exception {
		return DatosExternos.leerCSV(archivoDataModificarAgregaSubreclamo, ',', false);
	}
	
	@DataProvider(name = "Reporte Indicadores Siniestros")
	public Object[][] obtenerDatosEntradaReporteIndicadoresSiniestros() throws Exception {
		return DatosExternos.leerCSV(archivoDataReporteIndicadoresSiniestros, ',', false);
	}
	
	@DataProvider(name = "Reporte Solicitudes Pago")
	public Object[][] obtenerDatosEntradaReporteSolicitudesPago() throws Exception {
		return DatosExternos.leerCSV(archivoDataReporteSolicitudesPago, ',', false);
	}
	
	@DataProvider(name = "Reporte Siniestros")
	public Object[][] obtenerDatosEntradaReporteSiniestros() throws Exception {
		return DatosExternos.leerCSV(archivoDataReporteSiniestros, ',', false);
	}

	@DataProvider(name = "Crea Nuevo Siniestro")
	public Object[][] obtenerDatosEntradaSiniestro() throws Exception {
		return DatosExternos.leerCSV(archivoDataCrearSiniestros, ',', false);
	}
	
	@DataProvider(name = "Datos Busqueda")
	public Object[][] obtenerDatosEntradaBusqueda() throws Exception {
		return DatosExternos.leerCSV(archivoDataBusqueda, ',', false);	
	}
	
	@DataProvider(name = "Solicitud Pago")
	public Object[][] obtenerDatosEntradaSolicitudPago() throws Exception {
		return DatosExternos.leerCSV(archivoDataSolicitudPago, ',', false);
	}

}
