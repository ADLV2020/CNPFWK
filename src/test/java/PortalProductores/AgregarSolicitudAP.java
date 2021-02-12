package PortalProductores;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AgregarSolicitudAP {
	
	@FindBy(xpath="//input[@name='TomadorSexo' and @value='F']")
	private WebElement radFemenino;
	
	@FindBy(xpath="//input[@name='TomadorSexo' and @value='M']")
	private WebElement radMasculino;
	
	@FindBy(xpath="//select[@name='TomadorTipoDocumento']")
	private WebElement selTipoDocumento;
	
	@FindBy(xpath="//input[@name='TomadorDocumentoNumero']")
	private WebElement txtNumDocumento;
	
	@FindBy(xpath="//select[@name='LugarNasc']")
	private WebElement selLugarNacimiento;
	
	@FindBy(xpath="//input[@name='Nacionalidade']")
	private WebElement txtNacionalidad;
	
	@FindBy(xpath="//select[@name='EstadoCivil']")
	private WebElement selEstadoCivil;
	
	@FindBy(xpath="//input[@id='DataNasc']")
	private WebElement txtFechaNacimiento;
	
	@FindBy(xpath="//input[@name='Endereco']")
	private WebElement txtCalle;
	
	@FindBy(xpath="//input[@name='EnderecoNumero']")
	private WebElement txtNumerocalle;
	
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
	
	@FindBy(xpath="//input[@name='TipoBeneficiarioCodigo' and @value='2']")
	private WebElement radHerederos;
	
	@FindBy(xpath="//input[@name='TipoBeneficiarioCodigo' and @value='1']")
	private WebElement radContratante;
	
	@FindBy(xpath="//input[@name='assuredNome']")
	private WebElement txtNombreAsegurado;
	
	@FindBy(xpath="//input[@name='assuredSobrenome']")
	private WebElement txtApellidoAsegurado;
	
	@FindBy(xpath="//tbody/tr[1]/td[1]/div[3]/div[2]/div[1]/div[1]/input[1]")
	private WebElement txtFechaNacimientoAsegurado;
	
	@FindBy(xpath="//input[@name='1251' and @value='M']")
	private WebElement radMasculinoAsegurado;
	
	@FindBy(xpath="//input[@name='1252' and @value='F']")
	private WebElement radFemeninoAsegurado;
	
	@FindBy(xpath="//tbody/tr[1]/td[1]/div[4]/div[2]/div[1]/select[1]")
	private WebElement selTipoDocumentoAsegurado;
	
	@FindBy(xpath="//tbody/tr[1]/td[1]/div[4]/div[4]/div[1]/div[1]/input[1]")
	private WebElement txtNumDocumentoAsegurado;
	
	@FindBy(xpath="//button[@id='InsertVidaColectivo']")
	private WebElement btnGuardarAP;
	
	@FindBy(xpath="//button[contains(text(),'Sí')]")
	private WebElement btnSi;
	
	@FindBy(xpath="//button[contains(text(),'Cerrar')]")
	private WebElement btnCerrar;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public AgregarSolicitudAP(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void cargaDatosTomador(String genero, String tipoDocumento, String numDocumento, String lugarNacimiento, String nacionalidad, String estadoCivil, String fechaNacimiento, String calle, String numeroCalle, String piso, String dpto, String localidad, String codPostal, String actividad, String mail, String telEmpresa, String celular, String politica, String ley) {
		if(genero.equals("F")) {
			radFemenino.click();
		}
		else {
			radMasculino.click();
		}
		selTipoDocumento.sendKeys(tipoDocumento);
		txtNumDocumento.sendKeys(numDocumento);
		selLugarNacimiento.sendKeys(lugarNacimiento);
		txtNacionalidad.sendKeys(nacionalidad);
		selEstadoCivil.sendKeys(estadoCivil);
		txtFechaNacimiento.sendKeys(fechaNacimiento);
		txtCalle.sendKeys(calle);
		txtNumerocalle.sendKeys(numeroCalle);
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
	
	public void cargaDesignacion(String tipoBeneficiario) {
		if(tipoBeneficiario.startsWith("Herederos")) {
			radHerederos.click();
		} else {
			radContratante.click();
		}
	}
	
	public void cargaMedioPago(String tarjeta, String numTarjeta) {
		selMedioPago.sendKeys(tarjeta);
		txtNumTarjeta.clear();
		txtNumTarjeta.sendKeys(numTarjeta);
	}
	
	public void cargaDatosAsegurado(String nombreAsegurado, String apellidoAsegurado, String fechaNacimientoAsegurado, String generoAsegurado, String tipoDocumentoAsegurado, String numDocumentoAsegurado) {
		txtNombreAsegurado.sendKeys(nombreAsegurado);
		txtApellidoAsegurado.sendKeys(apellidoAsegurado);
		txtFechaNacimientoAsegurado.sendKeys(fechaNacimientoAsegurado);
		if(generoAsegurado.startsWith("M")) {
			radMasculinoAsegurado.click();
		} else {
			radFemeninoAsegurado.click();
		}
		selTipoDocumentoAsegurado.sendKeys(tipoDocumentoAsegurado);
		txtNumDocumentoAsegurado.sendKeys(numDocumentoAsegurado);
	}
	
	public void clicOnGuardar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardarAP)).click();
	}
	
	public void clicOnSiConfirmar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSi)).click();
	}

	public void clicOnCerrar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar)).click();
	}

}
