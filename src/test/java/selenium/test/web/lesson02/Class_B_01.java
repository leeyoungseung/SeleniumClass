/**
 * 조작 1 : 기본 조작 + 정보 가져오기
 */
package selenium.test.web.lesson02;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Class_B_01 {

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
			System.out.println("[ " + sec + " ] 초간 기다립니다.");
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * WebElement의 기본조작
	 * @throws IOException
	 * @throws WebDriverException
	 */
	@Test
	public void Test001() throws IOException, WebDriverException {
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		// 이동
		driver.get("http://127.0.0.1/seleniumTest/Class_B_01.html");

		// clear : 입력란을 초기화
		WebElement wel = driver.findElement(By.id("email"));
		wel.clear();

		// sendKeys : 입력란에 텍스트 입력
		wel = driver.findElement(By.id("pwd"));
		wel.sendKeys("myEmail@google.com");

		// getText : 태그의 텍스트 값 가져오기
		System.out.println(driver.findElement(By.xpath("/html/body/div/form/div[3]/label")).getText());

		// getAttribute : 태그의 속성값을 String으로 반환함
		wel = driver.findElement(By.id("check1"));
		System.out.println(wel.getAttribute("class"));
		System.out.println(wel.getAttribute("id"));
		System.out.println(wel.getAttribute("name"));
		System.out.println(wel.getAttribute("value"));
		System.out.println(wel.getAttribute("checked"));
		System.out.println(wel.getAttribute("type"));

		// getRect, : 요소의 위치와 크기 정보를 가져오기
		wel = driver.findElement(By.id("check2"));
		System.out.println(wel.getRect().getHeight() + " / " + wel.getRect().getWidth());
		System.out.println(wel.getRect().getX() + " / " + wel.getRect().getY());
		// getLocation : 요소의 위치 정보 가져오기
		System.out.println(wel.getLocation());
		// getSize : 요소의 크기 정보 가져오기
		System.out.println(wel.getSize());

		// isDisplayed : 요소가 보이는지
		// isEnabled : 요소를 컨트롤 할 수 있는지
		// isSelected : 요소를 선택할수 있는지
		System.out.println("선택할 수 없는 요소");
		System.out.println(wel.isDisplayed());
		System.out.println(wel.isEnabled());
		System.out.println(wel.isSelected());

		System.out.println("선택한 후 요소를 다시 확인");
		// click : 지정한 요소를 클릭
		wel.click();
		System.out.println(wel.isSelected());

		// 선택할 수 없는 요소를 확인해보자
		System.out.println("선택할 수 있는 요소");
		wel = driver.findElement(By.id("check3"));
		System.out.println(wel.isDisplayed());
		System.out.println(wel.isEnabled());
		System.out.println(wel.isSelected());
		
		//현재 페이지의 URL
		String currentUrl = driver.getCurrentUrl();
		//현재 페이지의 Title
		String title = driver.getTitle();
		//현재 페이지의 HTML 소스 코드
		String pageSource = driver.getPageSource();
			
		System.out.println("currentUrl : "+currentUrl);
		System.out.println("title : "+title);
		System.out.println("pageSource : "+pageSource);
	}
	
	/**
	 * 연습 문제 : 왜 우리는 다양한 요소 지정하는 법과, 조작하는 법을 익혀야 하는지???
	 * @throws IOException
	 * @throws WebDriverException
	 */
	@Test
	public void Test003() throws IOException, WebDriverException{
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		// 네이버로 이동
		driver.get("https://www.naver.com/");
		sleep(3);
		
		//a태그를 선택하기
		System.out.println("a 태그를 선택합니다");
		WebElement wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[1]/div[2]/div[1]/ul[1]/li[3]/a"));
		wel.click();
		
		// 네이버로 이동
		driver.get("https://www.naver.com/");
		sleep(3);
		
		//span 태그선택하기
		System.out.println("span 태그를 선택합니다");
		wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[1]/div[2]/div[1]/ul[1]/li[3]/a/span[2]"));
		wel.click();
//		System.out.println(wel.isEnabled());
//		System.out.println(wel.isSelected());
		
	}

}
