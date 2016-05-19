package by.alex.bsuir.instagram.service.country;

import by.alex.bsuir.instagram.dto.CountryDTO;

import java.util.Map;

public interface CountryService {
    CountryDTO getCountry(long countryId);
    Map<Long,String> getCountriesByLocale();
}
