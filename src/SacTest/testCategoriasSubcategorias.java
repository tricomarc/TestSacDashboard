package SacTest;

import org.testng.annotations.Test;

import testCases.TestBase;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class testCategoriasSubcategorias {
	WebDriver driver;
	
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "D:\\WorkSpaceEclipse\\TestDashboardSAC\\DriverNavegadores\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("http://certificacion.colloky.gcapp.cl/admin/admin/administradores/login");
	  driver.manage().window().maximize();
	  WebElement txtUser = driver.findElement(By.xpath("//*[@id=\"AdministradorUsuario\"]"));
	  txtUser.sendKeys("admin");
	  WebElement txtPassword = driver.findElement(By.xpath("//*[@id=\"AdministradorClave\"]"));
	  txtPassword.sendKeys("andain5546");
	  WebElement btnIngresar = driver.findElement(By.xpath("//*[@id=\"AdministradorAdminLoginForm\"]/div[4]/div/button"));
	  btnIngresar.click();
  }
  
  @Test
  public void Test_1_subMenuDash() throws InterruptedException {
	  driver.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[21]/a")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//*[@id=\"menu\"]/ul/li[21]/ul/li[5]/a")).click();
	  
	  System.out.println("Test_1_subMenuDash, con éxito");
  }
      
  @Test
  public void Test_2_opcionesSubcategorias() throws InterruptedException, IOException {
	   WebElement categorias = driver.findElement(By.xpath("//*[@id=\"panelEdit\"]/ul"));
	   List<WebElement> categoriasList = categorias.findElements(By.tagName("li"));
	   
	   for (WebElement li : categoriasList) {
		   if (li.getText().equals("Categorías y subcategorías")) {
			li.click();
		}
	   }
       //categorias.click();
	   Thread.sleep(3000);
	   WebElement marca = driver.findElement(By.xpath("//*[@id=\"FormCategoriasSubcategorias\"]/div[4]/div/div/button/span"));
	   //WebElement marca = driver.findElement(By.xpath("//*[@id=\"FormEvolutivoTickets\"]/div[4]/div/div"));
	   /*Toma screenshot de la página*/
	   File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   BufferedImage fullPage = ImageIO.read(screenShot);
	   
	   Point ubicacion = marca.getLocation();
	   int anchoMarca = marca.getSize().getWidth();
	   int altoMarca = marca.getSize().getHeight();
	   
	   BufferedImage elementoScreen = fullPage.getSubimage(ubicacion.getX(), ubicacion.getY(), anchoMarca, altoMarca);
	   ImageIO.write(elementoScreen, "png", screenShot);
	   
	   File screenShotUbicacion = new File("D:\\WorkSpaceEclipse\\TestDashboardSAC\\Evidencias\\campoMarca.png");
	   FileUtils.copyFile(screenShot, screenShotUbicacion);
	   
	   WebElement categoria = driver.findElement(By.xpath("//*[@id=\"FormCategoriasSubcategorias\"]/div[5]/div/div/button/span"));
	   WebElement origen = driver.findElement(By.xpath("//*[@id=\"FormCategoriasSubcategorias\"]/div[6]/div/div/button/span"));
	   WebElement canal = driver.findElement(By.xpath("//*[@id=\"FormCategoriasSubcategorias\"]/div[7]/div/div/button/span"));
	   Thread.sleep(3000);
	   boolean visible = false;
	   
	   if (!marca.isDisplayed() && !categoria.isDisplayed() && !origen.isDisplayed() && !canal.isDisplayed()) {
		
		   visible = false;
	}else {
		
		visible = true;
	}
	   
	   assertTrue(visible);
	   System.out.println("Test_1_subMenuDash, con éxito");
  }
  
  @Test
  public void Test_3_filtroDia() throws InterruptedException {
	  Thread.sleep(3000);
	  WebElement btnDia= driver.findElement(By.xpath("//*[@id=\"tab-categorias-subcategorias\"]/div[2]/div[2]/button[3]"));
	  btnDia.click();
	  Thread.sleep(3000);
	  //WebDriverWait wait = new WebDriverWait(driver,3);
	  WebElement tablaConsulta = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_3\"]"));
	  WebElement tablaConsultas = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_4\"]"));
	  WebElement tablaReclamos = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_5\"]"));
	  //WebElement fechaInicio = driver.findElement(By.xpath("//*[@id=\"desde\"]"));
	  //WebElement fechaTermino = driver.findElement(By.xpath("//*[@id=\"hasta\"]"));
	  boolean visible = false;
	  if (!tablaConsulta.isDisplayed() && !tablaConsultas.isDisplayed() && !tablaReclamos.isDisplayed()) {
		  
		  visible = false;
	  }else {
		  visible = true;
	  }
	  
	  assertTrue(visible);
	  System.out.println("Test_3_filtroDia, con éxito");
  }

 @Test
  public void Test_4_filtroMes() throws InterruptedException {
	  WebElement btnMes = driver.findElement(By.xpath("//*[@id=\"tab-categorias-subcategorias\"]/div[2]/div[2]/button[2]"));
	  btnMes.click();
	  Thread.sleep(3000);
	  WebElement tablaConsulta = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_6\"]"));
	  WebElement tablaConsultas = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_7\"]"));
	  WebElement tablaReclamos = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_8\"]"));
	  boolean visible = false;
	  if (!tablaConsulta.isDisplayed() && !tablaConsultas.isDisplayed() && !tablaReclamos.isDisplayed()) {
		
		  visible = false;
	}else {
		visible  = true;
	}
	  
	  assertTrue(visible);
	  
	  System.out.println("Test_4_filtroMes, con éxito");
  }
  
  @Test
  public void Test_5_filtroSemana() throws InterruptedException {
	  Thread.sleep(5000);
	  WebElement btnSemana = driver.findElement(By.xpath("//*[@id=\"tab-categorias-subcategorias\"]/div[2]/div[2]/button[1]"));
	  btnSemana.click();
	  Thread.sleep(5000);
	  WebElement tablaConsulta = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_9\"]"));
	  WebElement tablaConsultas = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_10\"]"));
	  WebElement tablaReclamos = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_11\"]"));
	  boolean visible = false;
	  if (!tablaConsulta.isDisplayed() && !tablaConsultas.isDisplayed() && !tablaReclamos.isDisplayed()) {
		
		  visible = false;
	}else {
		visible  = true;
	}
	  
	  assertTrue(visible);
	  
	  System.out.println("Test_5_filtroSemana, con éxito");
  }
  
 
  public void Test_6_btnCantidad() throws InterruptedException {
	  Thread.sleep(5000);
	  WebElement btnCantidad =  driver.findElement(By.xpath("//*[@id=\"tab-categorias-subcategorias\"]/div[2]/div[1]/button[1]"));
	  //WebElement btnPorcentaje = driver.findElement(By.xpath("//*[@id=\"tab-categorias-subcategorias\"]/div[2]/div[1]/button[2]"));
	  btnCantidad.click();
	  //String numeroConsulta = driver.findElement(By.className("text-center")).getText();
	  String numeroConsulta = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_9\"]/tbody/tr[8]/th[2]")).getText();
	  
	  boolean isNumber = false;
	  if (!esNumerico(numeroConsulta.trim())) {
		
		  isNumber = false;
	}else {
		isNumber = true;
	}
	  
	  assertTrue(isNumber);
	  System.out.println("Test_6_btnCantidad, con éxito");
  }
  
  @AfterClass
  public void afterClass() {
	  driver.close();
  }
  
  
  
  /*
   * Comprueba que el valor del campo sea numérico
   */
  
    public static boolean esNumerico(String numero) {
	  try {
		if (numero != null) {
			Integer.parseInt(numero);
		}
	} catch (NumberFormatException error) {
		
		return false;
		
	}
	  
	  return false;
  }

}

	
