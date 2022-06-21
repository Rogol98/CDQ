package pl.rogol;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import pl.rogol.model.Brewery;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CSVProcessor {

    public static Map<String, Integer> getBreweriesInStates(String filePath) throws IOException {

        Map<String, Integer> breweriesInStates = new HashMap<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {

            CsvToBean<Brewery> csvToBean = new CsvToBeanBuilder<Brewery>(reader)
                    .withMappingStrategy(CSVReader.getStrategy())
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Brewery brewery : csvToBean) {
                System.out.println("ID: " + brewery.getId());
                System.out.println("Name: " + brewery.getName());
                System.out.println("longitude: " + brewery.getLongitude());
                System.out.println("postalcode: " + brewery.getPostalCode());

            }

        }
        return breweriesInStates;
    }
}
