package com.flutter.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;

public class FlutterTestMain {
	public static AppiumDriver driver;
	public static WebDriverWait wait;
	private static AppiumDriverLocalService service;

	public static void main(String[] args) throws IOException {

		File app = new File("E:/flutterdemos/fluttertestapp/build/app/outputs/flutter-apk/app-profile.apk");

		System.out.println(app.getAbsolutePath());
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();

		if (service == null || !service.isRunning()) {
			throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "b1b8bc0b");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("automationName", "Flutter");

		driver = new AndroidDriver(service.getUrl(), capabilities);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitForFirstFrame();

		if (driver != null) {
			driver.quit();
		}
		if (service != null) {
			service.stop();
		}

	}

	public static void waitForFirstFrame() {
		driver.executeScript("flutter:waitForFirstFrame");
	}

}
