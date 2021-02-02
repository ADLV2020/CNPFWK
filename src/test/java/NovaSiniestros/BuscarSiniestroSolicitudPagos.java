package NovaSiniestros;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuscarSiniestroSolicitudPagos {
	
	@FindBy(xpath="//h1[contains(text(), 'Seleccionar Siniestros')]")
	private WebElement pantallaDisponible;
	
	@FindBy(xpath="//input[@formcontrolname='numberFrom']")
	private WebElement txtSiniestroDesde;
	
	@FindBy(xpath="//input[@formcontrolname='numberTo']")
	private WebElement txtSiniestroHasta;
	
	@FindBy(xpath="//span[contains(text(), 'BUSCAR')]")
	private WebElement btnBuscar;
	
	@FindBy(xpath="//body/div[3]/div[2]/div[1]/mat-dialog-container[1]/app-select-claims-dialog[1]/div[3]/button[1]")
	private WebElement btnGuardar;
	
	WebDriverWait wait;
	
	public BuscarSiniestroSolicitudPagos(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean pantallaDisponible() {
		return pantallaDisponible.isDisplayed();
	}
	
	public void buscarSiniestro(String nroSiniestroBuscar) {
		txtSiniestroDesde.sendKeys(nroSiniestroBuscar);
		wait.until(ExpectedConditions.elementToBeClickable(btnBuscar)).click();
	}
	
	public void guardaSiniestro() {
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardar)).click();
	}

}