/**
 * JavascriptExecutor의 사용법
 */
package selenium.test.web.lesson02;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Class_B_02 {
	
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
	
	/**
	 * @reference :
	 *            https://www.guru99.com/execute-javascript-selenium-webdriver.html
	 * @throws IOException
	 * @throws WebDriverException
	 */
	@Test
	public void Test001() throws IOException, WebDriverException {
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		// 이동
		jse.executeScript("window.location = 'http://127.0.0.1/seleniumTest/Class_B_01.html'");
		// 입력란을 초기화
		WebElement wel = driver.findElement(By.id("email"));
		jse.executeScript("arguments[0].value = '';", wel);


		// sendKeys : 입력란에 텍스트 입력
		wel = driver.findElement(By.id("pwd"));
		String val = "myEmail@google.com";
		jse.executeScript("arguments[0].value = '" + val + "';", wel);

		
		// getText : 태그의 텍스트 값 가져오기
		wel = driver.findElement(By.xpath("/html/body/div/form/div[3]/label"));
		System.out.println((String)jse.executeScript("return arguments[0].innerText;", wel));

		
		// getAttribute : 태그의 속성값을 String으로 반환함
		wel = driver.findElement(By.id("check1"));
		val = (String)jse.executeScript("return arguments[0].getAttribute('class');", wel);
		System.out.println(val);
		
		wel = driver.findElement(By.id("check2"));
		jse.executeScript("arguments[0].click();", wel);
		

	}
	
	@Test
	public void Test002() throws IOException, WebDriverException{
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		// 이동
		driver.get("https://www.naver.com/");
		
		sleep(3);
		
		//현재의 위치가 0
		//상 -> 하 : 양수 ex) 800
		//하 -> 상 : 음수 ex) -800
		//(<좌우 : 0>,<상하 : 800>)
		jse.executeScript("window.scrollBy(0,800)");
		sleep(3);
		jse.executeScript("window.scrollBy(0,-800)");
		sleep(3);
		//좌 -> 우 : 양수 ex) 200
		//우 -> 좌 : 음수 ex) -200 
		//(<좌우 : 200>,<상하 : 0>)
		jse.executeScript("window.scrollBy(200,0)");
		sleep(3);
		jse.executeScript("window.scrollBy(-200,0)");
		sleep(3);
		
		//요소의 위치까지 스크롤하기
		WebElement wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[4]/div[3]/div/ul/li[1]/a"));
		int location_x = wel.getLocation().getX();
		int location_y = wel.getLocation().getY();
		jse.executeScript("window.scrollBy("+location_y+","+location_x+")");
		System.out.println("초기화");
		driver.navigate().refresh();
		sleep(3);
		
		wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[4]/div[3]/div/ul/li[1]/a"));
		System.out.println("요소로 자동 이동");
		jse.executeScript("arguments[0].scrollIntoView(true);",wel);
		sleep(3);
		wel.click();
	}

}
