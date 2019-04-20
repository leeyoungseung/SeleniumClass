package selenium.test.web;

import org.junit.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runners.MethodSorters;


import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Class01_HelloSelenium {
	
	private WebDriver driver;
	private static String CHROMEDRIVER_PATH = "C:\\Users\\leeyoungseung\\project_source\\SeleniumClass\\src\\main\\resources\\chromedriver.exe";

	//드라이버 시작
	private void startDriver() throws IOException, WebDriverException{
		System.out.println("Driver Start!!!");
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
		driver = new ChromeDriver();
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
	 * 셀레늄을 기동시키는 테스트
	 * @throws WebDriverException
	 * @throws IOException
	 */
	@Test
	public void test001() throws WebDriverException, IOException {
		//드라이버 시작
		startDriver();
		
		//브라우저 로딩 타임아웃 10초
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
		//웹브라우저 창 사이즈
		driver.manage().window().setSize(new Dimension(1024, 768));
		
		//google로 이동
		driver.get("https://www.google.com/");
		
		//driver닫기
		driver.quit();
	}
	
}
