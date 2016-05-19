package by.alex.bsuir.instagram.dao.country;


import by.alex.bsuir.instagram.entity.Country;

import java.util.List;

public interface CountryDao {
    Country getCountry(long country);
    List<Country> getCountriesByLocale();
}
