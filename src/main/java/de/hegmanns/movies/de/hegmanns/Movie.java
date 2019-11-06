package de.hegmanns.movies.de.hegmanns;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private  String _title;
    private  Integer _priceCode;

    public Movie(String _title, Integer _priceCode) {
        this._title = _title;
        this._priceCode = _priceCode;
    }


    public void set_priceCode(Integer _priceCode) {
        this._priceCode = _priceCode;
    }

    public String get_title() {
        return _title;
    }

    public Integer get_priceCode() {
        return _priceCode;
    }
}
