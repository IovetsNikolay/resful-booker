package booker.services;

import booker.models.booking.Booking;
import booker.models.booking.BookingId;
import booker.models.booking.BookingCreated;
import io.restassured.RestAssured;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static restassured.Specifications.responseSpecification;
import static restassured.Specifications.successResponseSpecification;

public class BookingService {

    public List<BookingId> getBookingsIds(Map<String, String> parameterMap) {
        return given()
                .queryParams(parameterMap)
                .when()
                .get("/booking")
                .then()
                .extract()
                .body()
                .jsonPath().getList(".", BookingId.class);
    }

    public List<BookingId> getBookingsIds() {
        return getBookingsIds(Map.of());
    }

    public Booking getBooking(Integer bookingId) {
        return given()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .extract()
                .body().as(Booking.class);
    }

    public Boolean isBookingExist(Integer bookingId) {
        RestAssured.responseSpecification = responseSpecification();
        Integer statusCode = given()
                .when()
                .get("/booking/" + bookingId)
                .getStatusCode();
        RestAssured.responseSpecification = successResponseSpecification();
        return statusCode < 400;
    }

    public BookingCreated createBooking(Booking booking) {
        return given()
                .when()
                .body(booking)
                .post("/booking")
                .then()
                .extract()
                .body().as(BookingCreated.class);
    }

    public Booking updateBooking(Booking booking, Integer bookingId) {
        return given()
                .when()
                .body(booking)
                .put("/booking/" + bookingId)
                .then()
                .extract()
                .body().as(Booking.class);
    }

    public Booking partialUpdateBooking(Booking booking, Integer bookingId) {
        return given()
                .when()
                .body(booking)
                .patch("/booking/" + bookingId)
                .then()
                .extract()
                .body().as(Booking.class);
    }

    public void deleteBooking(Integer bookingId) {
        given()
                .when()
                .delete("/booking/" + bookingId);
    }

}
