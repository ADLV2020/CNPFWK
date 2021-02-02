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

public class PolizasModificacionNombreTomador {
	
	@FindBy(xpath="//input[@id='Apelido']")
	private WebElement txtApellido;
	
	@FindBy(xpath="//input[@id='Nome']")
	private WebElement txtNombre;
	
	@FindBy(xpath="//select[@id='TipoDocumentoCodigo']")
	private WebElement selTipoDNI;
	
	@FindBy(xpath="//input[@id='NumeroDocumento']")
	private WebElement txtDNI;
	
	@FindBy(xpath="//textarea[@id='observacao']")
	private WebElement txtObservaciones;
	
	@FindBy(xpath="//input[@name='NoAsignada']")
	private WebElement radNoAsignada;
	
	@FindBy(xpath="//input[@name='Normal']")
	private WebElement radNormal;
	
	@FindBy(xpath="//input[@name='Alta']")
	private WebElement radAlta;
	
	@FindBy(xpath="//button[contains(text(),'Guardar')]")
	private WebElement btnGuardar;
	
	@FindBy(xpath="//button[contains(text(), 'Sí')]")
	private WebElement btnConfirmar;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[3]/div[1]/div[1]/div[2]/button[1]")
	private WebElement btnCerrar;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public PolizasModificacionNombreTomador(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.driver = driver;
	}
	
	public void cargaDatosNombreTomador(String apellido, String nombre, String tipoDNI, String dni, String observacion, String prioridad) {
		txtApellido.sendKeys(apellido);
		txtNombre.sendKeys(nombre);
		wait.until(ExpectedConditions.elementToBeClickable(selTipoDNI)).click();
		Utilities.waiter(3);
		selTipoDNI.sendKeys(tipoDNI);
		txtDNI.sendKeys(dni);
		txtObservaciones.sendKeys(observacion);
		Utilities.waiter(2);
		if (prioridad.contains("Normal")) {
			wait.until(ExpectedConditions.elementToBeClickable(radNormal)).click();
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(radAlta)).click();
		}
	}
	
	public void clicOnGuardarTomador() {
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardar)).click();
	}
	
	public void clicOnConfirmarTomador() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmar)).click();
	}
	
	public void clicOnCerrarTomador() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar)).click();
	}

}
