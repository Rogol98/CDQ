package pl.rogol;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.rogol.model.Brewery;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CSVProcessor {

    private static final int NO_OF_LINES = CSVUtils.countLines(AppPaths.BREWERIES_FILE_PATH);
    private static final Logger logger = LoggerFactory.getLogger(CSVProcessor.class);
    private static final int EVERY_NTH_ROW = 500;

    private static void logProgress(int row) {
        if (row % EVERY_NTH_ROW == 0) {
            logger.info("Processing rows: {} out of {}", row, NO_OF_LINES);
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
                CSVUtils.assignBreweryToState(breweriesInStates, brewery);
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

    public static Map<String, Double> getWineBreweries(String filePath) throws IOException {
        Map<String, Integer> wineBreweriesInStates = new TreeMap<>();
        Map<String, Integer> breweriesInStates = new TreeMap<>();
        Map<String, Double> wineBreweriesInStatesPercentage = new TreeMap<>();
        int row = 1;

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<Brewery> csvToBean = new CsvToBeanBuilder<Brewery>(reader)
                    .withMappingStrategy(CSVUtils.getBreweryStrategy())
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Brewery brewery : csvToBean) {
                logProgress(row);
                CSVUtils.assignBreweryToState(breweriesInStates, brewery);
                if (brewery.getMenus().toLowerCase().contains("wine")) {
                    CSVUtils.assignBreweryToState(wineBreweriesInStates, brewery);
                }
                row++;
            }
        }


        for (Map.Entry<String, Integer> entry : wineBreweriesInStates.entrySet()) {
            double value = (double) entry.getValue() / breweriesInStates.get(entry.getKey()) * 100;
            value = BigDecimal.valueOf(value)
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue();
            wineBreweriesInStatesPercentage.put(entry.getKey(), value);
        }

        return wineBreweriesInStatesPercentage;
    }

    public static Set<Brewery> findDuplicatedRecords(String filePath) throws IOException {
        int row = 1;

        Set<Brewery> duplicatedBreweries = new HashSet<>();
        Set<Double> longitudes = new HashSet<>();
        Set<Double> latitudes = new HashSet<>();
        Set<String> names = new HashSet<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<Brewery> csvToBean = new CsvToBeanBuilder<Brewery>(reader)
                    .withMappingStrategy(CSVUtils.getBreweryStrategy())
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Brewery brewery : csvToBean) {
                logProgress(row);

                if(brewery.getLongitude() != 0.0 && !latitudes.add(brewery.getLongitude())){
                    System.out.println(brewery.getLongitude());
                }

                if (brewery.getLongitude() != 0.0 && !latitudes.add(brewery.getLongitude()) &&
                        brewery.getLatitude() != 0.0 && !longitudes.add(brewery.getLatitude()) &&
                        !names.add(brewery.getName())) {
                    duplicatedBreweries.add(brewery);
                }
                row++;
            }
        }
        return duplicatedBreweries;
    }

}


