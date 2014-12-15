package pais; /**
 * Created by lidan on 14/12/14.
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import pais.exceptions.SaveShowResult;
import pais.models.Seat;
import pais.models.Show;


public class PaisTest extends TestCase {
    protected PaisBridge bridge;

    // runs before the tests suite
    @BeforeClass
    public void setUp() throws Exception {
        bridge = TestDriver.getTestBridge(); /* getRealBridge(); */
    }

    // runs after each test (clear database)
    @After
    public void tearDown() { bridge.removeAllShows(); }

    @Test
    public void testShowAddPage() {
        // Show page
        assertEquals("New show should have an empty name", "", bridge.newShowPage().getName());
        assertEquals("New show's id should be == 0", 0, bridge.newShowPage().getShowId());
    }

    @Test
    public void testAddShow() {
        // Add show (happy path)
        Show s = new Show(1,
                "Name",
                "Desc",
                "Hall 1",
                LocalDateTime.of(2015, 1, 1, 20, 0),
                LocalDateTime.of(2015, 1, 1, 22, 0),
                50.0,
                LocalDateTime.of(2014, 12, 31, 23, 59), 10, 10);

        assertTrue("Add new show", bridge.addShow(s).isSuccess());
    }

    @Test
    public void testDuplicatedShows() {
        Show s = new Show(2,
                "Name",
                "Desc",
                "Hall 1",
                LocalDateTime.of(2015, 1, 1, 20, 0),
                LocalDateTime.of(2015, 1, 1, 22, 0),
                50.0,
                LocalDateTime.of(2014, 12, 31, 23, 59), 10, 10);
        bridge.addShow(s);

        // Duplication (sad path)
        SaveShowResult duplicateResult = bridge.addShow(s);
        assertFalse("Cannot save duplicated show", duplicateResult.isSuccess());
        assertEquals("Duplicate show should return an error message", "Duplicate", duplicateResult.getMessage());
    }

    @Test
    public void testIntersectedShows() {
        Show s = new Show(1,
                "Show 1",
                "Desc 1",
                "Hall 1",
                LocalDateTime.of(2015, 1, 1, 20, 0),
                LocalDateTime.of(2015, 1, 1, 22, 0),
                50.0,
                LocalDateTime.of(2014, 12, 31, 23, 59), 10, 10);
        bridge.addShow(s);

        // Intersection (sad path)
        Show intersectedShow = new Show(2,
                "Show 2",
                "Desc 2",
                "Hall 1",
                LocalDateTime.of(2015, 1, 1, 20, 0),
                LocalDateTime.of(2015, 1, 1, 23, 0),
                80.0,
                LocalDateTime.of(2014, 12, 15, 12, 00), 10, 10);

        SaveShowResult intersectionResult = bridge.addShow(intersectedShow);
        assertFalse("Cannot save intersected show", intersectionResult.isSuccess());
        assertEquals("Intersected show should return an error message", "Intersection", intersectionResult.getMessage());
    }

    @Test
    public void testAddShowWithInvalidDates() {
        // Last time order date > Show date
        Show badShow = new Show(1,
                "Name",
                "Desc",
                "Hall 1",
                LocalDateTime.of(2015, 1, 1, 20, 0),
                LocalDateTime.of(2015, 1, 1, 22, 0),
                50.0,
                LocalDateTime.of(2014, 12, 1, 23, 59), 10, 10);

        SaveShowResult badShowResult = bridge.addShow(badShow);
        assertFalse("Cannot save show with order date > show date", badShowResult.isSuccess());
        assertEquals("Show with last order date > show date should return an error message", "order date > show date", badShowResult.getMessage());
    }

    @Test
    public void testAddShowWithInvalidFields() {
        // Empty Fields (bad path)
        SaveShowResult emptyShowResult = bridge.addShow(new Show());
        assertFalse("Cannot save show with missing fields", emptyShowResult.isSuccess());
        assertEquals("Show with missing mandatory fields should return an error message", "Mandatory fields are missing", emptyShowResult.getMessage());
    }

    @Test
    public void testShowOrderPage() {
        Show s = new Show(2,
                "Name",
                "Desc",
                "Hall 1",
                LocalDateTime.of(2015, 1, 1, 20, 0),
                LocalDateTime.of(2015, 1, 1, 22, 0),
                50.0,
                LocalDateTime.of(2014, 12, 31, 23, 59), 2, 2);
        bridge.addShow(s);

        assertEquals("View show order page", s, bridge.openShowPage(1));
    }

    @Test
    public void testOrderTickets() {
        Show s = new Show(3,
                "Name",
                "Desc",
                "Hall 1",
                LocalDateTime.of(2015, 1, 1, 20, 0),
                LocalDateTime.of(2015, 1, 1, 22, 0),
                50.0,
                LocalDateTime.of(2014, 12, 31, 23, 59), 2, 2);
        bridge.addShow(s);

        List<Seat> orderedSeats = new ArrayList<Seat>();
        orderedSeats.add(new Seat(1, 1));
        orderedSeats.add(new Seat(1, 2));

        // 2. assert
        assertTrue("Order seats successfully", bridge.orderTickets(s, "Lidan", "050-5050505", orderedSeats));
    }

    @Test
    public void testOrderTicketsWithInvalidFields() {
        // Empty fields (bad path)
        Show s = new Show(4,
                "Name",
                "Desc",
                "Hall 1",
                LocalDateTime.of(2015, 1, 1, 20, 0),
                LocalDateTime.of(2015, 1, 1, 22, 0),
                50.0,
                LocalDateTime.of(2014, 12, 31, 23, 59), 2, 2);
        bridge.addShow(s);

        List<Seat> orderedSeats = new ArrayList<Seat>();
        orderedSeats.add(new Seat(2, 1));
        orderedSeats.add(new Seat(2, 2));
        assertFalse("Cannot order with empty name", bridge.orderTickets(s, "", "050-5050505", orderedSeats));
        assertFalse("Cannot order with empty phone", bridge.orderTickets(s, "Ohad", null, orderedSeats));
        assertFalse("Cannot order without seats", bridge.orderTickets(s, "Ohad", "050-5050505", null));
    }

    @Test
    public void testOrderTicketsForShowWithNoAvailableSeats() {
        Show fullShow = new Show(5,
                "Name",
                "Desc",
                "Hall 1",
                LocalDateTime.of(2015, 1, 1, 20, 0),
                LocalDateTime.of(2015, 1, 1, 22, 0),
                50.0,
                LocalDateTime.of(2014, 12, 31, 23, 59), 2, 2); // 1x1 Hall
        List<Seat> orderedSeats = new ArrayList<Seat>();
        orderedSeats.add(new Seat(1, 1));
        bridge.orderTickets(fullShow, "Ohad", "012-3456789", orderedSeats);

        assertEquals("Show is full, cannot show show's order page", null, bridge.openShowPage(5));
    }
}
