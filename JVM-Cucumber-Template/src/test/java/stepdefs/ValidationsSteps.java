package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import utils.PlaceAPITesting.TestData;
import utils.objects.PlaceAPITesting.Resources;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.PlaceAPITesting.ResponseUtils.getJsonPath;
import static utils.PlaceAPITesting.ResponseUtils.requestSpecification;

public class ValidationsSteps {
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    Response response;
    TestData data = new TestData();
    static String place_id;


    @Given("^Add Place Payload with \"([^\"]*)\",  \"([^\"]*)\" and \"([^\"]*)\"$")
    public void add_Place_Payload_with(String name, String language, String address) throws IOException {
        requestSpec = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));
    }

    @When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void user_calls_with_http_request(String resource, String method) {
        Resources resourceAPI = Resources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST"))
            response = requestSpec.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = requestSpec.when().get(resourceAPI.getResource());

    }

    @Then("^the API call got success with status code \"([^\"]*)\"$")
    public void the_API_call_got_success_with_status_code(int statusCode) {
        assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void in_response_body_is(String keyValue, String Expectedvalue) {
        assertEquals(getJsonPath(response, keyValue), Expectedvalue);
    }

    @Then("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        requestSpec = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @Given("DeletePlace Payload")
    public void deleteplace_Payload() throws IOException {
        requestSpec = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

    @Given("Oauth Example")
    public void oAuthExample() {
        String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";

        String partialcode = url.split("code=")[1];

        String code = partialcode.split("&scope")[0];

        System.out.println(code);

        String response =

                given()

                        .urlEncodingEnabled(false)

                        .queryParams("code", code)


                        .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

                        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

                        .queryParams("grant_type", "authorization_code")

                        .queryParams("state", "verifyfjdss")

                        .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

                        // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")


                        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

                        .when().log().all()

                        .post("https://www.googleapis.com/oauth2/v4/token").asString();

// System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);

        String accessToken = jsonPath.getString("access_token");

        System.out.println(accessToken);

        String r2 = given().contentType("application/json").

                queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)

                .when()

                .get("https://rahulshettyacademy.com/getCourse.php")

                .asString();

        System.out.println(r2);
    }
}
