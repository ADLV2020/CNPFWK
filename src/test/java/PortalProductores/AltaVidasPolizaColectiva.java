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

public class AltaVidasPolizaColectiva {
	
WebDriverWait wait;

	@FindBy(xpath="//select[@id='tipoDocumento']")
	private WebElement selTipoDNI;
	
	@FindBy(xpath="//input[@id='numeroDocumento']")
	private WebElement txtNumDni;
	
	@FindBy(xpath="//input[@id='nome']")
	private WebElement txtNombre;
	
	@FindBy(xpath="//input[@id='sobrenome']")
	private WebElement txtApellido;
	
	@FindBy(xpath="//select[@id='Sexo']")
	private WebElement selSexo;
	
	@FindBy(xpath="//input[@id='dataNascimento']")
	private WebElement txtFechaNacimiento;
	
	@FindBy(xpath="//select[@id='Categoria']")
	private WebElement selCategoria;
	
	@FindBy(xpath="//input[@id='salarioBruto']")
	private WebElement txtSalarioBruto;
	
	@FindBy(xpath="//input[@id='dataEntradaEmprego']")
	private WebElement txtFechaIngreso;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[8]/form[1]/div[1]/div[1]/div[2]/div[7]/div[1]/a[1]")
	private WebElement btnAgregarVida;
	
	@FindBy(xpath="//button[contains(text(),'Procesar Novedades')]")
	private WebElement btnProcesar;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[8]/form[1]/div[1]/div[1]/div[2]/div[7]")
	private WebElement unfocus;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[18]/div[1]/div[1]/div[2]/button[1]")
	private WebElement btnCerrar;
	
	public AltaVidasPolizaColectiva(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public void cargarAltaVida(String tipoDNI, String dni, String nombre, String apellido, String sexo, String fechaNacimiento, String categoria, String salarioBruto, String fechaIngreso) {
		//selTipoDNI.click();
		selTipoDNI.sendKeys(tipoDNI);
		txtNumDni.sendKeys(dni);
		txtNombre.sendKeys(nombre);
		txtApellido.sendKeys(apellido);
		selSexo.click();
		selSexo.sendKeys(sexo);
		txtFechaNacimiento.sendKeys(fechaNacimiento);
		selCategoria.click();
		selCategoria.sendKeys(categoria);
		txtSalarioBruto.clear();
		txtSalarioBruto.sendKeys(salarioBruto);
		txtFechaIngreso.sendKeys(fechaIngreso);
	}
	
	public void clicOnAgregar() {
		unfocus.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAgregarVida)).click();
	}
	
	public void clicOnProcesar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnProcesar)).click();
	}
	
	public void clicOnCerrar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar)).click();
	}

}
