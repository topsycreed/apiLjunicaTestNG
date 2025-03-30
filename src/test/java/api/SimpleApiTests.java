package api;

import chursov.dto.Pet;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SimpleApiTests {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_ENDPOINT = "/pet";

    @Test
    void createPetTest() {
        String bodyAsText = """
                {
                    "id": 0,
                    "category": {
                        "id": 0,
                        "name": "string"
                    },
                    "name": "doggie",
                    "photoUrls": [
                        "string"
                    ],
                    "tags": [
                        {
                            "id": 0,
                            "name": "string"
                        }
                    ],
                    "status": "available"
                }""";

        Response response = given().baseUri(BASE_URL)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .body(bodyAsText)
                .post(PET_ENDPOINT)
                .andReturn();

        assertEquals(200, response.getStatusCode());
    }

    @Test
    void createPetv2Test() {
        String bodyAsText = """
                {
                    "id": 0,
                    "category": {
                        "id": 0,
                        "name": "string"
                    },
                    "name": "cattie",
                    "photoUrls": [
                        "string"
                    ],
                    "tags": [
                        {
                            "id": 0,
                            "name": "string"
                        }
                    ],
                    "status": "available"
                }""";

        Response response = given().baseUri(BASE_URL)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .body(bodyAsText)
                .post(PET_ENDPOINT)
                .andReturn();
        response.prettyPrint();
        Pet createdPet = response.as(Pet.class);

        assertEquals(200, response.getStatusCode());
        assertEquals("cattie", createdPet.getName());
        assertEquals("available", createdPet.getStatus());
//        assertEquals(9223372036854775807L, createdPet.getId());
        assertTrue(createdPet.getId() > 9223372036854763530L);
        assertTrue(createdPet.getId() < Long.MAX_VALUE);
    }

    @Test
    void updatePetTest() {

    }

    @Test
    void getPetTest() {

    }

    @Test
    void deletePetTest() {

    }
}
