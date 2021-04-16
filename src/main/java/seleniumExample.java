import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: ChiYuan
 * Date: 2020/03/31 上午 10:05
 * To change this template use File | Settings | File Templates.
 */
public class seleniumExample {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/config/Selenium/chromedriver.exe");
        String accountName = "a811016g@yahoo.com.tw";
        String passWord = "love0713";
        // 假如不是登入狀態，用selenium進行登入
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        // 關閉推播
        capabilities.setCapability("profile.default_content_setting_values.notifications", 2);
        capabilities.setCapability("credentials_enable_service", false);
        capabilities.setCapability("profile.password_manager_enabled", false);

        // Chrome各種設置
        ChromeOptions options = new ChromeOptions();
        //隱藏視窗作業
//            options.addArguments("--headless");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        // 無痕模式
            options.addArguments("--incognito");
        //視窗最大化
        options.addArguments("--start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver webDriver = new ChromeDriver(capabilities);
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        WebDriverWait normalWait = new WebDriverWait(webDriver, 60);
        webDriver.get("https://www.facebook.com/");
        WebElement webElement;
        webElement = normalWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        webElement.sendKeys(accountName);
        webElement = webDriver.findElement(By.id("pass"));
        webElement.sendKeys(passWord);
        webElement = webDriver.findElement(By.cssSelector("input[type=\"submit\"]"));
        webElement.click();
        Thread.sleep(60000);
        webDriver.quit();
    }
}