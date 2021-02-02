package NovaSiniestros;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Menu {
	
	@FindBy(xpath="//span[contains(text(),'Inicio')]")
	private WebElement btnHome;

	@FindBy(xpath="//span[contains(text(),'Denuncias')]")
	private WebElement btnDenuncias;
	
	@FindBy(xpath="//a[@title='Busqueda']")
	private WebElement btnBusqueda;
	
	@FindBy(xpath="//span[contains(text(),'Solicitud de Pagos')]")
	private WebElement btnSolicitudPago;
	
	@FindBy(xpath="//span[contains(text(),'Reportes')]")
	private WebElement btnReportes;
	
	@FindBy(xpath="//span[contains(text(),'Siniestros')]")
	private WebElement btnReporteSiniestros;
	
	@FindBy(xpath="//span[contains(text(),'Sol. de Pagos')]")
	private WebElement btnReporteSolicitudesPago;
	
	@FindBy(xpath="//span[contains(text(),'Indicadores de Siniestros')]")
	private WebElement btnReporteIndicadoresSiniestros;
	
	@FindBy(xpath="//span[contains(text(),'Siniestros Pagados')]")
	private WebElement btnReporteSiniestrosPagados;
	
	@FindBy(xpath="//span[contains(text(),'Siniestros Prescriptos')]")
	private WebElement btnReporteSiniestrosPrescriptos;
	
	@FindBy(xpath="//span[contains(text(),'Siniestros Ingresados por Productor y Ramo')]")
	private WebElement btnReporteProductorRamo;
	
	@FindBy(xpath="//span[contains(text(),'Auditoria de Siniestros Pagados')]")
	private WebElement btnReporteAuditoriaSiniestrosPagados;
	
	@FindBy(xpath="//span[contains(text(),'Vencimiento de Siniestros')]")
	private WebElement btnReporteVencimientoSiniestros;
	
	@FindBy(xpath="//span[contains(text(), 'Bienvenido')]")
	private WebElement bienvenidaDisponible;
	
	public Menu(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
	}
	
	public void clicOnDenuncias() {
		btnDenuncias.click();
	}
	
	public void clicOnBusqueda() {
		btnBusqueda.click();
	}
	
	public void clicOnSolicitudPagos() {
		btnSolicitudPago.click();
	}
	
	public boolean pantallaMenuDisponible() {
		return bienvenidaDisponible.isDisplayed();
	}
	
	public void clicOnDespliegaReportes() {
		btnReportes.click();
	}
	
	public void clicOnReporteSiniestro() {
		btnReporteSiniestros.click();
	}
	
	public void clicOnReporteSolicitudesPago() {
		btnReporteSolicitudesPago.click();
	}
	
	public void clicOnReporteIndicadoresSiniestros() {
		btnReporteIndicadoresSiniestros.click();
	}
	
	public void clicOnReporteSiniestrosPagados() {
		btnReporteSiniestrosPagados.click();
	}
	
	public void clicOnReporteSiniestrosPrescriptos() {
		btnReporteSiniestrosPrescriptos.click();
	}
	
	public void clicOnReporteProductorRamo() {
		btnReporteProductorRamo.click();
	}
	
	public void clicOnAuditoriaSiniestrosPagados() {
		btnReporteAuditoriaSiniestrosPagados.click();
	}
	
	public void clicOnReporteVencimientoSiniestros() {
		btnReporteVencimientoSiniestros.click();
	}
	
	public void irAHome() {
		btnHome.click();
	}
}
