package ru.netology.rest;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


class MobileBankApiTestV1 {
    @Test
    @DisplayName("Testing status code 200")
    void shouldReturnDemoAccounts200Test() {
      // Given - When - Then
      // Предусловия
      given()
          .baseUri("http://localhost:9999/api/v1")
      // Выполняемые действия
      .when()
          .get("/demo/accounts")
      // Проверки
      .then()
          .statusCode(200);
    }

    @Test
    @DisplayName("Testing JSON Schema is valid")
    void shouldReturnJSONSchemaValid2Test() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }

    @Test
    @DisplayName("Testing Content Type is JSON")
    void shouldReturnContentTypeJSON3Test() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .contentType(ContentType.JSON);
    }

    // Чисто на всякий случай на память
    // .header("Content-Type", "application/json; charset=UTF-8")
    // специализированные проверки - лучше   .contentType(ContentType.JSON)

    @Test
    @DisplayName("Testing right number of objects")
    void shouldReturnNumberOfObjects4Test() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .body("",hasSize(3));
    }

    @Test
    @DisplayName("Testing currency value")
    void shouldReturnCurrency5Test() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .body("[0].currency",equalTo("RUB"));
    }

    @Test
    @DisplayName("Testing positive balance true")
    void shouldReturnPositiveBalance6Test() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .body("every{ it.balance >= 0 }", Matchers.is(true));
    }

}
