package booker.models.booking;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class BookingId {
    @JsonProperty("bookingid")
    private Integer bookingId;

    public BookingId() {}

    public BookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @JsonGetter("bookingid")
    public Integer getBookingId() {
        return bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingId bookingId1 = (BookingId) o;
        return Objects.equals(bookingId, bookingId1.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

    @Override
    public String toString() {
        return "\n" + new GsonBuilder().setPrettyPrinting().create().toJson(this) + "\n";
    }
}
