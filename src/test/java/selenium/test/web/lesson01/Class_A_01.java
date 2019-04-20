package selenium.test.web.lesson01;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runners.MethodSorters;

public class Class_A_01 {

	private WebDriver driver;
	private static String CHROMEDRIVER_PATH = "C:\\Users\\leeyoungseung\\project_source\\SeleniumClass\\src\\main\\resources\\chromedriver.exe";

	//드라이버 시작
	private void startDriver() throws IOException, WebDriverException{
		System.out.println("Driver Start!!!");
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
		driver = new ChromeDriver();
		
		//브라우저 로딩 타임아웃 5초
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
		//웹브라우저 창 사이즈
		driver.manage().window().setSize(new Dimension(1024, 768));
		
		
	}
	
	//슬립 함수
	private void sleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * WebElemet요소 지정하기 1
	 * @throws IOException
	 * @throws WebDriverException
	 */
	@Test
	public void Test001() throws IOException, WebDriverException{
		//드라이버 시작
		if(driver==null) {
			startDriver();
		}
		//이동
		driver.get("http://127.0.0.1/seleniumTest/Class_A_01.html");
		
		sleep(3);
		
		//결과를  담을 배열
		String [] res = new String[6];
		
		WebElement wel = driver.findElement(By.id("select_id"));
		res[0] = wel.getText();
		
		res[1] = driver.findElement(By.className("select_class")).getText();
		
		res[2] = driver.findElement(By.tagName("button")).getText();
		
		res[3] = driver.findElement(By.name("select_name")).getText();
		
		res[4] = driver.findElement(By.linkText("linked")).getAttribute("id");

		res[5] = driver.findElement(By.partialLinkText("build")).getText();

		for (int i=0; i<res.length; i++) {
			System.out.println("결과 확인 "+(i+1)+"번 째 값은 : ["+ res[i] +"]");
		}
	}
	
	@Test
	public void Test002() throws IOException, WebDriverException{
		//드라이버 시작
		if(driver==null) {
			startDriver();	
		}
		//이동
		driver.get("http://127.0.0.1/seleniumTest/Class_A_02.html");
		
		sleep(3);
		
		//결과를  담을 배열
		String [] res = new String[16];
		
		WebElement wel = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]"));
		res[0] = wel.getTagName();
		res[1] = driver.findElement(By.cssSelector("#loginForm > div:nth-child(1)")).getTagName();
		
		res[2] = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/label")).getTagName();
		res[3] = driver.findElement(By.cssSelector("body > div > form > div:nth-child(1) > label")).getTagName();
		
		res[4] = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[2]")).getTagName();
		res[5] = driver.findElement(By.cssSelector("#loginForm > div:nth-child(2)")).getTagName();
		
		res[6] = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[2]/label[1]")).getTagName();
		res[7] = driver.findElement(By.cssSelector("#loginForm > div:nth-child(2) > label:nth-child(1)")).getTagName();
		
		res[8] = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[2]/label[2]")).getTagName();
		res[9] = driver.findElement(By.cssSelector("#loginForm > div:nth-child(2) > label:nth-child(2)")).getTagName();
		
		res[10] = driver.findElement(By.xpath("//*[@id=\"pwd\"]")).getTagName();
		res[11] = driver.findElement(By.cssSelector("#pwd")).getTagName();
		
		res[12] = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]")).getTagName();
		res[13] = driver.findElement(By.cssSelector("#loginForm > div.form-group.form-check")).getTagName();
		
		res[14] = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/label[2]/a")).getTagName();
		res[15] = driver.findElement(By.cssSelector("#loginForm > div.form-group.form-check > label:nth-child(2) > a")).getTagName();
		
		for (int i=0; i<res.length; i++) {
			System.out.println("결과 확인 "+(i+1)+"번 째 값은 : ["+ res[i] +"]");
		}
		
	}
	
}
