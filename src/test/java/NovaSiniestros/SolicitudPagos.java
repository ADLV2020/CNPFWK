package NovaSiniestros;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SolicitudPagos {
	
	@FindBy(xpath="//mat-card-title[contains(text(),'Solicitudes de Pago')]")
	private WebElement tituloPantalla;
	
	@FindBy(xpath="//span[contains(text(),' NUEVO REGISTRO ')]")
	private WebElement nuevaSolicitudPago;
	
	public SolicitudPagos(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
	}
	
	public boolean pantallaDisponible() {
		return tituloPantalla.isDisplayed();
	}
	
	public void clicOnNuevaSolicitudPago() {
		nuevaSolicitudPago.click();
	}

}
