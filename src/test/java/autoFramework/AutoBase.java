package autoFramework;

import org.openqa.selenium.WebDriver;
import webTestFramework.SeleniumControl;

import java.text.MessageFormat;
import java.util.ArrayList;

public class AutoBase extends AutoLogger {

    protected static WebDriver webDriver;
    public SeleniumControl seleniumControl;

    private WebDriverFactory webDriverFactory = new WebDriverFactory();

    public void Sleep(int seconds) throws InterruptedException{
        if(seconds > 5)
            Info(MessageFormat.format("  Sleeping for {0} seconds....", seconds));

        Thread.sleep(seconds * 1000L);
    }

    public void InitWebDriver()
    {
        webDriver = webDriverFactory.CreateSeleniumDriver();
        setWebDriver(webDriver);
    }

    public void GoToURL(String url)
    {
        this.webDriver.navigate().to(url);
        Info("Navigating to " + url);
    }

    public void Quit()
    {
        this.webDriver.quit();
    }

    public void setWebDriver(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver()
    {
        return webDriver;
    }

    public void switchToiFrame(String iFrameID)
    {
        this.webDriver = webDriver.switchTo().frame(iFrameID);
    }

    public void switchToMainFrame()
    {
        this.webDriver = webDriver.switchTo().defaultContent();
    }

    public void switchToNewlyOpenTab()
    {
        ArrayList<String> allTabs = new ArrayList<>(webDriver.getWindowHandles());
        int lastTabIndex = allTabs.size() - 1;
        this.webDriver = webDriver.switchTo().window(allTabs.get(lastTabIndex));
        Info(String.format("Switched to newest tab: %s", this.webDriver.getTitle()));
    }
}
