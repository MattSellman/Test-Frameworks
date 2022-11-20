Feature: Example Http Requests with Jira API

  Scenario Outline: Login Request
    Given I log in to Jira with post request
      | UserName   | Password   |
      | <username> | <password> |
    Then a token should be returned

    Examples:
      | username     | password   |
      | matt.sellman | 1234567890 |

  Scenario Outline: Post Request
    Given I "post" a "bug ticket" to Jira with table
      | Key   | Summary   | Description   | Name   |
      | <key> | <summary> | <description> | <name> |
    When I run a "post" request to Jira
    Then the status returns "403"
#    And I print from the response using key ""

    Examples:
      | key | summary                    | description            | name |
      | MSL | Issue 5 for adding comment | Creating my second bug | Bug  |

  Scenario: Get Request
    Given I create a "get" request to Jira
    When I run a "get" request to Jira
    Then the status returns "403"
#    And I print from the response using key ""

  Scenario Outline: Put Request
    Given I "put" a "bug ticket" to Jira with table
      | Key   | Summary   | Description   | Name   |
      | <key> | <summary> | <description> | <name> |
    When I run a "put" request to Jira
    Then the status returns "403"
#    And I print from the response using key "id"

    Examples:
      | key | summary                    | description            | name |
      | MSL | Issue 5 for adding comment | Creating my second bug | Bug  |

  Scenario: Delete Request
    Given I create a "delete" request to Jira
    When I run a "delete" request to Jira
    Then the status returns "403"

