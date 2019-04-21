/**
 * Action등 키보드, 마우스 조작법
 */
package selenium.test.web.lesson02;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

public class Class_B_03 {

	private WebDriver driver;
	private static String CHROMEDRIVER_PATH = "C:\\Users\\leeyoungseung\\project_source\\SeleniumClass\\src\\main\\resources\\chromedriver.exe";

	// 드라이버 시작
	private void startDriver() throws IOException, WebDriverException {
		System.out.println("Driver Start!!!");
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
		driver = new ChromeDriver();

		// 브라우저 로딩 타임아웃 5초
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// 웹브라우저 창 사이즈
		driver.manage().window().setSize(new Dimension(1024, 768));
	}

	// 슬립 함수
	private void sleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
