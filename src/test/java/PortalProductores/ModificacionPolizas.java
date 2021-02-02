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

public class ModificacionPolizas {
	
	@FindBy(xpath="//a[contains(text(), 'Modificación de póliza')]")
	private WebElement btnModificar;
	
	//modifica de conducto de pago
	@FindBy(xpath="//a[@title= 'Cambio de Conducto de Pago Tomador']")
	private WebElement btnConductoPagoTomador;
	
	@FindBy(xpath="//select[@id='MeioPagamento']")
	private WebElement selectMedioDePago;
	
	@FindBy(xpath="//*[@id=\'NumeroCondutoPagamento\']")
	private WebElement txtNroTarjeta;
	
	@FindBy(xpath="//input[@id='divTitularS']")
	private WebElement radTitular;
	
	@FindBy(xpath="//*[@id=\'divTitularN\']")
	private WebElement radNoTitular;
	
	@FindBy(xpath="//*[@id='observacao']")
	private WebElement txtObservaciones;
	
	@FindBy(xpath="//*[@id=\'Payment\']/form[1]/div[1]/div[7]/div[1]/label[2]/input[1]")
	private WebElement radPrioridadNormal;
	
	@FindBy(xpath="//label[@class='radio-inline' and contains(text(), 'Normal')]")
	private WebElement radioNormal;
	
	
	@FindBy(xpath="//*[@id=\"Payment\"]/form[1]/div[1]/div[10]/div[1]/div[1]/button[1]")
	private WebElement btnGuardar;
	
	@FindBy(xpath="//button[contains(text(), 'Sí')]")
	private WebElement btnConfirmar;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[7]/div[1]/div[1]/div[3]/button[1]")
	private WebElement btnSi;
	
	@FindBy(xpath="//*[@id=\"successadd\"]/div[1]/div[1]/div[3]/button[1]")
	private WebElement btnCerrar;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[18]/div[1]/div[1]/div[2]/button[1]")
	private WebElement btnFinalizaBaja;
	
	//modifica domicilio
	@FindBy(xpath="//*[@id=\"tab_6\"]/div[2]/div[1]/div[7]/a[1]")
	private WebElement btnInfoDomicilio;
	
	@FindBy(xpath="//*[@id=\"Rua\"]")
	private WebElement txtCalle;
	
	@FindBy(xpath="//*[@id=\"Altura\"]")
	private WebElement txtAltura;
	
	@FindBy(xpath="//*[@id=\"Piso\"]")
	private WebElement txtPiso;
	
	@FindBy(xpath="//*[@id=\"DTO\"]")
	private WebElement txtDto;
	
	@FindBy(xpath="//*[@id=\"Localidade\"]")
	private WebElement txtLocalidad;
	
	@FindBy(xpath="//*[@id=\"ProvinciaCodigo\"]")
	private WebElement selectProvincia;
	
	@FindBy(xpath="//*[@id=\"CodPostal\"]")
	private WebElement txtCodPostal;
	
	@FindBy(xpath="//*[@id=\"TelefoneTomador\"]")
	private WebElement txtTelefono;
	
	@FindBy(xpath="//*[@id=\"Email\"]")
	private WebElement txtMail;
	
	@FindBy(xpath="//*[@id=\"homeInfo\"]/form[1]/div[1]/div[14]/div[1]/label[2]/input[1]")
	private WebElement radNormal;
	
	@FindBy(xpath="//button[@title='Guardar']")
	private WebElement btnGuardarDomicio;
	
	@FindBy(xpath="//button[@class='btn btn-success']")
	private WebElement btnConfirmarDomicilio;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[3]/div[1]/div[1]/div[2]/button[1]")
	private WebElement btnCerrarDomicilio;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[6]/div[1]/h4[1]/a[1]")
	private WebElement pantallaModificacion;
	
	@FindBy(xpath="//a[contains(text(),'Solicitud de Endoso')]")
	private WebElement pantallaEditarCampos;
	
	@FindBy(xpath="//h4[contains(text(),'La operación se completó exitosamente')]")
	private WebElement pantallaConfirmar;
	
	@FindBy(xpath="//a[contains(text(), 'Incremento/Disminución de Prima')]")
	private WebElement btnModificaPrisma;
	
	@FindBy(xpath="//a[contains(text(), 'Actividad Laboral Asegurado')]")
	private WebElement btnModificaActividadLaboral;
	
	@FindBy(xpath="//a[contains(text(), 'Beneficiarios Asegurado')]")
	private WebElement btnModificaAsegurado;
	
	@FindBy(xpath="//a[contains(text(), 'Nombre y/o Documento Asegurado')]")
	private WebElement btnModificaNombreDocumentoAsegurado;
	
	@FindBy(xpath="//a[contains(text(), 'Nombre y/o Documento Tomador')]")
	private WebElement btnModificaNombreDocumentoTomador;
	
	@FindBy(xpath="//tbody/tr[1]/td[6]/a[1]/i[1]")
	private WebElement btnDescargaHistorial;
	
	@FindBy(xpath="//tbody/tr[1]/td[7]/a[1]/i[1]")
	private WebElement btnImprimir;
	
	@FindBy(xpath="//body[1]/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[6]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[8]/a[1]/i[1]")
	private WebElement btnVer;
	
	@FindBy(xpath="//tbody/tr[2]/td[9]/a[1]/i[1]")
	private WebElement btnAprobar;
	
	@FindBy(xpath="//button[contains(text(),'Salir')]")
	private WebElement btnSalirHistorial;
	
	@FindBy(xpath="//button[contains(text(), 'Guardar')]")
	private WebElement btnGuardarSalir;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[9]/div[1]/div[1]/div[2]/button[1]")
	private WebElement btnCerrarAprobacion;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[3]/div[1]/div[1]/div[3]/button[1]")
	private WebElement btnCerrarSalir;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[3]/div[1]/div[1]/div[2]/button[1]")
	private WebElement btnCerrarVerPoliza;
	
	@FindBy(xpath="//input[@id='Comentario']")
	private WebElement txtComentario;
	
	@FindBy(xpath="//button[contains(text(),'Aprobar')]")
	private WebElement btnAprobar2;
	
	@FindBy(xpath="//a[contains(text(), 'Imprimir Deuda')]")
	private WebElement btnImprimirDeuda;
	
	@FindBy(xpath="//a[contains(text(), 'Constancia de Cobertura')]")
	private WebElement btnConstancia;
	
	@FindBy(xpath="//a[contains(text(),'Baja de Vidas')]")
	private WebElement btnBajaVidas;
	
	@FindBy(xpath="//a[contains(text(),'Alta de Vidas')]")
	private WebElement btnAltaVidas;
	
	@FindBy(xpath="//button[contains(text(), 'Imprimir Constancia de Cobertura')]")
	private WebElement btnImprimirConstancia;
	
	//ver una mejor forma de localizar el botón de agregar un asegurado
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[6]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/a[1]/i[1]")
	private WebElement btnAgregarAsegurado;
	
	@FindBy(xpath="//input[@id='CUIT']")
	private WebElement txtCuit;
	
	@FindBy(xpath="//input[@id='RazaoSocial']")
	private WebElement txtRazonSocial;
	
	@FindBy(xpath="//button[contains(text(),'Imprimir Constancia de Cobertura')]")
	private WebElement btnImprimirConstanciaCobertura;
	
	@FindBy(xpath="//button[contains(text(),'Aceptar')]")
	private WebElement btnAceptar;
	
	@FindBy(xpath="//body[1]/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[13]/a[1]/i[1]")
	private WebElement btnImprimirCertificado;
	
	@FindBy(xpath="//a[contains(text(), 'Asegurado')]")
	private WebElement btnAsegurado;
	
	@FindBy(xpath="//a[contains(text(), 'Endosos')]")
	private WebElement btnEndosos;
	
	@FindBy(xpath="//body[1]/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[11]/a[1]/i[1]")
	private WebElement btnDescargaEndoso;
	
	@FindBy(xpath="//a[contains(text(),'Historia de Registros')]")
	private WebElement btnHistorialRegistros;
	
	WebDriverWait wait;
	
	public ModificacionPolizas(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public void clicOnImprimirCertificado() {
		btnImprimirCertificado.click();
	}
	
	public void clicOnCerrarVerPoliza() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrarVerPoliza)).click();
	}
	
	public void clicOnAsegurado() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAsegurado)).click();
	}
	
	public void clicOnHistorialRegistros() {
		wait.until(ExpectedConditions.elementToBeClickable(btnHistorialRegistros)).click();
	}
	
	public void clicOnEndosos() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEndosos)).click();
	}
	
	public void clicOnDescargaEndoso() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDescargaEndoso)).click();
	}
	
	public void clicOnModificar() {
		Utilities.waiter(3);
		wait.until(ExpectedConditions.elementToBeClickable(btnModificar)).click();
	}
	
	public void clicOnModificarPrisma() {
		wait.until(ExpectedConditions.elementToBeClickable(btnModificaPrisma)).click();
	}
	
	public void clicOnModificarActividadLaboral() {
		wait.until(ExpectedConditions.elementToBeClickable(btnModificaActividadLaboral)).click();
	}
	
	public void clicOnModificarAsegurado() {
		wait.until(ExpectedConditions.elementToBeClickable(btnModificaAsegurado)).click();
	}
	
	public void clicOnDescargarHistorial() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDescargaHistorial)).click();
	}
	
	public void clicOnImprimirLibreDeuda() {
		wait.until(ExpectedConditions.elementToBeClickable(btnImprimirDeuda)).click();
	}
	
	public void clicOnConstanciaCobertura() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConstancia)).click();
	}
	
	public void clicOnImprimirConstancia() {
		wait.until(ExpectedConditions.elementToBeClickable(btnImprimirConstancia)).click();
	}
	
	public void clicOnAgregarasegurado() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAgregarAsegurado)).click();
	}
	
	public void clicOnImprimirConstanciaCobertura() {
		wait.until(ExpectedConditions.elementToBeClickable(btnImprimirConstanciaCobertura)).click();
	}
	
	public void clicOnBajaVidas() {
		wait.until(ExpectedConditions.elementToBeClickable(btnBajaVidas)).click();
	}
	
	public void clicOnAceptar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAceptar)).click();
	}
	
	public void clicOnAltaVidas() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAltaVidas)).click();
	}
	
	public void clicOnConfirmaBaja() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSi)).click();
	}
	
	public void clicOnFinalizaBaja() {
		wait.until(ExpectedConditions.elementToBeClickable(btnFinalizaBaja)).click();
	}
	
	//
	
	public void clicOnImprimirPoliza() {
		wait.until(ExpectedConditions.elementToBeClickable(btnImprimir)).click();
	}
	
	public void clicOnVer() {
		wait.until(ExpectedConditions.elementToBeClickable(btnVer)).click();
	}
	
	public void clicOnGuardarSalir() {
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardarSalir)).click();
	}
	
	public void clicOnCerrarSalir() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrarSalir)).click();
	}
	
	public void clicOnAprobar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAprobar)).click();
	}
	
	public void agregarComentarioAprobacion(String comentario) {
		txtComentario.sendKeys(comentario);
	}
	
	public void clicOnAprobar2() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAprobar2)).click();
	}
	
	public void clicOnCerrarAprobacion() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrarAprobacion)).click();
	}
	
	//
	
	public void clicnOnSalirHistorial() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSalirHistorial)).click();
	}
	
	public void clicOnModificarNombreDocumentoAsegurado() {
		wait.until(ExpectedConditions.elementToBeClickable(btnModificaNombreDocumentoAsegurado)).click();
	}
	
	public void clicOnModificarNombreDocumentoTomador() {
		wait.until(ExpectedConditions.elementToBeClickable(btnModificaNombreDocumentoTomador)).click();
	}
	
	public void clicOnConductoPagoTomador() {
		btnConductoPagoTomador.click();
	}
	
	public void modificarMedioDePago(String metodoPago, String nroTarjeta, String observaciones) {
		selectMedioDePago.click();
		selectMedioDePago.sendKeys(metodoPago);
		txtNroTarjeta.sendKeys(nroTarjeta);
		radTitular.click();
		txtObservaciones.sendKeys(observaciones);
		//radPrioridadNormal.click();
	}
	
	public void clicOnGuardarCambios() {
		btnGuardar.click();
	}
	
	public void clicOnConfirmar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmar)).click();
	}
	
	public void clicOnCerrar() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrar));
		btnCerrar.click();
	}
	
	public void clicOnInformacionDomiciliaria() {
		btnInfoDomicilio.click();
	}
	
	public void modificaInfoDomicio(String calle, String altura, String piso, String dto, String localidad, String provincia, String codpostal, String telefono, String mail, String observaciones) {
		txtCalle.clear();
		txtCalle.sendKeys(calle);
		txtAltura.clear();
		txtAltura.sendKeys(altura);
		txtPiso.clear();
		txtPiso.sendKeys(piso);
		txtDto.clear();
		txtDto.sendKeys(dto);
		txtLocalidad.clear();
		txtLocalidad.sendKeys(localidad);
		selectProvincia.sendKeys(provincia);
		txtCodPostal.clear();
		txtCodPostal.sendKeys(codpostal);
		txtTelefono.clear();
		txtTelefono.sendKeys(telefono);
		txtMail.clear();
		txtMail.sendKeys(mail);
		txtObservaciones.clear();
		txtObservaciones.sendKeys(observaciones);
		radNormal.click();
	}
	
	public void clicOnGuardarDomicilio() {
		wait.until(ExpectedConditions.elementToBeClickable(btnGuardarDomicio)).click();
	}
	
	public void clicOnConfirmarDomicilio() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmar)).click();
	}
	
	public void clicOnCerrarDomicilio() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCerrarDomicilio)).click();
	}
	
	public boolean pantallaModificacionDisponible() {
		return pantallaModificacion.isDisplayed();
	}
	
	public boolean pantallaEdicionCamposDisponible() {
		return pantallaEditarCampos.isDisplayed();
	}
	
	public boolean pantallaConfirmarDisponible() {
		return pantallaConfirmar.isDisplayed();
	}
}
