package by.alex.bsuir.instagram.dao.profile;

import by.alex.bsuir.instagram.entity.Profile;

import java.util.List;

public interface ProfileDao {
    long saveProfile(Profile profile);
    void deleteProfile(long id);
    void updateProfile(Profile profile);
    Profile getProfile(long id);
    Profile getProfileByUserId(long id);
    List<Profile> getListOfProfiles();
}
