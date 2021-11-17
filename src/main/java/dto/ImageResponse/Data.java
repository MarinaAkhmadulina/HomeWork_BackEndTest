
package dto.ImageResponse;

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
        "type",
        "animated",
        "width",
        "height",
        "size",
        "views",
        "bandwidth",
        "vote",
        "favorite",
        "nsfw",
        "section",
        "account_url",
        "account_id",
        "is_ad",
        "in_most_viral",
        "has_sound",
        "tags",
        "ad_type",
        "ad_url",
        "edited",
        "in_gallery",
        "deletehash",
        "name",
        "link"
})
@Getter
@Setter
public class Data implements Serializable {

    @JsonProperty("id")
    public String id;
    @JsonProperty("title")
    public Object title;
    @JsonProperty("description")
    public Object description;
    @JsonProperty("datetime")
    public Integer datetime;
    @JsonProperty("type")
    public String type;
    @JsonProperty("animated")
    public Boolean animated;
    @JsonProperty("width")
    public Integer width;
    @JsonProperty("height")
    public Integer height;
    @JsonProperty("size")
    public Integer size;
    @JsonProperty("views")
    public Integer views;
    @JsonProperty("bandwidth")
    public Integer bandwidth;
    @JsonProperty("vote")
    public Object vote;
    @JsonProperty("favorite")
    public Boolean favorite;
    @JsonProperty("nsfw")
    public Boolean nsfw;
    @JsonProperty("section")
    public Object section;
    @JsonProperty("account_url")
    public Object accountUrl;
    @JsonProperty("account_id")
    public Integer accountId;
    @JsonProperty("is_ad")
    public Boolean isAd;
    @JsonProperty("in_most_viral")
    public Boolean inMostViral;
    @JsonProperty("has_sound")
    public Boolean hasSound;
    @JsonProperty("tags")
    public List<Object> tags = null;
    @JsonProperty("ad_type")
    public Integer adType;
    @JsonProperty("ad_url")
    public String adUrl;
    @JsonProperty("edited")
    public String edited;
    @JsonProperty("in_gallery")
    public Boolean inGallery;
    @JsonProperty("deletehash")
    public String deletehash;
    @JsonProperty("name")
    public String name;
    @JsonProperty("link")
    public String link;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3547167765254157986L;

    /**
     * No args constructor for use in serialization
     */
    public Data() {
    }

    /**
     * @param link
     * @param description
     * @param adUrl
     * @param section
     * @param title
     * @param type
     * @param deletehash
     * @param datetime
     * @param adType
     * @param id
     * @param isAd
     * @param vote
     * @param views
     * @param height
     * @param accountUrl
     * @param bandwidth
     * @param nsfw
     * @param edited
     * @param hasSound
     * @param tags
     * @param accountId
     * @param size
     * @param width
     * @param name
     * @param animated
     * @param inGallery
     * @param favorite
     * @param inMostViral
     */
    public Data(String id, Object title, Object description, Integer datetime, String type, Boolean animated, Integer width, Integer height, Integer size, Integer views, Integer bandwidth, Object vote, Boolean favorite, Boolean nsfw, Object section, Object accountUrl, Integer accountId, Boolean isAd, Boolean inMostViral, Boolean hasSound, List<Object> tags, Integer adType, String adUrl, String edited, Boolean inGallery, String deletehash, String name, String link) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.datetime = datetime;
        this.type = type;
        this.animated = animated;
        this.width = width;
        this.height = height;
        this.size = size;
        this.views = views;
        this.bandwidth = bandwidth;
        this.vote = vote;
        this.favorite = favorite;
        this.nsfw = nsfw;
        this.section = section;
        this.accountUrl = accountUrl;
        this.accountId = accountId;
        this.isAd = isAd;
        this.inMostViral = inMostViral;
        this.hasSound = hasSound;
        this.tags = tags;
        this.adType = adType;
        this.adUrl = adUrl;
        this.edited = edited;
        this.inGallery = inGallery;
        this.deletehash = deletehash;
        this.name = name;
        this.link = link;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}