package SacTest;

import org.testng.annotations.Test;
import org.testng.eclipse.refactoring.ImportAssertRewriter;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class DashboardTest {
	
	WebDriver driver;
	
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "D:\\WorkSpaceEclipse\\TestDashboardSAC\\DriverNavegadores\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("http://certificacion.colloky.gcapp.cl/admin/admin/administradores/login");
	  driver.manage().window().maximize();
  }
  
 
  
  
  @Test /*Test envía por parametros credenciales de acceso*/
  public void accesoAplicacion() throws InterruptedException {
	  WebElement txtUser = driver.findElement(By.xpath("//*[@id=\"AdministradorUsuario\"]"));
	  txtUser.sendKeys("admin");
	  WebElement txtPassword = driver.findElement(By.xpath("//*[@id=\"AdministradorClave\"]"));
	  txtPassword.sendKeys("andain5546");
	  WebElement btnIngresar = driver.findElement(By.xpath("//*[@id=\"AdministradorAdminLoginForm\"]/div[4]/div/button"));
	  btnIngresar.click();
	  String urlEsperada = "http://certificacion.colloky.gcapp.cl/admin/admin/visita/visitas";
	  String urlActual = driver.getCurrentUrl();	  
	  assertEquals(urlActual, urlEsperada, "Ingreso usuario exitoso");
	  System.out.println("Ingreso de Usuario exitoso");
	  
  }
  

  		
  
  @Test /*Despliega submenu SAC/Dashboard*/
  public void filtrosDashboard()throws InterruptedException{
	  driver.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[21]/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[21]/ul/li[5]/a")).click();
	  
	  String urlActual = driver.getCurrentUrl();
	  String urlEsperada = "http://certificacion.colloky.gcapp.cl/admin/admin/sac/sac/dashboard";
	  assertEquals(urlActual, urlEsperada, "Vista dashboard exitosa");
  }
  
 
  
  
  @Test/*Verifica que se visualicen filtros Rango de fecha, marca, categoria, origen y canal*/
   public void filtrosDisponibles()throws InterruptedException{
	  WebElement fechaIncicio = driver.findElement(By.xpath("//*[@id=\"desde\"]")); /*Fecha inicio*/
	  WebElement fechaTermino = driver.findElement(By.xpath("//*[@id=\"hasta\"]"));/*Fecha término*/
	  driver.findElement(By.xpath("//*[@id=\"FormEvolutivoTickets\"]/div[4]/div/div/button/span")).isEnabled();/*Marca*/
	  driver.findElement(By.xpath("//*[@id=\"FormEvolutivoTickets\"]/div[5]/div/div/button/span")).isEnabled();/*Categoría*/
	  driver.findElement(By.xpath("//*[@id=\"FormEvolutivoTickets\"]/div[6]/div/div/button/span")).isEnabled();/*Origen*/
	  driver.findElement(By.xpath("//*[@id=\"FormEvolutivoTickets\"]/div[7]/div/div/button/span")).isEnabled();/*Canal*/
	  
	  boolean isPresente = false;
	  if (!fechaIncicio.isEnabled() && !fechaTermino.isEnabled()) {
		isPresente = false;
	  }else {
		isPresente = true;
	  }
	
	  assertTrue(isPresente);
  }
  
  /*
   * Validación que los campos marca y categoría contengan por defecto la opción colloky y reclamos
   */
    
  @Test 
   public void opcionesFiltro() {
	  WebElement marca = driver.findElement(By.xpath("//*[@id=\"FormEvolutivoTickets\"]/div[4]/div/div/button/span"));
	  WebElement categoria = driver.findElement(By.xpath("//*[@id=\"FormEvolutivoTickets\"]/div[5]/div/div/button/span"));
	  boolean valida = false;
	  if (!marca.getText().equals("COLLOKY") && !categoria.getText().equals("RECLAMOS")) {
		valida = false;
	}else {
		valida = true;
	}
	  
	  assertTrue(valida, "Opciones de Filtro" );
	  
  }
  
    
   @Test
   public void opcionesFiltroBtn() {
	   WebElement btnSemana = driver.findElement(By.xpath("//*[@id=\"tab-evolutivo-tickets\"]/div[2]/div[2]/button[1]"));
	   WebElement btnMes = driver.findElement(By.xpath("//*[@id=\"tab-evolutivo-tickets\"]/div[2]/div[2]/button[2]"));
	   WebElement btnDia = driver.findElement(By.xpath("//*[@id=\"tab-evolutivo-tickets\"]/div[2]/div[2]/button[3]"));
	   
	   
	   boolean habilitado = false;
	   
	   if (!btnSemana.isEnabled() && !btnMes.isEnabled() && !btnDia.isEnabled()) {
		
		   habilitado = false;
	}else {
		habilitado = true;
	}
	   
	   assertTrue(habilitado);
			   
	   
	   
   }
  
   
   @Test
   public void opcionesFiltroDia() throws InterruptedException {
	   WebElement btnDia = driver.findElement(By.xpath("//*[@id=\"tab-evolutivo-tickets\"]/div[2]/div[2]/button[3]"));
	   btnDia.click();
	   Thread.sleep(5000);
	   WebDriverWait wait = new WebDriverWait(driver, 3);
	   WebElement grafico1 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"grafico1\"]"))));
	   boolean visible = false;
	   WebElement grafico2 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"grafico2\"]"))));
	   if (!grafico1.isEnabled()&&!grafico2.isEnabled()) {
		visible = false;
	}else {
		visible = true;
	}
	   assertTrue(visible);

   }
   
   
   @Test
   public void opcionesFiltroMes() throws InterruptedException {
	   WebElement btnMes = driver.findElement(By.xpath("//*[@id=\"tab-evolutivo-tickets\"]/div[2]/div[2]/button[2]"));
	   btnMes.click();
	   Thread.sleep(5000);
	   WebDriverWait wait = new WebDriverWait(driver, 3);
	   WebElement grafico1 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"grafico1\"]"))));
	   boolean visible = false;
	   WebElement grafico2 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"grafico2\"]"))));
	   if (!grafico1.isEnabled()&&!grafico2.isEnabled()) {
		visible = false;
	}else {
		visible = true;
	}
	   assertTrue(visible);
	   
	   
   }
   
   
   @Test
   public void opcionesFiltroSemana() throws InterruptedException {

	   WebElement btnSemana = driver.findElement(By.xpath("//*[@id=\"tab-evolutivo-tickets\"]/div[2]/div[2]/button[1]"));
	   btnSemana.click();
	   Thread.sleep(5000);
	   WebDriverWait wait = new WebDriverWait(driver, 3);
	   WebElement grafico1 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"grafico1\"]"))));
	   boolean visible = false;
	   WebElement grafico2 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"grafico2\"]"))));
	   if (!grafico1.isEnabled()&&!grafico2.isEnabled()) {
		visible = false;
	}else {
		visible = true;
	}
	   assertTrue(visible);
	   
	   
   }
   
   
   
  
   
   @Test
   public void validaDias() throws InterruptedException {
	   Thread.sleep(5000);
	   WebElement fechaInicio = driver.findElement(By.xpath("//*[@id=\"desde\"]"));
	   WebElement fechaTermino =  driver.findElement(By.xpath("//*[@id=\"hasta\"]"));
	   //Calendar fechaSistema = new GregorianCalendar();
	   fechaInicio.sendKeys(/*fechaSistema.getCalendarType()*/"21-12-2020");
	   fechaInicio.sendKeys(Keys.TAB);
	   Thread.sleep(3000);
	   fechaTermino.sendKeys("02-02-2021");
	   fechaTermino.sendKeys(Keys.TAB);
	   Thread.sleep(5000);
	   WebElement btnDia = driver.findElement(By.xpath("//*[@id=\"tab-evolutivo-tickets\"]/div[2]/div[2]/button[3]"));
	   btnDia.click();
	   Thread.sleep(5000);
	   WebElement alerta = driver.findElement(By.xpath("//*[@id=\"alerta-sistema\"]/section/header/h2"));
	   Thread.sleep(5000);
	   WebElement btnCerrar = driver.findElement(By.xpath("//*[@id=\"alerta-sistema\"]/section/footer/div/div/button"));
	   btnCerrar.click();
	   assertTrue(alerta.isDisplayed());//Verifica si es que alerta es visible
	   
   }
      
      
  @AfterClass
  public void afterClass() {
	  
	 driver.close();
  }

}
