package com.example.popularmoviesapp;

public class Movie {

    private String title;
    private String poster;
    private Double rating;
    private String overView;

    public Movie(String title, String poster, Double rating, String overView) {
        this.title = title;
        this.poster = poster;
        this.rating = rating;
        this.overView = overView;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public Double getRating() {
        return rating;
    }

    public String getOverView() {
        return overView;
    }

}
