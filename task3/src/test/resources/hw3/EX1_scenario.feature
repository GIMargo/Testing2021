Feature: HW3 EX1

  Background:
     Given Test site is opened 
     And I logged in as "ROMAN IOVLEV"
    
  Scenario: Assert Browser title
     Then Browser title is "Home Page"

  Scenario: Assert User name
     Then User name is "ROMAN IOVLEV"
     And Browser title is "Home Page"

  Scenario: Assert 4 items on the header section
     Then There are 4 items on the header section
     And These items have proper texts 
     And These items are displayed

  Scenario: Assert 4 images on the Index Page
     Then There are 4 images on the Index Page
     And All images are displayed

  Scenario: Assert 4 texts on the Index Page
    Then There are 4 texts on the Index Page
    And All texts are displayed
    And Texts are proper

  Scenario: Assert a text of the main headers
    Then Texts of the main headers are displayed
    And Texts of the main headers are proper

  Scenario: Assert the iframe in the center of page
    Then IFrame is displayed
    When I switch to the iframe
    Then There is epam-logo
    And I switch to original window back

  Scenario: Assert the sub header
    Then The text of the sub header is displayed
    And The text of the sub header is "JDI GITHUB"
    And The sub header is a link
    And The URL is "https://github.com/epam/JDI"

  Scenario: Assert the Left Section and Footer
    Then There is a Left Section
    And There is a Footer
    