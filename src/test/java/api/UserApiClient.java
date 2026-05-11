package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {
    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";
    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String DELETE_ENDPOINT = "/api/auth/user";

    public Response createUser(String name, String email, String password) {
        String body = String.format("{\"name\":\"%s\", \"email\":\"%s\", \"password\":\"%s\"}", name, email, password);
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(REGISTER_ENDPOINT);
    }

    public Response login(String email, String password) {
        String body = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    public void deleteUser(String accessToken) {
        given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_ENDPOINT);
    }
}