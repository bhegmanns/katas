package de.hegmanns.rental;

import java.util.*;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    };

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Iterator<Rental> rentalIterator = this.rentals.iterator();
        String result = "Rental Record for " + getName() + "\n";
        while (rentalIterator.hasNext()) {
            double thisAmount = 0;
            Rental each = rentalIterator.next();
            // determine amounts for each line
            int daysRental = each.getDaysRental();
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (daysRental > 2)
                        thisAmount += (daysRental - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += daysRental * 3;
                    break;
                case Movie.CHILDREN:
                    thisAmount += 1.5;
                    if (daysRental > 3)
                        thisAmount += (daysRental - 3) * 1.5;
                    break;
            }
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && daysRental > 1)
                frequentRenterPoints++;
            // show figures line for this rental
            result += "\t" + each.getMovie().getTitle() + "\t"
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
