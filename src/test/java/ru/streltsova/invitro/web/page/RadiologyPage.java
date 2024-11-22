package ru.streltsova.invitro.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.streltsova.invitro.web.page.block.CityBlock;
import ru.streltsova.invitro.web.page.block.ContentBlock;
import ru.streltsova.invitro.web.page.block.SearchBlock;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class RadiologyPage {

    private final CityBlock cityBlock = new CityBlock();
    private final SearchBlock searchBlock = new SearchBlock();
    private final ContentBlock contentBlock = new ContentBlock();

    private final SelenideElement labResultButton = $x("//*[@class='invitro_header-bottom_right']/*[@class='invitro_header-get_result']");

    private final SelenideElement forPatientsButton = $x("//*[@id='buttonOpenPopupTargetSTATICSTRINGFORID']");
    private final SelenideElement forPatientsMenu = $x("//*[@id='popupTargetSTATICSTRINGFORID']");
    private final ElementsCollection forPatientsOptions = forPatientsMenu.$$x(".//span");

    public RadiologyPage() {
        open("https://www.invitro.ru/svetliy/radiology/");
    }

    public CityBlock getCityDiv() {
        return cityBlock;
    }

    public LabResultPage getLabResultPage() {
        labResultButton.click();
        return new LabResultPage();
    }

    public SearchBlock getSearchBlock() {
        return searchBlock;
    }

    public RadiologyPage clickForPatientsButton() {
        forPatientsButton.click();
        forPatientsMenu.should(appear);
        return this;
    }

    public boolean inMenu(String menuOption) {
        return forPatientsOptions.stream().anyMatch(option -> option.has(text(menuOption)));
    }

    public void clickAllContent() {
        contentBlock.clickAllContent();
    }
}
