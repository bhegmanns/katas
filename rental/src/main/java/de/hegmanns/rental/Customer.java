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
        double totalPrice = 0;
        int loyalityPointsForRental = 0;
        Iterator<Rental> rentalIterator = this.rentals.iterator();
        String statement = createHeader(getName());
        while (rentalIterator.hasNext()) {
            double priceForCurrentRental = 0;
            Rental each = rentalIterator.next();
            // determine amounts for each line
            int daysRental = each.getDaysRental();
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    priceForCurrentRental += 2;
                    if (daysRental > 2)
                        priceForCurrentRental += (daysRental - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    priceForCurrentRental += daysRental * 3;
                    break;
                case Movie.CHILDREN:
                    priceForCurrentRental += 1.5;
                    if (daysRental > 3)
                        priceForCurrentRental += (daysRental - 3) * 1.5;
                    break;
            }

            loyalityPointsForRental++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && daysRental > 1)
                loyalityPointsForRental++;

            statement += createRentalInformation(priceForCurrentRental, each);
            totalPrice += priceForCurrentRental;
        }

        statement += createFooter(totalPrice, loyalityPointsForRental);
        return statement;
    }

    private String createRentalInformation(double priceForCurrentRental, Rental each) {
        return "\t" + each.getMovie().getTitle() + "\t"
                + String.valueOf(priceForCurrentRental) + "\n";
    }

    private String createFooter(double totalPrice, int loyalityPointsForRental) {
        String footer = "Amount owed is " + String.valueOf(totalPrice) + "\n";
        footer += "You earned " + String.valueOf(loyalityPointsForRental)
                + " frequent renter points";
        return footer;
    }

    private String createHeader(String name) {
        return "Rental Record for " + name + "\n";
    }
}
