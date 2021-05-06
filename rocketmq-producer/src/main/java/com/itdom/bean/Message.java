package com.itdom.bean;

import java.io.Serializable;

public class Message<T> implements Serializable {
    private String id;
    private T content;

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content=" + content +
                '}';
    }
}
