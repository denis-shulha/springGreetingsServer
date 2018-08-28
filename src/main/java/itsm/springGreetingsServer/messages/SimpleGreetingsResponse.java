package itsm.springGreetingsServer.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleGreetingsResponse {

    @JsonProperty("message")
    private String message;

    public SimpleGreetingsResponse() {

    }

    public SimpleGreetingsResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
