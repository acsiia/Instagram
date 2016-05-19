package by.alex.bsuir.instagram.converter.country;

import by.alex.bsuir.instagram.dto.CountryDTO;
import by.alex.bsuir.instagram.entity.Country;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryToCountryDTOConverter implements Converter<Country, CountryDTO>{
    @Override
    public CountryDTO convert(Country country) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCountryId(country.getCountryId());
        countryDTO.setCountryEn(country.getCountryEn());
        countryDTO.setCountryRu(country.getCountryRu());

        return countryDTO;
    }
}
