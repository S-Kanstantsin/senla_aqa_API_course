package utilsAPI;

import com.google.gson.*;
import io.restassured.response.Response;
import static com.google.gson.JsonParser.parseString;

public class ParseMethods {
    public static JsonArray getJsonArrayFromResponse(Response res){
        return parseString(res.getBody().asString()).getAsJsonArray();
    }
}
