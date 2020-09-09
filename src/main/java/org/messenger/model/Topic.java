package org.messenger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue
    private int topicId;
    @Column
    private String category_name;
    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private List<Message> messages;

    public Topic() {
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topic_id) {
        this.topicId = topic_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic_id=" + topicId +
                ", category_name='" + category_name + '\'' +
                ", messages=" + messages +
                '}';
    }
}
