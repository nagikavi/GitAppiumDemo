package PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage {
	
	AndroidDriver<AndroidElement> driver;
	
	public CartPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productValueId;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalPurchaseAmount;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	public WebElement checkBoxButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	public WebElement termsButton;
	
	@AndroidFindBy(id="android:id/button1")
	public WebElement termsOfConditionsCloseButton;
	
	@AndroidFindBy(xpath="//*[@text='Visit to the website to complete purchase']")
	public WebElement visitWebsiteButton;
	

}
