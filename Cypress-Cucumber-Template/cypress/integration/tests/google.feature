Feature: Google Search
  
        @google
        Scenario: Performing Google Search
            Given I open Google
             Then I see "Google" in the title
              And I Search "apple"