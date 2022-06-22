package pl.rogol;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.rogol.model.Brewery;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CSVProcessor {

    private static final int noOfLines = CSVUtils.countLines(AppPaths.BREWERIES_FILE_PATH);
    private static final Logger logger = LoggerFactory.getLogger(CSVProcessor.class);
    private static final int EVERY_NTH_ROW = 500;

    private static void logProgress(int row) {
        if (row % EVERY_NTH_ROW == 0) {
            logger.info("Procesing rows: " + row + " out of " + noOfLines);
        }
    }

    public static Map<String, Integer> getBreweriesInStates(String filePath) throws IOException {
        Map<String, Integer> breweriesInStates = new TreeMap<>();
        int row = 1;

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<Brewery> csvToBean = new CsvToBeanBuilder<Brewery>(reader)
                    .withMappingStrategy(CSVUtils.getBreweryStrategy())
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Brewery brewery : csvToBean) {
                logProgress(row);
                if (Arrays.asList(CSVUtils.states).contains(brewery.getProvince())) {
                    breweriesInStates.merge(brewery.getProvince(), 1, Integer::sum);
                } else if (Arrays.asList(CSVUtils.stateCodes).contains(brewery.getProvince())) {
                    breweriesInStates.merge(CSVUtils.translateCodeToState(brewery.getProvince()), 1, Integer::sum);
                } else {
                    String stateFromCity = CSVUtils.getStateFromCity(brewery.getCity());
                    if (stateFromCity != null) {
                        breweriesInStates.merge(stateFromCity, 1, Integer::sum);
                    }
                }
                row++;
            }
        }
        return breweriesInStates;
    }

    public static Map<String, Integer> getBreweriesInCities(String filePath) throws IOException {
        Map<String, Integer> breweriesInCities = new HashMap<>();
        int row = 1;

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<Brewery> csvToBean = new CsvToBeanBuilder<Brewery>(reader)
                    .withMappingStrategy(CSVUtils.getBreweryStrategy())
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Brewery brewery : csvToBean) {
                logProgress(row);
                breweriesInCities.merge(brewery.getCity(), 1, Integer::sum);
                row++;
            }
        }
        return CSVUtils.sortByValue(breweriesInCities);
    }

    public static Set<String> getWebsites(String filePath) throws IOException {
        Set<String> websites = new HashSet<>();
        int row = 1;

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<Brewery> csvToBean = new CsvToBeanBuilder<Brewery>(reader)
                    .withMappingStrategy(CSVUtils.getBreweryStrategy())
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Brewery brewery : csvToBean) {
                logProgress(row);
                websites.add(brewery.getWebsites());
                row++;
            }
        }
        return websites;
    }

    public static int getNoOfTacosInDelaware(String filePath) throws IOException {
        int tacos = 0;
        int row = 1;

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<Brewery> csvToBean = new CsvToBeanBuilder<Brewery>(reader)
                    .withMappingStrategy(CSVUtils.getBreweryStrategy())
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Brewery brewery : csvToBean) {
                logProgress(row);
                if ("Delaware".equalsIgnoreCase(brewery.getProvince()) || "DE".equalsIgnoreCase(brewery.getProvince())
                        && brewery.getMenus().toLowerCase().contains("taco")) {
                    tacos++;
                }
                row++;
            }
        }
        return tacos;
    }
}
