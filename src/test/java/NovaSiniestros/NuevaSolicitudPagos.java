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

public class NuevaSolicitudPagos {
	
	@FindBy(xpath="//mat-card-title[contains(text(),' Nueva Solicitud de Pago ')]")
	private WebElement tituloPantalla;
	
	@FindBy(xpath="//body/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-form-payment-request[1]/div[1]/mat-accordion[1]/mat-expansion-panel[1]/div[1]/div[1]/form[1]/div[1]/div[1]/mat-form-field[1]")
	private WebElement selTipoMovimiento;
	
	@FindBy(xpath="//body/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-form-payment-request[1]/div[1]/mat-accordion[1]/mat-expansion-panel[1]/div[1]/div[1]/form[1]/div[1]/div[2]/mat-form-field[1]")
	private WebElement selPagarA;
	
	@FindBy(xpath="//select-subsidiary[@formcontrolname='CodSuc']")
	private WebElement selSucursal;
	
	//
	
	@FindBy(xpath="//input[@formcontrolname='FecEmi']")
	private WebElement txtFechaRegistro;
	
	@FindBy(xpath="//input[@formcontrolname='FecIngresoContable']")
	private WebElement txtFechaContable;
	
	@FindBy(xpath="//input[@formcontrolname='FecEstimPago']")
	private WebElement txtFecEstimPago;
	
	//
	
	@FindBy(xpath="//select-branch[@formcontrolname='CodRamo']")
	private WebElement selRamo;
	
	@FindBy(xpath="//input[@aria-label='Nombre']")
	private WebElement txtAsegurado;
	
	@FindBy(xpath="//select-type-of-document[@formcontrolname='Tdoc']")
	private WebElement selTipoDoc;
	
	@FindBy(xpath="//span[contains(text(),'DNI')]")
	private WebElement DniSeleccionado;
	
	@FindBy(xpath="//input[@formcontrolname='NroCuit']")
	private WebElement txtNroCuit;
	
	@FindBy(xpath="//input[@formcontrolname='TxtChequeANom']")
	private WebElement txtChequeANom;
	
	@FindBy(xpath="//select-currency[@formcontrolname='CodMoneda']")
	private WebElement selTipoMoneda;
	
	@FindBy(xpath="//span[contains(text(),' Dolares EE.UU ')]")
	private WebElement dolaresSeleccionados;
	
	@FindBy(xpath="//input[@formcontrolname='ImpCambio']")
	private WebElement txtImpCambio;
	
	@FindBy(xpath="//input[@formcontrolname='ImpTotalPagar']")
	private WebElement txtImpTotalPagar;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-form-payment-request[1]/div[1]/mat-accordion[1]/mat-expansion-panel[1]/div[1]/div[1]/form[1]/div[7]/div[1]/mat-form-field[1]/div[1]/div[1]/div[3]/input[1]")
	private WebElement txtDescripcion;
	
	@FindBy(xpath="//input[@formcontrolname='NroCbu']")
	private WebElement txtNroCbu;
	
	//
	
	@FindBy(xpath="//span[contains(text(),'AGREGAR SINIESTROS')]")
	private WebElement btnAgregarSiniestro;
	
	@FindBy(xpath="//mat-icon[contains(text(), 'edit')]")
	private WebElement btnEditar;
	
	@FindBy(xpath="//span[contains(text(), ' GUARDAR ')]")
	private WebElement btnGuardarFinalizar;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-form-payment-request[1]/div[1]/mat-accordion[1]/mat-expansion-panel[3]/div[1]/div[1]/div[2]/div[1]/button[2]/span[1]")
	private WebElement btnGuardar;
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public NuevaSolicitudPagos(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean pantallaDisponible() {
		return tituloPantalla.isDisplayed();
	}
	
	public void cargarCamposCabecera(String pagarA, String sucursal) {
		//selTipoMovimiento.click();
		//tipoMovimientoSeleccionado.click();		
		wait.until(ExpectedConditions.elementToBeClickable(selPagarA)).click();
		driver.findElement(By.xpath("//span[contains(text(),'" + pagarA + "')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(selSucursal)).click();
		driver.findElement(By.xpath("//span[contains(text(), ' " + sucursal + " ')]")).click();
	}
	
	public void cargarCamposFechas(String fechaRegistro, String fechaContable, String fechaEstimPago) {
		txtFechaRegistro.clear();
		txtFechaContable.clear();
		txtFecEstimPago.clear();
		txtFechaRegistro.sendKeys(fechaRegistro);
		txtFechaContable.sendKeys(fechaContable);
		txtFecEstimPago.sendKeys(fechaEstimPago);
	}
	
	public void cargarCamposPersonales(String ramo, String nombreAsegurado, String tipoDocumento, String nroDocumento, String moneda, String nombreCobrador, String impCambio, String impTotalPagar, String desc, String cbu) {
		wait.until(ExpectedConditions.elementToBeClickable(selRamo)).click();
		driver.findElement(By.xpath("//span[contains(text(), ' " + ramo + " ')]")).click();
		txtAsegurado.clear();
		txtAsegurado.sendKeys(nombreAsegurado);
		wait.until(ExpectedConditions.elementToBeClickable(selTipoDoc)).click();
		wait.until(ExpectedConditions.elementToBeClickable(DniSeleccionado)).click();
		txtNroCuit.sendKeys(nroDocumento);
		wait.until(ExpectedConditions.elementToBeClickable(selTipoMoneda)).click();
		wait.until(ExpectedConditions.elementToBeClickable(dolaresSeleccionados)).click();
		txtChequeANom.clear();
		txtChequeANom.sendKeys(nombreCobrador);
		wait.until(ExpectedConditions.elementToBeClickable(txtImpCambio)).clear();
		txtImpCambio.sendKeys(impCambio);
		txtImpTotalPagar.clear();
		txtImpTotalPagar.sendKeys(impTotalPagar);
		txtDescripcion.clear();
		txtDescripcion.sendKeys(desc);
		txtNroCbu.sendKeys(cbu);
	}
	
	public void clicOnAgregarSiniestro() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAgregarSiniestro)).click();
	}
	
	public void clicOnEditar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEditar)).click();
	}
	
	public void clicOnGuardar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardar)).click();
	}

}
