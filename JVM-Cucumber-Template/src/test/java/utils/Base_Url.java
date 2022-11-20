package utils;

import org.json.JSONObject;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Base_Url {
    public static final String BASE_URL_CAT = "https://catfact.ninja/fact";

    public static final String BASE_URL_NAME = "https://reqres.in/api/";
    public static final String BASE_URL_GITHUB = "https://api.github.com/";
    public static final String BASE_URL_JIRA = "http://localhost:8080/";
    public static final String BASE_URL_JENKINS = "http://localhost:8080/";
    public static String JIRA_TOKEN = "";

    public static HttpClient httpClient = HttpClient.newBuilder().build();
    public static HttpResponse<String> response;
    public static HttpRequest get;
    public static HttpRequest post;
    public static HttpRequest put;
    public static HttpRequest patch;
    public static HttpRequest delete;
    public static List<Map<String, String>> itemsToLoad;
    public static int actualCode;
    public static String bodyJson;



}

