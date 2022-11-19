using CSharp_HttpBuilder_Template.Helpers;

namespace CSharp_HttpBuilder_Template;

public class DeleteRequest
{
    private string _baseUrl;

    [SetUp]
    public void Setup()
    {
        _baseUrl = ApiPaths.BaseUrl;
    }

    [Test]
    public async Task ADeleteRequest()
    {
        HttpClient client = new HttpClient();
        
        var request = new HttpRequestMessage {
            Method = HttpMethod.Delete,
            RequestUri = new Uri(_baseUrl + "/users/2"),
        };
      
        var response = await client.SendAsync(request).ConfigureAwait(false);
        response.EnsureSuccessStatusCode();

        var responseBody = await response.Content.ReadAsStringAsync().ConfigureAwait(false);
        Console.WriteLine("ResponseContent: " + responseBody);
    }
}