using System.Net.Mime;
using System.Text;
using CSharp_HttpBuilder_Template.Helpers;

namespace CSharp_HttpBuilder_Template;

public class PutRequest
{
    private string _baseUrl;

    [SetUp]
    public void Setup()
    {
        _baseUrl = ApiPaths.BaseUrl;
    }

    [Test]
    public async Task APutRequest()
    {
        HttpClient client = new HttpClient();
        
        var request = new HttpRequestMessage {
            Method = HttpMethod.Post,
            RequestUri = new Uri(_baseUrl + "/users/2"), 
            Content = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("name", "john"),
                new KeyValuePair<string, string>("job", "leader")
            })
        };
      
        var response = await client.SendAsync(request).ConfigureAwait(false);
        response.EnsureSuccessStatusCode();
        Console.WriteLine("Response: " + response.Content);

        var responseBody = await response.Content.ReadAsStringAsync().ConfigureAwait(false);
        Console.WriteLine("ResponseContent: " + responseBody);
    }
}