package JAVAforSelenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class FitpeoAssignment {

	public static void main(String[] args) throws InterruptedException, ATUTestRecorderException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		ATUTestRecorder recorder=new ATUTestRecorder("test-result/", "test-execution", false);
		recorder.start();
		
		
		driver.get("https://fitpeo.com/");

		driver.findElement(By.xpath("//div[contains(text(),'Revenue Calculator')]")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='MuiBox-root css-j7qwjs']//span[contains(@class,'MuiSlider-thumb')]")));

		WebElement sliderEle = driver.findElement(
				By.xpath("//div[@class='MuiBox-root css-j7qwjs']//span[contains(@class,'MuiSlider-thumb')]"));

		System.out.println("Location of the slider element " + sliderEle.getLocation());// (718, 663)

		Actions act = new Actions(driver);
		act.dragAndDropBy(sliderEle, 93, 0).build().perform();

		Thread.sleep(3000);
		System.out.println("Location of the slider element after movement is: " + sliderEle.getLocation());

		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(30));
		mywait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@class,'MuiInputBase-input')]")));

		WebElement sliderTextEle = driver.findElement(By.xpath("//input[contains(@class,'MuiInputBase-input')]"));

		Thread.sleep(3000);
		String actual_value = sliderTextEle.getAttribute("value");
		System.out.println("value is: " + actual_value);

		int expected_value = 817;
		if (actual_value.equals(String.valueOf(expected_value))) {
			System.out.println("Value updated correctly and TC is passed");

		} else {
			System.out.println(" TC is failed");

		}

		Thread.sleep(5000);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// jse.executeScript("arguments[0].value='0'", sliderTextEle);
		// System.out.println("Location of the slider element after changing price is: "
		// + sliderEle.getLocation());

		Thread.sleep(5000);
		jse.executeScript("arguments[0].value='560'", sliderTextEle);

		String new_value = sliderTextEle.getAttribute("value");
		System.out.println("new_value is: " + new_value);

		jse.executeScript("arguments[0].value='817'", sliderTextEle);

		String new_value1 = sliderTextEle.getAttribute("value");
		System.out.println("new_value1 is: " + new_value1);
		Thread.sleep(5000);
		System.out.println("Location of the slider element after changing price is: " + sliderEle.getLocation());


		//driver.findElement(By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox'][1]")).click();

		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3' and @type='checkbox']")); 
		   System.out.println(checkboxes.size());
	
		  checkboxes.get(0).click();

		  checkboxes.get(1).click();

		  checkboxes.get(2).click();
		  
		  checkboxes.get(7).click();
		  
		  WebElement ele = driver.findElement(By.xpath("//div[@class='MuiBox-root css-m1khva']/p[normalize-space()='$110295']"));
		  String text_actual=ele.getText();
		  System.out.println(text_actual);
		  String text_expected="$110295";
		  
		  if (text_actual.equals(text_expected)) {
			  System.out.println("TC passed");
			
		}else {
			System.out.println("TC failed");
		}

		  driver.close();
          recorder.stop();
	
	}

}