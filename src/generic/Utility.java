package generic;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utility  {
	  
	//1: Method for taking Screenshot:
	public void openApp(WebDriver driver, String path) {
		try {
		TakesScreenshot t=(TakesScreenshot)driver;//TypeCasting
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		File destFile= new File(path);
		FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//2: Method for handle notification Pop-up (neither inspect nor move)
	//Notification Pop-up contains two buttons "Allow and Block"
	//Notification Pop-up are similar to alert Pop-up, can not be inspect and move
	//It is displayed below the address bar but on the left side/beginning of ad-bar 
	//it can be handled by the following method:
	public void notificationPopup() {
		ChromeOptions options = new ChromeOptions();	
		options.addArguments("--disable-notifications");
	}
	
	//3: Method to Handle ChildBrowser Pop-up
	
	public void childBrowserPopup(WebDriver driver) throws InterruptedException {
		//windowHandle of Parent
		String parent = driver.getWindowHandle();
		//setting Timeout:
		//WindowHandles of all browser
		Set<String> allWHS = driver.getWindowHandles();
		//remove parent from all browser's WindowHandle
		allWHS.remove(parent);
		//closing all the Child browser:
		for(String wh:allWHS)
		driver.switchTo().window(wh);
		driver.close();
		Thread.sleep(2000);
	}
	/*
	 4: Method to Handle Alert Pop-up:
	 Alert Pop-up is also called as Java-script Pop-up
	 it is displayed below the address bar in the middle of it
	 it can not be moved and inspected
	 it can handle by the following method:
	 */
	
	public void handleJavascriptPopup(WebDriver driver) {
		Alert alert = driver.switchTo().alert();//Alert is an Interface 
		System.out.println(alert.getText());//1Method :alert.getText();
		alert.accept();//2Method
		//alert.dismiss();//3Method
	}
	
}
