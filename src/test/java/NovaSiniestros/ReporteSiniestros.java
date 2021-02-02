package NovaSiniestros;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ReporteSiniestros {
	
	@FindBy(xpath="//mat-card-title[contains(text(),'Registro de Denuncias de Siniestros')]")
	private WebElement pantallaReporteSiniestro;
	
	@FindBy(xpath="//input[@formcontrolname='dateFrom']")
	private WebElement txtFechaDesde;
	
	@FindBy(xpath="//input[@formcontrolname='dateTo']")
	private WebElement txtFechaHasta;
	
	@FindBy(xpath="//span[contains(text(),'BUSCAR')]")
	private WebElement btnBuscar;
	
	@FindBy(xpath="//span[contains(text(),'PDF')]")
	private WebElement btnDescargarPDF;
	
	@FindBy(xpath="//span[contains(text(),'Excel')]")
	private WebElement btnDescargarExcel;
	
	
	public ReporteSiniestros(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
	}
	
	public void ingresaFechas(String desde, String hasta) {
		// clear no funciona acá
		txtFechaDesde.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		txtFechaDesde.sendKeys(desde);
		txtFechaHasta.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		txtFechaHasta.sendKeys(hasta);
	}
	
	public void clicOnBuscar() {
		btnBuscar.click();
	}
	
	public void clicOnDescargarPDF() {
		btnDescargarPDF.click();
	}
	
	public void clicOnDescargarExcel() {
		btnDescargarExcel.click();
	}
	
	public boolean pantallaReporteDisponible() {
		return pantallaReporteSiniestro.isDisplayed();
	}

}
