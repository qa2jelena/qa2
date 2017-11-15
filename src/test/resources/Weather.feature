Feature: WeatherTest

  Scenario: Testing weather

    Given City name for test is London

    When Requesting weather information

    Then City geo location is:
      | longitude | -0.13 |
      | latitude  | 51.51 |

    And Weather parameters are:
      | weather condition id               | 300                     |
      | group of weather parameters        | Drizzle                 |
      | weather condition within the group | light intensity drizzle |
      | weather icon id                    | 09d                     |

    And Base parameter is stations

    And Main weather parameters are:
      | temperature          | 280.32 |
      | atmospheric pressure | 1012   |
      | humidity             | 81     |
      | minimum temperature  | 279.15 |
      | maximum temperature  | 281.15 |

    And Visibility is 10000

    And Wind parameters are:
      | speed     | 4.1 |
      | direction | 80  |

    And Cloudiness is 90

    And Time of data calculation is 1485789600

    And Sys parameters are:
      | type         | 1          |
      | id           | 5091       |
      | message      | 0.0103     |
      | country code | GB         |
      | sunrise time | 1485762037 |
      | sunset time  | 1485794875 |

    And City ID is 2643743

    And City name is London

    And Cod parameter is 200