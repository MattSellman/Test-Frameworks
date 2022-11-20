package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newBuilder;
import static utils.Base_Url.*;
import static utils.Common_Methods.getValueFor;
import static utils.objects.CatFacts.Facts.FACT;

public class CatFactSteps {

    HttpResponse<String> response;

    @Given("^I connect to the catFactsApi$") // Arrange
    public void i_connect_to_the_catFactsApi() throws Exception {
        httpClient = newBuilder().build();
        get = HttpRequest.newBuilder(URI.create(BASE_URL_CAT))
                .GET()
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
    }

    @When("^I run a get request to the catFactsApi$") // Act
    public void i_make_a_get_request() throws Exception {
        response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
    }

    @Then("^it returns a cat fact$") // Assert
    public void it_returns_a_cat_fact() throws Exception {
        String jsonBody = response.body();
        JSONObject jsonObject = new JSONObject(jsonBody);

        String fact = (String) getValueFor(jsonObject, FACT);
        System.out.println("Cat Fact: " + fact);
    }
}
