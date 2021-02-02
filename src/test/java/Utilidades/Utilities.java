package Utilidades;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	public static String obtenerFechaActual() {
		Calendar cal = Calendar.getInstance();
		 
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyyMMddhhmmss");
        //System.out.println(dateOnly.format(cal.getTime()));
        
        return dateOnly.format(cal.getTime());
	}
	
	public static void waiter(int segundos) {
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;

		while (elapsedTime < segundos * 1000) {
		    elapsedTime = (new Date()).getTime() - startTime;
		}
	}
	
	public static void waitForVisibility(WebDriver driver, WebElement element) {
	       new WebDriverWait(driver, Duration.ofSeconds(5))
	            .until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void fillBodyRequest(WebDriver driver, WebElement txtBodyRequest, String bodyRequest) {
		waitForVisibility(driver, txtBodyRequest);
		txtBodyRequest.sendKeys(bodyRequest);
	}
	
	public static void realizarScrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}
	
	public static void realizarScrollUp(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}
}
