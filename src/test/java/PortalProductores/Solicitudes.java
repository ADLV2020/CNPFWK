package PortalProductores;

import java.time.Duration;

import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilidades.Utilities;

public class Solicitudes {
	
	@FindBy(xpath="//input[@id='NroSolicitud']")
	private WebElement txtNroSolicitud;
	
	@FindBy(xpath="//input[@id='dateInitial']")
	private WebElement txtFechaInicio;
	
	@FindBy(xpath="//input[@id='dateFinal']")
	private WebElement txtFechaFinal;
	
	@FindBy(xpath="//input[@id='NomeCliente']")
	private WebElement txtNombreTomador;
	
	@FindBy(xpath="//input[@id='DocumentoCliente']")
	private WebElement txtDocumentoTomador;
	
	@FindBy(xpath="//select[@id='Estado']")
	private WebElement selEstado;
	
	@FindBy(xpath="//input[@id='NomeAssegurado']")
	private WebElement txtNombreAsegurado;
	
	@FindBy(xpath="//input[@id='DocumentoAssegurado']")
	private WebElement txtDocumentoAsegurado;
	
	@FindBy(xpath="//select[@id='Ramo']")
	private WebElement selRamo;
	
	@FindBy(xpath="//tbody/tr[1]/td[8]/a[2]/i[1]")
	private WebElement solicitud;
	
	@FindBy(xpath="//tbody/tr[1]/td[8]/a[1]/i[1]")
	private WebElement btnDescargar;
	
	@FindBy(xpath="//a[contains(text(), 'Buscar')]")
	private WebElement btnBuscar;
	
	@FindBy(xpath="//span[@id='section-header']")
	private WebElement header;
	
	@FindBy(xpath="//input[@name='Name']")
	private WebElement txtRazonSocial;
	
	@FindBy(xpath="//input[@name='DocumentNumber']")
	private WebElement txtCUIL;
	
	@FindBy(xpath="//a[@title='Incluir']")
	private WebElement btnIncluir;
	
	@FindBy(xpath="//a[@title='Impresión']")
	private WebElement btnImpresion;
	
	@FindBy(xpath="//a[@title='Ver/ Editar Solicitud']")
	private WebElement btnVerEditar;
	
	@FindBy(xpath="//a[@title='Enviar Solicitud']")
	private WebElement btnEnviarSolicitud;
	
	@FindBy(xpath="//button[contains(text(),'S')]")
	private WebElement btnSi;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[5]/div[1]/div[1]/div[2]/button[1]")
	private WebElement btnConfirmar;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[6]/div[1]/div[1]/div[2]/button[1]")
	private WebElement btnCerrar;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public Solicitudes(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void clicOnEnviar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEnviarSolicitud)).click();
	}
	
	public void cargaDatosConsulta(String nroSolicitud, String fechaDesde, String fechaHasta, String nombreTomador, String documentoTomador, String estado, String nombreAsegurado, String documentoAsegurado, String  ramo, String productor) {
		txtNroSolicitud.sendKeys(nroSolicitud);
		txtNombreTomador.sendKeys(nombreTomador);
		txtDocumentoTomador.sendKeys(documentoTomador);
		selEstado.sendKeys(estado);
		txtNombreAsegurado.sendKeys(nombreAsegurado);
		txtDocumentoAsegurado.sendKeys(documentoAsegurado);
		selRamo.sendKeys(ramo);
		txtFechaInicio.clear();
		txtFechaInicio.sendKeys(fechaDesde);
		txtFechaFinal.clear();
		txtFechaFinal.sendKeys(fechaHasta);
		Utilities.waiter(5);
		wait.until(ExpectedConditions.elementToBeClickable(header)).click();
	}
	
	public void clicOnBuscar() {
		btnBuscar.click();
	}
	
	public void clicOnSolicitud() {
		solicitud.click();
	}
	
	public void clicOnEditar() {
		btnVerEditar.click();
	}
	
	public void clicOnCertificado() {
		btnDescargar.click();
	}
	
	public void impresionCertificado(String razonSocial, String cuil) {
		txtRazonSocial.sendKeys(razonSocial);
		txtCUIL.sendKeys(cuil);
	}
	
	public void clicOnIncluir() {
		btnIncluir.click();
	}
	
	public void clicOnDescargar() {
		btnImpresion.click();
	}
	
	public void clicOnSiConfirmar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmar)).click();
	}
	
	public void clicOnCerrar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar)).click();
	}

}
