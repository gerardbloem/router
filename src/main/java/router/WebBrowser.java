package router;

// sudo apt-get install chromium-chromedriver
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebBrowser {

    private static WebBrowser instance = null;
    private ChromeOptions chromeOptions;
    private ChromeDriver chromeDriver;

    private WebBrowser(){

        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeDriver = new ChromeDriver(chromeOptions);
    }

    public ChromeDriver getChromeDriver() {
        return chromeDriver;
    }

    public static WebBrowser getInstance() {
        if(instance == null) {
            instance = new WebBrowser();
        }
        return instance;
    }

}