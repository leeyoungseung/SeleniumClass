/**
 * 조작 3 : Action등 키보드, 마우스 조작법 
 */
package selenium.test.web.lesson02;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
			System.out.println("[ " + sec + " ] 초간 기다립니다.");
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 페이지 이동
	@Test
	public void Test001() throws IOException, WebDriverException {
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}

		// 이동
		driver.get("https://www.naver.com/");
		// 요소 저장
		WebElement wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[1]/div[2]/div[1]/ul[1]/li[3]/a"));
		sleep(1);

		// 이동
		System.out.println("github로 이동");
		driver.navigate().to("https://github.com/");
		sleep(1);

		// 뒤로가기
		System.out.println("뒤로가기(naver)");
		driver.navigate().back();
		sleep(1);
		try {
			System.out.println("이동 한 후 값은 어찌되나? [ " + wel.getText() + " ]");
		} catch (Exception e) {
			System.out.println("값은 사라졌습니다...");
		}

		// 앞으로 가기
		System.out.println("앞으로 가기(github)");
		driver.navigate().forward();
		sleep(1);

		System.out.println("JsExecutor로도 같은 기능을 사용할 수 있습니다.");
		System.out.println("뒤로가기(naver)");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("history.go(-1)");
		sleep(1);
		wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[1]/div[2]/div[1]/ul[1]/li[3]/a"));
		System.out.println("값은 어찌되나? [ " + wel.getText() + " ]");

		System.out.println("새로고침 후에는 어찌될까?");
		driver.navigate().refresh();
		try {
			System.out.println("새로고침 한 후 값은 어찌되나? [ " + wel.getText() + " ]");
		} catch (Exception e) {
			System.out.println("값은 사라졌습니다...");
		}

	}

	// 마우스 조작
	@Test
	public void Test002() throws IOException, WebDriverException {
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		WebElement wel = null;
		int x, y;
		// 이동
		driver.get("https://www.naver.com/");
		// 요소 저장
		wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[1]/div[2]/div[1]/ul[1]/li[3]/a/span[2]"));
		x = wel.getLocation().getX();
		y = wel.getLocation().getY();

		// 마우스 이동 후 클릭
		Actions ac = new Actions(driver);
		ac.moveByOffset(x + 1, y + 1).click();
		ac.perform();

	}

	/**
	 * 드래그 앤 드롭
	 * https://stackoverflow.com/questions/39436870/why-drag-and-drop-is-not-working-in-selenium-webdriver
	 * 
	 * @throws IOException
	 * @throws WebDriverException
	 */
	@Test
	public void Test003() throws IOException, WebDriverException {
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		WebElement wel = null;

		// 드래그 하기
		driver.get("http://127.0.0.1/seleniumTest/Class_B_03.html");

		wel = driver.findElement(By.id("drag1"));
		WebElement wel2 = driver.findElement(By.id("drag2"));
		sleep(2);

		if (wel.isEnabled() && wel2.isEnabled()) {
			System.out.println("모두 사용가능합니다.");
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(
				"function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
						+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
						+ "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
						+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n"
						+ "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
						+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
						+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
						+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n"
						+ "}\n" + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
						+ "var dragStartEvent =createEvent('dragstart');\n"
						+ "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n"
						+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
						+ "var dragEndEvent = createEvent('dragend');\n"
						+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
						+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
						+ "simulateHTML5DragAndDrop(source,destination);",
				wel2, wel);

		// 드래그 앤 드롭은 더이상 지원하질 않아요.....
		// 참고 사항
		// :https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/3604
		// 드래그 앤 드롭
		/*
		 * driver.navigate().refresh();
		 * 
		 * wel = driver.findElement(By.id("drag1")); wel2 =
		 * driver.findElement(By.id("drag2")); sleep(2);
		 * 
		 * if (wel.isEnabled() && wel2.isEnabled()) { System.out.println("모두 사용가능합니다.");
		 * }
		 * 
		 * System.out.println("드래그 앤 드롭!!"); Actions ac = new Actions(driver);
		 * ac.dragAndDrop(wel2, wel).perform();
		 */
	}

	/**
	 * 키보드 조작
	 * 
	 * @throws IOException
	 * @throws WebDriverException
	 */
	@Test
	public void Test004() throws IOException, WebDriverException {
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		WebElement wel = null;

		driver.get("https://www.naver.com/");

		//그냥 클릭
		wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[1]/div[2]/div[1]/ul[1]/li[3]/a"));
		wel.click();

		// 뒤로가기
		System.out.println("뒤로가기(naver)");
		driver.navigate().back();

		// Ctrl + 클릭
		System.out.println("새창에서 실행");
		wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[1]/div[2]/div[1]/ul[1]/li[3]/a"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(wel).perform();

	}

	/**
	 * 마우스 우클릭
	 * https://stackoverflow.com/questions/11428026/select-an-option-from-the-right-click-menu-in-selenium-webdriver-java
	 * @throws IOException
	 * @throws WebDriverException
	 */
	@Test
	public void Test005() throws IOException, WebDriverException {
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}
		
		// 이동 하기
		driver.get("https://www.naver.com/");
		
		WebElement wel = driver.findElement(By.xpath("//*[@id=\"PM_ID_ct\"]/div[1]/div[2]/div[1]/ul[1]/li[3]/a"));

		// 마우스 우클릭
		Actions action = new Actions(driver);
		//action.moveToElement(wel);
		sleep(1);
		action.contextClick(wel).build().perform();
		sleep(3);
		int selector = 3;
		Robot robot;
		try {
			robot = new Robot();
			for(int i=0; i<selector; i++) {
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
			}
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 팝업 대응
	 * @throws IOException
	 * @throws WebDriverException
	 */
	@Test
	public void Test006() throws IOException, WebDriverException{
		// 드라이버 시작
		if (driver == null) {
			startDriver();
		}

		// 테스트 페이지로 이동
		driver.get("http://127.0.0.1/seleniumTest/Class_B_03.html");

		//(1) Yes Or No (Confirmation)
		WebElement wel = driver.findElement(By.id("alert_btn1"));
		wel.click();
		sleep(2);
		// 팝업으로 포커싱
		Alert al = driver.switchTo().alert();
		// 수락하기
		System.out.println("수락하기");
		al.accept();
		
		wel = driver.findElement(By.id("alert_btn1"));
		wel.click();
		sleep(2);
		// 팝업으로 포커싱
		al = driver.switchTo().alert();
		// 거절하기
		System.out.println("거절하기");
		al.dismiss();
		
		//(2) 입력이 필요할 때 (prompt)
		WebElement wel2 = driver.findElement(By.id("alert_btn2"));
		wel2.click();
		sleep(2);
		// 팝업으로 포커싱
		al = driver.switchTo().alert();
		al.sendKeys("안녕히계세요 여러분!!!");
		al.accept();
		

	}
	
	
}
