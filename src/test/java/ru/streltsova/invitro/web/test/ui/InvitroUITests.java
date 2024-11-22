package ru.streltsova.invitro.web.test.ui;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.streltsova.invitro.web.page.AnalizesPage;
import ru.streltsova.invitro.web.page.RadiologyPage;
import ru.streltsova.invitro.web.page.block.CityBlock;
import ru.streltsova.invitro.web.page.block.ItemBlock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class InvitroUITests extends BaseUIConfig {

    private static final Pattern COUNT_PATTERN = Pattern.compile("\"count\":(\\d+)");

    @Test
    public void clickAll() {
        new RadiologyPage().clickAllContent();
        System.out.println();
    }

    @Test
    public void testChangeCity_toOmsk() {
        final String newCity = "Омск";

        final CityBlock cityBlock = new RadiologyPage().getCityDiv();

        cityBlock.changeCityTo(newCity);
        assertEquals(newCity, cityBlock.getCityName());
    }

    @Test
    public void testGetAnalizeResults() {
        final long orderNumber = 231231231L;
        final String birthDate = "11.12.2000";
        final String lastName = "тест";

        new RadiologyPage()
                .getLabResultPage()
                .validateHeader()
                .searchResults()
                .errorAppeared()
                .orderNumber(orderNumber)
                .birthDate(birthDate)
                .lastName(lastName)
                .searchResults()
                .checkOrderNumberIs(orderNumber)
                .checkBirthDateIs(birthDate)
                .checkLastNameIs(lastName);
    }

    @Test
    public void testAddToCardAndRememberPrice() {
        final ItemBlock item = new AnalizesPage().getItem(0);
        final int priceFromList = Integer.parseInt(item.getPrice());

        if (priceFromList > 10000) {
            System.out.println("Больше 10000");
        } else if (priceFromList < 10000) {
            System.out.println("Меньше 10000");
        } else {
            fail("Равно 10000");
        }

        item.addToCart();

        Selenide.refresh();
        String state;
        do {
            state = Selenide.executeJavaScript("return document.readyState");
        } while (!"complete".equals(state));
        Selenide.sleep(5000L);

        final String scriptContent = Selenide.executeJavaScript(
                "return Array.from(document.querySelectorAll('script'))" +
                        ".find(script => script.innerText.includes('App.baskeModule.actualDataBasket')).innerText;");

        final Matcher matcher = COUNT_PATTERN.matcher(scriptContent);
        if (matcher.find()) {
            final int count = Integer.parseInt(matcher.group(1));
            assertEquals(2, count);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1616",
            "1414",
            "1515"
    })
    public void testSearchParameterized(String input) {
        assertDoesNotThrow(() -> new RadiologyPage().getSearchBlock().withInput(input).search());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Пациентам",
            "Врачам",
            "Франчайзинг",
            "Корпоративным клиентам",
            "Прессе"
    })
    public void testCheckMenu(String menuOption) {
        assertTrue(new RadiologyPage().clickForPatientsButton().inMenu(menuOption));
    }
}
