package deserialization.structure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    @JsonProperty("belong_number")
    private String belongNumber;
    @JsonProperty("send_date")
    private String sendDate;
    @JsonProperty("text")
    private String text;
}
