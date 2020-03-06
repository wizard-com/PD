package com.example.pdthird;

public class QuoteItem {

    private String colorCode;
    private String quoteBody;


    public QuoteItem(String colorCode, String quoteBody) {
        this.colorCode = colorCode;
        this.quoteBody = quoteBody;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getQuoteBody() {
        return quoteBody;
    }

    public void setQuoteBody(String quoteBody) {
        this.quoteBody = quoteBody;
    }
}
