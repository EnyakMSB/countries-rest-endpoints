package ro.fasttrackit.curs18.homework.countries.rest.endpoints.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.curs18.homework.countries.rest.endpoints.model.Countries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

@Service
public class CountryService {
    private final List<Countries> countries = new ArrayList<>();

    public CountryService(CountryReader reader) throws Exception {
        countries.addAll(reader.readInfoCountriesFromFile());
    }


    public List<Countries> getAllCountries() {
        return countries;
    }

    public List<String> getAllCountriesNames() {
        return countries.stream()
                .map(Countries::getName)
                .collect(toList());
    }

    public Optional<String> getCountryCapital(int countryId) {
        return countries.stream()
                .filter(countries -> countries.getId() == countryId)
                .map(Countries::getCapital)
                .findFirst();
    }

    public Optional<Long> getCountryPopulation(int countryId) {
        return countries.stream()
                .filter(countries -> countries.getId() == countryId)
                .map(Countries::getPopulation)
                .findFirst();
    }

    public List<Countries> getCoountriesFromContinent(String continent) {
        return countries.stream()
                .filter(countries -> countries.getContinent().equalsIgnoreCase(continent))
                .collect(toList());
    }

    public Optional<List<String>> getCountryNeighbours(int countryId) {
        return countries.stream()
                .filter(countries -> countries.getId() == countryId)
                .map(countries -> countries.getNeighbours())
                .findFirst();
    }

    public List<Countries> getCountriesWithLargerPopulation(String continent, Long minPopulation) {
        return countries.stream()
                .filter(countries -> countries.getContinent().equalsIgnoreCase(continent))
                .filter(countries -> countries.getPopulation() >= minPopulation)
                .collect(toList());
    }

    public List<Countries> getCountryNeighbours(String includeNeighbour, String excludeNeighbour) {
        return countries.stream()
                .filter(countries -> countries.getNeighbours().contains(includeNeighbour.toUpperCase()))
                .filter(not(countries -> countries.getNeighbours().contains(excludeNeighbour.toUpperCase())))
                .collect(toList());
    }


    public Map<String, Long> mapCountryToPopulation() {
        return countries.stream()
                .collect(toMap(Countries::getName, Countries::getPopulation));
    }

    public Map<String, List<Countries>> mapContinentToCountries() {
        return countries.stream()
                .collect(groupingBy(Countries::getContinent));
    }
}
