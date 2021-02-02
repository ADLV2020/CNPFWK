package APIManagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Home {
	@FindBy(xpath="//a[contains(text(),'Ingresar')]")
	WebElement botonIngresar;
	
	@FindBy(xpath="//a[contains(text(),'Productos')]")
	WebElement botonProductos;
	
	public Home(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
	}
	
	public void clicSignIn() {
		botonIngresar.click();
	} 
	
	public void goToProductos() {
		botonProductos.click();
	}
}
