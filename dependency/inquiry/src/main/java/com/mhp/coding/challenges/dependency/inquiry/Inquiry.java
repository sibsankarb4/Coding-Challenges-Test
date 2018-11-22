package com.mhp.coding.challenges.dependency.inquiry;

import java.util.Objects;

public class Inquiry {

    private String username;
    private String recipient;
    private String text;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Inquiry{" +
                "username='" + username + '\'' +
                ", recipient='" + recipient + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inquiry inquiry = (Inquiry) o;
        return Objects.equals(username, inquiry.username) &&
                Objects.equals(recipient, inquiry.recipient) &&
                Objects.equals(text, inquiry.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, recipient, text);
    }
}
