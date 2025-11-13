
Feature: Urban Ladder shopping and filtering experience
Scenario: Search and view Being At Home categories
    Given I search for "being at home"
    When the search results are displayed
    Then I should see a list of categories under Being At Home