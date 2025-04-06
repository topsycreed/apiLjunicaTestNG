package api;

import chursov.controllers.PetController;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PetTests {
    PetController petController = new PetController();

    @Test(description = "Create pet test")
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

        Response response = petController.createPet(bodyAsText);

        assertEquals(response.getStatusCode(), 200);
    }
}
