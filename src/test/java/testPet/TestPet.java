package testPet;

import endpoints.EndPoinPet;
import myModel.Category;
import myModel.Pet;
import myModel.TagPet;
import utilsAPI.ParseMethods;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPet extends BaseClass {

    private static File jsonSchema = new File("src/test/resources/petJsonSchema.json");

    @Test
    @DisplayName("Check to GET request")
    public void getRequestTest() {
        response = setRequestGet(EndPoinPet.STATUS + "?status=available");
        String actualStatus = ParseMethods.getJsonArrayFromResponse(response).get(5)
                .getAsJsonObject()
                .get("status")
                .getAsString();
        String expectedStatus = "available";
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    @DisplayName("Check to POST request")
    public void postRequestTest() {
        TagPet tag = new TagPet(25, "Bengal cat");
        Category category = new Category(1, "Cat");
        Pet pet = new Pet(1234, category, "Mouse", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), "available");
        setRequestPost(EndPoinPet.PET, pet)
                .then()
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));
    }

    @Test
    @DisplayName("Check to PUT request")
    public void putRequestTest() {
        TagPet tag = new TagPet(31, "Norwegian cat");
        Category category = new Category(3, "Cat");
        Pet pet = new Pet(1234, category, "Alice", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), "available");
        setRequestPut(EndPoinPet.PET, pet)
                .then()
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));
    }


    @Test
    @DisplayName("Check to Delete request")
    public void deleteRequestTest() {
        setRequestDelete(EndPoinPet.PET + "/1234");
        response = setRequestGet(EndPoinPet.PET + "/1234");
        response.prettyPrint();
    }
}



































