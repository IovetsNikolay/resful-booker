package booker.services;

import booker.models.auth.Auth;

import static io.restassured.RestAssured.given;

public class AuthService {

    public String getToken(Auth authModel) {
        return given()
                .body(authModel)
                .when()
                .post("/auth")
                .then()
                .extract().body()
                .jsonPath().get("token");
    }

}
