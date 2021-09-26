package com.roy.demoretrofit;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResult {

    @SerializedName("totalItems")
    private int noOfitems;

    @SerializedName("items")
    private List<Book> books;

    public int getNoOfitems() {
        return noOfitems;
    }

    public List<Book> getBooks() {
        return books;
    }
}

class Book {

    public BookDetails getBookInfo() {
        return bookInfo;
    }

    @SerializedName("volumeInfo")
    private BookDetails bookInfo;

    private String id;

    private String kind;

    public String getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }
}

class BookDetails {

    private String title;

    private String[] authors;

    private String description;

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }
}

