package ru.streltsova.invitro.web.page.block;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SearchBlock {

    private final SelenideElement searchInput = $x("//*[@name='q']");
    private final SelenideElement searchButton = $x("//*[contains(@class, 'search__btn')]");

    public SearchBlock withInput(String input) {
        searchInput.setValue(input);
        return this;
    }

    public SearchBlock search() {
        searchButton.click();
        return this;
    }
}
