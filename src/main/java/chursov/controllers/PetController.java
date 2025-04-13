package chursov.controllers;

import chursov.dto.Pet;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetController {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final String PET_ENDPOINT = "/pet";

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

    @Step("Create a new pet to the store")
    public Response createPet(Pet pet) {
        return requestSpecification.given()
                .when()
                .body(pet)
                .post(PET_ENDPOINT)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Get a pet by their id")
    public Response getPet(long id) {
        return requestSpecification.given()
                .when()
                .get(PET_ENDPOINT + "/" + id)
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Delete a pet (because now he has owner!)")
    public Response deletePet(long id) {
        return requestSpecification.given()
                .when()
                .delete(PET_ENDPOINT + "/" + id)
                .then()
                .log().all()
                .extract().response();
    }
}
