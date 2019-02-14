Feature: Ali search


  Scenario: Search item in post
    Given I opened belpost site
    When I search LA023558415CN
    Then I read item info and make decision what info i should collect