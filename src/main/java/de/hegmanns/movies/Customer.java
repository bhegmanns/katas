package de.hegmanns.movies;

import de.hegmanns.movies.de.hegmanns.Movie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Customer {
    private String name;
    private Map<Movie, Integer> rentals = new HashMap<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addRental(Movie m, int d) {
        if (d < 0) throw new IllegalArgumentException("negative days rented");

        rentals.put(m, d);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        Iterator<Movie> rentals = this.rentals.keySet().iterator();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasNext()) {
            double thisAmount = 0;
            Movie each = (Movie) rentals.next();
            // determine amounts for each line
            int dr = this.rentals.get(each);
            switch (each.get_priceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (dr > 2)
                        thisAmount += (dr - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += dr * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (dr > 3)
                        thisAmount += (dr - 3) * 1.5;
                    break;
            }
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (each.get_priceCode() != null &&
                    (each.get_priceCode() == Movie.NEW_RELEASE)
                    && dr > 1)
                frequentRenterPoints++;
            // show figures line for this rental
            result += "\t" + each.get_title() + "\t"
                    + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints)
                + " frequent renter points";
        return result;
    }
}
