Feature: Defect Status

  Background: Tester is logged in
    Given Tester Login

  Scenario: Change Status
    Given The tester is on the Home Page
    Then The tester can can see only defects assigned to them
    When The tester changes the defect to any status
    Then The tester should see the defect has a different status