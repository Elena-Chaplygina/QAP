package guru.qa.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Message {
    private final String text;
    private final int from;
    private final int to;
    private final Date creationDate;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Message(@JsonProperty("text") String text,
                   @JsonProperty("from") int from,
                   @JsonProperty("to") int to,
                   @JsonProperty("creationDate") Date creationDate) {
        this.text = text;
        this.from = from;
        this.to = to;
        this.creationDate = creationDate;
    }


    public String getText() {
        return text;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
