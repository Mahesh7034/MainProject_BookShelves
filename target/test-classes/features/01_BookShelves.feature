
Feature: Urban Ladder shopping and filtering experience
 
  As a user of the Urban Ladder website
  I want to browse, filter, and purchase items
  So that I can find products that match my preferences
 
  
  Scenario: Filter the products by storage,price and availability
    Given I open the Urban Ladder website
    And I navigate to the "Living" section
    When I click on the "Bookshelves" category
    And I close the popup modal if it appears
    And I apply the "Open" storage type filter
    And I adjust the price range using the slider  
    And I select the "In Stock Only" checkbox
    Then I should see the top 3 bookshelves displayed with their names and prices