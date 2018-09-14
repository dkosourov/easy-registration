package com.ccss.registration.email;

public class Mail {

    private String to;
    private String subject;
    private String text;

    public Mail() {
    }

    public Mail(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String pTo) {
        to = pTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String pSubject) {
        subject = pSubject;
    }

    public String getText() {
        return text;
    }

    public void setText(String pText) {
        text = pText;
    }

    @Override
    public String toString() {
        return "Mail{" + "to='" + to + '\'' + ", subject='" + subject + '\'' + ", text='"
                + text + '\'' + '}';
    }
}