Feature: Return a cat fact

Scenario: Cat Fact Printer
  Given I connect to the catFactsApi
  When I run a get request to the catFactsApi
  Then it returns a cat fact