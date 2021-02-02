package APIManagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Productos {
	@FindBy(xpath="//a[contains(text(),'Testing-Ecosistemas')]")
	WebElement productoTesting;
	
	@FindBy(xpath="//span[contains(text(),'Cotizador Assurplan - ASSET')]")
	WebElement assurplanApis;
	
	@FindBy(xpath="//span[contains(text(),'Integración Llamando al doctor')]")
	WebElement llamandoApis;
	
	public Productos(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
	}
	
	public void goToApis() {
		productoTesting.click();
	}
	
	public void goToAssurplanApis() {
		assurplanApis.click();
	}
	
	public void goToLlamandoApis() {
		llamandoApis.click();
	}
}
