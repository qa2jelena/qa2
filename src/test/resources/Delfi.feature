Feature: Testing Delfi page

  Scenario: Second Delfi test
    Given Article title for test: Собчак разъяснила свои слова о поддержке антироссийских санкций
    When Open web home page
    And Open mob home page
    And Get article by title on web home page
    And Get article comment count on web home page
    And Get article by title on mob home page
    And Get article comment count on mob home page
    Then Compare article comment counts on web and mob home page
    And Quit browser windows