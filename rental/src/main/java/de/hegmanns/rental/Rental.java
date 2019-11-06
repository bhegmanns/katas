package de.hegmanns.rental;

public class Rental {
    private final Movie movie;
    private final int days;

    public Rental(Movie movie, int days) {
        if (days <= 0) throw new IllegalArgumentException("Negative days rented");
        this.movie = movie;
        this.days = days;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDays() {
        return days;
    }
}
