package NovaSiniestros;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReporteVencimientoSiniestros {
	
	@FindBy(xpath="//mat-card-title[contains(text(),'Registro de Vencimiento de Siniestros')]")
	private WebElement pantallaReporte;
	
	@FindBy(xpath="//input[@formcontrolname='dateVigencia']")
	private WebElement txtFechaVigencia;
	
	@FindBy(xpath="//input[@formcontrolname='numeroSiniestro']")
	private WebElement txtNroSiniestro;
	
	@FindBy(xpath="//select-branch[@formcontrolname='codRamo']")
	private WebElement selRamo;
	
	@FindBy(xpath="//mat-checkbox[@formcontrolname='esconderEnEspera']")
	private WebElement checkEsconderEnEspera;
	
	@FindBy(xpath="//span[contains(text(),'BUSCAR')]")
	private WebElement btnBuscar;
	
	@FindBy(xpath="//span[contains(text(),'PDF')]")
	private WebElement btnDescargarPDF;
	
	@FindBy(xpath="//span[contains(text(),'Excel')]")
	private WebElement btnDescargarExcel;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public ReporteVencimientoSiniestros(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean pantallaReporteDisponible() {
		return pantallaReporte.isDisplayed();
	}
	
	public void ingresaDatosBusqueda(String vigencia, String siniestro, String ramo, String esconderEnEspera) {
		// clear no funciona acá
		txtFechaVigencia.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		txtFechaVigencia.sendKeys(vigencia);
		txtNroSiniestro.sendKeys(siniestro);
		selRamo.click();
		driver.findElement(By.xpath("//span[contains(text(), '" + ramo + "')]")).click();
		if(esconderEnEspera == "NO") {
			checkEsconderEnEspera.click();
		}
			
	}
	
	public void clicOnBuscar() {
		btnBuscar.click();
	}
	
	public void clicOnDescargarPDF() {
		//btnDescargarPDF.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnDescargarPDF)).click();
	}
	
	public void clicOnDescargarExcel() {
		//btnDescargarExcel.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnDescargarExcel)).click();

	}

}
