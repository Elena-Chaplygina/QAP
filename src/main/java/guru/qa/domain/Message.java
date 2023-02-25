package guru.qa.domain;

import java.util.Date;

public class Message {
    private final String text;
    private final int from;
    private final int to;
    private final Date creationDate;

    public Message(String text, int from, int to, Date creationDate) {
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
