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

public class CierreComisiones {
	
	@FindBy(xpath="//input[@id='dateInitial']")
	private WebElement txtFechaInicial;
	
	@FindBy(xpath="//input[@id='dateFinal']")
	private WebElement txtFechaFinal;
	
	@FindBy(xpath="//button[contains(text(),'Buscar')]")
	private WebElement btnBuscar;
	
	@FindBy(xpath="//tbody/tr[1]/td[8]/a[1]/i[1]")
	private WebElement btnLupaConsultar;
	
	@FindBy(xpath="//tbody/tr[1]/td[9]/a[1]/i[1]")
	private WebElement btnDescargaPDF;
	
	@FindBy(xpath="//tbody/tr[1]/td[9]/a[2]/i[1]")
	private WebElement btnDescargaExcel;
	
	@FindBy(xpath="//span[@class='input-group-btn']")
	private WebElement btnLupaProductor;
	
	@FindBy(xpath="//input[@id='Name']")
	private WebElement txtNombreProductor;
	
	@FindBy(xpath="//input[@id='DocumentNumber']")
	private WebElement txtCodigoProductor;
	
	@FindBy(xpath="//a[@title='Buscar']")
	private WebElement btnBuscarProductor;
	
	@FindBy(xpath="//tbody/tr[1]/td[4]/a[1]")
	private WebElement btnSumarProductor;
	
	WebDriverWait wait;
	
	public CierreComisiones(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void cargarDatosBusqueda(String fechaInicio, String fechaFin) {
		wait.until(ExpectedConditions.elementToBeClickable(txtFechaInicial)).clear();
		txtFechaInicial.sendKeys(fechaInicio);
		wait.until(ExpectedConditions.elementToBeClickable(txtFechaFinal)).clear();
		txtFechaFinal.sendKeys(fechaFin);
	}
	
	public void clicOnBuscar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnBuscar)).click();
	}
	
	public void clicOnConsultar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLupaConsultar)).click();
	}
	
	public void clicOnLupaProductor() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLupaProductor)).click();
	}
	
	public void cargaDatosProductor(String nombreProd, String codigoProd) {
		Utilities.waiter(3);
		txtNombreProductor.sendKeys(nombreProd);
		txtCodigoProductor.sendKeys(codigoProd);
		wait.until(ExpectedConditions.elementToBeClickable(btnBuscarProductor)).click();
	}
	
	public void clicOnSumarProductor() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSumarProductor)).click();
	}
	
	public void clicOnDescargarComisiones() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDescargaPDF)).click();
		Utilities.waiter(4);
		wait.until(ExpectedConditions.elementToBeClickable(btnDescargaExcel)).click();
	}

}
