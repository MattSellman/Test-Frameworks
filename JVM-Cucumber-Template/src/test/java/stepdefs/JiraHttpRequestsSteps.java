package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static utils.Base_Url.*;
import static utils.Common_Methods.*;

public class JiraHttpRequestsSteps {
    @Given("^I log in to Jira with post request$")
    public void i_log_in_to_Jira_with_post_request(DataTable dataTable) {
        itemsToLoad = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < itemsToLoad.size(); i++) {
            post = HttpRequest.newBuilder(URI.create(BASE_URL_JIRA + "rest/auth/1/session"))
                    .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                            "    \"username\": \"" + itemsToLoad + "\",\n" +
                            "    \"password\": \"" + itemsToLoad + "\"\n" +
                            "}"))
                    .setHeader("User-Agent", "Java 11 Http bot")
                    .build();

        }
    }

    @Then("^a token should be returned$")
    public void i_should_be_logged_in() throws Exception {
        response = httpClient.send(post, HttpResponse.BodyHandlers.ofString());
        System.out.println("Here: " + response);
        actualCode = response.statusCode();

        String jsonBody = response.body();
        System.out.println(jsonBody);
        JSONObject jsonObject = new JSONObject(jsonBody);

//        String sessionToken = getJsonArray(jsonObject, "session");
//        System.out.println("Session Token: " + sessionToken);
//        JIRA_TOKEN = sessionToken;

        System.out.println("Full response: " + bodyJson);
        System.out.println("StatusCode: " + actualCode);
    }

    @Given("^I create a \"([^\"]*)\" request to Jira$")
    public void i_create_a_request_to_Jira(String requestType) throws IOException {
        if (requestType.equalsIgnoreCase("GET")) {
            get = HttpRequest.newBuilder(URI.create(BASE_URL_JIRA + "rest/api/3/issue/MSL-1"))
                    .GET()
                    .setHeader("Content-Type", "application/json")
                    .setHeader("Cookie", "JSESSIONID=" + JIRA_TOKEN)
                    .build();
        } else if (requestType.equalsIgnoreCase("DELETE")) {
            delete = HttpRequest.newBuilder(URI.create(BASE_URL_JIRA + "rest/api/3/issue/MSL-1"))
                    .DELETE()
                    .setHeader("Content-Type", "application/json")
                    .build();
        }
    }

    @Given("^I \"([^\"]*)\" a \"([^\"]*)\" to Jira with table$")
    public void i_create_a_request_with_table(String requestType, String arg, DataTable dataTable) throws IOException {
        if (requestType.equalsIgnoreCase("POST")) {
            itemsToLoad = dataTable.asMaps(String.class, String.class);
            for (int i = 0; i < itemsToLoad.size(); i++) {
                post = HttpRequest.newBuilder(URI.create(BASE_URL_JIRA + "/rest/api/2/issue"))
                        .POST(HttpRequest.BodyPublishers.ofString("{" +
                                "\"fields\": {" +
                                "\"project\":{" +
                                "\"key\": \"" + itemsToLoad + "\"" +
                                "}," +
                                "\"summary\": \"" + itemsToLoad + "\"," +
                                "\"description\": \"" + itemsToLoad + "\"," +
                                "\"issuetype\": {" +
                                "\"name\": \"" + itemsToLoad + "\"" +
                                "}" +
                                "}}"))
                        .setHeader("User-Agent", "Java 11 Http bot")
                        .header("Cookie", "JSESSIONID=" + JIRA_TOKEN)
                        .build();
            }
        } else if (requestType.equalsIgnoreCase("PUT")) {
            itemsToLoad = dataTable.asMaps(String.class, String.class);
            for (int i = 0; i < itemsToLoad.size(); i++) {
                put = HttpRequest.newBuilder(URI.create(BASE_URL_JIRA + "/rest/api/2/issue"))
                        .PUT(HttpRequest.BodyPublishers.ofString("{" +
                                "\"fields\": {" +
                                "\"project\":{" +
                                "\"key\": \"" + itemsToLoad + "\"" +
                                "}," +
                                "\"summary\": \"" + itemsToLoad + "\"," +
                                "\"description\": \"" + itemsToLoad + "\"," +
                                "\"issuetype\": {" +
                                "\"name\": \"" + itemsToLoad + "\"" +
                                "}" +
                                "}}"))
                        .setHeader("User-Agent", "Java 11 Http bot")
                        .header("Cookie", "JSESSIONID=" + JIRA_TOKEN)
                        .build();
            }
        }
    }

    @When("^I run a \"([^\"]*)\" request to Jira$")
    public void i_run_a_request_to_Jira(String requestType) throws Exception {
        if (requestType.equalsIgnoreCase("POST")) {
            response = httpClient.send(post, HttpResponse.BodyHandlers.ofString());
            System.out.println("Here: " + response);
            actualCode = response.statusCode();
            bodyJson = String.valueOf(response.body());
            System.out.println("StatusCode: " + actualCode);
        } else if (requestType.equalsIgnoreCase("PUT")) {
            response = httpClient.send(put, HttpResponse.BodyHandlers.ofString());
            actualCode = response.statusCode();
            System.out.println("StatusCode: " + actualCode);
        } else if (requestType.equalsIgnoreCase("GET")) {
            response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
            actualCode = response.statusCode();
            System.out.println("Request: " + response);
        } else if (requestType.equalsIgnoreCase("DELETE")) {
            response = httpClient.send(delete, HttpResponse.BodyHandlers.ofString());
            actualCode = response.statusCode();
            System.out.println("StatusCode: " + actualCode);
        }
    }
}