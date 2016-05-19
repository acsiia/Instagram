package by.alex.bsuir.instagram.service.profile.impl;

import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.dto.ProfileDTO;
import by.alex.bsuir.instagram.entity.Profile;
import by.alex.bsuir.instagram.util.InstagramConstants;
import by.alex.bsuir.instagram.dao.profile.ProfileDao;
import by.alex.bsuir.instagram.service.profile.ProfileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public long saveProfile(ProfileDTO profileDTO) {
        Profile profile = conversionService.convert(profileDTO, Profile.class);
        long id = profileDao.saveProfile(profile);
        LOGGER.info(messageSource.getMessage("service.profile.save", new Object[]{profileDTO},
                InstagramConstants.LOGGER_LOCALE));

        return id;
    }

    @Override
    @Transactional
    public void deleteProfile(long id) {
        profileDao.deleteProfile(id);
        LOGGER.info(messageSource.getMessage("service.profile.delete", new Object[]{id},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional
    public void updateProfile(ProfileDTO profileDTO) {
        Profile profile = conversionService.convert(profileDTO, Profile.class);
        profileDao.updateProfile(profile);
        LOGGER.info(messageSource.getMessage("service.profile.update", new Object[]{profileDTO},
                InstagramConstants.LOGGER_LOCALE));
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDTO getProfileById(long id) {
        ProfileDTO profileDTO = conversionService.convert(profileDao.getProfile(id), ProfileDTO.class);
        LOGGER.info(messageSource.getMessage("service.profile.getPostById", new Object[]{id, profileDTO},
                InstagramConstants.LOGGER_LOCALE));

        return profileDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileDTO getProfileByUserId(long id) {
        ProfileDTO profileDTO = conversionService.convert(profileDao.getProfileByUserId(id), ProfileDTO.class);
        LOGGER.info(messageSource.getMessage("service.profile.getProfileDTOByUserId",
                new Object[]{id, profileDTO}, InstagramConstants.LOGGER_LOCALE));

        return profileDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileDTO> getListOfProfiles() {
        List<Profile> profiles = profileDao.getListOfProfiles();
        List<ProfileDTO> profileDTOList = new ArrayList();

        for (Profile profile : profiles) {
            profileDTOList.add(conversionService.convert(profile, ProfileDTO.class));
        }
        LOGGER.info(messageSource.getMessage("service.profile.getList", new Object[]{profileDTOList},
                InstagramConstants.LOGGER_LOCALE));

        return profileDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public void setPostSendersProfiles(List<PostDTO> posts){
        for (PostDTO post : posts) {
            post.setSenderAvatar(getProfileByUserId(post.getSender()).getAvatar());
        }

        LOGGER.info(messageSource.getMessage("service.profile.setPostSendersProfiles",
                null, InstagramConstants.LOGGER_LOCALE));

    }
}
