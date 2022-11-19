using SpecFlowTemplate.Helpers;

namespace SpecFlowTemplate;

using RestSharp;
using NUnit.Framework;
using FluentAssertions;
using Newtonsoft.Json;
using System.Collections.Generic;
using System;
using System.Net;

[Binding]
public class ExampleHttpRequestSteps
{
    private RestClient client = new RestClient();
    private RestRequest request = new RestRequest();
    private RestResponse response = new RestResponse();
    
    [Given("I create a request")]
    public void GivenARequestIsCreated()
    { 
        client = new RestClient(APIPaths.BaseUrl);
    }

    [When("I run a get request")]
    public void WhenAGetRequestIsRun()
    {
        request = new RestRequest("/users?page=2", Method.Get);
    }
    
    [When("I run a post request")]
    public void WhenAPostRequestIsRun()
    {
        request = new RestRequest("users", Method.Post);
        request.AddParameter("application/json; charset=utf-8", ParameterType.RequestBody);
        request.RequestFormat = DataFormat.Json;
        request.AddBody(
            "    \"name\": \"" + "Morpheus" + "\",\n" +
            "    \"job\": \"" + "Leader" + "\"\n" + "}"
        );
    }
    
    [When("I run a put request")]
    public void WhenAPutRequestIsRun()
    {
        request = new RestRequest("users/2", Method.Put);
        request.AddParameter("application/json; charset=utf-8", ParameterType.RequestBody);
        request.RequestFormat = DataFormat.Json;
        request.AddBody(
            "    \"name\": \"" + "Morpheus" + "\",\n" +
            "    \"job\": \"" + "Leader" + "\"\n" + "}"
            );
        } 
    
    [When("I run a delete request")]
    public void WhenADeleteRequestIsRun()
    {
        request = new RestRequest("users/2", Method.Delete);
    } 
    

    [Then("it returns (.*)")]
    public async Task ThenTheResultShouldBe(String expectedResult)
    {   
        response = await client.ExecuteAsync(request);
        var actualCode = response.StatusCode;

        // response.StatusCode.Should().Be(HttpStatusCode.OK);
        // Assert.AreEqual(HttpStatusCode.OK, actualCode);
        
       
        Console.WriteLine("Actual code is: " + actualCode);  // OK(get/put) or Created(post) or NoContent(delete)
    }

    [Then(@"I print the response array")]
    public void ThenIPrintTheResponseArray()
    {
        Console.WriteLine("Response is: " + response.Content); // full response
        // then pull certain field from response 

//        var fullResponse = JsonConvert.DeserializeObject<List>(response.Content);
//        fullResponse.Should().NotBeNull();
//        Console.WriteLine("Full Response" + fullResponse);
        
        

        // for arrays (List within the above so <List<OtherList>>
//         foreach (var item in fullResponse)
//        {
            // items in the array can be laid out below:
//            item.HasTechRadarAccess.Should().Be(true);
//        }
    }
}