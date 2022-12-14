package utils.PlaceAPITesting;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class ResponseUtils {
    public static RequestSpecification requestSpec;
    public static RequestSpecification requestSpecification() throws IOException
    {

        if(requestSpec == null)
        {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpec;
        }
        return requestSpec;
    }

    public static String getGlobalValue(String key) throws IOException
    {
        Properties prop =new Properties();
        FileInputStream fis =new FileInputStream("/Users/mattsellman2/bitbucket/JVM-Cucumber-Template/src/test/java/utils/objects/PlaceAPITesting/global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public static String getJsonPath(Response response, String key)
    {
        String jsonResponse = response.asString();
        JsonPath   js = new JsonPath(jsonResponse);
        return js.get(key).toString();
    }
}
