package by.alex.bsuir.instagram.service.country.impl;

import by.alex.bsuir.instagram.dao.country.CountryDao;
import by.alex.bsuir.instagram.dto.CountryDTO;
import by.alex.bsuir.instagram.entity.Country;
import by.alex.bsuir.instagram.service.country.CountryService;
import by.alex.bsuir.instagram.util.InstagramConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service("countryService")
public class CountryServiceImpl implements CountryService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private CountryDao countryDao;

    @Override
    @Transactional(readOnly = true)
    public CountryDTO getCountry(long countryId) {
        CountryDTO countryDTO = conversionService.convert(countryDao.getCountry(countryId), CountryDTO.class);
        LOGGER.info(messageSource.getMessage("service.country.getCountryById", new Object[]{countryId, countryDTO},
                InstagramConstants.LOGGER_LOCALE));

        return countryDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, String> getCountriesByLocale() {
        List<Country> countries = countryDao.getCountriesByLocale();
        Map<Long, String> countryMap = new LinkedHashMap<>();
        if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale("en").getLanguage())) {
            for (Country country : countries) {
                countryMap.put(country.getCountryId(),country.getCountryEn());
            }

            LOGGER.info(messageSource.getMessage("service.country.getCountriesOfLocaleEn", null,
                    InstagramConstants.LOGGER_LOCALE));
        } else if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale("ru").getLanguage())) {
            for (Country country : countries) {
                countryMap.put(country.getCountryId(),country.getCountryRu());
            }

            LOGGER.info(messageSource.getMessage("service.country.getCountriesOfLocaleRu", null,
                    InstagramConstants.LOGGER_LOCALE));
        }

        return countryMap;
    }
}
