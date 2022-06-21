package pl.rogol;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

import static pl.rogol.CSVProcessor.readSomeFields;

public class App {

    private static final String FILE_PATH = "src/main/resources/breweries_usa.csv";


    public static void main(String... args) throws IOException, CsvException {

        readSomeFields(FILE_PATH);

    }

}

