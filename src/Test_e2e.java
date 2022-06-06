import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Test_e2e {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriverwin32.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
		List<WebElement> radioButtons = driver.findElements(By.cssSelector("[name='radioButton']"));

		for (WebElement radioButton : radioButtons) {

			if (radioButton.getAttribute("value").equalsIgnoreCase("radio2")) {
				Assert.assertFalse(radioButton.isSelected());
				radioButton.click();
				Assert.assertTrue(radioButton.isSelected());
				break;
			}

		}

		WebElement staticDropdown = driver.findElement(By.id("dropdown-class-example"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByIndex(3);

		driver.findElement(By.id("autocomplete")).sendKeys("rus");
		Thread.sleep(2000);
		List<WebElement> options = driver.findElements(By.cssSelector("li[class=\"ui-menu-item\"] div"));
		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("Russian Federation")) {

				option.click();

				break;
			}
		}

		List<WebElement> checkBoxies = driver.findElements(By.cssSelector("[type='checkbox']"));

		for (WebElement checkbox : checkBoxies) {
			Assert.assertFalse(checkbox.isSelected());
			checkbox.click();
			Assert.assertTrue(checkbox.isSelected());

		}

		String name = "Tesvan";
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("alertbtn")).click();
		String textExpected = "Hello " + name + ", share this practice page and share your knowledge";
		Assert.assertEquals(driver.switchTo().alert().getText(), textExpected);
		driver.switchTo().alert().accept();

		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("confirmbtn")).click();
		String textExpectedConfirmButton = "Hello " + name + ", Are you sure you want to confirm?";
		Assert.assertEquals(driver.switchTo().alert().getText(), textExpectedConfirmButton);
		driver.switchTo().alert().dismiss();

		driver.findElement(By.id("opentab")).click();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentID = it.next();
		String childID = it.next();
		driver.switchTo().window(childID);
		System.out.println(driver.findElement(By.xpath("//h3/span")).getText());
		driver.close();
		driver.switchTo().window(parentID);
		driver.findElement(By.id("openwindow")).click();
		Set<String> windows1 = driver.getWindowHandles();
		Iterator<String> it1 = windows1.iterator();
		String parentID1 = it1.next();
		String childID1 = it1.next();
		driver.switchTo().window(childID1);
		driver.close();
		driver.switchTo().window(parentID1);
		WebElement textBox = driver.findElement(By.id("displayed-text"));
		System.out.println("Element displayed is :" + textBox.isDisplayed());
		driver.findElement(By.id("hide-textbox")).click();
		System.out.println("Element displayed is :" + textBox.isDisplayed());

		driver.findElement(By.id("show-textbox")).click();
		System.out.println("Element displayed is :" + textBox.isDisplayed());

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('.tableFixHead').scrollBy(0,400)");
		// work with iframe

		driver.switchTo().frame("courses-iframe");
		driver.findElement(By.xpath("//div[@class='login-btn']/a[@class='theme-btn']")).click();

	}

}
