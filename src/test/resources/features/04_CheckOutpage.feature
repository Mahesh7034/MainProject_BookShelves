
Feature: Urban Ladder shopping and filtering experience

Scenario: Add an item to cart and proceed to checkout
    Given click on a specific Being At Home carpet item
    When I add the item to the cart
    And I proceed to checkout
    And I enter an invalid email address
    Then I should see an error message indicating invalid email