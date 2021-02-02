package PortalProductores;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CotizacionCNPVie {
	
	@FindBy(xpath="//input[@value='M']")
	private WebElement radHombre;
	
	@FindBy(xpath="//select[@id='NR_Seq_Tipo_Documento']")
	private WebElement selTipoDNI;
	
	@FindBy(xpath="//input[@name='DS_Documento']")
	private WebElement txtDocumento;
	
	@FindBy(xpath="//select[@id='NR_Seq_Provincia']")
	private WebElement selProvincia;
	
	@FindBy(xpath="//input[@name='DS_Sobrenome']")
	private WebElement txtApellido;
	
	@FindBy(xpath="//input[@name='DS_Nome_Tomador']")
	private WebElement txtNombre;
	
	@FindBy(xpath="//input[@id='DT_Nascimento']")
	private WebElement txtFechaNacimiento;
	
	@FindBy(xpath="//select[@id='NR_Seq_Estado_Civil']")
	private WebElement selEstadoCivil;
	
	@FindBy(xpath="//input[@id='DS_Telefone']")
	private WebElement txtTelefono;
	
	@FindBy(xpath="//input[@name='DS_Email']")
	private WebElement txtEmail;
	
	// importes y plazos
	@FindBy(xpath="//select[@name='TP_Moeda']")
	private WebElement selMoneda;
	
	@FindBy(xpath="//input[@name='NR_Anos_Prazo']")
	private WebElement txtPlazo;
	
	@FindBy(xpath="//select[@name='TP_Periodicidade_Pgto']")
	private WebElement selPeriodoPago;
	
	// cobertura
	@FindBy(xpath="//input[@id='NR_Valor_Fallecimiento']")
	private WebElement txtMuerte;
	
	@FindBy(xpath="//input[@name='ST_DMA']")
	private WebElement txtDMA;
	
	@FindBy(xpath="//input[@name='ST_ITP']")
	private WebElement txtITP;
	
	@FindBy(xpath="//input[@name='ST_PPA']")
	private WebElement txtPPA;
	
	@FindBy(xpath="//input[@name='ST_CP']")
	private WebElement txtCP;
	
	@FindBy(xpath="//input[@name='ST_CET']")
	private WebElement txtCET;
	
	//finalizar
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]")
	private WebElement btnCotizar;
		
	@FindBy(xpath="//input[@name='TP_Periodicidade_Pagamento']")
	private WebElement radFacturaUnica;
		
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/a[2]")
	private WebElement btnFinalizar;
		
	@FindBy(xpath="//button[contains(text(),'Sí')]")
	private WebElement btnSi;
		
	@FindBy(xpath="//input[@ng-model='inputEmail.Email']")
	private WebElement txtMail;
		
	@FindBy(xpath="//a[@ng-click='EnviarCorreo()']")
	private WebElement btnEnviar;
	
	private WebDriver driver;
	
	public CotizacionCNPVie(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
	}
	
	public void cargaDatosAsegurado(String genero, String tipodni, String dni, String provincia, String apellido, String nombre, String fechanacimiento, String estadoCivil, String telefono, String mail) {
		//driver.findElement(By.xpath("//input[@value='"+ genero +"']")).click();
		radHombre.click();
		selTipoDNI.sendKeys(tipodni);
		txtDocumento.sendKeys(dni);
		selProvincia.sendKeys(provincia);
		txtApellido.sendKeys(apellido);
		txtNombre.sendKeys(nombre);
		txtFechaNacimiento.sendKeys(fechanacimiento);
		selEstadoCivil.sendKeys(estadoCivil);
		txtTelefono.click();
		txtTelefono.sendKeys(telefono);
		txtEmail.sendKeys(mail);
	}
	
	public void cargaOpcionesProducto(String moneda, String plazo, String periodoPago) {
		selMoneda.sendKeys(moneda);
		txtPlazo.sendKeys(plazo);
		selPeriodoPago.sendKeys(periodoPago);
	}
	
	public void cargaCoberturas(String aporteAdicional) {
		txtMuerte.sendKeys(aporteAdicional);
		txtDMA.click();
		txtITP.click();
		txtPPA.click();
		txtCP.click();
		txtCET.click();
	}
	
	public void clicOnCotizar() {
		btnCotizar.click();
	}
	
	public void clicOnFinalizar() {
		btnFinalizar.click();
	}
	
	public void clicOnSi() {
		btnSi.click();
	}
	
	public void confirmarMailFinalizar(String mail) {
		txtMail.sendKeys(mail);
		btnEnviar.click();
	}

}
