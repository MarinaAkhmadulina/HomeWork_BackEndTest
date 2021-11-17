
package dto.AlbumResponse;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "title",
    "description",
    "datetime",
    "cover",
    "cover_edited",
    "cover_width",
    "cover_height",
    "account_url",
    "account_id",
    "privacy",
    "layout",
    "views",
    "link",
    "favorite",
    "nsfw",
    "section",
    "images_count",
    "in_gallery",
    "is_ad",
    "include_album_ads",
    "is_album",
    "deletehash",
    "images",
    "ad_config"
})
@Getter
@Setter
public class Data implements Serializable
{

    @JsonProperty("id")
    public String id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public Object description;
    @JsonProperty("datetime")
    public Integer datetime;
    @JsonProperty("cover")
    public Object cover;
    @JsonProperty("cover_edited")
    public Object coverEdited;
    @JsonProperty("cover_width")
    public Object coverWidth;
    @JsonProperty("cover_height")
    public Object coverHeight;
    @JsonProperty("account_url")
    public String accountUrl;
    @JsonProperty("account_id")
    public Integer accountId;
    @JsonProperty("privacy")
    public String privacy;
    @JsonProperty("layout")
    public String layout;
    @JsonProperty("views")
    public Integer views;
    @JsonProperty("link")
    public String link;
    @JsonProperty("favorite")
    public Boolean favorite;
    @JsonProperty("nsfw")
    public Boolean nsfw;
    @JsonProperty("section")
    public Object section;
    @JsonProperty("images_count")
    public Integer imagesCount;
    @JsonProperty("in_gallery")
    public Boolean inGallery;
    @JsonProperty("is_ad")
    public Boolean isAd;
    @JsonProperty("include_album_ads")
    public Boolean includeAlbumAds;
    @JsonProperty("is_album")
    public Boolean isAlbum;
    @JsonProperty("deletehash")
    public String deletehash;
    @JsonProperty("images")
    public List<Object> images = null;
    @JsonProperty("ad_config")
    public AdConfig adConfig;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3548497579319193569L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param includeAlbumAds
     * @param link
     * @param description
     * @param privacy
     * @param section
     * @param title
     * @param deletehash
     * @param cover
     * @param datetime
     * @param isAlbum
     * @param id
     * @param isAd
     * @param views
     * @param accountUrl
     * @param adConfig
     * @param coverEdited
     * @param images
     * @param nsfw
     * @param imagesCount
     * @param coverHeight
     * @param layout
     * @param accountId
     * @param coverWidth
     * @param inGallery
     * @param favorite
     */
    public Data(String id, String title, Object description, Integer datetime, Object cover, Object coverEdited, Object coverWidth, Object coverHeight, String accountUrl, Integer accountId, String privacy, String layout, Integer views, String link, Boolean favorite, Boolean nsfw, Object section, Integer imagesCount, Boolean inGallery, Boolean isAd, Boolean includeAlbumAds, Boolean isAlbum, String deletehash, List<Object> images, AdConfig adConfig) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.datetime = datetime;
        this.cover = cover;
        this.coverEdited = coverEdited;
        this.coverWidth = coverWidth;
        this.coverHeight = coverHeight;
        this.accountUrl = accountUrl;
        this.accountId = accountId;
        this.privacy = privacy;
        this.layout = layout;
        this.views = views;
        this.link = link;
        this.favorite = favorite;
        this.nsfw = nsfw;
        this.section = section;
        this.imagesCount = imagesCount;
        this.inGallery = inGallery;
        this.isAd = isAd;
        this.includeAlbumAds = includeAlbumAds;
        this.isAlbum = isAlbum;
        this.deletehash = deletehash;
        this.images = images;
        this.adConfig = adConfig;
    }

    public Data withId(String id) {
        this.id = id;
        return this;
    }

    public Data withTitle(String title) {
        this.title = title;
        return this;
    }

    public Data withDescription(Object description) {
        this.description = description;
        return this;
    }

    public Data withDatetime(Integer datetime) {
        this.datetime = datetime;
        return this;
    }

    public Data withCover(Object cover) {
        this.cover = cover;
        return this;
    }

    public Data withCoverEdited(Object coverEdited) {
        this.coverEdited = coverEdited;
        return this;
    }

    public Data withCoverWidth(Object coverWidth) {
        this.coverWidth = coverWidth;
        return this;
    }

    public Data withCoverHeight(Object coverHeight) {
        this.coverHeight = coverHeight;
        return this;
    }

    public Data withAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
        return this;
    }

    public Data withAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }

    public Data withPrivacy(String privacy) {
        this.privacy = privacy;
        return this;
    }

    public Data withLayout(String layout) {
        this.layout = layout;
        return this;
    }

    public Data withViews(Integer views) {
        this.views = views;
        return this;
    }

    public Data withLink(String link) {
        this.link = link;
        return this;
    }

    public Data withFavorite(Boolean favorite) {
        this.favorite = favorite;
        return this;
    }

    public Data withNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
        return this;
    }

    public Data withSection(Object section) {
        this.section = section;
        return this;
    }

    public Data withImagesCount(Integer imagesCount) {
        this.imagesCount = imagesCount;
        return this;
    }

    public Data withInGallery(Boolean inGallery) {
        this.inGallery = inGallery;
        return this;
    }

    public Data withIsAd(Boolean isAd) {
        this.isAd = isAd;
        return this;
    }

    public Data withIncludeAlbumAds(Boolean includeAlbumAds) {
        this.includeAlbumAds = includeAlbumAds;
        return this;
    }

    public Data withIsAlbum(Boolean isAlbum) {
        this.isAlbum = isAlbum;
        return this;
    }

    public Data withDeletehash(String deletehash) {
        this.deletehash = deletehash;
        return this;
    }

    public Data withImages(List<Object> images) {
        this.images = images;
        return this;
    }

    public Data withAdConfig(AdConfig adConfig) {
        this.adConfig = adConfig;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Data withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
