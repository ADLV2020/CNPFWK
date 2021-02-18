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

public class Menu {
	@FindBy(xpath="//span[contains(text(),'Cartera')]")
	WebElement lblCartera;

	@FindBy(xpath="//a[contains(text(),'Pólizas')]")
	WebElement lblPolizas;
	
	@FindBy(xpath="//span[contains(text(),'Nuevo Producto')]")
	WebElement lblNuevoProducto;
	
	@FindBy(xpath="//a[contains(text(),'Cotizaciones')]")
	WebElement lblCotizaciones;
	
	@FindBy(xpath="//span[contains(text(),'Comisiones')]")
	private WebElement lblComisiones;
	
	@FindBy(xpath="//a[contains(text(),'Cierres')]")
	private WebElement btnCierres;
	
	@FindBy(xpath="//body/div[2]/aside[1]/section[1]/ul[1]/li[4]/ul[1]/li[2]/a[1]")
	private WebElement lblSolicitudes;
	
	@FindBy(xpath="//label[contains(text(), 'NAVEGACIÓN PRINCIPAL')]")
	private WebElement lblPantallaPrincipal;
	
	WebDriverWait wait;
	
	public Menu(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void clicOnPolizas() {
		wait.until(ExpectedConditions.elementToBeClickable(lblCartera));
		lblCartera.click();
		lblPolizas.click();
	}
	
	public void clicOnCotizaciones() {
		wait.until(ExpectedConditions.elementToBeClickable(lblNuevoProducto)).click();
		lblCotizaciones.click();
	}
	
	public void clicOnSolicitudes() {
		wait.until(ExpectedConditions.elementToBeClickable(lblNuevoProducto)).click();
		Utilities.waiter(5);
		wait.until(ExpectedConditions.elementToBeClickable(lblSolicitudes)).click();
	}
	
	public void clicOnCierres() {
		wait.until(ExpectedConditions.elementToBeClickable(lblComisiones)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnCierres)).click();
	}
	
	public boolean pantallaPrincipalDisponible() {
		return lblPantallaPrincipal.isDisplayed();
	}
}
