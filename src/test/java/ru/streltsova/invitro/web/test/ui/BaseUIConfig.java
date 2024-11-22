package ru.streltsova.invitro.web.test.ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseUIConfig {

    @BeforeEach
    public void setUp() {
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
        Configuration.browserVersion = "131.0.6778.86";
        Configuration.pageLoadTimeout = 60000;
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
