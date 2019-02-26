package support.steps;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.browserstack.local.Local;

import support.datas.Constant;
import support.datas.DataProvider;
import support.utils.ReadProperties;

public class ParentStepsSupport{

    public static WebDriver driver;
    public static String USERNAME = "USERNAME";
    public static String AUTOMATE_KEY = "AUTOMATE_KEY";
    public static String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static Local bsLocal = new Local();
    public static ChromeOptions options;
    public static DesiredCapabilities capa;

    /**
     * @throws MalformedURLException
     * @throws Exception
     * 
     */

    public ParentStepsSupport() {
        String run = ReadProperties.getConfigSelenium(Constant.RUN_ON);
        String browser = ReadProperties.getConfigSelenium(Constant.RUN_BROWSER);

        switch (run) {
            case Constant.HOST_LOCAL:
                switch (browser) {
                    case Constant.BROWSER_CHROME:
                        createLocalChrome();
                        break;
                    case Constant.BROWSER_FIREFOX:
                        createLocalFirefox();
                        break;
                    case Constant.BROWSER_IE:
                        createLocalIE();
                        break;
                    default:
                        createService(false);
                }
                break;
            case Constant.HOST_GRID:
                createGrid(browser);
                break;
            default:
                break;
        }

    }

    public String[] getVar(String var) {
        String[] part = var.split(",");
        return part;
    }

    public void createBrowserStack(String env, String nameSession, String browser, String os) throws Exception {
        USERNAME = ReadProperties.getConfigSelenium("browserstack_username");
        AUTOMATE_KEY = ReadProperties.getConfigSelenium("browserstack_key");
        URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + ReadProperties.getConfigSelenium("browserstack_url");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("name", nameSession);
        caps.setCapability("acceptSslCerts", "true");
        String local_link = ReadProperties.getConfigSelenium("browserstack_local_link");
        if (Constant.ENV_WEB.equals(env)) {

            String browserstack_browser = ReadProperties.getConfigSelenium("browserstack_browser");
            String browserstack_browser_version = ReadProperties.getConfigSelenium("browserstack_browser_version");
            String browserstack_os = ReadProperties.getConfigSelenium("browserstack_os");
            String browserstack_os_version = ReadProperties.getConfigSelenium("browserstack_os_version");

            for (int i = 0; i < getVar(browserstack_browser).length; i++) {
                if (browser.equals(getVar(browserstack_browser)[i]) && os.equals(getVar(browserstack_os)[i])) {
                    browserstack_browser = getVar(browserstack_browser)[i];
                    browserstack_browser_version = getVar(browserstack_browser_version)[i];
                    browserstack_os = getVar(browserstack_os)[i];
                    browserstack_os_version = getVar(browserstack_os_version)[i];
                }
            }
            caps.setCapability("browser", browserstack_browser);
            caps.setCapability("browser_version", browserstack_browser_version);
            caps.setCapability("os", browserstack_os);
            caps.setCapability("os_version", browserstack_os_version);
            caps.setCapability("browserstack.debug", "true");

        } else if (Constant.ENV_PHONE.equals(env)) {// Phone
            caps.setCapability("browserName", ReadProperties.getConfigSelenium("browserstack_phone"));
            caps.setPlatform(getPlatformPhone(Constant.Phone));
            caps.setCapability("device", ReadProperties.getConfigSelenium("browserstack_phonename"));
        } else if (Constant.ENV_TABLET.equals(env)) {// Tablet
            caps.setCapability("browserName", ReadProperties.getConfigSelenium("browserstack_tablet"));
            caps.setPlatform(getPlatformPhone(Constant.TAB));
            caps.setCapability("device", ReadProperties.getConfigSelenium("browserstack_tabletname"));
        }
        if ("yes".equals(local_link)) {
            try {
                caps.setCapability("browserstack.local", "true");
                driver = new RemoteWebDriver(new URL(URL), caps);
            }
            catch (Exception e) {
                HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
                bsLocalArgs.put("key", AUTOMATE_KEY);
                bsLocalArgs.put("force", "true");
                DataProvider dp = new DataProvider();
                String path = "";
                if (SystemUtils.IS_OS_WINDOWS) {
                    path = dp.getReferencesFile("BrowserStackLocal.exe");
                } else if (SystemUtils.IS_OS_LINUX) {
                    boolean is64bit = System.getProperty("sun.arch.data.model").contains("64");
                    if (is64bit) {
                        path = dp.getReferencesFile("BrowserStackLocal");
                    } else {
                        path = dp.getReferencesFileLinux("BrowserStackLocal");
                    }
                }

                bsLocalArgs.put("binarypath", path);
                bsLocal.start(bsLocalArgs);
                caps.setCapability("browserstack.local", "true");
                driver = new RemoteWebDriver(new URL(URL), caps);
            }

        } else {
            try {
                driver = new RemoteWebDriver(new URL(URL), caps);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private Platform getPlatformPhone(String device) {
        String plat = ReadProperties.getConfigSelenium(Constant.PHONE_GRID);
        if (Constant.TAB.equals(device)) {
            plat = ReadProperties.getConfigSelenium(Constant.TAB_GRID);
        }

        switch (plat) {
            case Constant.PLAT_ANDROID:
                return Platform.ANDROID;
            case Constant.PLAT_ANY:
                return Platform.ANY;
            case Constant.PLAT_EL_CAPITAN:
                return Platform.EL_CAPITAN;
            case Constant.IOS_LINUX:
                return Platform.LINUX;
            case Constant.IOS_MAC:
                return Platform.MAC;
            case Constant.PLAT_MAVERICKS:
                return Platform.MAVERICKS;
            case Constant.PLAT_MOUNTAIN_LION:
                return Platform.MOUNTAIN_LION;
            case Constant.PLAT_SNOW_LEOPARD:
                return Platform.SNOW_LEOPARD;
            case Constant.PLAT_UNIX:
                return Platform.UNIX;
            case Constant.PLAT_VISTA:
                return Platform.VISTA;
            case Constant.PLAT_YOSEMITE:
                return Platform.YOSEMITE;
            case Constant.PLAT_XP:
                return Platform.XP;
            case Constant.IOS_WINDOWS8:
                return Platform.WIN8;
            case Constant.IOS_WINDOWS8_1:
                return Platform.WIN8_1;
            case Constant.IOS_WINDOWS:
                return Platform.WINDOWS;
            case Constant.IOS_WIN10:
                return Platform.WIN10;
            default:
                return Platform.WINDOWS;
        }
    }

    private Platform getPlatform() {

        String iso = ReadProperties.getConfigSelenium(Constant.IOS_GRID);
        switch (iso) {
            case Constant.IOS_WINDOWS8:
                return Platform.WIN8;
            case Constant.IOS_WINDOWS8_1:
                return Platform.WIN8_1;
            case Constant.IOS_WINDOWS:
                return Platform.WINDOWS;
            case Constant.IOS_LINUX:
                return Platform.LINUX;
            case Constant.IOS_MAC:
                return Platform.MAC;
            default:
                return Platform.WINDOWS;
        }
    }

    protected void setBrowserSize() {
        String size = ReadProperties.getConfigSelenium(Constant.BROWSER_SIZE);
        switch (size) {
            case Constant.BROWSER_MAXIMUM:
                driver.manage().window().maximize();
                break;
            default:
                String[] spl = size.split(Constant.REGEX);
                driver.manage().window().setPosition(new Point(0, 0));
                driver.manage().window().setSize(new Dimension(Integer.parseInt(spl[0]), Integer.parseInt(spl[1])));
                break;
        }
    }

    public void createGrid(String browser) {
        if (driver == null) {
            try {
                DataProvider datap = new DataProvider();
                String path = datap.getOutputFile("download");
                capa = new DesiredCapabilities();
                capa.setPlatform(getPlatform());
                capa.setBrowserName(browser);
                if (browser.equals("firefox")) {

                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("network.http.phishy-userpass-length", 255);
                    profile.setPreference("browser.download.folderList", 2);
                    profile.setPreference("browser.download.dir", path);
                    profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                    profile.setPreference(
                            "browser.helperApps.neverAsk.saveToDisk",
                            "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
                    profile.setPreference("browser.download.manager.showWhenStarting", false);
                    profile.setPreference("browser.download.manager.focusWhenStarting", false);
                    profile.setPreference("browser.download.useDownloadDir", true);
                    profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                    profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                    profile.setPreference("browser.download.manager.closeWhenDone", true);
                    profile.setPreference("browser.download.manager.showAlertOnComplete", false);
                    profile.setPreference("browser.download.manager.useWindow", false);
                    profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
                    profile.setPreference("pdfjs.disabled", true);
                    capa.setCapability(FirefoxDriver.PROFILE, profile);

                } else if (browser.equals("chrome")) {

                    options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put(
                            "browser.helperApps.neverAsk.saveToDisk",
                            "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
                    options.setExperimentalOption("prefs", prefs);
                    capa.setCapability(ChromeOptions.CAPABILITY, options);

                }
                driver = new RemoteWebDriver(new URL(ReadProperties.getConfigSelenium(Constant.GRID_HOST)), capa);
                setBrowserSize();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createLocalChrome() {
        if (driver == null) {
            DataProvider dp = new DataProvider();
            String path = "";
            if (SystemUtils.IS_OS_WINDOWS) {
                path = dp.getReferencesFile("chromedriver.exe");
            } else {
                path = dp.getReferencesFile("chromedriver");
            }
            System.setProperty("webdriver.chrome.driver", path);
            String path1 = dp.getOutputFile("download");

            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", path1);
            prefs.put("Proxy", null);
            prefs.put(
                    "browser.helperApps.neverAsk.saveToDisk",
                    "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");

            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("useAutomationExtension", false);
//            options.addArguments("start-maximized");

            driver = new ChromeDriver(options);
            setBrowserSize();
        }
    }

    private void killService(String name) {
        try {
            Runtime.getRuntime().exec("taskkill /f /im " + name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createLocalIE() {
        if (driver == null) {

            DataProvider dp = new DataProvider();
            String path = "";
            if (SystemUtils.IS_OS_WINDOWS) {
                boolean is64bit = System.getProperty("sun.arch.data.model").contains("64");
                if (is64bit) {
                    // path = dp.getReferencesFile("MicrosoftWebDriver.exe");
                    path = dp.getReferencesFile("IEDriverServer_win_x64.exe");

                } else {
                    killService("IEDriverServer_win_x32.exe");
                    path = dp.getReferencesFile("IEDriverServer_win_x32.exe");
                }
            }
            System.setProperty("webdriver.ie.driver", path);
            driver = new InternetExplorerDriver();

            setBrowserSize();
        }
    }

    public void createLocalFirefox() {
        if (driver == null) {
        	DataProvider dp = new DataProvider();
            String path = dp.getReferencesFile("geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", path);
            driver = new FirefoxDriver();
            setBrowserSize();
        }
    }

    /**
     * create with service driver, it's not open
     * 
     * @param option
     *            enable javascript
     */
    public void createService(boolean option) {
        if (driver == null) {
            // turn off htmlunit warnings
            java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
            java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
            System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
            driver = new HtmlUnitDriver(option);
        }
    }

}
