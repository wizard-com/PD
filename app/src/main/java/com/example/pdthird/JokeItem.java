package com.example.pdthird;

public class JokeItem {

    private String content;
    private String punchline;


    public JokeItem(String content, String punchline) {
        this.content = content;
        this.punchline = punchline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }
}
