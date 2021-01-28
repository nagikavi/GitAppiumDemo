package resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {
	
	public static AndroidDriver<AndroidElement> driver;
	public static AppiumDriverLocalService service;
	
	//Method to start Appium server programatically
	public AppiumDriverLocalService startServer()
	{
		boolean flag=checkIfServerIsRunnning(4723);
		if(!flag)
		{
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}	
		return service;
	}
	
	
	//To check if server is already opened or not
	
	public static boolean checkIfServerIsRunnning(int port)
	{		
			boolean isServerRunning = false;
			ServerSocket serverSocket;
			try {
				serverSocket = new ServerSocket(port);
				
				serverSocket.close();
			} catch (IOException e) {
				//If control comes here, then it means that the port is in use
				isServerRunning = true;
			} finally {
				serverSocket = null;
			}
		return isServerRunning;
	}
	
	//To start emulator
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(8000);
	}
	

	public static AndroidDriver<AndroidElement> Capabilities(String app) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\global.properties");
		prop.load(fis);
		
		//Using File class to get the app path w/o hardcoding
		File f=new File("src");
	//	File fs=new File(f,"ApiDemos-debug.apk");
		File fs=new File(f,(String) prop.get(app));
		
		//DesiredCapabilities to pass emulator info and the app to invoke in th eemulator
		DesiredCapabilities cap=new DesiredCapabilities();
		
		//To access the propertie of device through global parameters will do the following step
		//String device=(String) prop.get("device");
		
		//To get the propertie of device or any prpoerty from command prompt through Maven we do the following
		String device=System.getProperty("deviceName");
		
		if(device.contains("emulator"))
		{
			startEmulator();
		}
		
		//to not hardcode b/w device or emulator need to use if statement
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		//Need to give give below line for versions above Android 6
		 cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		//We can give timeout too with command if we want to wait till object is displayed
		 cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
				
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		
		//Creating AndroidDriver to execute our testcases
		driver=new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		return driver;
		
	}
	
	//getScrren shot method
	public static void getScreenShot(String testCaseName) throws IOException
	{
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\"+testCaseName+".png"));
	}

}
