package services;

import booker.models.booking.Booking;
import booker.models.booking.BookingCreated;
import booker.models.booking.BookingId;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import restassured.Client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class BookingServiceTest {
    private static Client client;
    private static Booking booking;
    private static Integer bookingId;


    @BeforeClass
    public static void authAdmin() {
        client = new Client("admin", "password123");
        Booking newBooking = Booking.generateRandomBooking();
        BookingCreated bookingCreated = client.booking.createBooking(newBooking);
        booking = bookingCreated.getBooking();
        bookingId = bookingCreated.getBookingId();
    }

    @Test
    public void partialUpdateBookingTest() {
        booking.setFirstName(RandomStringUtils.randomAlphabetic(10)+"_updated");
        booking.setLastName(RandomStringUtils.randomAlphabetic(10)+"_updated");
        Booking bookingResponse = client.booking.partialUpdateBooking(booking, bookingId);
        Assert.assertEquals(booking, bookingResponse);
    }

    @Test
    public void getBookingsIds() {
        List<BookingId> bookingsIds = client.booking.getBookingsIds();
        Assert.assertTrue("Created bookingId: " + bookingId + " must be present in GET /booking response",
                bookingsIds.contains(new BookingId(bookingId)));
    }

    @Test
    public void getBookingsIdsByName() {
        List<BookingId> bookingsIds = client.booking.getBookingsIds(Map.of("firstname", booking.getFirstName(), "lastname", booking.getLastName()));
        Assert.assertTrue("Created bookingId: " + bookingId + "must be present in GET /booking response with name filter",
                bookingsIds.contains(new BookingId(bookingId)));
    }

    @Test
    public void getBookingsIdsByDate() {
        List<BookingId> bookingsIds = client.booking.getBookingsIds(Map.of(
                "checkin", LocalDate.now().minusDays(6).format(DateTimeFormatter.ISO_DATE),
                "checkout", LocalDate.now().minusDays(2).format(DateTimeFormatter.ISO_DATE)));
        Assert.assertTrue("Created bookingId: " + bookingId + "must be present in GET /booking response with date filter",
                bookingsIds.contains(new BookingId(bookingId)));
    }

    @Test
    public void deleteBooking() {
        client.booking.deleteBooking(bookingId);
        Assert.assertFalse("Booking with id: "+bookingId+" must not exist after delete", client.booking.isBookingExist(bookingId));

        //create new booking for test independence
        Booking newBooking = Booking.generateRandomBooking();
        BookingCreated bookingCreated = client.booking.createBooking(newBooking);
        booking = bookingCreated.getBooking();
        bookingId = bookingCreated.getBookingId();
    }
}
