package booker.services;

import booker.models.booking.Booking;
import booker.models.booking.BookingCreated;
import booker.models.booking.BookingId;
import io.restassured.RestAssured;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static restassured.Specifications.responseSpecification;
import static restassured.Specifications.successResponseSpecification;

public class PingService {

    public String pingHealthCheck() {
        return given()
                .when()
                .get("/ping")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .asPrettyString();
    }

}
