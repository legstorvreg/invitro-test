package ru.streltsova.invitro.web.page;

import com.codeborne.selenide.ElementsCollection;
import ru.streltsova.invitro.web.page.block.ItemBlock;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class AnalizesPage {

    private final ElementsCollection items = $$x("//*[@class='pagination-items']/div[not(@data-section-id)]");

    public AnalizesPage() {
        open("https://www.invitro.ru/analizes/for-doctors/");
    }

    public ItemBlock getItem(int index) {
        return new ItemBlock(items.get(index));
    }
}
