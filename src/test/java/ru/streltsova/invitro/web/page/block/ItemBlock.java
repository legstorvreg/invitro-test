package ru.streltsova.invitro.web.page.block;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.clickable;

public class ItemBlock {

    private final SelenideElement buyItemBlock;

    public ItemBlock(SelenideElement itemBlock) {
        this.buyItemBlock = itemBlock.$x(".//*[@class='analyzes-item__total']");
    }

    public String getPrice() {
        return buyItemBlock.$x(".//*[@class='analyzes-item__total--sum']").text().replace(" ₽", "");
    }

    public ItemBlock addToCart() {
        buyItemBlock.$x(".//*[contains(@href, '/analizes/for-doctors/')]").shouldBe(clickable).click();
        return this;
    }
}
