package testPet;

import endpoints.EndPoinPet;
import org.junit.jupiter.api.BeforeAll;
import  utilsAPI.SetRequest;

import static io.restassured.RestAssured.given;


public class BaseClass extends SetRequest {

    @BeforeAll
    public void setUp(){
        response = given()
                .spec(requestSpecification)
                .when()
                .get(EndPoinPet.PET);
    }

}
