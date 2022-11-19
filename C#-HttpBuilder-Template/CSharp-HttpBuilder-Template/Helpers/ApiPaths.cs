namespace CSharp_HttpBuilder_Template.Helpers;

public class ApiPaths
{
    public const string BaseUrl = "https://reqres.in/api";

    public static WinHttpHandler handler = new WinHttpHandler();
    public HttpClient client = new HttpClient(handler);
    // needs the above two for the code to work - need to import these into the file
}