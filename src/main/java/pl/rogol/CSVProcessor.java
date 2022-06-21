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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CSVProcessor {

    private static final Logger logger = LoggerFactory.getLogger(CSVProcessor.class);

    public static Map<String, Integer> getBreweriesInStates(String filePath) throws IOException {

        Map<String, Integer> breweriesInStates = new HashMap<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {

            CsvToBean<Brewery> csvToBean = new CsvToBeanBuilder<Brewery>(reader)
                    .withMappingStrategy(CSVReader.getStrategy())
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (Brewery brewery : csvToBean) {
                breweriesInStates.merge(brewery.getProvince(), 1, Integer::sum);
            }

        }
        return breweriesInStates;
    }
}
