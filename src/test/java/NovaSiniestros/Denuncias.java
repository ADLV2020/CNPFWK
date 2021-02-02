package NovaSiniestros;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Denuncias {

		@FindBy(xpath="//body/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-multiple-claims[1]/div[1]/mat-card[1]/mat-card-content[1]/div[1]/div[1]/button[1]")
		private WebElement btnNuevoRegistro;
		
		@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-create-complaint[1]/div[1]/mat-card[1]/mat-card-content[1]/form[1]/div[1]/div[1]/mat-form-field[1]/div[1]/div[1]/div[3]/input[1]")
		private WebElement txtDenunciante;
		
		@FindBy(xpath="//body/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-create-complaint[1]/div[1]/mat-card[1]/mat-card-content[1]/form[1]/div[1]/div[2]/mat-form-field[1]/div[1]/div[1]/div[4]/mat-datepicker-toggle[1]/button[1]")
		private WebElement abreCalendarioNotificacion;
		
		@FindBy(xpath="//tbody/tr[2]/td[5]/div[1]")
		private WebElement seleccionaFechaNotificacion;
		
		@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-create-complaint[1]/div[1]/mat-card[1]/mat-card-content[1]/div[5]/form[1]/div[1]/div[1]/mat-form-field[1]/div[1]/div[1]/div[3]/input[1]")
		private WebElement txtBuscarPorDNI;
		
		@FindBy(xpath="//input[@formcontrolname='dateInsuranceClaim']")
		private WebElement fechaSiniestro;
		
		@FindBy(xpath="//input[@formcontrolname='dateNotificaction']")
		private WebElement fechaNotificacion;
		
		@FindBy(xpath="//input[@formcontrolname='hourInsuranceClaim']")
		private WebElement txtHoraSiniestro;
		
		@FindBy(xpath="//span[contains(text(),'AGREGAR ASEGURADO')]")
		private WebElement btnAgregarAsegurado;
		
		@FindBy(xpath="//span[contains(text(),'GUARDAR')]")
		private WebElement btnGuardar;
		
		@FindBy(xpath="//mat-icon[contains(text(),'search')]")
		private WebElement btnSearch;
		
		
		//pantallas
		
		@FindBy(xpath="//mat-card-title[contains(text(),'Denuncias de Siniestros')]")
		private WebElement pantallaDenunciasSiniestros;
		
		@FindBy(xpath="//mat-card-title[contains(text(),'Nueva Denuncia')]")
		private WebElement pantallaNuevaDenuncia;
		
		@FindBy(xpath="//mat-card-title[contains(text(),'Siniestros')]")
		private WebElement pantallaSiniestros;
		
		@FindBy(xpath="//h1[contains(text(), 'Seleccionar Polizas')]")
		private WebElement pantallaSeleccionaPolizas;
		
		@FindBy(xpath="//mat-card-title[contains(text(),'Ver Denuncia')]")
		private WebElement pantallaVerDenuncia;
		
		@FindBy(xpath="//span[contains(text(), 'SELECCIONAR POLIZAS')]")
		private WebElement btnSeleccionarPoliza;
		
		@FindBy(xpath="//tbody/tr[2]/td[5]/mat-checkbox[1]/label[1]/div[1]")
		private WebElement checkPoliza;
		
		@FindBy(xpath="//h4[contains(text(), 'ASSURMAX')]")
		private WebElement clicParaHacerFoco;
		
		@FindBy(xpath="//select-subsidiary[@formcontrolname='subsidiary']")
		private WebElement selSucursal;
		
		@FindBy(xpath="//span[contains(text(),'CASA CENTRAL')]")
		private WebElement sucursalSeleccionada;
		
		@FindBy(xpath="//select-claim-cause[@formcontrolname='claimCause']")
		private WebElement selCausaSiniestro;
		
		@FindBy(xpath="//select-type-of-accident[@formcontrolname='typeOfAccident']")
		private WebElement selTipoAccidente;
		
		// campos de agregar subreclamo
		
		@FindBy(xpath="//mat-panel-title/div[contains(text(), 'Poliza')]")
		private WebElement btnDespliegaSubReclamos;
		
		@FindBy(xpath="//span[contains(text(), 'AGREGAR SUB RECLAMO')]")
		private WebElement btnAgregarSubReclamo;
		
		@FindBy(xpath="//body/div[3]/div[2]/div[1]/mat-dialog-container[1]/sub-claim-form-dialog[1]/div[2]/button[1]/span[1]")
		private WebElement btnAgregarSubReclamo1;
		
		@FindBy(xpath="//body[1]/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-admin[1]/div[1]/mat-card[1]/mat-card-content[1]/app-admin-form-medium[1]/app-admin-form-large[1]/div[2]/mat-tab-group[1]/div[1]/mat-tab-body[1]/div[1]/policies-group[1]/div[2]/mat-accordion[1]/policy-form-large[1]/form[1]/mat-expansion-panel[1]/div[1]/div[1]/div[1]/div[4]/div[2]/button[1]/span[1]")
		private WebElement btnGuardarSubReclamo;
		
		
		// campos del subreclamo
		
		@FindBy(xpath="//select-sub-claim-state[@formcontrolname='subClaimState']")
		private WebElement estadoSubSiniestro;
		
		@FindBy(xpath="//select-sub-claim-sub-state[@formcontrolname='subClaimSubState']")
		private WebElement subEstadoSubSiniestro;
		
		@FindBy(xpath="//select-currency[@formcontrolname='currency']")
		private WebElement tipoMoneda;
		
		@FindBy(xpath="/html/body/div[3]/div[2]/div/mat-dialog-container/sub-claim-form-dialog/div[1]/sub-claim-form/form/div[3]/div/mat-form-field/div/div[1]/div[3]/mat-select")
		private WebElement cobertura;
		
		@FindBy(xpath="//select-trigger-incident[@formcontrolname='triggerIncident']")
		private WebElement hechoGenerador;
		
		@FindBy(xpath="//select-type-of-injury[@formcontrolname='typeOfInjury']")
		private WebElement tipoLesion;
		
		@FindBy(xpath="//mat-radio-button[@value='insured']")
		private WebElement radAsegurado;
		
		@FindBy(xpath="//input[@formcontrolname='indemnificationAmount']")
		private WebElement txtIndemnizacionMonto;
		
		@FindBy(xpath="//input[@formcontrolname='indemnificationSavings']")
		private WebElement txtIndemnizacionAhorro;
		
		@FindBy(xpath="//input[@formcontrolname='indemnificationReinsurance']")
		private WebElement txtIndemnizacionReaseguro;
		
		@FindBy(xpath="/html/body/div[3]/div[2]/div/mat-dialog-container/sub-claim-form-dialog/div[2]/button[1]")
		private WebElement guardarSubreclamo;
		
		@FindBy(xpath="//span[contains(text(),'OK')]")
		private WebElement btnOk;
		
		@FindBy(xpath="//input[@formcontrolname='dateFrom']")
		private WebElement txtFechaDesde;
		
		@FindBy(xpath="//input[@formcontrolname='dateTo']")
		private WebElement txtFechaHasta;
		
		@FindBy(xpath="//span[contains(text(),'BUSCAR')]")
		private WebElement btnBuscarSiniestros;
		
		@FindBy(xpath="//mat-icon[contains(text(), 'edit')]")
		private WebElement btnLapizEdicion;
		
		//duplicado?
		@FindBy(xpath="//body[1]/app-root[1]/app-layout[1]/div[1]/section[1]/div[1]/app-admin[1]/div[1]/mat-card[1]/mat-card-content[1]/app-admin-form-medium[1]/app-admin-form-large[1]/div[2]/mat-tab-group[1]/div[1]/mat-tab-body[1]/div[1]/policies-group[1]/div[2]/mat-accordion[1]/policy-form-large[1]/form[1]/mat-expansion-panel[1]/div[1]/div[1]/div[1]/div[4]/div[2]/button[1]/span[1]")
		private WebElement btnGuardarTodo;
		
		private WebDriver driver;
		
		public Denuncias(WebDriver driver) {
			PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
			this.driver = driver;
		}
		
		public void ingresaFechasBusqueda(String desde, String hasta) {
			txtFechaDesde.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			txtFechaDesde.sendKeys(desde);
			txtFechaHasta.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			txtFechaHasta.sendKeys(hasta);
		}
		
		public void clicOnLapizEdicion() {
			btnLapizEdicion.click();
		}
		
		public void clicOnLapizCorrecto(String denunciante) {
			for (int x=2; x < 9; x++) {
				WebElement campoDenunciante = driver.findElement(By.xpath("//mat-row[@class='mat-row']["+ x +"]//mat-cell[2]")); //Posición del nombre
				if ((campoDenunciante.toString()).contains(denunciante)) {
					WebElement btnEditar = driver.findElement(By.xpath("//mat-row[@class='mat-row']["+ x + "]//mat-cell[6]")); //Posición del lapiz
					btnEditar.click();
					break;
					}
				}
		}
		
		public void clicOnBuscarSiniestros() {
			btnBuscarSiniestros.click();
		}
		
		public void clicOnNuevoRegistro() {
			btnNuevoRegistro.click();
		}
		
		public void ingresaDenunciante(String denunciante) {
			txtDenunciante.sendKeys(denunciante);
		}
		
		public void ingresaFechaNotificacion(String fechaNoti) {
			fechaNotificacion.click();
			
			fechaNotificacion.clear();
			
			fechaNotificacion.sendKeys(fechaNoti);
			
		}
		
		public void ingresarDatosBusqueda(String dni) {
			txtBuscarPorDNI.sendKeys(dni);
		}
		
		public void seleccionaResultadoBusquedaDNI(String denunciante, String dni) {
			driver.findElement(By.xpath("//*[contains(@class, 'mat-option') and contains(@class, 'mat-focus-indicator') and contains(@class, 'ng-star-inserted') and contains(@class, 'mat-active')]/span[contains(text(),'"+ dni +" - "+ denunciante +"')]")).click();
		}
		
		public void ingresaFechaSiniestro(String fecha) {
			fechaSiniestro.click();
			fechaSiniestro.sendKeys(fecha);
		}
		/*
		public void seleccionaFechaSiniestro() {
			abreCalendarioSiniestro.click();
			seleccionaFechaSiniestro.click();
		}
		*/
		
		public void ingresarHoraSiniestro(String hora) {
			txtHoraSiniestro.clear();
			txtHoraSiniestro.sendKeys(hora);
		}
		
		public void clicOnAgregarAsegurado() {
			btnAgregarAsegurado.click();
		}
		
		public void clicOnGuardarDenuncia() {
			btnGuardar.click();
		}
		
		public void clicOnLupa() {
			btnSearch.click();
		}
		
		
		//Pantallas disponibles
		
		public boolean paginaDenunciasSiniestrosDisponible() {
			return pantallaDenunciasSiniestros.isDisplayed();
		}
		
		public boolean paginaNuevaDenunciaDisponible() {
			return pantallaNuevaDenuncia.isDisplayed();
		}		
		
		public boolean paginaSiniestrosDisponible() {
			return pantallaSiniestros.isDisplayed();
		}
		
		public boolean paginaSeleccionaPolizasDisponible() {
			return pantallaSeleccionaPolizas.isDisplayed();
		}
		
		public boolean pantallaVerDenunciaDisponible() {
			return pantallaVerDenuncia.isDisplayed();
		}
		
		public void seleccionarPoliza() {
			btnSeleccionarPoliza.click();
			checkPoliza.click();
		}
		
		public void clicOnGuardarPolizaSeleccionada() {
			btnGuardar.click();
		}
		
		public void seleccionarSucursal(String ramo, String sucursal) {
			driver.findElement(By.xpath("//h4[contains(text(), '"+ ramo +"')]")).click();
			selSucursal.click();
			driver.findElement(By.xpath("//span[contains(text(),'"+ sucursal +"')]")).click();
			//sucursalSeleccionada.click();
		}
		
		public void seleccionarCausaSiniestro(String causa) {
			selCausaSiniestro.click();
			driver.findElement(By.xpath("//span[contains(text(),'"+ causa +"')]")).click();
		}
		
		public void seleccionarTipoAccidente(String tipoAccidente) {
			selTipoAccidente.click();
			driver.findElement(By.xpath("//span[contains(text(),' "+ tipoAccidente +" ')]")).click();
		}
		
		
		public void clicOnDesplegarSubReclamos() {
			btnDespliegaSubReclamos.click();
		}
		
		public void clicOnAgregarSubReclamo() {
			btnAgregarSubReclamo.click();
		}
		
		
		//métodos de subreclamo
		
		
		public void cargaCamposSubreclamo(String estado, String subEstado, String moneda, String coberturaSub, String hechoGeneradorSub, String tipoLesionSub) {
		
			estadoSubSiniestro.click();
			driver.findElement(By.xpath("//span[contains(text(), ' "+ estado +" ')]")).click();
			subEstadoSubSiniestro.click();
			driver.findElement(By.xpath("//span[contains(text(), ' "+ subEstado +" ')]")).click(); 
			tipoMoneda.click();
			driver.findElement(By.xpath("//span[contains(text(), ' "+ moneda +" ')]")).click();
			cobertura.click();
			driver.findElement(By.xpath("//span[contains(text(), '"+ coberturaSub +"')]")).click();
			hechoGenerador.click();
			driver.findElement(By.xpath("//span[contains(text(), '"+ hechoGeneradorSub +"')]")).click();
			tipoLesion.click();
			driver.findElement(By.xpath("//span[contains(text(), ' " + tipoLesionSub + " ')]")).click();
			radAsegurado.click();
			txtIndemnizacionMonto.clear();
			txtIndemnizacionMonto.sendKeys("25000");
			txtIndemnizacionAhorro.clear();
			txtIndemnizacionAhorro.sendKeys("5000");
			txtIndemnizacionReaseguro.clear();
			txtIndemnizacionReaseguro.sendKeys("2000");
		}
		
		public void guardaCamposSubreclamo() {
			guardarSubreclamo.click();
		}

		public void aceptarSubReclamoAgregado() {
			btnOk.click();
		}
}
