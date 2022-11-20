Feature: Example Http Requests with Github API

  Scenario: Get Request
    Given I create a "get" request to Github
    When I run a "get" request to Github
    Then the status returns "200"

  Scenario: Delete Request
    Given I create a "delete" request to Github
    When I run a "delete" request to Github
    Then the status returns "404"