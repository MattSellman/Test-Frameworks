package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static utils.Base_Url.*;

public class GithubHttpRequestsSteps {

    @Given("^I create a \"([^\"]*)\" request to Github$")
    public void i_create_a_request_to_github(String requestType) throws IOException {
        if(requestType.equalsIgnoreCase("GET")) {
            get = HttpRequest.newBuilder(URI.create(BASE_URL_GITHUB))
                    .GET()
                    .setHeader("User-Agent", "Java 11 Http bot")
                    .build();
        } else if(requestType.equalsIgnoreCase("DELETE")) {
            delete = HttpRequest.newBuilder(URI.create(BASE_URL_GITHUB + "repos/mattsell/deleteme"))
                    .DELETE()
                    .setHeader("User-Agent", "Java 11 Http bot")
                    .build();
        }
    }

    @Given("^I \"([^\"]*)\" a \"([^\"]*)\" to Github with table$")
    public void i_create_a_request_with_table(String requestType, String arg, DataTable dataTable) throws IOException {
        if(requestType.equalsIgnoreCase("POST")) {
            itemsToLoad = dataTable.asMaps(String.class, String.class);
            for (int i = 0; i < itemsToLoad.size(); i++) {
                post = HttpRequest.newBuilder(URI.create(BASE_URL_GITHUB))
                        .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                                "    \"name\": \"" + itemsToLoad + "\",\n" +
                                "    \"job\": \"" + itemsToLoad + "\"\n" +
                                "}"))
                        .setHeader("User-Agent", "Java 11 Http bot")
                        .build();
            }
        } else if (requestType.equalsIgnoreCase("PUT")) {
            itemsToLoad = dataTable.asMaps(String.class, String.class);
            for (int i = 0; i < itemsToLoad.size(); i++) {
                put = HttpRequest.newBuilder(URI.create(BASE_URL_GITHUB))
                        .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                                "    \"name\": \"" + itemsToLoad + "\",\n" +
                                "    \"job\": \"" + itemsToLoad + "\"\n" +
                                "}"))
                        .setHeader("User-Agent", "Java 11 Http bot")
                        .build();
            }
        }
    }

    @When("^I run a \"([^\"]*)\" request to Github$")
    public void i_run_a_request_to_Jira(String requestType) throws Exception {
        if(requestType.equalsIgnoreCase("POST")) {
            response = httpClient.send(post, HttpResponse.BodyHandlers.ofString());
            System.out.println("Here: " + response);
            actualCode = response.statusCode();
            bodyJson = String.valueOf(response.body());
            System.out.println("StatusCode: " + actualCode);
        } else if(requestType.equalsIgnoreCase("PUT")) {
            response = httpClient.send(put, HttpResponse.BodyHandlers.ofString());
            actualCode = response.statusCode();
            System.out.println("StatusCode: " + actualCode);
        } else if(requestType.equalsIgnoreCase("GET")) {
            response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
            actualCode = response.statusCode();
            System.out.println("Request: " + response);
            System.out.println("Full response" + response.body());
        } else if(requestType.equalsIgnoreCase("DELETE")) {
            response = httpClient.send(delete, HttpResponse.BodyHandlers.ofString());
            actualCode = response.statusCode();
            System.out.println("StatusCode: " + actualCode);
        }
    }
}
