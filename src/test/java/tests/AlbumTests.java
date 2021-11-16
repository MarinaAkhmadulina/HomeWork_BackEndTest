package tests;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@Story("Album API Tests")
public class AlbumTests extends BaseTest {

    static String albumDeleteHash;
    static String albumHash;
    static Response valueAlbum;
    private static String NAMEALBUM = "My Album";

    private static Response creationAlbum(String nameAlbum) {
        valueAlbum = given()
                .headers("Authorization", token)
                .param("title", nameAlbum)
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post("https://api.imgur.com/3/album")
                .prettyPeek();
        albumHash = valueAlbum
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
        albumDeleteHash = valueAlbum
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash");
        return valueAlbum;
    }

    private static Response deletionAlbum() {
        return given()
                .headers("Authorization", token)
                .expect()
                .body("success", is(true))
                .when()
                .delete("https://api.imgur.com/3/album/{albumDeleteHash}", albumDeleteHash)
                .prettyPeek();
    }

    @AfterEach
    void comeBack() {
        given()
                .headers("Authorization", token)
                .when()
                .delete("https://api.imgur.com/3/account/{username}/album/{albumHash}",
                        username, albumHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void creationAlbumTest() {
        creationAlbum(NAMEALBUM)
                .then()
                .statusCode(200);
    }

    @Test
    void deletionAlbumTest() {
        creationAlbum(NAMEALBUM);
        deletionAlbum().then()
                .statusCode(200);
    }

    @Test
    void favouriteDeletedAlbumTest() {
        creationAlbum(NAMEALBUM);
        deletionAlbum();
        given()
                .headers("Authorization", token)
                .post("https://api.imgur.com/3/album/{albumHash}/favorite", albumHash)
                .prettyPeek()
                .then()
                .statusCode(404);
    }

    @Test
    void getAlbumInfoTest() {
        creationAlbum(NAMEALBUM);
        given()
                .headers("Authorization", token)
                .expect()
                .body("success", is(true))
                .when()
                .get("https://api.imgur.com/3/album/{albumHash}", albumHash)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}