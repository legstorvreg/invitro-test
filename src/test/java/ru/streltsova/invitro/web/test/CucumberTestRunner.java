package ru.streltsova.invitro.web.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "ru/streltsova/invitro/web/test/back"
)
public class CucumberTestRunner {
}
