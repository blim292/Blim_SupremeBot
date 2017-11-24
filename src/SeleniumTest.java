
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	public static void main(String[] args) {
		
		PickObject userObj = new PickObject();
		userObj.pickItem();

		String supremeSite = "http://www.supremenewyork.com";

		String projectLocation = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectLocation + "/lib/chromedriver/chromedriver");
		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait1 = new WebDriverWait(driver,120);
		
		driver.get(supremeSite + userObj.category);
//		Page refresher.
		do{
			int randomNum = ThreadLocalRandom.current().nextInt(800, 1000 + 1); // between .8 - 1 second refresh rate.
			try {
				Thread.sleep(randomNum);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.navigate().refresh();
		}
		while(!driver.getPageSource().contains(userObj.itemName));
		
	
		if (driver.getPageSource().contains(userObj.itemName)) {
			
//			Check to make sure the product name and color point to the same link.
//			If both point to same link, go to link.
			List<WebElement> itemHref = driver.findElements(By.partialLinkText(userObj.itemName));
			List<WebElement> colorHref = driver.findElements(By.partialLinkText(userObj.itemColor));
			
			for(WebElement weItem : itemHref) {
				for (WebElement weColor : colorHref) {
					if (weItem.getAttribute("href").equals(weColor.getAttribute("href"))) {
						weColor.click();
						break;
					}
				}
			}
			
//			If color doesn't matter.
//			driver.findElement(By.partialLinkText(userObj.itemColor)).click();
			
//			Change Size
			String cartSize;
			switch (userObj.itemSize) {
			case "XS":
				cartSize = "XSmall";
				break;
			case "S":
				cartSize = "Small";
				break;
			case "M":
				cartSize = "Medium";
				break;
			case "L":
				cartSize = "Large";
				break;
			case "XL":
				cartSize = "XLarge";
				break;
			default:
				cartSize = "Small";
				break;
			}
			
			wait1.until(ExpectedConditions.elementToBeClickable(By.name("s")));
			Select droplist = new Select(driver.findElement(By.name("s"))); 
			droplist.selectByVisibleText(cartSize);
			
			
			
//			Cart item.
			wait1.until(ExpectedConditions.elementToBeClickable(By.name("commit")));
			driver.findElement(By.name("commit")).click();
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("checkout")));
			driver.findElement(By.partialLinkText("checkout")).click();
			
//			Checkout.
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('order_billing_name').value = arguments[0]", userObj.name);
			js.executeScript("document.getElementById('order_email').value = arguments[0]", userObj.email);
			js.executeScript("document.getElementById('order_tel').value = arguments[0]", userObj.tele);
			js.executeScript("document.getElementById('bo').value = arguments[0]", userObj.address);
			js.executeScript("document.getElementById('order_billing_zip').value = arguments[0]", userObj.zip);
			js.executeScript("document.getElementById('order_billing_city').value = arguments[0]", userObj.city);
			js.executeScript("document.getElementById('order_billing_state').value = arguments[0]", userObj.state);
			js.executeScript("document.getElementById('order_billing_country').value = arguments[0]", userObj.country);
			js.executeScript("document.getElementById('nnaerb').value = arguments[0]", userObj.ccNum);
			js.executeScript("document.getElementById('credit_card_month').value = arguments[0]", userObj.ccMonth);
			js.executeScript("document.getElementById('credit_card_year').value = arguments[0]", userObj.ccYear);
			js.executeScript("document.getElementById('orcer').value = arguments[0]", userObj.ccv);
						
			WebElement element = driver.findElement(By.id("order_terms"));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();

//			Confirm purchase.
//			driver.findElement(By.name("commit")).click();
			
			
		}
		else {
			System.out.println("String you are searching for not present on webpage.");
		}
		
//		driver.quit();
	}
}

