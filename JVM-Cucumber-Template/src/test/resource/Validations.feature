Feature: Validations

  @AddPlace @Regression
  Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
    Given Add Place Payload with "<name>",  "<language>" and "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the API call got success with status code "200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      |name 	  | language | address		     |
      | BuildingA | English  | Land cross center |
	  | BuildingB | Spanish  | Sea cross center  |

  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call got success with status code "200"
    And "status" in response body is "OK"
















