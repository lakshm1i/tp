package seedu.navi.canteenfinder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.navi.canteenfinder.helperclasses.NearestCanteenData;
import seedu.navi.exceptions.CanteenNotFound;
import seedu.navi.exceptions.LocationNotFound;
import seedu.navi.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class CanteenFinderParserTest {
    @BeforeEach
    public void setUp() {
        Storage.processDataFromFiles();
    }

    @Test
    public void testFindNearestCanteenToMe_correctCanteenSOC_success() {
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("soc",
                null);
        assertEquals("Terrace", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_wrongCanteenSOC_success() {
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("soc",
                null);
        assertNotEquals("Flavours", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_correctCanteenSOCWithDietRestrictions_success() {
        String[] dietRestrictions = {"halal certified", "aircon"};
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("soc",
                dietRestrictions);
        assertEquals("The Deck", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_correctCanteenScience_success() {
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("faculty of science",
                null);
        assertEquals("Frontier", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_wrongCanteenScience_success() {
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("faculty of science",
                null);
        assertNotEquals("Deck", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_correctCanteenHSSML_success() {
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("hssml",
                null);
        assertEquals("Terrace", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_wrongCanteenHSSML_success() {
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("hssml",
                null);
        assertNotEquals("Deck", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_correctCanteenHSSMLWithDietRestrictions_success() {
        String[] dietRestrictions = {"halal certified", "aircon"};
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("hssml",
                dietRestrictions);
        assertEquals("The Deck", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_wrongCanteenHSSMLWithDietRestrictions_success() {
        String[] dietRestrictions = {"halal certified", "aircon"};
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("hssml",
                dietRestrictions);
        assertNotEquals("Terrace", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_correctCanteenEusoff_success() {
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("eusoff",
                null);
        assertEquals("The Deck", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_wrongCanteenEusoff_success() {
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("eusoff",
                null);
        assertNotEquals("Flavours", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_correctCanteenEusoffWithDietRestrictions_success() {
        String[] dietRestrictions = {"vegetarian"};
        NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("eusoff",
                dietRestrictions);
        assertEquals("The Deck", nearestCanteenData.nearestCanteen().getName());
    }

    @Test
    public void testFindNearestCanteenToMe_wrongCanteenEusoffWithDietRestrictions_exceptionThrown() {
        String[] dietRestrictions = {"vegetarian", "aircon"};
        try {
            NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("eusoff",
                    dietRestrictions);
            fail();
        } catch (CanteenNotFound e) {
            assertTrue(true);
        }
    }

    @Test
    public void testFindNearestCanteenToMe_invalidLandmark_exceptionThrown() {
        try {
            NearestCanteenData nearestCanteenData = CanteenFinderParser.findNearestCanteenToMe("hss",
                    null);
            fail();
        } catch (LocationNotFound e) {
            assertTrue(true);
        }
    }
}
