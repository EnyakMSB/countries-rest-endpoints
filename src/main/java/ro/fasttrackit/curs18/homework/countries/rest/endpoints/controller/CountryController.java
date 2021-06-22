package ro.fasttrackit.curs18.homework.countries.rest.endpoints.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs18.homework.countries.rest.endpoints.model.Countries;
import ro.fasttrackit.curs18.homework.countries.rest.endpoints.service.CountryService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("countries")
    public List<Countries> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("names")
    public List<String> getAllCountriesNames() {
        return countryService.getAllCountriesNames();
    }

    @GetMapping("/{countryId}/capital")
    public Optional<String> getCountryCapital(@PathVariable int countryId) {
        return countryService.getCountryCapital(countryId);
    }

    @GetMapping("/{countryId}/population")
    public Optional<Long> getCountryPopulation(@PathVariable int countryId) {
        return countryService.getCountryPopulation(countryId);
    }

    //- get countries in continent : /continents/<continentName>/countries -> returns list of Country objects
    @GetMapping("/{continent}/countries")
    public List<Countries> getCoountriesFromContinent(@PathVariable String continent) {
        return countryService.getCoountriesFromContinent(continent);
    }

    //- get country neighbours : /countries/<countryId>/neighbours -> returns list of Strings
    @GetMapping("/{countryId}/neighbours")
    public Optional<List<String>> getCountryNeighbours(@PathVariable int countryId) {
        return countryService.getCountryNeighbours(countryId);
    }

    /* - get countries in <continent> with population larger than <population> :
     /continents/<continentName>/countries?minPopulation=<minimum population> -> returns list of Country objects
    //localhost:8080/countries/continents/europe/countries?minPopulation=7153784
    */
    @GetMapping("continents/{continent}/countries")
    public List<Countries> getCountriesWithLargerPopulation(
            @PathVariable String continent,
            @RequestParam Long minPopulation) {
        return countryService.getCountriesWithLargerPopulation(continent, minPopulation);
    }

    /*
    - get countries that neighbor X but not neighbor Y :
    /countries?includeNeighbour=<includedNeighbourCode>&excludeNeighbour=<excludedNeighbourCode> ->
    returns list of Country objects
     */
    @GetMapping("/country")

    public List<Countries> getCountryNeighbours(
            @RequestParam String includeNeighbour,
            @RequestParam String excludeNeighbour) {
        return countryService.getCountryNeighbours(includeNeighbour, excludeNeighbour);
    }
    //- get map from country to population : /countries/population -> returns map from String to Long

    @GetMapping("/population")
    public Map<String, Long> mapCountryToPopulation() {
        return countryService.mapCountryToPopulation();
    }
    //- get map from continent to list of countries : /continents/countries  -> returns Map from String to List<Country>
    // http://localhost:8080/countries/continent/countries
    @GetMapping("/continent/countries")
    public Map<String, List<Countries>> mapContinentToCountries() {
        return countryService.mapContinentToCountries();
    }
}
