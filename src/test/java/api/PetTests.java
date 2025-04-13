package api;

import chursov.controllers.PetController;
import chursov.dto.Category;
import chursov.dto.Pet;
import chursov.dto.Tag;
import chursov.enums.Status;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

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

    @Test(description = "Get pet test")
    void getPetTest() throws InterruptedException {
        Pet pet = new Pet(new Category(0, "string"), "doggie",
                List.of("string"), List.of(new Tag(0, "string")), Status.available);

        Response createResponse = petController.createPet(pet);
        // 1 - use jsonPath
        long id = createResponse.getBody().jsonPath().getLong("id");
        // 2 - parsing json to java object, and then get the field
        System.out.println("Id = " + id);
        //creating the same pet twice because of defect
        Pet petWithId = new Pet(id, new Category(0, "string"), "doggie",
                List.of("string"), List.of(new Tag(0, "string")), Status.available);
        petController.createPet(petWithId);

        Response getResponse = petController.getPet(id);
        assertEquals(getResponse.statusCode(), 200);
    }

    @Test(description = "Delete pet test", groups = {"defect", "flaky"})
    void deletePetTest() throws InterruptedException {
        Pet pet = new Pet(new Category(0, "string"), "doggie",
                List.of("string"), List.of(new Tag(0, "string")), Status.available);

        Response createResponse = petController.createPet(pet);
        long id = createResponse.getBody().jsonPath().getLong("id");
        System.out.println("Id = " + id);
        Pet petWithId = new Pet(id, new Category(0, "string"), "doggie",
                List.of("string"), List.of(new Tag(0, "string")), Status.available);
        petController.createPet(petWithId);

        petController.getPet(petWithId.getId());
        Response response = petController.deletePet(petWithId.getId());
        assertEquals(response.statusCode(), 200);

        Response getResponse = petController.getPet(petWithId.getId());
        Thread.sleep(5000);
        assertEquals(getResponse.statusCode(), 404);
    }
}
