package by.alex.bsuir.instagram.service.profile;

import by.alex.bsuir.instagram.dto.PostDTO;
import by.alex.bsuir.instagram.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {
    long saveProfile(ProfileDTO profileDTO);
    void deleteProfile(long id);
    void updateProfile(ProfileDTO profileDTO);
    ProfileDTO getProfileById(long id);
    ProfileDTO getProfileByUserId(long id);
    List<ProfileDTO> getListOfProfiles();
    void setPostSendersProfiles(List<PostDTO> posts);
}
