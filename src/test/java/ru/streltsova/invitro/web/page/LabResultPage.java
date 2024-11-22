package ru.streltsova.invitro.web.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$x;

public class LabResultPage {

    private final SelenideElement headerElement = $x("//h2");
    private final SelenideElement searchResultsButton = headerElement.parent().$x("./div[@class='']/button");
    private final SelenideElement orderNumberInput = $x("//input[@type='text' and (@name='orderNumber')]");
    private final SelenideElement birthdayInput = $x("//input[@type='text' and (@name='birthday')]");
    private final SelenideElement lastNameInput = $x("//input[@type='text' and (@name='lastName')]");

    public LabResultPage validateHeader() {
        headerElement.should(exist).shouldHave(text("Введите индивидуальный номер заказа, чтобы посмотреть результаты анализов"));
        return this;
    }

    public LabResultPage searchResults() {
        searchResultsButton.shouldBe(clickable).click();
        return this;
    }

    public LabResultPage errorAppeared() {
        shouldBeRed(orderNumberInput);
        shouldBeRed(birthdayInput);
        shouldBeRed(lastNameInput);
        headerElement.parent().$x("./div[contains(@class, 'error')]").shouldHave(text("Поля Код ИНЗДата рожденияФамилия обязательны для заполнения"));
        return this;
    }

    public LabResultPage orderNumber(Long orderNumber) {
        orderNumberInput.setValue(orderNumber.toString());
        return this;
    }

    public LabResultPage birthDate(String birthDate) {
        birthdayInput.setValue(birthDate);
        return this;
    }

    public LabResultPage lastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public LabResultPage checkOrderNumberIs(Long orderNumber) {
        orderNumberInput.shouldHave(value(orderNumber.toString()));
        return this;
    }

    public LabResultPage checkBirthDateIs(String birthDate) {
        birthdayInput.shouldHave(value(birthDate));
        return this;
    }

    public LabResultPage checkLastNameIs(String lastName) {
        lastNameInput.shouldHave(value(lastName));
        return this;
    }

    private void shouldBeRed(SelenideElement element) {
        element.shouldHave(cssValue("border-color", "rgb(255, 0, 0)"), Duration.ofSeconds(5));
    }
}
