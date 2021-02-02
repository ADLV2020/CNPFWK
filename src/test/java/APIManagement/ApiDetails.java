package APIManagement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Utilidades.Utilities;

public class ApiDetails {
	// Apis
	@FindBy(xpath="//li[@class='nav-item']//a[@class='nav-link text-truncate nav-link-active']")
	WebElement btnBienvenidaApi;
	
	@FindBy(xpath="//main//main//li[2]//a[1]")
	WebElement btnCotizarApi;
	
	@FindBy(xpath="//main//main//li[3]//a[1]")
	WebElement btnGenerarSolicitudApi;
	
	@FindBy(xpath="//li[4]//a[1]")
	WebElement btnObtenerPdfApi;
	
	@FindBy(xpath="//li[@class='nav-item']//a[@class='nav-link text-truncate nav-link-active']")
	WebElement btnAffiliate;
	
	// Probar una API previamente seleccionada
	@FindBy(xpath="//button[@class='open-console-button']")
	WebElement btnTryItApi;
	
	// Ejecutar la prueba de la API selecionada
	@FindBy(xpath="//button[@class='button button-primary btn-sm']")
	WebElement btnSend;
	
	// Ingresar el JSON Body para probar una API
	@FindBy(xpath="//textarea[@class='form-control form-control-sm']")
	WebElement txtBodyRequest;
	
	// Parametros API Affiliate
	@FindBy(xpath="//body/main/main/section[@class='dzqrwsreup dzqrwsreup-xl']/div[@class='huzbxuibav huzbxuibav-md huzbxuibav-sm huzbxuibav-lg huzbxuibav-xl']/div[@class='ycqdvmhchp ycqdvmhchp-md ycqdvmhchp-xl']/operation-details/div[@class='detachable-right scrollable flex-grow animation-fade-in']/operation-console[@class='test flex flex-column']/div[@class='panel panel-dark animation-fade-in']/div/div[1]/div[2]/div[1]/input[1]")
	WebElement txtDni;
	
	@FindBy(xpath="//body/main/main/section[@class='dzqrwsreup dzqrwsreup-xl']/div[@class='huzbxuibav huzbxuibav-md huzbxuibav-sm huzbxuibav-lg huzbxuibav-xl']/div[@class='ycqdvmhchp ycqdvmhchp-md ycqdvmhchp-xl']/operation-details/div[@class='detachable-right scrollable flex-grow animation-fade-in']/operation-console[@class='test flex flex-column']/div[@class='panel panel-dark animation-fade-in']/div/div[2]/div[2]/div[1]/input[1]")
	WebElement txtReference;

	@FindBy(xpath="//div[3]//div[1]//div[2]//div[1]//div[1]//input[1]")
	WebElement txtNroSolicitud;
	
	// Botón de Cierre del intento de ejecución del servicio
	@FindBy(xpath="//button[@class='no-border pull-right']//i[@class='icon icon-simple-remove']")
	WebElement btnX;
	
	@FindBy(css="section.dzqrwsreup.dzqrwsreup-xl div.huzbxuibav.huzbxuibav-md.huzbxuibav-sm.huzbxuibav-lg.huzbxuibav-xl div.ycqdvmhchp.ycqdvmhchp-md.ycqdvmhchp-xl div.detachable-right.scrollable.flex-grow.animation-fade-in operation-console.test.flex.flex-column div.panel.panel-dark.menu.menu-horizontal.animation-fade-in:nth-child(3) pre:nth-child(2) span:nth-child(2) > span:nth-child(1)")
	WebElement responseCode;
	
	@FindBy(xpath="//span[contains(text(),'{')]")
	WebElement responseBody;
	
	WebDriver driver;
	
	public ApiDetails(WebDriver miDriver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (miDriver,20), this);
		driver = miDriver;
	}
	
	public void goToCotizarApi() {
		Utilities.waitForVisibility(driver, btnCotizarApi);
		btnCotizarApi.click();
	}
	
	public void goToBienvenidaApi() {
		Utilities.waitForVisibility(driver, btnBienvenidaApi);
		btnBienvenidaApi.click();
	}
	
	public void goToGenerarSolicitudApi() {
		Utilities.waitForVisibility(driver, btnGenerarSolicitudApi);
		btnGenerarSolicitudApi.click();
	}
	
	public void goToObtenerPdfApi() {
		Utilities.waitForVisibility(driver, btnObtenerPdfApi);
		btnObtenerPdfApi.click();
	}
	
	public void clicOnTryItApi() {
		Utilities.waitForVisibility(driver, btnTryItApi);
		btnTryItApi.click();
	}
	
	public void clicOnSend() {
		Utilities.waitForVisibility(driver, btnSend);
		btnSend.click();
	}
	
	public void fillBodyRequest(String bodyRequest) {
		Utilities.waitForVisibility(driver, txtBodyRequest);
		txtBodyRequest.sendKeys(bodyRequest);
	}
	
	public void goToAffiliateApi() {
		Utilities.waitForVisibility(driver, btnAffiliate);
		btnAffiliate.click();
	}
	
	public void fillAffiliateParams(String dni, String reference) {
		Utilities.waitForVisibility(driver, txtDni);
		txtDni.sendKeys(dni);
		
		Utilities.waitForVisibility(driver, txtReference);
		txtReference.sendKeys(reference);
	}
	
	public void fillGetPdfParam(String nroSolicitud) {
		Utilities.waitForVisibility(driver, txtNroSolicitud);
		txtNroSolicitud.sendKeys(nroSolicitud);
	}
	
	public void closeTryItSection() {
		Utilities.waitForVisibility(driver, btnX);
		btnX.click();
	}
	
	public String getResponseCode() {
		return responseCode.getText();
	}
	
	public String getResponseBody() {
		return responseBody.getText();
	}
}