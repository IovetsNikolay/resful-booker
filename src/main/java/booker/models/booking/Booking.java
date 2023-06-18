package booker.models.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Booking {

    @JsonProperty("firstname")

    private String firstName;
    @JsonProperty("lastname")

    private String lastName;
    @JsonProperty("totalprice")

    private Integer totalPrice;
    @JsonProperty("depositpaid")

    private Boolean depositPaid;
    @JsonProperty("bookingdates")

    private BookingDates bookingDates;
    @JsonProperty("additionalneeds")

    private String additionalNeeds;

    public Booking() {}

    public Booking(String firstname, String lastname, Integer totalprice, Boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.totalPrice = totalprice;
        this.depositPaid = depositpaid;
        this.bookingDates = bookingdates;
        this.additionalNeeds = additionalneeds;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Boolean getDepositPaid() {
        return depositPaid;
    }

    public BookingDates getBookingDates() {
        return bookingDates;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public void setTotalPrice(Integer totalprice) {
        this.totalPrice = totalprice;
    }

    public void setDepositPaid(Boolean depositpaid) {
        this.depositPaid = depositpaid;
    }

    public void setBookingDates(BookingDates bookingdates) {
        this.bookingDates = bookingdates;
    }

    public void setAdditionalNeeds(String additionalneeds) {
        this.additionalNeeds = additionalneeds;
    }

    public static Booking generateRandomBooking() {
        Booking randomBooking = new Booking();
        randomBooking.setFirstName(RandomStringUtils.randomAlphabetic(10)+"_created");
        randomBooking.setLastName(RandomStringUtils.randomAlphabetic(10)+"_created");
        randomBooking.setTotalPrice(RandomUtils.nextInt());
        randomBooking.setDepositPaid(RandomUtils.nextBoolean());
        randomBooking.setAdditionalNeeds(RandomStringUtils.randomAlphabetic(5));
        randomBooking.setBookingDates(
                new BookingDates(
                        LocalDate.now().minusDays(5).format(DateTimeFormatter.ISO_DATE),
                        LocalDate.now().minusDays(2).format(DateTimeFormatter.ISO_DATE)));
        return randomBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(firstName, booking.firstName)
                && Objects.equals(lastName, booking.lastName)
                && Objects.equals(totalPrice, booking.totalPrice)
                && Objects.equals(depositPaid, booking.depositPaid)
                && Objects.equals(bookingDates, booking.bookingDates)
                && Objects.equals(additionalNeeds, booking.additionalNeeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, totalPrice, depositPaid, bookingDates, additionalNeeds);
    }

    @Override
    public String toString() {
        return "\n" + new GsonBuilder().setPrettyPrinting().create().toJson(this) + "\n";
    }

}