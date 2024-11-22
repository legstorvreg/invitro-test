package ru.streltsova.invitro.web.page.block;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.back;

public class ContentBlock {

    private final SelenideElement contentBlock = $x("//*[@class='content__row']");
    private final SelenideElement popUpCloseButton = $x("//*[contains(@class, 'popmechanic-reset')]/*[@class='popmechanic-close']");

    public void clickAllContent() {
        for (int i = 0; i < getLinks().size(); i++) {
            if (i == 5 || i > 6) {
                continue;
            }
            if (popUpCloseButton.is(appear)) {
                popUpCloseButton.click();
            }
            getLinks().get(i).should(exist).shouldBe(visible).click();
            if (!getSubLinks().isEmpty()) {
                for (int j = 0; j < getSubLinks().size(); j++) {
                    if (popUpCloseButton.is(appear)) {
                        popUpCloseButton.click();
                    }
                    getSubLinks().get(j).should(exist).shouldBe(visible).click();
                }
            }
            back();
        }
    }

    private ElementsCollection getLinks() {
        return contentBlock.$$x(".//a").filterBy(visible).filterBy(clickable);
    }

    private ElementsCollection getSubLinks() {
        return $$x("//*[@id='subMenuCatalogPrepare']//a");
    }
}
