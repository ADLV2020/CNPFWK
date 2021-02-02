package PortalProductores;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PolizasModificacionBeneficiarioAsegurado {
	
	@FindBy(xpath="//input[@id='Apellido']")
	private WebElement txtApellido;
	
	@FindBy(xpath="//input[@id='Nombre']")
	private WebElement txtNombre;
	
	@FindBy(xpath="//select[@id='TipoDocumento']")
	private WebElement selTipoDNI;
	
	@FindBy(xpath="//input[@id='DsDocumento']")
	private WebElement txtDNI;
	
	@FindBy(xpath="//select[@id='Vincluo']")
	private WebElement selVinculo;
	
	@FindBy(xpath="//select[@id='RelacaoBeneficiario']")
	private WebElement selRelacion;
	
	@FindBy(xpath="//input[@id='Designacao']")
	private WebElement txtDisgnacion;
	
	@FindBy(xpath="//button[@id='btnAgregar']")
	private WebElement btnAgregarBeneficiario;
	
	@FindBy(xpath="//button[contains(text(),'Guardar')]")
	private WebElement btnGuardar;
	
	@FindBy(xpath="//button[contains(text(), 'Sí')]")
	private WebElement btnConfirmar;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[3]/div[1]/div[1]/div[3]/button[1]")
	private WebElement btnCerrar;
	
	@FindBy(xpath="//tbody/tr[1]/td[8]/div[1]/a[1]/i[1]")
	private WebElement btnEliminar;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public PolizasModificacionBeneficiarioAsegurado(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.driver = driver;
	}
	
	public void cargaDatosBeneficiarioAsegurado(String apellido, String nombre, String tipoDNI, String dni, String vinculo, String relacion, String designacion) {
		txtApellido.sendKeys(apellido);
		txtNombre.sendKeys(nombre);
		selTipoDNI.click();
		selTipoDNI.sendKeys(tipoDNI);
		txtDNI.sendKeys(dni);
		selVinculo.click();
		selVinculo.sendKeys(vinculo);
		selRelacion.click();
		selRelacion.sendKeys(relacion);
		txtDisgnacion.sendKeys(designacion);
	}
	
	public void clicOnAgregaAsegurado() {
		btnAgregarBeneficiario.click();
	}
	
	
	public void clicOnGuardarAsegurado() {
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardar)).click();
	}
	
	public void clicOnConfirmarAsegurado() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmar)).click();
	}
	
	public void clicOnCerrarAsegurado() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar)).click();
	}
	
	public void eliminarAsegurado() {
		btnEliminar.click();
	}

}
