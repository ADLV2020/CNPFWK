package PortalProductores;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Utilidades.Utilities;

public class CotizacionAccidentesPersonales {
	
	@FindBy(xpath="//input[@name='TP_Pessoa' and @value='F']")
	private WebElement checkPersonaFisica;
	
	@FindBy(xpath="//input[@name='TP_Pessoa' and @value='J']")
	private WebElement checkPersonaJuridica;
	
	@FindBy(xpath="//label[contains(text(), 'Física')]")
	private WebElement checkFisica;
	
	@FindBy(xpath="//select[@id='NR_Seq_Provincia']")
	private WebElement selProvincia;
	
	@FindBy(xpath="//input[@name='DS_Sobrenome']")
	private WebElement txtApellido;
	
	@FindBy(xpath="//input[@name='DS_Nome_Tomador']")
	private WebElement txtNombre;
	
	@FindBy(xpath="//input[@name='DS_Razao_Social']")
	private WebElement txtRazonSocial;
	
	@FindBy(xpath="//input[@name='TP_Cotacao_AP']")
	private WebElement radTipoCotizacion;
	
	@FindBy(xpath="//input[@name='TP_Cotacao_AP' and @value='1']")
	private WebElement radCotizacionIndividual;
	
	@FindBy(xpath="//input[@name='TP_Cotacao_AP' and @value='2']")
	private WebElement radCotizacionColectiva;
	
	@FindBy(xpath="//input[@name='TP_Vigencia_Cobertura' and @value='1']")
	private WebElement radVigenciaPeriodo;
	
	@FindBy(xpath="//input[@name='TP_Vigencia_Cobertura' and @value='2']")
	private WebElement radVigenciaAnual;
	
	@FindBy(xpath="//input[@name='ST_Moto' and @value='1']")
	private WebElement radMotoSi;
	
	@FindBy(xpath="//input[@name='ST_Moto' and @value='0']")
	private WebElement radMotoNo;
	
	@FindBy(xpath="//input[@name='TP_Ambito' and @value='1']")
	private WebElement radAmbitoJornada;
	
	@FindBy(xpath="//input[@name='TP_Ambito' and @value='2']")
	private WebElement radAmbito24;
	
	@FindBy(xpath="//select[@id='PeriodoCurto']")
	private WebElement selPeriodoCorto;
	
	@FindBy(xpath="//input[@id='DT_Inicio_Vigencia']")
	private WebElement txtVigenciaDesde;
	
	@FindBy(xpath="//td[contains(text(),'31')]")
	private WebElement fechaProv;
	
	@FindBy(xpath="//select[@id='Atividade']")
	private WebElement selActividad;
	
	@FindBy(xpath="//input[@id='NR_Valor_Morte']")
	private WebElement txtMuerte;
	
	@FindBy(xpath="//input[@name='ST_Incapacidade']")
	private WebElement checkITyP;
	
	@FindBy(xpath="//input[@name='NR_Valor_ITyP']")
	private WebElement txtITyP;
	
	@FindBy(xpath="//input[@name='ST_Gastos_Medicos']")
	private WebElement checkGastosMedicos;
	
	@FindBy(xpath="//input[@name='NR_Valor_Gastos_Medicos']")
	private WebElement txtGastosMedicos;
	
	@FindBy(xpath="//input[@id='ST_Gastos_Sepelio']")
	private WebElement checkGastosSepelio;
	
	@FindBy(xpath="//a[@title='Nova Cotação']")
	private WebElement btnIncluir;
	
	@FindBy(xpath="//a[@data-wizard='finish']")
	private WebElement btnGuardar;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]")
	private WebElement btnCotizar;
	
	@FindBy(xpath="//input[@name='TP_Periodicidade_Pagamento']")
	private WebElement radFacturaUnica;
	
	@FindBy(xpath="//body/div[2]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/a[2]")
	private WebElement btnFinalizar;
	
	@FindBy(xpath="//button[contains(text(),'Sí')]")
	private WebElement btnSi;
	
	@FindBy(xpath="//input[@ng-model='inputEmail.Email']")
	private WebElement txtMail;
	
	@FindBy(xpath="//a[@ng-click='EnviarCorreo()']")
	private WebElement btnEnviar;
	
	private WebDriver driver;
	
	public CotizacionAccidentesPersonales(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory (driver,20), this);
		this.driver = driver;
	}
	
	public void cargaDatosAsegurado(String provincia, String apellido, String nombre, String tipoPersona, String razonSocial) {
		if(tipoPersona.equals("Física")) {
			checkPersonaFisica.click();
			selProvincia.click();
			selProvincia.sendKeys(provincia);
			txtApellido.sendKeys(apellido);
			txtNombre.sendKeys(nombre);
		}
		else {
			checkPersonaJuridica.click();
			selProvincia.click();
			selProvincia.sendKeys(provincia);
			txtRazonSocial.sendKeys(razonSocial);
			
		}
	}
	
	public void cargaVariablesCotizacion(String tipoCotizacion, String vigenciaCobertura, String moto, String ambito) {
		if(tipoCotizacion.equals("Individual")) {
			radCotizacionIndividual.click();
		}
		else {
			radCotizacionColectiva.click();
		}
		
		if(vigenciaCobertura.equals("Anual")) {
			radVigenciaAnual.click();
		}
		else {
			radVigenciaPeriodo.click();
		}
		
		if(moto.equals("No")) {
			radMotoNo.click();
		}
		else {
			radMotoSi.click();
		}
		
		if(ambito.equals("24 horas")) {
			radAmbito24.click();
		}
		else {
			radAmbitoJornada.click();
		}
		
		/*radTipoCotizacion.click();
		radVigencia.click();
		radMoto.click();
		radAmbito.click();*/
	}
	
	public void cargarVigencia(String vigencia) {
		selPeriodoCorto.click();
		selPeriodoCorto.sendKeys(vigencia);
		//txtVigenciaDesde.sendKeys("30/12/2020");
		Utilities.waiter(3);
		txtVigenciaDesde.click();
		Utilities.waiter(3);
		fechaProv.click();
	}
	
	public void cargaActividad(String actividad, String muerte, String ityp, String gastosMedicos) {
		selActividad.sendKeys(actividad);
		txtMuerte.sendKeys(muerte);
		checkITyP.click();
		txtITyP.clear();
		txtITyP.sendKeys(ityp);
		checkGastosMedicos.click();
		txtGastosMedicos.clear();
		txtGastosMedicos.sendKeys(gastosMedicos);
		checkGastosSepelio.click();
	}
	
	public void clicOnIncluir() {
		btnIncluir.click();
	}
	
	public void clicOnCotizar() {
		btnCotizar.click();
	}
	
	public void clicOnFinalizar() {
		radFacturaUnica.click();
		btnFinalizar.click();
	}
	
	public void clicOnSi() {
		btnSi.click();
	}
	
	public void confirmarMailFinalizar(String mail) {
		txtMail.sendKeys(mail);
		btnEnviar.click();
	}

}
