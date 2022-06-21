package pl.rogol;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import pl.rogol.model.Brewery;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVReader {

    private static final String[] columns = {"id", "address", "categories", "city", "country", "hours", "keys",
            "latitude", "longitude", "menus", "name", "postalCode", "province", "twitter", "websites"};

    public static ColumnPositionMappingStrategy<Brewery> getStrategy() {
        ColumnPositionMappingStrategy<Brewery> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Brewery.class);
        strategy.setColumnMapping(columns);
        return strategy;
    }




}
