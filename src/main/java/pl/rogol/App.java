package pl.rogol;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {

    private static final String FILE_PATH = "src/main/resources/test1.csv";

    public static void main(String... args)throws IOException, CsvException {

        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> System.out.println(Arrays.toString(x)));
        }

    }

}

