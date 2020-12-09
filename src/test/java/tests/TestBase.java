package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Browsers.FIREFOX;
import static helpers.AttachmentsHelper.*;

public class TestBase {

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureListener", new AllureSelenide().screenshots(true).savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
//        Configuration.browser = FIREFOX;
        Configuration.startMaximized = true;

        Configuration.remote = "https://user1:1234@" + System.getProperty("remote.browser.url") + ":4444/wd/hub/";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/";
    }

    @AfterEach
    @Step("Attachments")
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();

        Selenide.closeWebDriver();
    }
}