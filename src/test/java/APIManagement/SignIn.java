package APIManagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SignIn {
	@FindBy(xpath="//input[@id='email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement txtContrasenia;
	
	@FindBy(xpath="//button[@id='signin']")
	WebElement btnSignIn;
	
	public SignIn(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
	}
	
	public void fillSignIngForm(String email, String contrasenia) {
		txtEmail.sendKeys(email);
		txtContrasenia.sendKeys(contrasenia);
	} 
	
	public void clicOnSignIn() {
		btnSignIn.click();
	}
}
