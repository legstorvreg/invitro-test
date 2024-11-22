package ru.streltsova.invitro.web.page.block;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CityBlock {

    private final SelenideElement mainButton = $x("//*[contains(@class, 'city__btn')]");
    private final SelenideElement chooseOtherCityButton = $x("//*[contains(@class, 'btn--empty')]");
    private final SelenideElement changeCityInput = $x("//*[@class='change-city-search-input']");
    private final SelenideElement cityAutoCompleteElement = $x("//*[@id='eac-container-select-basket-city-input']");

    public void changeCityTo(String city) {
        mainButton.shouldBe(clickable).click();
        chooseOtherCityButton.shouldBe(clickable).click();
        changeCityInput.shouldBe(visible).setValue(city);
        cityAutoCompleteElement.$x(".//*[@class='eac-item' and contains(., '%s')]".formatted(city)).should(exist).click();
    }

    public String getCityName() {
        return mainButton.getText();
    }
}
