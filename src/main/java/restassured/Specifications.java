package restassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class Specifications {

    //Specification with added token to requests
    public static RequestSpecification requestSpecification(String baseUrl, String token) {
        return requestSpecification(baseUrl).cookie("token", token);
    }

    //Basic request specification
    public static RequestSpecification requestSpecification(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .build();
    }

    //Request specification with success status code validation
    public static ResponseSpecification successResponseSpecification() {
        return responseSpecification()
                .statusCode(anyOf(is(200), is(201)));
    }

    //Request specification without success status code validation
    public static ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    //Enable logging to each request
    public static void enableLogging() {
        RestAssured.filters(new ResponseLoggingFilter());
        RestAssured.filters(new RequestLoggingFilter());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
