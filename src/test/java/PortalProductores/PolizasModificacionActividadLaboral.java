package PortalProductores;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilidades.Utilities;

public class PolizasModificacionActividadLaboral {
	
	@FindBy(xpath="//select[@id='atividade']")
	private WebElement selActividad;
	
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
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[3]/div[1]/div[1]/div[2]/button[1]")
	private WebElement btnCerrar;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public PolizasModificacionActividadLaboral(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.driver = driver;
	}
	
	public void cargaDatosActividadLaboral(String actividad, String observacion, String prioridad) {
		selActividad.click();
		driver.findElement(By.xpath("//option[contains(text(),'"+ actividad +"')]")).click();
		txtObservaciones.sendKeys(observacion);
		Utilities.waiter(2);
		if (prioridad.contains("Normal")) {
			radNormal.click();
		} else {
			radAlta.click();
		}
	}
	
	public void clicOnGuardarActividad() {
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardar)).click();
	}
	
	public void clicOnConfirmarActividad() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmar)).click();
	}
	
	public void clicOnCerrarActividad() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar)).click();
	}

}
