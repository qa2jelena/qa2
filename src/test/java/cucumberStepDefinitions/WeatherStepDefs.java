package cucumberStepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberStepDefinitions.model.WeatherResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WeatherStepDefs {

    private WeatherRequester weatherRequester = new WeatherRequester();
    private String cityName;
    private WeatherResponse weatherResponse;

    @Given("City name for test is (.*)")
    public void setCityName(String name) {
        cityName = name;
    }

    @When("Requesting weather information")
    public void requestWeatherInformation() throws IOException {
        weatherResponse = weatherRequester.getWeather(cityName);
    }

    @Then("City geo location is:")
    public void checkCoordinates(Map<String, BigDecimal> params) {
        assertEquals(params.get("longitude"), weatherResponse.getCoord().getLon());
        assertEquals(params.get("latitude"), weatherResponse.getCoord().getLat());
    }

    @Then("Weather parameters are:")
    public void checkWeatherParameters(Map<String, String> params) {
        assertEquals(Integer.valueOf(params.get("weather condition id")), weatherResponse.getWeather().get(0).getId());
        assertEquals(params.get("group of weather parameters"), weatherResponse.getWeather().get(0).getMain());
        assertEquals(params.get("weather condition within the group"), weatherResponse.getWeather().get(0).getDescription());
        assertEquals(params.get("weather icon id"), weatherResponse.getWeather().get(0).getIcon());
    }

    @Then("Base parameter is (.*)")
    public void checkBase(String base) {
        assertEquals(base, weatherResponse.getBase());
    }

    @Then("Main weather parameters are:")
    public void checkMainParameters(Map<String, String> params) {
        assertEquals(new BigDecimal(params.get("temperature")), weatherResponse.getMain().getTemp());
        assertEquals(Integer.valueOf(params.get("atmospheric pressure")), weatherResponse.getMain().getPressure());
        assertEquals(Integer.valueOf(params.get("humidity")), weatherResponse.getMain().getHumidity());
        assertEquals(new BigDecimal(params.get("minimum temperature")), weatherResponse.getMain().getTemp_min());
        assertEquals(new BigDecimal(params.get("maximum temperature")), weatherResponse.getMain().getTemp_max());
    }

    @Then("Visibility is (.*)")
    public void checkVisibility(Integer visibility) {
        assertEquals(visibility, weatherResponse.getVisibility());
    }

    @Then("Wind parameters are:")
    public void checkWind(Map<String, String> params) {
        assertEquals(new BigDecimal(params.get("speed")), weatherResponse.getWind().getSpeed());
        assertEquals(Integer.valueOf(params.get("direction")), weatherResponse.getWind().getDeg());
    }

    @Then("Cloudiness is (.*)")
    public void checkCloudiness(Integer clouds) {
        assertEquals(clouds, weatherResponse.getClouds().getAll());
    }

    @Then("Time of data calculation is (.*)")
    public void checkCalculationTime(Long time) {
        assertEquals(time, weatherResponse.getDt());
    }

    @Then("Sys parameters are:")
    public void checkSysParameters(Map<String, String> params) {
        assertEquals(Integer.valueOf(params.get("type")), weatherResponse.getSys().getType());
        assertEquals(Long.valueOf(params.get("id")), weatherResponse.getSys().getId());
        assertEquals(new BigDecimal(params.get("message")), weatherResponse.getSys().getMessage());
        assertEquals(params.get("country code"), weatherResponse.getSys().getCountry());
        assertEquals(Long.valueOf(params.get("sunrise time")), weatherResponse.getSys().getSunrise());
        assertEquals(Long.valueOf(params.get("sunset time")), weatherResponse.getSys().getSunset());
    }

    @Then("City ID is (.*)")
    public void checkCityId(Long id) {
        assertEquals(id, weatherResponse.getId());
    }

    @Then("City name is (.*)")
    public void checkCityName(String name) {
        assertEquals(name, weatherResponse.getName());
    }

    @Then("Cod parameter is (.*)")
    public void checkCod(Integer cod) {
        assertEquals(cod, weatherResponse.getCod());
    }
}
