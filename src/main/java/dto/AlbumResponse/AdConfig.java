
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
    "safeFlags",
    "highRiskFlags",
    "unsafeFlags",
    "wallUnsafeFlags",
    "showsAds"
})
@Getter
@Setter
public class AdConfig implements Serializable
{

    @JsonProperty("safeFlags")
    public List<String> safeFlags = null;
    @JsonProperty("highRiskFlags")
    public List<Object> highRiskFlags = null;
    @JsonProperty("unsafeFlags")
    public List<String> unsafeFlags = null;
    @JsonProperty("wallUnsafeFlags")
    public List<Object> wallUnsafeFlags = null;
    @JsonProperty("showsAds")
    public Boolean showsAds;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -733872077051507267L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AdConfig() {
    }

    /**
     * 
     * @param showsAds
     * @param unsafeFlags
     * @param wallUnsafeFlags
     * @param safeFlags
     * @param highRiskFlags
     */
    public AdConfig(List<String> safeFlags, List<Object> highRiskFlags, List<String> unsafeFlags, List<Object> wallUnsafeFlags, Boolean showsAds) {
        super();
        this.safeFlags = safeFlags;
        this.highRiskFlags = highRiskFlags;
        this.unsafeFlags = unsafeFlags;
        this.wallUnsafeFlags = wallUnsafeFlags;
        this.showsAds = showsAds;
    }

    public AdConfig withSafeFlags(List<String> safeFlags) {
        this.safeFlags = safeFlags;
        return this;
    }

    public AdConfig withHighRiskFlags(List<Object> highRiskFlags) {
        this.highRiskFlags = highRiskFlags;
        return this;
    }

    public AdConfig withUnsafeFlags(List<String> unsafeFlags) {
        this.unsafeFlags = unsafeFlags;
        return this;
    }

    public AdConfig withWallUnsafeFlags(List<Object> wallUnsafeFlags) {
        this.wallUnsafeFlags = wallUnsafeFlags;
        return this;
    }

    public AdConfig withShowsAds(Boolean showsAds) {
        this.showsAds = showsAds;
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

    public AdConfig withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
