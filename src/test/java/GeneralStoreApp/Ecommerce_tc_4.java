package GeneralStoreApp;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.TapOptions.tapOptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjectModel.CartPage;
import PageObjectModel.GeneralstoreHomePage;
import PageObjectModel.GeneralstoreProductPage;
import PageObjectModel.Utilities;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.base;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class Ecommerce_tc_4 extends base{
	
	AndroidDriver<AndroidElement> driver;
	GeneralstoreHomePage ghp;
	
	
	@Test
	public void totalValidation() throws IOException, InterruptedException
	{
	
		//To make the server start Automatically need to download few dependencies in POM file
		//slf4j - simple , slf4j - api, commons - lang3, commons - io, commons - validator
		service=startServer();
		// TODO Auto-generated method stub
		
		//TC:- Validate Mobile Gestures working for Links(long press) and navigate to webview
		
		
		driver=Capabilities("AppName");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//To hide active keyboard
		driver.hideKeyboard();
		
		//Entering Name in the box,choose the gender button
		ghp=new GeneralstoreHomePage(driver);
		ghp.nameBox.sendKeys("Hello");
		ghp.genderButton.click();
		ghp.countryDropDown.click();
		//to scoll till we see the country name we want to click,we need to scrool
			
		Utilities u=new Utilities(driver);
		u.scrollToText("Argentina");
		//Now click on Argentina
		ghp.countryArgentina.click();
		ghp.shopButton.click();
		
		//Tc3- Adding first 2 products to the cart and pressing cart button
		GeneralstoreProductPage gpp=new GeneralstoreProductPage(driver);
		gpp.firstProduct.get(0).click();
		gpp.secondProduct.get(0).click();
		gpp.cartButton.click();
		Thread.sleep(3000);
		
		//adding product price in add to cart and validating if it matches with TotalPrduct Amount
		double sum = 0 ;
		CartPage cp=new CartPage(driver);
		
		int totalProduct=cp.productValueId.size();
	    for(int i=0;i<totalProduct;i++)
	    {
	    	String productPrice=cp.productValueId.get(i).getText();
	    	//System.out.println(productPrice);
	    	double productValue=getAmount(productPrice);
	    	sum=sum+productValue;
	    }
	    System.out.println("Sum of total products  :" +sum);
		String total=cp.totalPurchaseAmount.getText();
		double totalValue=getAmount(total);
		System.out.println("Total Value of products  :" +totalValue);
		Assert.assertEquals(sum, totalValue);

		//Tc4 -Tapping on the check box in the Cart Page , Longpressing on "Terms And Conditions" link
		//And clicking on the "Visit to the website Button"
		WebElement checkBox=cp.checkBoxButton;
		TouchAction t=new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkBox))).perform();
		
		WebElement termsLink=cp.termsButton;
		t.longPress(longPressOptions().withElement(element(termsLink)).withDuration(ofSeconds(3))).release().perform();
	
		cp.termsOfConditionsCloseButton.click();
		cp.visitWebsiteButton.click();
		
		//Stop the appium server
		service.stop();
	
	}
	
	//To kill all the server which is in case open outside the framework we are writing the below method
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	

	public static double getAmount(String value)
	{
		value=value.substring(1);
    	double productPriceDou=Double.parseDouble(value);
    	return productPriceDou;
	}

}
