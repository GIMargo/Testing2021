Feature: HW3 EX2

  Background:
    Given Test site is opened
    And I logged in as "ROMAN IOVLEV"

  Scenario: Assert Browser title
    Then Browser title is "Home Page"

  Scenario: Assert User name
    Then User name is "ROMAN IOVLEV"
    And Browser title is "Home Page"

  Scenario: Assert "Service" subcategory in the header
    Then There are 9 elements with proper texts in the header

  Scenario: Assert "Service" subcategory in the left section
    Then There are 9 elements with proper texts in the left section

  Scenario: Assert interface of Different Elements page
    When The "Different Elements" page is opened
    Then There are 4 checkboxes and radios and 2 buttons and 1 dropdown

  Scenario: Assert that there is Right Section and Left Section
    When The "Different Elements" page is opened
    Then There are right and left sections

  Scenario: Select checkboxes
    When The "Different Elements" page is opened
    Then Checkboxes are selected
    Then Log rows of checkboxes are displayed and correct
    Then Unselect checkboxes
    Then Log rows of unselected checkboxes are displayed and correct

  Scenario: Select radios
    When The "Different Elements" page is opened
    Then Selen is selected
    Then Log rows of radios are displayed and correct

  Scenario: Select in dropdown
    When The "Different Elements" page is opened
    Then Yellow is selected
    Then Log rows of dropdown are displayed and correct

