Feature: Example Http Requests
![Calculator](https://specflow.org/wp-content/uploads/2020/09/calculator.png)
Simple calculator for adding **two** numbers

Link to a feature: [Calculator]($projectname$/Features/Calculator.feature)
***Further read***: **[Learn more about how to generate Living Documentation](https://docs.specflow.org/projects/specflow-livingdoc/en/latest/LivingDocGenerator/Generating-Documentation.html)**

Scenario: Get Request
    Given I create a request
    When I run a get request
    Then it returns "200"
    And I print the response array

    Scenario: Post Request
        Given I create a request
        When I run a post request
        Then it returns "201"
        # And I print the int value from the response using key "id"

    Scenario: Put Request
        Given I create a request
        When I run a put request
        Then it returns "200"
        #And I print the value from the response using key "updatedAt"

    Scenario: Delete Request
        Given I create a request
        When I run a delete request
        Then it returns "204"






    # @mytag
    #Scenario: Add two numbers
    #	Given the first number is 50
    #	And the second number is 70
    #	When the two numbers are added
    #	Then the result should be 120