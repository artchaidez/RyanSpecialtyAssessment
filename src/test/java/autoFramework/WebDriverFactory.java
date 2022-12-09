package autoFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory extends AutoLogger{

    public WebDriverFactory()
    {

    }

    public WebDriver CreateSeleniumDriver()
    {
        WebDriver _WebDriver;
        Info("Creating WebDriver.....");

        _WebDriver = CreateWebDriver();
        _WebDriver.manage().window().maximize();
        _WebDriver.manage().deleteAllCookies();

        return _WebDriver;
    }

    public WebDriver CreateWebDriver()
    {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }


}