package pl.rogol;

import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static pl.rogol.CSVProcessor.*;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String... args) throws IOException, CsvException {

        // a)
//        getBreweriesInStates(AppPaths.BREWERIES_FILE_PATH).forEach((key, value) -> logger.info(key + ":" + value));

        // b)
//        getBreweriesInCities(AppPaths.BREWERIES_FILE_PATH).forEach((key, value) -> logger.info(key + ":" + value));

        // c)
//        logger.info("number of websites: " + (getWebsites(AppPaths.BREWERIES_FILE_PATH).size()));

        // d)
//        logger.info("number of tacos in Delaware: " + getNoOfTacosInDelaware(AppPaths.BREWERIES_FILE_PATH));

        // e)
//        getWineBreweries(AppPaths.BREWERIES_FILE_PATH).forEach((key, value) -> logger.info("{} : {}%", key, value));

        //3
        System.out.println(findDuplicatedRecords(AppPaths.BREWERIES_FILE_PATH).size());


    }

}

