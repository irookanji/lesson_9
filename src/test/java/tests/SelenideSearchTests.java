package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SelenideSearchTests extends TestBase {
    @Test
    @DisplayName("Successful Selenide search in Bing")
    void bingSearchTest() {
        // Открыть bing
        step("Open bing.com", () -> open("https://www.bing.com/"));

        // Ввести Selenide в поиск
        step("Search for Selenide", () -> $(byName("q")).setValue("Selenide").pressEnter());

        // Проверить, что Selenide появился в результатах поиска
        step("Assert that Selenide in the search result", () -> $("html").shouldHave(Condition.text("selenide.org")));

    }
}
