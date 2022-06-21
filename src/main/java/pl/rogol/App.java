package pl.rogol;

import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static pl.rogol.CSVProcessor.getBreweriesInStates;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String FILE_PATH = "src/main/resources/breweries_usa.csv";

    public static void main(String... args) throws IOException, CsvException {

        getBreweriesInStates(FILE_PATH).forEach((key, value) -> logger.info(key + ":" + value));


    }

}

