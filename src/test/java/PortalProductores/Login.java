package PortalProductores;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Login {
	@FindBy(xpath="//input[@id='UsuarioNome']")
	WebElement txtUsuario;
	
	@FindBy(xpath="//input[@id='Senha']")
	WebElement txtContrasenia;
	
	@FindBy(xpath="//button[contains(text(),'Entrar')]")
	WebElement btnEntrar;
	
	@FindBy(xpath="//div[@class='validation-summary-errors']/ul/li[contains(text(), 'Los datos ingresados para iniciar sesión no son válidos. Por favor, compruebe su usuario y/o contraseña y vuelva a intentarlo.')]")
	private WebElement txtErrorLogin;
	
	@FindBy(xpath="//body/div[1]/div[2]/form[1]/div[3]/b[1]/div[1]/ul[1]/li[1]")
	private WebElement errorLogin;
	
	
	public Login(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
	}
	
	public void fillCredentials(String usuario, String contrasenia) {
		txtUsuario.sendKeys(usuario);
		txtContrasenia.sendKeys(contrasenia);
	}
	
	public void clicOnButton() {
		btnEntrar.click();
	}
	
	public String errorLogin() {
		return txtErrorLogin.getText();
	}
	
	public String falloLogin() {
		return errorLogin.getText();
	}
}