package PortalProductores;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilidades.Utilities;

public class ModificarSolicitudAP {
	
	@FindBy(xpath="//input[@name='Nacionalidade']")
	private WebElement txtNacionalidad;
	
	@FindBy(xpath="//select[@name='EstadoCivil']")
	private WebElement selEstadoCivil;
	
	@FindBy(xpath="//input[@id='DataNasc']")
	private WebElement txtFechaNacimiento;
	
	@FindBy(xpath="//input[@name='Endereco']")
	private WebElement txtCalle;
	
	@FindBy(xpath="//input[@name='EnderecoNumero']")
	private WebElement txtNumCalle;
	
	@FindBy(xpath="//input[@name='EnderecoPiso']")
	private WebElement txtPiso;
	
	@FindBy(xpath="//input[@name='EnderecoDepartamento']")
	private WebElement txtDpto;
	
	@FindBy(xpath="//input[@name='EnderecoLocalizacao']")
	private WebElement txtLocalidad;
	
	@FindBy(xpath="//input[@name='EnderecoCodigoPostal']")
	private WebElement txtCodPostal;
	
	@FindBy(xpath="//select[@name='Atividade']")
	private WebElement selActividad;
	
	@FindBy(xpath="//input[@name='Email']")
	private WebElement txtMail;
	
	@FindBy(xpath="//input[@name='RepetirEmail']")
	private WebElement txtRepetirMail;
	
	@FindBy(xpath="//input[@name='TelefonoEmpresa']")
	private WebElement txtTelEmpresa;
	
	@FindBy(xpath="//input[@name='Celular']")
	private WebElement txtCelular;
	
	@FindBy(xpath="//input[@name='TomadorPoliticamenteExposta' and @value='S']")
	private WebElement radSi;
	
	@FindBy(xpath="//input[@name='TomadorPoliticamenteExposta' and @value='N']")
	private WebElement radNo;
	
	@FindBy(xpath="//input[@name='TomadorSujeitoObrigatorioLei25246' and @value='S']")
	private WebElement radLeySi;
	
	@FindBy(xpath="//input[@name='TomadorSujeitoObrigatorioLei25246' and @value='N']")
	private WebElement radLeyNo;
	
	@FindBy(xpath="//select[@id='MeioPagamento']")
	private WebElement selMedioPago;
	
	@FindBy(xpath="//input[@name='NumeroCartao']")
	private WebElement txtNumTarjeta;
	
	@FindBy(xpath="//button[contains(text(),'Guardar')]")
	private WebElement btnGuardar;
	
	@FindBy(xpath="//button[contains(text(),'Sí')]")
	private WebElement btnSi;
	
	@FindBy(xpath="//button[contains(text(),'Cerrar')]")
	private WebElement btnCerrar;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public ModificarSolicitudAP(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void cargarDatosPersonales(String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley) {
		Utilities.waiter(4);
		txtNacionalidad.clear();
		txtNacionalidad.sendKeys(nacionalidad);
		selEstadoCivil.sendKeys(estadoCivil);
		txtFechaNacimiento.clear();
		txtFechaNacimiento.sendKeys(fechaNacimiento);
		txtCalle.clear();
		txtCalle.sendKeys(calle);
		txtNumCalle.clear();
		txtNumCalle.sendKeys(numCalle);
		txtPiso.clear();
		txtPiso.sendKeys(piso);
		txtDpto.clear();
		txtDpto.sendKeys(dpto);
		txtLocalidad.clear();
		txtLocalidad.sendKeys(localidad);
		txtCodPostal.clear();
		txtCodPostal.sendKeys(codPostal);
		selActividad.sendKeys(actividad);
		txtMail.clear();
		txtMail.sendKeys(mail);
		txtRepetirMail.clear();
		txtRepetirMail.sendKeys(mail);
		txtTelEmpresa.clear();
		txtTelEmpresa.sendKeys(telEmpresa);
		txtCelular.clear();
		txtCelular.sendKeys(celular);
		if(politica.startsWith("S")) {
			radSi.click();
		} else {
			radNo.click();
		}
		
		if(ley.startsWith("S")) {
			radLeySi.click();
		} else {
			radLeyNo.click();
		}
		
	}
	
	public void cargaMedioPago(String tarjeta, String numTarjeta) {
		selMedioPago.sendKeys(tarjeta);
		txtNumTarjeta.clear();
		txtNumTarjeta.sendKeys(numTarjeta);
	}
	
	public void clicOnGuardar() {
		//btnGuardar.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardar)).click();
	}
	
	public void clicOnSiConfirmar() {
		//btnSi.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSi)).click();
	}

	public void clicOnCerrar() {
		//btnCerrar.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar)).click();
	}
}
