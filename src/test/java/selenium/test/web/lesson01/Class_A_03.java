package selenium.test.web.lesson01;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Class_A_03 {

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

	@Test
	public void Test001() throws IOException, WebDriverException {
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		// 이동
		driver.get("http://127.0.0.1/seleniumTest/Class_A_03.html");

		// findElements메소드는 지정한 값에 해당하는 WebElement의 List를 반환한다.
		List<WebElement> welist = driver.findElements(By.tagName("th"));

		System.out.println("몇개의 요소가 있는지 : [ " + welist.size() + " ]");

		// list안의 WebElement에서 지정하고 싶은 WebElement를 다음과 같은 방법으로 지정할 수 있다.
		WebElement selectedWel = null;
		for (WebElement wel : welist) {
			if (wel.getText().equals("Lastname")) {
				selectedWel = wel;
			}
			System.out.println(wel.getText());
		}
		System.out.println("★★★★★★★★★★★★★★[ " + selectedWel.getText() + " ]★★★★★★★★★★★★★★");

		WebElement webElement = driver.findElement(By.xpath("/html/body/div/table"));

		// 지정한 WebElement의 자식 WebElement를 findElements메소드로 지정할 수 있다.
		List<WebElement> welist2 = webElement.findElements(By.tagName("tr"));
		System.out.println("테이블의 tr자식 요소는 몇개인지 : [ " + welist2.size() + " ]");
		for (WebElement wel : welist2) {
			// tr에는 text값이 없지만 자식인 td에 text값이 있으므로 그것을 출력함
			System.out.println(wel.getText());
		}

		List<WebElement> welist3 = webElement.findElements(By.tagName("td"));
		System.out.println("테이블의 td자식 요소는 몇개인지 : [ " + welist3.size() + " ]");
		for (WebElement wel : welist3) {
			System.out.println(wel.getText());
		}
	}

	@Test
	public void Test002() throws IOException, WebDriverException {
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		// 이동
		driver.get("http://127.0.0.1/seleniumTest/Class_A_03.html");

		// findElements메소드는 지정한 값에 해당하는 WebElement의 List를 반환한다.
		WebElement wel = driver.findElement(By.tagName("table"));
		// WebElement wel2 = driver.findElement(By.className("table table-bordered"));
		// -> 중간에 뛰어쓰기가 있는 것은 인식을 못함ㄷㄷ
		System.out.println(wel.getAttribute("class"));

		// 선택한 WebElement의 부모 요소를 지정하기
		// 참조 :
		// https://stackoverflow.com/questions/24267413/how-to-get-parent-of-webelement
		// xpath를 이용한 방법
		WebElement parentElement = wel.findElement(By.xpath("./.."));
		System.out.println("부모의 태그 값 : [ " + parentElement.getTagName() + " ]");
		System.out.println("부모의 속성 값 : [ " + parentElement.getAttribute("class") + " ]");

		parentElement = wel.findElement(By.xpath("./../.."));
		System.out.println("할머니의 태그 값 : [ " + parentElement.getTagName() + " ]");

		// JavascriptExecutor를 이용한 방법
		List<WebElement> welist = driver.findElements(By.tagName("td"));
		for (WebElement element : welist) {
			if (element.getText().equals("John")) {
				wel = element;
				System.out.println(element.getText());
				break;
			}
		}

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		parentElement = (WebElement) executor.executeScript("return arguments[0].parentNode;", wel);
		System.out.println("부모의 태그 값 : [ " + parentElement.getTagName() + " ]");

		parentElement = (WebElement) executor.executeScript("return arguments[0].parentNode;", parentElement);
		System.out.println("할머니의 태그 값 : [ " + parentElement.getTagName() + " ]");
	}

}
