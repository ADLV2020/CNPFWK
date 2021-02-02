package NovaSiniestros;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Login {
	
	@FindBy(xpath="//a[contains(text(),'AZURE AD')]")
	private WebElement btnLoginAzure;
	
	@FindBy(xpath="//input[@id='i0116']")
	private WebElement txtMail;
	
	@FindBy(xpath="//input[@id='i0118']")
	private WebElement txtContrasenia;
	
	@FindBy(xpath="//input[@id='idSIButton9']")
	private WebElement btnConfirmar;
	
	@FindBy(xpath="//input[@id='idBtn_Back']")
	private WebElement btnNoMantenerSesion;
	
	
	public Login(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
	}
	
	public void ingresaAzure() {
		btnLoginAzure.click();
	}
	
	public void ingresarMail(String usuario) {
		txtMail.sendKeys(usuario);
	}
	
	public void clicOnLogin() {
		btnConfirmar.click();
	}
	
	public void ingresaPassword(String pass) {
		txtContrasenia.sendKeys(pass);
		btnConfirmar.click();
	}
	
	public void clicOnNoMantenerSesion() {
		btnNoMantenerSesion.click();
	}
}
