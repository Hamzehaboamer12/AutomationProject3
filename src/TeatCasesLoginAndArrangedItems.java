import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TeatCasesLoginAndArrangedItems {

	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void MySetup() {
		String TheURL = "https://www.saucedemo.com/";
		driver.get(TheURL);

	}

	@Test(priority = 1)
	public void LoginTest() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
	}

	@Test(priority = 2, description = "Test Products element")
	public void VerifyProductsElementIsThere() {
//		String TheExpectedResult = "Products";
//		String TheActualResult = driver.findElement(By.xpath("//span[@data-test='title']")).getText();
//		Assert.assertEquals(TheActualResult, TheExpectedResult);

		WebElement ProductElement = driver.findElement(By.xpath("//span[@data-test='title']"));
		boolean ExpextedResult = true;
		boolean ActualResult = ProductElement.isDisplayed();
		Assert.assertEquals(ActualResult, ExpextedResult);

	}

	@Test(priority = 3)
	public void SortingItems() throws InterruptedException {
		WebElement SelectItems = driver.findElement(By.className("product_sort_container"));
		Select MySelctor = new Select(SelectItems);
		Thread.sleep(2000);
		MySelctor.selectByValue("lohi");

//		String ExpectedLowPrice = "$7.99";
//		List<WebElement> ThePrices = driver.findElements(By.className("inventory_item_price"));
//		// System.out.println(ThePrices.size()); // check size list
//		String ActualLowPrice = ThePrices.get(0).getText();
//		Assert.assertEquals(ActualLowPrice, ExpectedLowPrice);
		// System.out.println(ThePrices.get(ThePrices.size()-1).getText()); // find last
		// items

		List<WebElement> ThePrices = driver.findElements(By.className("inventory_item_price"));

		double ThelowestPrice = Double.parseDouble(ThePrices.get(0).getText().replace("$", ""));
		double theHighestPrice =  Double.parseDouble(ThePrices.get(ThePrices.size()-1).getText().replace("$", ""));
//	System.out.println(theHighestPrice);
//	System.out.println(ThelowestPrice);
		boolean ActualResult = true;
		Assert.assertEquals(ThelowestPrice>theHighestPrice,ActualResult);

	}

}
