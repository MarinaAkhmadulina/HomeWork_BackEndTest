
package dto.AlbumResponse;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "success",
    "status"
})
@Getter
@Setter
public class AlbumResponse implements Serializable
{

    @JsonProperty("data")
    public Data data;
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("status")
    public Integer status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 631290007556485248L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AlbumResponse() {
    }

    /**
     * 
     * @param data
     * @param success
     * @param status
     */
    public AlbumResponse(Data data, Boolean success, Integer status) {
        super();
        this.data = data;
        this.success = success;
        this.status = status;
    }

    public AlbumResponse withData(Data data) {
        this.data = data;
        return this;
    }

    public AlbumResponse withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public AlbumResponse withStatus(Integer status) {
        this.status = status;
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

    public AlbumResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
