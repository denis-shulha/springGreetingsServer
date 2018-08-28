package itsm.springGreetingsServer.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "message"})
public class SimpleGreetingsRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("message")
    private String message;

    public SimpleGreetingsRequest() {
    }

    public SimpleGreetingsRequest(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
