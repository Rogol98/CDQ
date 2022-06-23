package pl.rogol.CSV;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.lang3.ArrayUtils;
import pl.rogol.AppPaths;
import pl.rogol.model.Brewery;
import pl.rogol.model.USACity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVUtils {

    protected static final String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
            "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
            "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
            "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};

    protected static final String[] stateCodes = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
            "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT",
            "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN",
            "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};

    private static final String[] columns = {"id", "address", "categories", "city", "country", "hours", "keys",
            "latitude", "longitude", "menus", "name", "postalCode", "province", "twitter", "websites"};

    public static String translateCodeToState(String stateCode) {
        int correspondingIndex = ArrayUtils.indexOf(stateCodes, stateCode);
        return states[correspondingIndex];
    }

    public static int getNoOfUniqueRecordsInList(List<Brewery> recordsList) {
        Set<Brewery> uniqueRecords = new HashSet<>(recordsList);
        return uniqueRecords.size();
    }

    public static ColumnPositionMappingStrategy<Brewery> getBreweryStrategy() {
        ColumnPositionMappingStrategy<Brewery> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Brewery.class);
        strategy.setColumnMapping(columns);
        return strategy;
    }

    public static ColumnPositionMappingStrategy<USACity> getUSACityStrategy() {
        ColumnPositionMappingStrategy<USACity> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(USACity.class);
        strategy.setColumnMapping("city", "stateName");
        return strategy;
    }

    public static String getStateFromCity(String city) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(AppPaths.CITIES_FILE_PATH))) {

            CsvToBean<USACity> csvToBean = new CsvToBeanBuilder<USACity>(reader)
                    .withMappingStrategy(getUSACityStrategy())
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (USACity usaCity : csvToBean) {
                if (city.equalsIgnoreCase(usaCity.getCity())) {
                    return usaCity.getStateName();
                }
            }
        }
        return null;
    }

    public static Map<String, Integer> assignBreweryToState(Map<String, Integer> breweriesMap, Brewery brewery) throws IOException {
        if (Arrays.asList(CSVUtils.states).contains(brewery.getProvince())) {
            breweriesMap.merge(brewery.getProvince(), 1, Integer::sum);
        } else if (Arrays.asList(CSVUtils.stateCodes).contains(brewery.getProvince())) {
            breweriesMap.merge(CSVUtils.translateCodeToState(brewery.getProvince()), 1, Integer::sum);
        } else {
            String stateFromCity = CSVUtils.getStateFromCity(brewery.getCity());
            if (stateFromCity != null) {
                breweriesMap.merge(stateFromCity, 1, Integer::sum);
            }
        }
        return breweriesMap;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static int countLines(String fileName) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;

    }

}
