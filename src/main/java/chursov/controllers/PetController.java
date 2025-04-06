package chursov.controllers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetController {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_ENDPOINT = "/pet";

    RequestSpecification requestSpecification = given();

    public PetController() {
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.log().all();
    }

    @Step("Create a new pet to the store")
    public Response createPet(String body) {
        return requestSpecification.given()
                .when()
                .body(body)
                .post(PET_ENDPOINT)
                .then()
                .log().all()
                .extract().response();
    }
}
