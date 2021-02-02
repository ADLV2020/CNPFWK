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

public class PolizasModificacionPrima {
	
	@FindBy(xpath="//input[@id='Prima']")
	private WebElement txtNuevaPrima;
	
	@FindBy(xpath="//textarea[@id='observacao']")
	private WebElement txtObservaciones;
	
	@FindBy(xpath="//input[@name='NoAsignada']")
	private WebElement radNoAsignada;
	
	@FindBy(xpath="//input[@name='Normal']")
	private WebElement radNormal;
	
	@FindBy(xpath="//input[@name='Alta']")
	private WebElement radAlta;
	
	@FindBy(xpath="//a[contains(text(),'Incremento/Disminución de Prima (Universal)')]")
	private WebElement pantallaModificacion;
	
	@FindBy(xpath="//button[contains(text(),'Guardar')]")
	private WebElement btnGuardar;
	
	@FindBy(xpath="//button[contains(text(), 'Sí')]")
	private WebElement btnConfirmar;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[3]/div[1]/div[1]/div[3]/button[1]")
	private WebElement btnCerrar;
	
	WebDriverWait wait;
	
	public PolizasModificacionPrima(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public void cargaDatosPrima(String prima, String observacion, String prioridad) {
		txtNuevaPrima.clear();
		txtNuevaPrima.sendKeys(prima);
		txtObservaciones.sendKeys(observacion);
		if (prioridad.contains("Normal")) {
			radNormal.click();
		} else {
			radAlta.click();
		}
		
	}
	
	public boolean pantallaModificacionDisponible() {
		return pantallaModificacion.isDisplayed();
	}
	
	public void clicOnGuardarCambios() {
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardar)).click();
	}
	
	public void clicOnConfirmar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmar)).click();
	}
	
	public void clicOnCerrar() {
		Utilities.waiter(4);
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar)).click();
	}
}
