package com.taxibooking.domain.models;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Rating {

    private final int stars;
    private final String comment;
    private final LocalDateTime ratedAt;

    public Rating(int stars, String comment) {
        validateStars(stars);
        validateComment(comment);

        this.stars = stars;
        this.comment = comment != null ? comment.trim() : null;
        this.ratedAt = LocalDateTime.now();
    }

    public Rating(int stars, String comment, LocalDateTime ratedAt) {
        validateStars(stars);
        validateComment(comment);

        this.stars = stars;
        this.comment = comment != null ? comment.trim() : null;
        this.ratedAt = ratedAt;
    }

    private void validateStars(int stars) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Stars must be between 1 and 5");
        }
    }

    private void validateComment(String comment) {
        if (comment != null && comment.length() > 500) {
            throw new IllegalArgumentException("Comment must not exceed 500 characters");
        }
    }

    public boolean isPositive() {
        return stars >= 4;
    }

    public boolean isNegative() {
        return stars <= 2;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getRatedAt() {
        return ratedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return stars == rating.stars &&
                Objects.equals(comment, rating.comment) &&
                Objects.equals(ratedAt, rating.ratedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stars, comment, ratedAt);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "stars=" + stars +
                ", comment='" + comment + '\'' +
                ", ratedAt=" + ratedAt +
                '}';
    }
}