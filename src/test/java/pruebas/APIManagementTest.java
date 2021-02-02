package pruebas;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import APIManagement.ApiDetails;
import APIManagement.Home;
import APIManagement.Productos;
import APIManagement.SignIn;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExternos;
import Utilidades.Utilities;

public class APIManagementTest {
	private WebDriver driver;
	private String pathDriver = "..\\Ecosistemas-CNP\\Drivers\\chromedriver.exe";
	private String rutaEvidencias = "..\\Ecosistemas-CNP\\Evidencias";
	private String nombreArchivoEvidencias;
	private Home home;
	private Productos productos;
	private ApiDetails apiDetails;
	private String successfulResponseCode = "200";
	private String archivoDataCotizarApi = "..\\Ecosistemas-CNP\\Entrada\\cotizar.csv";
	private String archivoDataGenerarSolicitudApi = "..\\Ecosistemas-CNP\\Entrada\\generarSolicitud.csv";
	private String archivoDataConsultaPdfApi = "..\\Ecosistemas-CNP\\Entrada\\consultaPdf.csv";
	private String archivoDataAffiliateApi = "..\\Ecosistemas-CNP\\Entrada\\affiliate.csv";
	private String archivoDatosAmbiente = "..\\Ecosistemas-CNP\\Entrada\\ambiente.csv";
	private String nroCotizacion;
	private String nroSolicitud;
	
	@BeforeSuite
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", pathDriver);
		
		driver = new ChromeDriver();
		
		Object[][] datosURL = DatosExternos.leerCSV(archivoDatosAmbiente, ',', false);
		
		driver.get(datosURL[0][0].toString());
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		System.out.println("Inicio de Suite de Pruebas");
		
		nombreArchivoEvidencias = "CNP - Testing - API Proyecto Nova - Evidencias - " + Utilities.obtenerFechaActual() + ".docx";
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla Principal");
		
		// Hacer clic en la opción Ingresar de la página de inicio
		home = new Home(driver);
		home.clicSignIn();
			
		// Ingresar credenciales de acceso al API Management
		SignIn signIn = new SignIn(driver);
		signIn.fillSignIngForm(datosURL[0][1].toString(), datosURL[0][2].toString());
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Credenciales para el login");
		
		signIn.clicOnSignIn();
		
		Utilities.waiter(5);
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Pantalla principal luego del login");
	}
	
	@BeforeTest
	public void login() throws Exception {
		System.out.println("Inicio de Prueba");
	}
	
	@Test
	public void bienvenidaApi() throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "API Bienvenida", 16);
		
		ingresarEnApis();

		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "APIs disponibles para el usuario");
		
		// Acceder a APIs de Assurplan
		productos.goToAssurplanApis();
		
		// Ir a la API a probar
		apiDetails = new ApiDetails(driver);
		apiDetails.goToBienvenidaApi();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la API a probar");
		
		apiDetails.clicOnTryItApi();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Definición de la API a probar");
		
		// Sin Parametros
		apiDetails.clicOnSend();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la ejecución");

		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Response Code - " + apiDetails.getResponseCode() + " - Response Body - " + apiDetails.getResponseBody(), 13);
		
		Assert.assertEquals(apiDetails.getResponseCode(), successfulResponseCode);

		// Cierre de sección Try It
		apiDetails.closeTryItSection();
	}
	
	@Test(dataProvider = "API Cotizar Data")
	public void cotizarApi(String codigoProductor, String fechaNacimiento, String cantidadModulos, String expectedResult, String testDescription) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "API Cotizar - " + testDescription, 16);
		
		ingresarEnApis();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "APIs disponibles para el usuario");
		
		// Acceder a APIs de Assurplan
		productos.goToAssurplanApis();
		
		// Ir a la API a probar
		apiDetails = new ApiDetails(driver);
		apiDetails.goToCotizarApi();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la API a probar");
		
		apiDetails.clicOnTryItApi();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Definición de la API a probar");
		
		// Parametros
		apiDetails.fillBodyRequest("{\"codigoProductor\": " + codigoProductor + 
									",\"fechaNacimiento\":\"" + fechaNacimiento + 
									"\",\"cantidadModulos\":" + cantidadModulos + "}");
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada para la ejecución");
		
		apiDetails.clicOnSend();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la ejecución");
		
		try {
			//System.out.println(apiDetails.getResponseBody());
			JSONObject jsonObject = new JSONObject(apiDetails.getResponseBody());
			nroCotizacion = jsonObject.get("id").toString();
			//System.out.println(nroCotizacion);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		Assert.assertEquals(apiDetails.getResponseCode(), expectedResult);
		
		// Cierre de sección Try It
		apiDetails.closeTryItSection();
	}
	
	@Test(dataProvider = "API Generar Solicitud Data")
	public void generarSolicitudApi(String productor__codigo, String productor__nombre, String productor__matricula, String cotizacion__CodigoCotizacion, String cotizacion__CodigoProductor, String cotizacion__fechaNacimiento, String cotizacion__cantidadModulos, String medioDePago__TipoMedioDePago, String medioDePago__cbu, String medioDePago__tarjetaCredito__marcaTarjeta, String medioDePago__tarjetaCredito__marcaTarjetaOtras, String medioDePago__tarjetaCredito__numero, String medioDePago__tarjetaCredito__codigoSeguridad, String medioDePago__tarjetaCredito__vencimiento, String medioDePago__tarjetaCredito__codigoAutorizacion, String asegurado__apellido1, String asegurado__apellido2, String asegurado__nombre, String asegurado__tipoDocumento, String asegurado__numeroDocumento, String asegurado__estadoCivil, String asegurado__fechaNacimiento, String asegurado__lugarNacimiento, String asegurado__nacionalidad, String asegurado__sexo, String asegurado__cuit, String asegurado__email, String asegurado__actividadLaboral, String asegurado__ingresoMensual, String asegurado__zurdo, String domicilioParticular__calle, String domicilioParticular__numero, String domicilioParticular__piso, String domicilioParticular__departamento, String domicilioParticular__codigoPostal, String domicilioParticular__localidad, String domicilioParticular__provincia, String domicilioParticular__telefono, String beneficiarios__vinculo, String beneficiarios__porcentaje, String beneficiarios__apellido1, String beneficiarios__apellido2, String beneficiarios__nombre, String beneficiarios__tipoDocumento, String beneficiarios__numeroDocumento, String expectedResult, String testDescription) throws InvalidFormatException, IOException, InterruptedException {		
		cotizarApi("122345", "1970-03-20", "10", "200", "1-Exitoso");
		//System.out.println(nroCotizacion);
		
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "API Generar Solicitud - " + testDescription, 16);
		
		ingresarEnApis();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "APIs disponibles para el usuario");
		
		// Acceder a APIs de Assurplan
		productos.goToAssurplanApis();
		
		// Ir a la API a probar
		apiDetails = new ApiDetails(driver);
		apiDetails.goToGenerarSolicitudApi();
		apiDetails.clicOnTryItApi();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Definición de la API a probar");
		
		// Parametros
		apiDetails.fillBodyRequest("{" + 
				"   \"productor\":{" + 
				"      \"codigo\":"+ productor__codigo + "," + 
				"      \"nombre\":\"" + productor__nombre + "\"," + 
				"      \"matricula\":" + productor__matricula + "" + 
				"   }," + 
				"   \"cotizacion\":{" + 
				"      \"CodigoCotizacion\":" + (nroCotizacion == null?cotizacion__CodigoCotizacion:nroCotizacion) + "," + 
				"      \"CodigoProductor\":" + cotizacion__CodigoProductor + "," + 
				"      \"fechaNacimiento\":\"" + cotizacion__fechaNacimiento + "\"," + 
				"      \"cantidadModulos\":" + cotizacion__cantidadModulos + "" + 
				"   }," + 
				"   \"medioDePago\":{" + 
				"      \"TipoMedioDePago\":" + medioDePago__TipoMedioDePago + "," + 
				"      \"cbu\":\"" + medioDePago__cbu + "\"," + 
				"      \"tarjetaCredito\":{" + 
				"         \"marcaTarjeta\":" + medioDePago__tarjetaCredito__marcaTarjeta + "," + 
				"         \"marcaTarjetaOtras\":\"" + medioDePago__tarjetaCredito__marcaTarjetaOtras + "\"," + 
				"         \"numero\":\"" + medioDePago__tarjetaCredito__numero + "\"," + 
				"         \"codigoSeguridad\":\"" + medioDePago__tarjetaCredito__codigoSeguridad + "\"," + 
				"         \"vencimiento\":\"" + medioDePago__tarjetaCredito__vencimiento + "\"," + 
				"         \"codigoAutorizacion\":\"" + medioDePago__tarjetaCredito__codigoAutorizacion + "\"" + 
				"      }" + 
				"   }," + 
				"   \"asegurado\":{" + 
				"      \"apellido1\":\"" + asegurado__apellido1 + "\"," + 
				"      \"apellido2\":\"" + asegurado__apellido2 + "\"," + 
				"      \"nombre\":\"" + asegurado__nombre + "\"," + 
				"      \"tipoDocumento\":" + asegurado__tipoDocumento + "," + 
				"      \"numeroDocumento\":\"" + asegurado__numeroDocumento + "\"," + 
				"      \"estadoCivil\":" + asegurado__estadoCivil + "," + 
				"      \"fechaNacimiento\":\"" + asegurado__fechaNacimiento + "\"," + 
				"      \"lugarNacimiento\":\"" + asegurado__lugarNacimiento + "\"," + 
				"      \"nacionalidad\":\"" + asegurado__nacionalidad + "\"," + 
				"      \"sexo\":" + asegurado__sexo + "," + 
				"      \"cuit\":\"" + asegurado__cuit + "\"," + 
				"      \"email\":\"" + asegurado__email + "\"," + 
				"      \"actividadLaboral\":\"" + asegurado__actividadLaboral + "\"," + 
				"      \"ingresoMensual\":" + asegurado__ingresoMensual + "," + 
				"      \"zurdo\":" + asegurado__zurdo + "" + 
				"   }," + 
				"   \"domicilioParticular\":{" + 
				"      \"calle\":\"" + domicilioParticular__calle + "\"," + 
				"      \"numero\":\"" + domicilioParticular__numero + "\"," + 
				"      \"piso\":\"" + domicilioParticular__piso + "\"," + 
				"      \"departamento\":\"" + domicilioParticular__departamento + "\"," + 
				"      \"codigoPostal\":\"" + domicilioParticular__codigoPostal + "\"," + 
				"      \"localidad\":\"" + domicilioParticular__localidad + "\"," + 
				"      \"provincia\":\"" + domicilioParticular__provincia + "\"," + 
				"      \"telefono\":\"" + domicilioParticular__telefono + "\"" + 
				"   }," + 
				"   \"beneficiarios\":[" + 
				"      {" + 
				"         \"vinculo\":\"" + beneficiarios__vinculo + "\"," + 
				"         \"porcentaje\":" + beneficiarios__porcentaje + "," + 
				"         \"apellido1\":\"" + beneficiarios__apellido1 + "\"," + 
				"         \"apellido2\":\"" + beneficiarios__apellido2 + "\"," + 
				"         \"nombre\":\"" + beneficiarios__nombre + "\"," + 
				"         \"tipoDocumento\":" + beneficiarios__tipoDocumento + "," + 
				"         \"numeroDocumento\":\"" + beneficiarios__numeroDocumento + "\"" + 
				"      }" +
				"   ]" + 
				"}");
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada para la ejecución");
		
		apiDetails.clicOnSend();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la ejecución");

		try {
			//System.out.println(apiDetails.getResponseBody());
			JSONObject jsonObject = new JSONObject(apiDetails.getResponseBody());
			nroSolicitud = jsonObject.get("id").toString();
			//System.out.println(nroSolicitud);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Response Code - " + apiDetails.getResponseCode() + " - Response Body - " + apiDetails.getResponseBody(), 13);
				
		Assert.assertEquals(apiDetails.getResponseCode(), expectedResult);
		
		// Cierre de sección Try It
		apiDetails.closeTryItSection();
	}
	
	@Test(dataProvider = "API Consulta Pdf Data")
	public void obtenerPdfApi(String Nro_Solicitud, String expectedResult, String testDescription) throws InvalidFormatException, IOException, InterruptedException {
		if (expectedResult != successfulResponseCode) {
			generarSolicitudApi("122345", "Nombre Productor", "123456", nroCotizacion, "224455", "1970-03-20", "10", "0", "1234567890123456789012", "1", "Marca Tarjeta Otras", "4338300001018776", "123", "0921", "123","Lisboa", "De los arcos", "Juan Manuel", "3", "20455676", "1", "1970-03-29", "Villa Ballester", "Argentina", "0", "23-20455676-2", "mail@test.com", "Empleado","65.000", "true", "Libertad", "970", "7", "A", "1201", "CABA", "Buenos Aires", "011-47782222", "Esposa", "0.50", "Lamarque", "B1 Apellido 2", "Libertad", "3", "25467889","200", "1-Exitoso");
		}
		
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "API Obtener PDF - " + testDescription, 16);
		
		ingresarEnApis();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "APIs disponibles para el usuario");
		
		// Acceder a APIs de Assurplan
		productos.goToAssurplanApis();
		
		// Ir a la API a probar
		apiDetails = new ApiDetails(driver);
		apiDetails.goToObtenerPdfApi();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la API a probar");
		
		apiDetails.clicOnTryItApi();
		
		// Parametros
		apiDetails.fillGetPdfParam((expectedResult != successfulResponseCode)? Nro_Solicitud : nroSolicitud);
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada para la ejecución");
		
		apiDetails.clicOnSend();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la ejecución");
		
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Response Code - " + apiDetails.getResponseCode() + " - Response Body - " + apiDetails.getResponseBody(), 13);
				
		Assert.assertEquals(apiDetails.getResponseCode(), expectedResult);
		
		// Cierre de sección Try It
		apiDetails.closeTryItSection();
	}
	
	@Test(dataProvider = "API Affiliate Data")
	public void llamandoAlDoctorApi(String dni, String reference, String expectedResult, String testDescription) throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "API Llamando al Doctor - " + testDescription, 16);
		
		ingresarEnApis();
		
		// Acceder a APIs de Llamando al Doctor
		productos.goToLlamandoApis();
		
		// Ir a la API a probar
		apiDetails = new ApiDetails(driver);
		apiDetails.goToAffiliateApi();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de la API a probar");
		
		apiDetails.clicOnTryItApi();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Definición de la API a probar");
		
		// Parametros
		apiDetails.fillAffiliateParams(dni, reference);
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Datos de entrada para la ejecución");
		
		apiDetails.clicOnSend();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Resultado de la ejecución");
		
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencias + "\\" + nombreArchivoEvidencias, "Response Code - " + apiDetails.getResponseCode() + " - Response Body - " + apiDetails.getResponseBody(), 13);
		
		Assert.assertEquals(apiDetails.getResponseCode(), expectedResult);
		
		// Cierre de sección Try It
		apiDetails.closeTryItSection();
	}

	@AfterTest
	public void cierre() {
		//driver.close(); Descomentar cuando terminen las pruebas para que el explorador se cierre
		
		System.out.println("Fin de Prueba");
	}
	
	@AfterSuite
	public void notificarFin() {
		System.out.println("Fin de Suite de Pruebas");
	}
	
	@DataProvider(name = "API Cotizar Data")
	public Object[][] obtenerDatosEntradaCotizarApi() throws Exception {
		return DatosExternos.leerCSV(archivoDataCotizarApi, ',', false);
	}
	
	@DataProvider(name = "API Generar Solicitud Data")
	public Object[][] obtenerDatosEntradaGenerarSolicitudApi() throws Exception {
		return DatosExternos.leerCSV(archivoDataGenerarSolicitudApi, ',', false);
	}
	
	@DataProvider(name = "API Consulta Pdf Data")
	public Object[][] obtenerDatosEntradaConsultaPdfApi() throws Exception {
		return DatosExternos.leerCSV(archivoDataConsultaPdfApi, ',', false);
	}
	
	@DataProvider(name = "API Affiliate Data")
	public Object[][] obtenerDatosEntradaAffiliateApi() throws Exception {
		return DatosExternos.leerCSV(archivoDataAffiliateApi, ',', false);
	}
	
	public void ingresarEnApis() throws InvalidFormatException, IOException, InterruptedException {		
		// Ir a sección de Productos
		home.goToProductos();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "Opciones de producto disponibles para el usuario");
		
		// Ir a las APIs de Testing
		productos = new Productos(driver);
		productos.goToApis();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencias + "\\img.png", rutaEvidencias + "\\" + nombreArchivoEvidencias, "APIs disponibles para el usuario");
	}
}
