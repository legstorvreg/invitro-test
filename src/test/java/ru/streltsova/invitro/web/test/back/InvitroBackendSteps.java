package ru.streltsova.invitro.web.test.back;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvitroBackendSteps {

    private static final String URL = "https://www.invitro.ru/local/ajax/current-city.php";

    private Response response;
    private String currentCode;

    @Допустим("я строю запрос с параметром \"CODE\" равным {string}")
    public void яСтроюЗапросСПараметромCODE(String code) {
        this.currentCode = code;
    }

    @Когда("я отправляю запрос")
    public void яОтправляюЗапрос() {
        response = RestAssured.given().header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36").header(new Header("Content-Type", "application/json")).queryParam("CODE", currentCode).log().all().when().get(URL);
    }

    @Тогда("я проверяю, что код ответа равен {int}")
    public void яПроверяюЧтоКодОтветаРавен(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode(), "Код ответа не совпадает с ожидаемым");
    }

    @И("я проверяю, что тело ответа содержит обязательные поля {string}, {string}, {string}")
    public void яПроверяюЧтоТелоОтветаСодержитОбязательныеПоля(String field1, String field2, String field3) {
        response.then().body("$", hasKey(field1)).body("$", hasKey(field2)).body("$", hasKey(field3));
    }
}
