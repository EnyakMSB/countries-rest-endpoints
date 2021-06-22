package ro.fasttrackit.curs18.homework.countries.rest.endpoints.service;

import org.springframework.stereotype.Component;
import ro.fasttrackit.curs18.homework.countries.rest.endpoints.model.Countries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CountryReader {

    List<Countries> readInfoCountriesFromFile() throws Exception {
        List<Countries> countries = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/countries2.txt"));
        int id = 1;
        String line;
        while ((line = fileReader.readLine()) != null) {
            countries.add(getCountries(id++, line));
        }
        return countries;
    }

    private Countries getCountries(int id, String countryInfo) {
        String[] countryInfos = countryInfo.split("\\|");
        List<String> neighbours = new ArrayList<>();
        if (countryInfos.length > 5) {
            neighbours = getAllNeighbours(countryInfos[5]);
        }
        return new Countries(id,
                countryInfos[0],
                countryInfos[1],
                Long.parseLong(countryInfos[2]),
                Long.parseLong(countryInfos[3]),
                countryInfos[4],
                neighbours);
    }

    private List<String> getAllNeighbours(String neighbours) {

        return Arrays.asList(neighbours.split("~"));
    }
}
