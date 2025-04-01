package automation.common.helpers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class CaptureHelper {
	public static String projectPath = System.getProperty("user.dir") + "/";
	private static SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
	public void captureScreenShot(WebDriver driver, String screenName) {
		
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			
			File sourse = ts.getScreenshotAs(OutputType.FILE);
			
			File theDir = new File("./screenShot/");
			if(!theDir.exists()) {
				theDir.mkdir();
			}
			FileHandler.copy(sourse, new File("./screenShot/" + screenName + "_" + date.format(new Date()) +".png"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error with " + e.getMessage());
		}
	}
}
