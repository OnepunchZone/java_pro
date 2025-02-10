package deserialization.structure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatSession {
    @JsonProperty("chat_identifier")
    private String chatIdentifier;
    @JsonProperty("members")
    private List<Member> members;
    @JsonProperty("messages")
    private List<Message> messages;
}
