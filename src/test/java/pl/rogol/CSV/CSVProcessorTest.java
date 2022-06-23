package pl.rogol.CSV;

import org.junit.jupiter.api.Test;
import pl.rogol.AppPaths;
import pl.rogol.model.Brewery;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.rogol.CSV.CSVProcessor.*;


class CSVProcessorTest {

    @Test
    void getBreweriesInCitiesTest() throws IOException {

        Map<String, Integer> actual = getBreweriesInCities(AppPaths.TEST_FILE_PATH1);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("Lecktown", 1);
        expected.put("Pawtucket", 1);
        expected.put("Trych", 1);
        expected.put("Saint Louis", 1);
        expected.put("Longmont", 1);
        expected.put("Rochester", 1);
        expected.put("Grand Rapids", 1);
        expected.put("Redondo Beach", 1);
        expected.put("Moorhead", 1);
        expected.put("Sherman Oaks", 1);
        expected.put("Folsom", 1);
        expected.put("Gillette", 1);
        expected.put("Aurora", 1);
        expected.put("Boise", 1);
        expected.put("Los Angeles", 1);
        expected.put("Rowlett", 1);
        expected.put("Tampa", 1);
        expected.put("Peru", 1);
        expected.put("San Marcos", 1);
        expected.put("Pacoima", 1);
        expected.put("Providence", 5);
        assertThat(actual, is(expected));
    }

    @Test
    void getBreweriesInStatesTest() throws IOException {
        Map<String, Integer> actual = getBreweriesInStates(AppPaths.TEST_FILE_PATH2);
        Map<String, Integer> expected = new TreeMap<>();
        expected.put("California", 2);
        expected.put("Colorado", 1);
        expected.put("Maryland", 1);
        expected.put("Massachusetts", 1);
        expected.put("Michigan", 1);
        expected.put("Missouri", 1);
        expected.put("Nebraska", 1);
        expected.put("New Jersey", 1);
        expected.put("New Mexico", 2);
        expected.put("New York", 2);
        expected.put("Ohio", 1);
        expected.put("Oregon", 1);
        expected.put("Pennsylvania", 2);
        expected.put("South Carolina", 1);
        expected.put("Texas", 1);
        expected.put("Wisconsin", 1);
        assertThat(actual, is(expected));
    }

    @Test
    void getWebsitesTest() throws IOException {
        Set<String> actual = getWebsites(AppPaths.TEST_FILE_PATH1);
        Set<String> expected = new HashSet<>();
        expected.add("http://www.kingharborbrewing.com");
        expected.add("http://api.citygridmedia.com/content/places/v2/");
        expected.add("http://codabrewing.com,http://ursulabrewery.com");
        expected.add("http://stonehouseidaho.com");
        expected.add("http://www.300sunsbrewing.com");
        expected.add("http://firewheelbrewing.com");
        expected.add("http://gillettebrewingcompany.com");
        expected.add("http://api.citygridmedia.com/content/places/v2/click?q=9YKflVKbY9NauPJdMy0B1gS1IhB4xv4EWw0zDoT-UWc_izWF3zs5PKGdfOHubWrvM0QwDCYwbOH2fdLi0dK5xArULcksCCbfR-WWA");
        expected.add("http://kirin.com");

        for (String ex : expected) {
            assertTrue(actual.contains(ex));
        }
        for (String ac : actual) {
            assertTrue(expected.contains(ac));
        }
    }

    @Test
    void getNoOfTacosInDelawareTest() throws IOException {
        assertEquals(2, getNoOfTacosInDelaware(AppPaths.TEST_FILE_PATH1));
        assertEquals(0, getNoOfTacosInDelaware(AppPaths.TEST_FILE_PATH2));
        assertEquals(1, getNoOfTacosInDelaware(AppPaths.TEST_FILE_PATH3));

    }

    @Test
    void getWineBreweriesTest() throws IOException {
        Map<String, Double> actual = getWineBreweries(AppPaths.TEST_FILE_PATH1);
        Map<String, Double> expected = new TreeMap<>();
        expected.put("Delaware", 100.0);
        expected.put("Rhode Island", 16.667);
        assertThat(actual, is(expected));

    }

    @Test
    void findDuplicatedRecordsTest() throws IOException {
        List<Brewery> actual = findDuplicatedRecords(AppPaths.TEST_FILE_PATH1);
        List<Brewery> expected = new ArrayList<>();
        expected.add(new Brewery("AV1dbuf53-Khe5l_MrIY", "95 Chestnut St 3", "Brewers", "Providence", "US", "",
                "us/ri/providence/95chestnutst3/-279043579", 0, 0, "", "Revival Brewing Co", "2903", "NY", "", ""));
        expected.add(new Brewery("AV1dbuf53-Khe5l_MrIY", "95 Chestnut St 3", "Brewers", "Providence", "US", "",
                "us/ri/providence/95chestnutst3/-279043579", 0, 0, "", "Revival Brewing Co", "2903", "NY", "", ""));
        expected.add(new Brewery("AV1dbuf2a4HuVbed6Uja", "10 Charles St", "Brewers", "Providence", "US", "", "us/ri/providence/10charlesst/1621289672", 0
                , 0, "", "Narragansett Brewing Co", "2904", "RI", "", ""));


        for (Brewery ex : expected) {
            assertTrue(actual.contains(ex));
        }
        for (Brewery ac : actual) {
            assertTrue(expected.contains(ac));
        }
    }
}