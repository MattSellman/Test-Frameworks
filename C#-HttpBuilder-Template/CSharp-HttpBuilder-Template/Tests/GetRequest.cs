using CSharp_HttpBuilder_Template.Helpers;

namespace CSharp_HttpBuilder_Template;

using System.Net.Mime;
using System.Text;
using NUnit.Framework;
// using FluentAssertions;
using System.Collections.Generic;
using System;
using System.Net;


public class GetRequest
{
    private string _baseUrl;

    [SetUp]
    public void Setup()
    {
        _baseUrl = ApiPaths.BaseUrl;
    }

    [Test]
    public async Task AGetRequest()
    {
        HttpClient client = new HttpClient();
        
        var request = new HttpRequestMessage {
            Method = HttpMethod.Get,
            RequestUri = new Uri(_baseUrl + "/users?page=2"),
        };
      
        var response = await client.SendAsync(request).ConfigureAwait(false);
        response.EnsureSuccessStatusCode();
        Console.WriteLine("Response: " + response.Content);

        var responseBody = await response.Content.ReadAsStringAsync().ConfigureAwait(false);
        Console.WriteLine("ResponseContent: " + responseBody);
    }
}