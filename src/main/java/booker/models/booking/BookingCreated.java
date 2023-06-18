package booker.models.booking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingCreated {

    @JsonProperty("bookingid")
    private Integer bookingId;
    private Booking booking;

    public Integer getBookingId() {
        return bookingId;
    }

    public Booking getBooking() {
        return booking;
    }

}
