package restassured;

import booker.models.auth.Auth;
import booker.services.AuthService;
import booker.services.BookingService;
import booker.services.PingService;

import static io.restassured.RestAssured.given;
import static restassured.Specifications.*;

public class Client {

    private String token;

    private static final String baseUrl = "https://restful-booker.herokuapp.com";

    public AuthService auth = new AuthService();
    public BookingService booking = new BookingService();
    public PingService ping = new PingService();

    //create client for unlogged user
    public Client() {
        installSpecification(requestSpecification(baseUrl), successResponseSpecification());
        enableLogging();
    }

    //create client for logged user with token attribute in cookie
    public Client(String userName, String password) {
        this();
        setToken(userName, password);
        installSpecification(requestSpecification(baseUrl, getToken()), successResponseSpecification());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String userName, String password) {
        this.token = auth.getToken(new Auth(userName, password));
    }

}
