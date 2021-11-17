package tests;

public class Endpoints {

    public static final String BASE_URL = "https://api.imgur.com";
    public static final String UPLOAD_IMAGE = BASE_URL + "/3/upload";
    public static final String DELETE_ACCOUNT_IMAGE = BASE_URL + "/3/account/{username}/image/{deleteHash}";
    public static final String DELETE_IMAGE = BASE_URL + "/3/image/{imageDeleteHash}";
    public static final String FAVORITE_IMAGE = BASE_URL + "/3/image/{imageHash}/favorite";
    public static final String GET_IMAGE = BASE_URL + "/3/image/{imageHash}";
    public static final String CREATE_ALBUM = BASE_URL + "/3/album";
    public static final String DELETE_ALBUM = BASE_URL + "/3/album/{albumDeleteHash}";
    public static final String DELETE_ACCOUNT_ALBUM = BASE_URL + "/3/account/{username}/album/{albumHash}";
    public static final String FAVORITE_ALBUM = BASE_URL + "/3/album/{albumHash}/favorite";
    public static final String GET_ALBUM = BASE_URL + "/3/album/{albumHash}";
}
