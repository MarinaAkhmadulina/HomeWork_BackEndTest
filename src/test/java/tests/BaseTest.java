package tests;

import dto.AlbumResponse.AlbumResponse;
import dto.ImageResponse.ImageResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static tests.Endpoints.*;

public class BaseTest {

    public static String imageDeleteHash;
    public static String imageHash;
    public static Response value;

    public static String albumDeleteHash;
    public static String albumHash;
    public static Response valueAlbum;

    public static ResponseSpecification positiveResponseSpecification;
    public static ResponseSpecification negativeResponseSpecification;

    public static RequestSpecification requestSpecificationAuth;
    public static RequestSpecification requestSpecificationWithAuthAndImage;
    public static RequestSpecification requestSpecificationWithAuthAndWrongFormatImage;

    public static MultiPartSpecification multiPartSpecWithImage;
    public static MultiPartSpecification multiPartSpecWithWrongFormatImage;

    public static Properties properties = new Properties();
    public static String token;
    public static String username;

    public static final String IMAGE = "src/test/java/resources/image.jpg";
    public static final String BOOK = "src/test/java/resources/book.fb2";

    public static String NAMEALBUM = "My Album";

    private static void getProperties() {
        try (InputStream output = new FileInputStream("src/test/java/resources/app.properties")) {
            properties.load(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
        getProperties();
        token = properties.getProperty("token");
        username = properties.getProperty("username");


        multiPartSpecWithImage = new MultiPartSpecBuilder(new File(IMAGE))
                .controlName("image")
                .build();

        multiPartSpecWithWrongFormatImage = new MultiPartSpecBuilder(new File(BOOK))
                .controlName("image")
                .build();

        requestSpecificationAuth = requestSpecificationAuth();

        requestSpecificationWithAuthAndImage = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addFormParam("type", "jpg")
                .addMultiPart(multiPartSpecWithImage)
                .build();

        requestSpecificationWithAuthAndWrongFormatImage = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addMultiPart(multiPartSpecWithWrongFormatImage)
                .build();

        positiveResponseSpecification = new ResponseSpecBuilder()
                .expectBody("status", equalTo(200))
                .expectBody("success", is(true))
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();

        negativeResponseSpecification = new ResponseSpecBuilder()
                .expectBody("status", equalTo(400))
                .expectBody("success", is(false))
                .expectContentType(ContentType.JSON)
                .expectStatusCode(400)
                .build();
    }

    public static RequestSpecification requestSpecificationAuth() {
        requestSpecificationAuth = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .build();
        return requestSpecificationAuth;
    }

    public static Response uploadImage(RequestSpecification spec) {
        value = given(spec)
                .post(UPLOAD_IMAGE)
                .prettyPeek();
        imageHash = value
                .then()
                .extract()
                .body()
                .as(ImageResponse.class)
                .getData().getId();
        imageDeleteHash = value
                .then()
                .extract()
                .body()
                .as(ImageResponse.class)
                .getData().getDeletehash();
        return value;
    }

    public static Response deletionImage() {
        return given(requestSpecificationAuth, positiveResponseSpecification)
                .delete(DELETE_IMAGE, imageDeleteHash)
                .prettyPeek();
    }

    public static Response creationAlbum(String nameAlbum) {
        valueAlbum = given(requestSpecificationAuth())
                .param("title", nameAlbum)
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post(CREATE_ALBUM)
                .prettyPeek();
        albumHash = valueAlbum
                .then()
                .extract()
                .body()
                .as(AlbumResponse.class)
                .getData().getId();
        albumDeleteHash = valueAlbum
                .then()
                .extract()
                .body()
                .as(AlbumResponse.class)
                .getData().getDeletehash();
        return valueAlbum;
    }

    public static Response deletionAlbum() {
        return given(requestSpecificationAuth(), positiveResponseSpecification)
                .delete(DELETE_ALBUM, albumDeleteHash)
                .prettyPeek();
    }
}