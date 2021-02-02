package NovaSiniestros;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModificarItemSolicitudPagos {
	
	@FindBy(xpath="//div[contains(text(), 'TOTAL')]")
	private WebElement radTotal;
	
	@FindBy(xpath="//mat-select[@formcontrolname='CodClasePago']")
	private WebElement selClasePago;

	@FindBy(xpath="//select-concepto-de-pago[@formcontrolname='CodCpto']")
	private WebElement selCptoPago;
	
	@FindBy(xpath="//input[@formcontrolname='ImpPago']")
	private WebElement txtImportePago;
	
	@FindBy(xpath="//span[contains(text(), 'ACEPTAR')]")
	private WebElement btnAceptar;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public ModificarItemSolicitudPagos(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	}
	
	public void clicOnTipoPago(String tipoPago) {
		driver.findElement(By.xpath("//div[contains(text(), '" + tipoPago + "')]")).click();
	}
	
	public void modificarItem(String clasePago, String conceptoPago, String impAPagar) {
		selClasePago.click();
		driver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(), '" + clasePago + "')]")).click();
		selCptoPago.click();
		driver.findElement(By.xpath("//span[contains(text(), '" + conceptoPago + "')]")).click();
		txtImportePago.clear();
		txtImportePago.sendKeys(impAPagar);
	}
	
	public void clicOnAceptar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAceptar)).click();
	}

}
