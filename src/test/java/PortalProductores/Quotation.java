package PortalProductores;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilidades.Utilities;

public class Quotation {
	@FindBy(xpath="//a[contains(text(),'Nueva Cotización')]")
	WebElement btnNuevaCotizacion;
	
	@FindBy(xpath="//a[@id='collectiveLife']")
	WebElement btnVidaColectiva;
	
	@FindBy(xpath="//select[@id='NR_Seq_Produto']")
	WebElement selProducto;
	
	@FindBy(xpath="//div[@class='row margin-bottom']//label[2]")
	WebElement radPersonaFisica;
	
	@FindBy(xpath="//div[@class='row margin-bottom']//label[3]")
	WebElement radPersonaJuridica;
	
	@FindBy(xpath="//select[@id='NR_Seq_Tipo_Documento']")
	WebElement selTipoDocumento;
	
	@FindBy(xpath="//input[@name='DS_Documento']")
	WebElement txtDocumento;
	
	@FindBy(xpath="//select[@id='NR_Seq_Provincia']")
	WebElement selProvincia;
	
	@FindBy(xpath="//input[@name='DS_Sobrenome']")
	WebElement txtApellido;
	
	@FindBy(xpath="//input[@name='DS_Nome_Tomador']")
	WebElement txtNombre;
	
	@FindBy(xpath="//body[@class='skin-green-light sidebar-mini']//div[@class='row']//div[@class='row']//div[@class='row']//label[2]")
	WebElement radMasculino;
	
	@FindBy(xpath="//body[@class='skin-green-light sidebar-mini']//div[@class='row']//div[@class='row']//div[@class='row']//label[3]")
	WebElement radFemenino;

	@FindBy(xpath="//div[@class='wizard-buttons ng-scope']//a[1]")
	WebElement btnCotizar;
	
	@FindBy(xpath="//select[@id='aTipoDocumentoCodigo']")
	WebElement selTipoDocumentoAsegurado;
	
	@FindBy(xpath="//input[@name='aDocumento']")
	private WebElement txtDocumentoAsegurado;
	
	@FindBy(xpath="//input[@id='aDataNascimento']")
	private WebElement txtFechaNacimiento;
	
	@FindBy(xpath="//input[@name='aSobrenome']")
	private WebElement txtApellidoAsegurado;
	
	@FindBy(xpath="//input[@name='aNome']")
	private WebElement txtNombreAsegurado;
	
	@FindBy(xpath="//*[@id=\"divAssegurados\"]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[3]/label[3]/input[1]")
	private WebElement radioFemenino;
	
	@FindBy(xpath="//*[@id=\"divAssegurados\"]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[3]/label[2]/input[1]")
	private WebElement radioMasculino;
	
	@FindBy(xpath="//a[@title='Nueva Cotización']")
	private WebElement btnIncluir;
	
	@FindBy(xpath="//a[contains(text(), 'Incluir')]")
	private WebElement btnIncluirLCT;
	
	@FindBy(xpath="//a[@data-wizard='finish']")
	private WebElement btnGuardar;
	
	@FindBy(xpath="//button[contains(text(), 'Sí')]")
	private WebElement btnConfirmar;
	
	@FindBy(xpath="//button[contains(text(), 'Cerrar')]")
	private WebElement btnCerrar;
	
	@FindBy(xpath="//input[@ng-model='inputEmail.Email']")
	private WebElement txtMail;
	
	@FindBy(xpath="//a[@ng-click='EnviarCorreo()']")
	private WebElement btnEnviar;
	
	//@FindBy(xpath="//*[@id=\"divAssegurados\"]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[4]/a[1]")
	//private WebElement btnIncluir;
	
	@FindBy(xpath="//span[@id='section-header' and contains(text(), 'Cotizaciones')]")
	private WebElement pantallaCotizaciones;
	
	@FindBy(xpath="//h3[contains(text(),'Nueva Cotización')]")
	private WebElement pantallaNuevaCotizacion;
	
	@FindBy(xpath="//h4[contains(text(),'La operación se completó exitosamente')]")
	private WebElement pantallaFinalizacion;
	
	//consulta cotizaciones
	@FindBy(xpath="//input[@id='DataInicio']")
	private WebElement txtFechaDesde;
	
	@FindBy(xpath="//input[@id='DataFim']")
	private WebElement txtFechaHasta;
	
	@FindBy(xpath="//select[@id='IdProduto']")
	private WebElement selRamo;
	
	@FindBy(xpath="//input[@id='Nome']")
	private WebElement inputNombre;
	
	@FindBy(xpath="//input[@id='Sobrenome']")
	private WebElement inputApellido;
	
	@FindBy(xpath="//a[contains(text(), 'Buscar')]")
	private WebElement btnBuscar;
	
	@FindBy(xpath="//option[contains(text(), 'ACCIDENTES PERSONALES COLECTIVO')]")
	private WebElement ramoSeleccionado;
	
	@FindBy(xpath="//tbody/tr[1]/td[9]/a[1]")
	private WebElement btnVerDetalles;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/div[1]/span[1]/button[1]")
	private WebElement btnBusquedaProductor;
	
	@FindBy(xpath="//input[@id='Name']")
	private WebElement txtNombreProductor;
	
	@FindBy(xpath="//input[@id='DocumentNumber']")
	private WebElement txtCodigo;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[3]/div[1]/a[2]")
	private WebElement btnBuscarProductor;
	
	@FindBy(xpath="//tbody/tr[1]/td[4]/a[1]")
	private WebElement btnSeleccionarProductor;
	
	@FindBy(xpath="//tbody/tr[1]/td[8]/a[1]")
	private WebElement btnDescargar;
	
	@FindBy(xpath="//input[@id='aSalarioBruto']")
	private WebElement txtSalario;
	
	@FindBy(xpath="//input[@id='aDataContratacao']")
	private WebElement txtFechaContrato;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/a[1]")
	private WebElement btnAccidentesPersonales;
	
	@FindBy(xpath="//a[@id='CNPUniversal']")
	private WebElement btnConfiance;
	
	@FindBy(xpath="//a[@id='CNPVie']")
	private WebElement btnCNPVie;
	
	@FindBy(xpath="//a[@id='CNPUniversalTresor']")
	private WebElement btnTresor;
	
	@FindBy(xpath="//tbody/tr[1]/td[10]/a[2]")
	private WebElement btnExcluir;
	
	@FindBy(xpath="//tbody/tr[1]/td[9]/a[1]/i[1]")
	private WebElement btnAgregaSolicitud;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public Quotation(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void clicOnNewQuotation() {
		btnNuevaCotizacion.click();
	}
	
	public void clicOnVidaColectiva() {
		btnVidaColectiva.click();
	}
	
	public void clicOnAccidentesPersonales() {
		btnAccidentesPersonales.click();
	}
	
	public void clicOnConfiance() {
		btnConfiance.click();
	}
	
	public void clicOnCNPVie() {
		btnCNPVie.click();
	}
	
	public void clicOnTresor() {
		btnTresor.click();
	}
	
	public void seleccionaProductoCM(String cm) {
		selProducto.click();
		selProducto.sendKeys(cm);
	}
	
	public void cargaDatosTitular(String dni, String documento, String provincia, String apellido, String nombre, String genero) {
		radPersonaFisica.click();
		selTipoDocumento.click();
		selTipoDocumento.sendKeys(dni);
		txtDocumento.sendKeys(documento);
		selProvincia.click();
		selProvincia.sendKeys(provincia);
		txtApellido.sendKeys(apellido);
		txtNombre.sendKeys(nombre);
		if(genero.equals("F")) {
			radFemenino.click();
		}
		else {
			radMasculino.click();
		}
	}
	
	public void cargaAsegurados(String dni, String documento, String fecha, String apellido, String nombre, String genero) {
		selTipoDocumentoAsegurado.click();
		selTipoDocumentoAsegurado.sendKeys(dni);
		txtDocumentoAsegurado.sendKeys(documento);
		txtFechaNacimiento.sendKeys(fecha);
		txtApellidoAsegurado.sendKeys(apellido);
		txtNombreAsegurado.sendKeys(nombre);
		if(genero.equals("F")) {
			radioFemenino.click();
		}
		else {
			radioMasculino.click();
		}
	}
	
	public void cargaAseguradosLCT(String dni, String documento, String fecha, String apellido, String nombre, String genero, String sueldo, String fechaContrato) {
		selTipoDocumentoAsegurado.click();
		selTipoDocumentoAsegurado.sendKeys(dni);
		txtDocumentoAsegurado.sendKeys(documento);
		txtFechaNacimiento.sendKeys(fecha);
		txtApellidoAsegurado.sendKeys(apellido);
		txtNombreAsegurado.sendKeys(nombre);
		txtSalario.sendKeys(sueldo);
		txtFechaContrato.sendKeys(fechaContrato);
		if(genero.equals("F")) {
			radioFemenino.click();
		}
		else {
			radioMasculino.click();
		}
	}
	
	public void clicOnIncluirAsegurado() {
		btnIncluir.click();
	}
	
	public void clicOnIncluirAseguradoLCT() {
		btnIncluirLCT.click();
	}
	
	public void cotizar() {
		btnCotizar.click();
	}
	
	public void guardar(String mail) {
		Utilities.waiter(3);
		btnGuardar.click();
		Utilities.waiter(3);
		btnConfirmar.click();
		Utilities.waiter(3);
		txtMail.sendKeys(mail);
		Utilities.waiter(3);
		btnEnviar.click();
	}
	
	//métodos de consultar cotizacion
	
	public void insertaDatosConsulta(String fechaDesde, String fechaHasta, String ramo, String nombreAsegurado, String apellidoAsegurado, String productor, String codigo) {
		selRamo.click();
		selRamo.sendKeys(ramo);
		Utilities.waiter(3);
		btnBusquedaProductor.click();
		Utilities.waiter(3);
		txtNombreProductor.sendKeys(productor);
		txtCodigo.sendKeys(codigo);
		btnBuscarProductor.click();
		Utilities.waiter(3);
		btnSeleccionarProductor.click();
		
		txtFechaDesde.sendKeys(fechaDesde);
		txtFechaHasta.sendKeys(fechaHasta);
		
		inputNombre.sendKeys(nombreAsegurado);
		inputApellido.sendKeys(apellidoAsegurado);
		btnBuscar.click();
	}
	
	public void clicOnBuscar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnBuscar)).click();
	}
	
	public void clicOnExcluir() {
		wait.until(ExpectedConditions.elementToBeClickable(btnExcluir)).click();
	}
	
	public void clicOnConfirmar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmar)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar)).click();
	}
	
	public void clicOnAgregarSolicitud() {
		btnAgregaSolicitud.click();
	}

	
	//método descargar cotización
	
	public void clicOnDescargar() {
		btnDescargar.click();
	}
	
	public void clicOnDetalles() {
		btnVerDetalles.click();
	}
	
	public boolean pantallaCotizacionesDisponible() {
		return pantallaCotizaciones.isDisplayed();
	}
	
	public boolean pantallaNuevaCotizacionesDisponible() {
		return pantallaNuevaCotizacion.isDisplayed();
	}
	
	public boolean pantallaFinalizacionDisponible() {
		return pantallaFinalizacion.isDisplayed();
	}
}
