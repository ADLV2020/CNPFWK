package NovaSiniestros;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Busqueda {
	
	@FindBy(xpath="//input[@formcontrolname='claimNumber']")
	private WebElement txtNroSiniestro;
	
	@FindBy(xpath="//input[@formcontrolname='policyNumber']")
	private WebElement txtNroPoliza;
	
	@FindBy(xpath="//input[@formcontrolname='dniNumber']")
	private WebElement txtNtoDNI;
	
	@FindBy(xpath="//select-branch[@formcontrolname='branch']")
	private WebElement selRamo;
	
	@FindBy(xpath="//input[@formcontrolname='dateFrom']")
	private WebElement txtFechaDesde;
	
	@FindBy(xpath="//input[@formcontrolname='dateTo']")
	private WebElement txtFechaHasta;
	
	@FindBy(xpath="//span[contains(text(), 'BUSCAR')]")
	private WebElement btnBuscar;
	
	@FindBy(xpath="//body/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-search-claim[1]/div[1]/mat-card[1]/mat-card-content[1]/div[2]/mat-table[1]/mat-row[1]/mat-cell[5]/div[1]/a[1]/button[1]/span[1]/mat-icon[1]")
	private WebElement btnLupa;
	
	@FindBy(xpath="//mat-card-title[contains(text(),'Busqueda de Siniestros')]")
	private WebElement pantallaBusqueda;
	
	@FindBy(xpath="//mat-card-title[contains(text(), 'Ver Siniestro')]")
	private WebElement pantallaSiniestro;

	private WebDriver driver;
	
	public Busqueda(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
	}
	
	
	public void ingresarDatosBusqueda(String siniestro, String poliza, String dni, String fechaDesde, String fechaHasta, String ramo) {
		txtNroSiniestro.sendKeys(siniestro);
		txtNroPoliza.sendKeys(poliza);
		txtNtoDNI.sendKeys(dni);
		if(!(ramo.isEmpty())) {
			selRamo.click();
			driver.findElement(By.xpath("//span[contains(text(),' " + ramo + " ')]")).click();
			}
		txtFechaDesde.sendKeys(fechaDesde);
		txtFechaHasta.sendKeys(fechaHasta);
	}
	
	public void clicOnBuscar() {
		btnBuscar.click();
	}
	
	public void clicOnDetalles() {
		btnLupa.click();
	}
	
	public boolean pantallaBusquedaDisponible() {
		return pantallaBusqueda.isDisplayed();
	}
	
	public boolean pantallaSiniestroDisponible() {
		return pantallaSiniestro.isDisplayed();
	}

}
