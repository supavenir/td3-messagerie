package fr.caensup.td3.messagerie;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
class SeleniumDemo {

  private WebDriver driver;

  @BeforeEach
  void setUp() throws Exception {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--headless");
    driver = new ChromeDriver(options);
    driver.navigate().to("http://srv2-vm-2110.sts-sio-caen.info/messagerie/");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
  }

  @AfterEach
  void tearDown() throws Exception {
    if (driver != null) {
      driver.quit();
    }
  }

  private void fillElement(String name, String content) {
    WebElement elm = driver.findElement(By.name(name));
    elm.sendKeys(content);
  }

  private void btnClick(String cssSelector) {
    driver.findElement(ByCssSelector.cssSelector(cssSelector)).click();
  }

  private void assertElementContainsText(String cssSelector, String text) {
    assertTrue(driver.findElement(ByCssSelector.cssSelector(cssSelector)).getText().contains(text));
  }

  public void waitForTextToAppear(String textToAppear, WebElement element, int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
    wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
  }

  public void waitForTextToAppear(String textToAppear, WebElement element) {
    waitForTextToAppear(textToAppear, element, 3000);
  }

  @Test
  void test() {
    System.out.println("Current URL is:" + driver.getCurrentUrl());
    WebElement divVersion = driver.findElement(ByCssSelector.cssSelector(".ui.brown.label"));
    assertTrue(divVersion.getText().equals("0.0.1"));
    btnClick(".ui.olive.button");
    assertElementContainsText("body", "Aliases");
    fillElement("name", "SeleniumTest");
    fillElement("aliases", "selenium.net");
    fillElement("domain", "selenium.org");
    btnClick(".ui.green.button");
    waitForTextToAppear("SeleniumTest", driver.findElement(ByCssSelector.cssSelector(".ui.table")));
    assertElementContainsText(".ui.celled.table", "selenium.net");
    driver.findElement(ByCssSelector.cssSelector("tbody>tr:last-child"))
        .findElement(ByCssSelector.cssSelector("td .ui.red.mini.icon.button")).click();
    driver.findElement(ByCssSelector.cssSelector(".ui.warning.message"))
        .findElement(ByCssSelector.cssSelector(".ui.positive.button")).click();

  }

}
