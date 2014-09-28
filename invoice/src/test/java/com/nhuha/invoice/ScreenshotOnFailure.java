package com.nhuha.invoice;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class ScreenshotOnFailure extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		Object currentClass = tr.getInstance();
		WebDriver driver = ((SuperTest) currentClass).getDriverInstance();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destDir = "target/surefire-reports/screenshots";
		new File(destDir).mkdirs();
		String destFile = new Date().getTime() + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.setEscapeHtml(false);
		Reporter.log("Saved <a href=./screenshots/" + destFile + ">Screenshot</a>");
		super.onTestFailure(tr);
	}

}
