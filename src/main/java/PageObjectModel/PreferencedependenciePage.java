package PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PreferencedependenciePage {
	
	
AndroidDriver<AndroidElement> driver;
	
	public PreferencedependenciePage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="android:id/checkbox")
	public WebElement checkBox;
	
	@AndroidFindBy(xpath="(//android.widget.RelativeLayout)[2]")
	public WebElement wifiSetting;
	
	@AndroidFindBy(className="android.widget.EditText")
	public WebElement settingEditBox;
	
	@AndroidFindBy(className="android.widget.Button")
	public List<WebElement> okButton;
	
	
	

}
