package PortalProductores;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilidades.Utilities;

public class Policy {
	@FindBy(xpath="//input[@id='NrApolice']")
	WebElement txtPoliza;
	
	@FindBy(xpath="//input[@id='NomeClienteTomador']")
	WebElement txtClienteTomador;
	
	@FindBy(xpath="//input[@id='NrSolicitacao']")
	WebElement txtSolicitud;
	
	@FindBy(xpath="//input[@id='CuitClienteTomador']")
	WebElement txtDocumentoClienteTomador;
	
	@FindBy(xpath="//input[@id='NomeAssegurado']")
	WebElement txtNombreClienteTomador;
	
	@FindBy(xpath="//input[@id='CuitAssegurado']")
	WebElement txtDniAsegurado;
	
	@FindBy(xpath="//input[@id='dateInitial']")
	WebElement txtFechaEmision;
	
	@FindBy(xpath="//input[@id='dateFinal']")
	WebElement txtHasta;
	
	@FindBy(xpath="//select[@id='NrProduto']")
	WebElement selRamo;
	
	@FindBy(xpath="//label[contains(text(), 'Ramo')]")
	private WebElement clicAca;
	
	@FindBy(xpath="//a[contains(text(), 'Buscar')]")
	WebElement btnBuscar;
	
	@FindBy(xpath="//a[@class='pointer']")
	WebElement btnDetalles;
	
	@FindBy(xpath="//span[contains(text(), 'Póliza')]")
	private WebElement pantallaPolizas;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/h4[1]/a[1]")
	private WebElement pantallaDetalle;
	
	@FindBy(xpath="//th[contains(text(),'Número Póliza')]")
	private WebElement resultadoBusqueda;
	
	@FindBy(xpath="//tbody/tr[1]/td[4]")
	private WebElement polizaVigente;
	
	@FindBy(xpath="//label[contains(text(), 'No existen registros que coincidan con la búsqueda especificada. Por favor, ajuste sus criterios de búsqueda e inténtelo nuevamente.')]")
	private WebElement lblBusquedaSinResultado;
	
	@FindBy(xpath="//a[@title='Ver perfil']")
	private WebElement btnVerPerfil;
	
	@FindBy(xpath="//a[contains(text(),'Asegurado')]")
	private WebElement btnAsegurado;

	@FindBy(xpath="//a[contains(text(),'Endosos')]")
	private WebElement btnEndosos;
	
	@FindBy(xpath="//a[contains(text(),'Estado de Cuenta')]")
	private WebElement btnEstadoCuenta;
	
	@FindBy(xpath="//a[@title='Descargar']")
	private WebElement btnDescargarEndoso;
	
	@FindBy(xpath="//button[@id='DescargarDetalle']")
	private WebElement btnDescargarEstadoCuenta;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public Policy(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.driver = driver;
	}
	
	
	
	public void enterSearchCriteria(String poliza, String clienteTomador, String solicitud, String documentoClienteTomador, String nombreClienteTomador, String dniAsegurado, String fechaEmision, String hasta, String ramo) {
		txtPoliza.sendKeys(poliza);
		txtClienteTomador.sendKeys(clienteTomador);
		txtSolicitud.sendKeys(solicitud);
		txtDocumentoClienteTomador.sendKeys(documentoClienteTomador);
		txtNombreClienteTomador.sendKeys(nombreClienteTomador);
		txtDniAsegurado.sendKeys(dniAsegurado);
		txtFechaEmision.sendKeys(fechaEmision);
		txtHasta.sendKeys(hasta);
		
		if (!ramo.isEmpty()) {
			Select selector = new Select(selRamo);
			selector.selectByVisibleText(ramo);
		}
		clicAca.click();
	}
	
	public void clicOnSearch() {
		btnBuscar.click();
	}
	
	public void clicOnDetalles() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDetalles)).click();
	}
	
	public boolean pantallaPolizaDisponible() {
		return pantallaPolizas.isDisplayed();
	}
	
	public boolean pantallaDetalleDisponible() {
		return pantallaDetalle.isDisplayed();
	}
	
	public boolean resultadoBusquedaDisponible(){
		return resultadoBusqueda.isDisplayed();
	}
	
	public String esPolizaVigente() {
		return polizaVigente.getText();
	}
	
	public String busquedaSinResultado() {
		return lblBusquedaSinResultado.getText();
	}
	
	public void clicOnVerPerfil() {
		wait.until(ExpectedConditions.elementToBeClickable(btnVerPerfil)).click();
	}
	
	public void clicOnVer(String ver) {
		Utilities.waiter(6);
		driver.findElement(By.xpath("//a[contains(text(),'"+ ver +"')]")).click();
	}
	
	public void clicOnVerEndoso() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEndosos)).click();
	}
	
	public void clicOnVerEstadoCuenta() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEstadoCuenta)).click();
	}
	
	public void clicOnDescargarEndoso() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDescargarEndoso)).click();
	}
	
	public void clicOnDescargarEstadoCuenta() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDescargarEstadoCuenta)).click();
	}
	
	public void clicOnAsegurado() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAsegurado)).click();
	}
}
