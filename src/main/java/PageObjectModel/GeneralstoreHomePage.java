package PageObjectModel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GeneralstoreHomePage {

	static AndroidDriver<AndroidElement> driver;
	
	public GeneralstoreHomePage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public WebElement nameBox;
	
	@AndroidFindBy(xpath="//*[@text='Female']")
	public WebElement genderButton;
	
	@AndroidFindBy(id="android:id/text1")
	public WebElement countryDropDown;

	//@AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))")
	//public WebElement scrollToDesiredText;
	
	@AndroidFindBy(xpath="//*[@text='Argentina']")
	public WebElement countryArgentina;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement shopButton;
	
	
	
	
	
}
