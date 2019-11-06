package de.hegmanns.rental;

public class Rental {
    private final Movie movie;
    private final int daysRental;

    public Rental(Movie movie, int daysRental) {
        if (daysRental <= 0) throw new IllegalArgumentException("Negative days rented");
        this.movie = movie;
        this.daysRental = daysRental;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRental() {
        return daysRental;
    }
}
