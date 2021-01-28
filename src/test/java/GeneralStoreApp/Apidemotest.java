package GeneralStoreApp;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectModel.ApiHomePage;
import PageObjectModel.ParameterizingTestData;
import PageObjectModel.PreferencePage;
import PageObjectModel.PreferencedependenciePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.base;

public class Apidemotest extends base {

	@Test(dataProvider="InputData",dataProviderClass=ParameterizingTestData.class)
	public void preferenceTest(String input) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		
		//Starting the server
		service=startServer();
		
		AndroidDriver<AndroidElement> driver=Capabilities("Apidemo");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Click on preference - preference dependencies - wifi - wifi settings
		ApiHomePage hp=new ApiHomePage(driver);
		hp.preference.click();
		
		PreferencePage pp=new PreferencePage(driver);
		pp.prefDependencie.click();
		
		PreferencedependenciePage pdp=new PreferencedependenciePage(driver);
		pdp.checkBox.click();
		pdp.wifiSetting.click();
		pdp.settingEditBox.sendKeys(input);
		pdp.okButton.get(1).click();
		
		//Stop server
		service.stop();
		
	}
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(6000);
	}

}
