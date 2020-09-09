package org.messenger.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Column
    private String name;
    @Column
    @NotNull
    @Size(min = 2,max = 40)
    private  String text;
    @Id
    @GeneratedValue
    private int userId;
    @Column
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime date;
    @Column
    private  boolean deleted=false;
    @ManyToOne
    private Topic topic;



    public Message() {
    }

    public Message(String name, String text,int userId) {
        this.name = name;
        this.text = text;
        this.date= LocalDateTime.now();
        this.userId=userId;

    }


    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
