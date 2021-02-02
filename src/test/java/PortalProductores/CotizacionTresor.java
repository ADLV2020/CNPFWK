package PortalProductores;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CotizacionTresor {
	
	@FindBy(xpath="//input[@name='TP_Moeda' and @value='1']")
	private WebElement radMoneda;
	
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
	
	@FindBy(xpath="//select[@id='TP_Estado_Civil']")
	private WebElement selEstadoCivil;
	
	@FindBy(xpath="//input[@id='DS_Telefone']")
	private WebElement txtTelefono;
	
	@FindBy(xpath="//input[@name='DS_Email']")
	private WebElement txtEmail;
	
	//
	@FindBy(xpath="//input[@name='TP_Calculo' and @value='1']")
	private WebElement radTipoCalculo;
	
	@FindBy(xpath="//input[@id='NR_Valor_Prima_Capital']")
	private WebElement txtPrimaPactada;
	
	@FindBy(xpath="//input[@name='NR_Prazo_Contratacao']")
	private WebElement txtPlazo;
	
	@FindBy(xpath="//select[@name='TP_Periodicidade_Pagamento']")
	private WebElement selPeriodoPago;
	
	@FindBy(xpath="//input[@name='ST_Ajustavel' and @value='1']")
	private WebElement radAjusteAutomatico;
	
	//
	@FindBy(xpath="//input[@name='NR_Percentual_Fondo_Com_Garantia']")
	private WebElement txtFondoConGarantia;
	
	@FindBy(xpath="//input[@name='NR_Percentual_Fondo_Sem_Garantia']")
	private WebElement txtFondoSinGarantia;
	
	//aporte adicional
	
	@FindBy(xpath="//input[@name='NR_Valor_Aporte_Adicional']")
	private WebElement txtMontoAporte;
	
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
	
	public CotizacionTresor(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
	}
	
	public void seleccionaMoneda() {
		radMoneda.click();
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
	
	public void cargaImportesYPlazos(String primaPactada, String plazo, String periodoPago, String opcionBeneficio) {
		radTipoCalculo.click();
		txtPrimaPactada.sendKeys(primaPactada);
		txtPlazo.sendKeys(plazo);
		selPeriodoPago.sendKeys(periodoPago);
		driver.findElement(By.xpath("//input[@value='"+ opcionBeneficio +"']")).click();
		radAjusteAutomatico.click();
	}
	
	public void cargarEstrategiaInversion(String fondoConGarantia, String fondoSinGarantia, String aporteAdicional) {
		txtFondoConGarantia.clear();
		txtFondoConGarantia.sendKeys(fondoConGarantia);
		txtFondoSinGarantia.clear();
		txtFondoSinGarantia.sendKeys(fondoSinGarantia);
		txtMontoAporte.sendKeys(aporteAdicional);
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
