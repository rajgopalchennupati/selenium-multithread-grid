package drivercreation;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.*;

import com.aventstack.extentreports.ExtentTest;

public class CreateDriver {

	private static CreateDriver instance = null;
	private static final int IMPLICIT_TIMEOUT = 0;
	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private ThreadLocal<AppiumDriver<MobileElement>> mobileDriver = new ThreadLocal<AppiumDriver<MobileElement>>();

	private ThreadLocal<String> sessionId = new ThreadLocal<String>();
	private ThreadLocal<String> sessionBrowser = new ThreadLocal<String>();
	private ThreadLocal<String> sessionPlatform = new ThreadLocal<String>();
	private ThreadLocal<String> sessionVersion = new ThreadLocal<String>();
	
	private  ThreadLocal<ExtentTest> testLogger = new ThreadLocal<ExtentTest>(); //we are not providing any initial value here, it will be set in test classes
	private  ThreadLocal<ExtentTest> nodeLog = new ThreadLocal<ExtentTest>();
	
	

	
	
	private String getEnv = null;
	private Properties props = new Properties();

	// constructor
	private CreateDriver() {
	}

	/**
	 * getInstance method to retrieve active driver instance
	 *
	 * @return CreateDriver
	 */
	public static CreateDriver getInstance() {
		if (instance == null) {
			instance = new CreateDriver();
		}
		return instance;
	}

	public final void setDriver(String browser, String environment, String platform,ExtentTest extentTestLogger,
			Map<String, Object>... optPreferences) throws Exception {

		DesiredCapabilities caps = null;
		String localHub = "http://127.0.0.1:4723/wd/hub";
		String getPlatform = null;
		switch (browser) {
		case "firefox":
			caps = DesiredCapabilities.firefox();

			FirefoxOptions ffOpts = new FirefoxOptions();
			FirefoxProfile ffProfile = new FirefoxProfile();

			ffProfile.setPreference("browser.autofocus", true);

			caps.setCapability(FirefoxDriver.PROFILE, ffProfile);
			caps.setCapability("marionette", true);
			// webDriver.set(new FirefoxDriver(caps));
			
			String firefoxPath = System.getProperty("user.dir") + "/resources/geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", firefoxPath);
			

			//System.setProperty("", "gecko_driver_windows_path/geckodriver.exe");
			if (optPreferences.length > 0) {
				processFFProfile(ffProfile, optPreferences);
			}

			webDriver.set(new FirefoxDriver(ffOpts.merge(caps)));
			testLogger.set(extentTestLogger);
			

			// then pass them to the local WebDriver
		
			break;
		case "chrome":
			caps = DesiredCapabilities.chrome();

			ChromeOptions chOptions = new ChromeOptions();
			Map<String, Object> chromePrefs = new HashMap<String, Object>();

			chromePrefs.put("credentials_enable_service", false);
			chOptions.setExperimentalOption("prefs", chromePrefs);
			chOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking");

			caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
			caps.setCapability("applicationCacheEnabled", false);

			String chromePath = System.getProperty("user.dir") + "/resources/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromePath);
			if (optPreferences.length > 0) {
				processCHOptions(chOptions, optPreferences);
			}

			// webDriver.set(new ChromeDriver(caps));
			// Selenium 3.7.x
			webDriver.set(new ChromeDriver(chOptions.merge(caps)));
			testLogger.set(extentTestLogger);
			
			

			break;
		case "internet explorer":
			caps = DesiredCapabilities.internetExplorer();
			InternetExplorerOptions ieOpts = new InternetExplorerOptions();
			ieOpts.requireWindowFocus();
			
			caps.setCapability("requireWindowFocus", true);
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("EnableNativeEvents", false);
			caps.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR,ElementScrollBehavior.BOTTOM);
			caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			 caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,false);
			//ieOpts.merge(caps);


			String ieDriverPath = System.getProperty("user.dir") + "/resources/IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			if (optPreferences.length > 0) {
				processDesiredCaps(caps, optPreferences);
			}

			// webDriver.set(new InternetExplorerDriver(caps));
			webDriver.set(new InternetExplorerDriver(ieOpts.merge(caps)));

			break;

		case "safari":
			caps = DesiredCapabilities.safari();
			SafariOptions safariOpts = new SafariOptions();
			
			caps.setCapability(SafariOptions.CAPABILITY, safariOpts);
			caps.setCapability("autoAcceptAlerts", true);

			// webDriver.set(new SafariDriver(caps));
			// Selenium 3.7.x
			webDriver.set(new SafariDriver(safariOpts.merge(caps)));
			break;
		case "microsoftedge":
			caps = DesiredCapabilities.edge();
			EdgeOptions edgeOpts = new EdgeOptions();
			edgeOpts.setPageLoadStrategy("normal");
			// caps.setCapability(EdgeOptions.CAPABILITY, edgeOpts); -- check why it is not
			// working
			caps.setCapability("requireWindowFocus", true);
			// webDriver.set(new EdgeDriver(caps));

			// Selenium 3.7.x
			webDriver.set(new EdgeDriver(edgeOpts.merge(caps)));

			break;
		case "iphone":
		case "ipad":
			if (browser.equalsIgnoreCase("ipad")) {
				caps = DesiredCapabilities.ipad();
			} else {
				caps = DesiredCapabilities.iphone();
			}

			caps.setCapability("appName", "https://myapp.com/myApp.zip");
			caps.setCapability("udid", "12345678"); // physical device
			caps.setCapability("device", "iPhone"); // or iPad

			mobileDriver.set(new IOSDriver<MobileElement>(new URL(localHub), caps));
			break;

		case "android":
			caps = DesiredCapabilities.android();
			caps.setCapability("appName", "https://myapp.com/myApp.apk");
			caps.setCapability("uuid", "12345678");// physical device
			caps.setCapability("device", "Android");

			mobileDriver.set(new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps));

			break;

		}

	}

	/**
	 * overloaded setDriver method to switch driver to specific WebDriver if running
	 * concurrent drivers
	 *
	 * @param driver
	 *            WebDriver instance to switch to
	 */
	public void setDriver(WebDriver driver) {
		webDriver.set(driver);
		sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
		sessionBrowser.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getBrowserName());
		sessionPlatform.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getPlatform().toString());
		// setBrowserHandle(getDriver().getWindowHandle()); -- what the hell is this for
		// ?

	}

	/**
	 * overloaded setDriver method to switch driver to specific AppiumDriver if
	 * running concurrent drivers
	 *
	 * @param driver
	 *            AppiumDriver instance to switch to
	 */
	public void setDriver(AppiumDriver<MobileElement> driver) {
		mobileDriver.set(driver);
		sessionId.set(mobileDriver.get().getSessionId().toString());
		sessionBrowser.set(mobileDriver.get().getCapabilities().getBrowserName());
		sessionPlatform.set(mobileDriver.get().getCapabilities().getPlatform().toString());
	}

	/**
	 * getDriver method to retrieve active driver
	 *
	 * @return WebDriver
	 */
	public WebDriver getDriver() {
		return webDriver.get();
	}
	
	
	public ExtentTest getExtentTestLogger() {
		return testLogger.get();
	}
	
	

	/**
	 * getDriver method will retrieve the active AppiumDriver
	 *
	 * @param mobile
	 *            boolean parameter * @return AppiumDriver
	 */
	public AppiumDriver<MobileElement> getDriver(boolean mobile) { // why do we need boolean
		return mobileDriver.get();
	}

	/**
	 * getCurrentDriver method will retrieve the active WebDriver or AppiumDriver
	 *
	 * @return WebDriver
	 * @throws Exception
	 */
	public WebDriver getCurrentDriver() throws Exception {
		System.out.println("current thread is " + Thread.currentThread().getName());
		if (getInstance().getSessionBrowser().contains("iphone") || getInstance().getSessionBrowser().contains("ipad")
				|| getInstance().getSessionBrowser().contains("android")) {
			return getInstance().getDriver(true);
		} else {
			return getInstance().getDriver();
		}
	}

	public void closeDriver() {
		try {
			getDriver().quit();
			
			nodeLog.remove();
			testLogger.remove();
		}

		catch (Exception e) {
			// do something
		}
	}

	/**
	 * getSessionId method to retrieve active id
	 *
	 * @return String
	 * @throws Exception
	 */
	public String getSessionId() throws Exception {
		return sessionId.get();
	}

	/**
	 * getSessionBrowser method to retrieve active browser
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getSessionBrowser() throws Exception {
		System.out.println("sessionBrowservalue is "  + sessionBrowser.toString());
		return sessionBrowser.get();
	}

	/**
	 * getSessionVersion method to retrieve active version
	 *
	 * @return String
	 * @throws Exception
	 */
	public String getSessionVersion() throws Exception {
		return sessionVersion.get();
	}

	/**
	 * getSessionPlatform method to retrieve active platform
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getSessionPlatform() throws Exception {
		return sessionPlatform.get();
	}
	
	
/*	
	public String getExtentLogger() throws Exception 
		return sessionId.get();
	}
	
*/	
	

	/**
	 * driverWait method pauses the driver in seconds
	 *
	 * @param seconds
	 *            to pause
	 */
	public void driverWait(long seconds) {
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
		} catch (InterruptedException e) {
			// do something
		}
	}

	/**
	 * driverRefresh method reloads the current browser page
	 * 
	 * @throws Exception
	 */
	public void driverRefresh() throws Exception {
		getCurrentDriver().navigate().refresh();
	}

	private void processDesiredCaps(DesiredCapabilities caps, Map<String, Object>[] options) throws Exception {
		for (int i = 0; i < options.length; i++) {
			Object[] keys = options[i].keySet().toArray();
			Object[] values = options[i].values().toArray();
			for (int j = 0; j < keys.length; j++) {
				if (values[j] instanceof Integer) {
					caps.setCapability(keys[j].toString(), (int) values[j]);
				} else if (values[j] instanceof Boolean) {
					caps.setCapability(keys[j].toString(), (boolean) values[j]);
				//} else if (isStringInt(values[j].toString())) {
				//	caps.setCapability(keys[j].toString(), Integer.valueOf(values[j].toString()));
				} else if (Boolean.parseBoolean(values[j].toString())) {
					caps.setCapability(keys[j].toString(), Boolean.valueOf(values[j].toString()));
				} else {
					caps.setCapability(keys[j].toString(), values[j].toString());
				}
			}
		}
	}
	
	
	private void processFFProfile(FirefoxProfile profile, Map<String, Object>[] options) throws Exception {
	    for (int i = 0; i < options.length; i++) {
	        Object[] keys = options[i].keySet().toArray();
	        Object[] values = options[i].values().toArray();
	 // same as Desired Caps except the following difference
	        for (int j = 0; j < keys.length; j++) {
	            if (values[j] instanceof Integer) {
	                profile.setPreference(keys[j].toString(), 
	                (int) values[j]);
	            }
	 // etc...
	        }
	    }
	}


	private void processCHOptions(ChromeOptions chOptions, Map<String, Object>[] options) throws Exception {
	    for (int i = 0; i < options.length; i++) {
	        Object[] keys = options[i].keySet().toArray();
	        Object[] values = options[i].values().toArray();
	 // same as Desired Caps except the following difference
	 for (int j = 0; j < keys.length; j++) {
	            if (values[j] instanceof Integer) {
	                values[j] = (int) values[j];
	                chOptions.setExperimentalOption("prefs", options[i]);
	            }
	 // etc...
	        }
	    }
	}



}
