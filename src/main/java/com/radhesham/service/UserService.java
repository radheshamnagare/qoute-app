package com.radhesham.service;

import com.radhesham.bean.request.UpdatePasswordRequest;
import com.radhesham.entity.UserEntity;
import com.radhesham.repo.CityDao;
import com.radhesham.repo.CountryDao;
import com.radhesham.repo.StateDao;
import com.radhesham.repo.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;
    @Autowired
    UserDao userDao;
    @Autowired
    CountryDao countryDao;
    @Autowired
    StateDao stateDao;
    @Autowired
    CityDao cityDao;
    @Autowired
    MailService mailService;

    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    public int saveNewUser(UserEntity user) {
        int res = 0;
        try {
            logger.info("Entry in saveNewUser()");
            if (Objects.nonNull(user)) {
                String password = generatePassword();
                user.setPassword(password);
                UserEntity saveUser = userDao.save(user);
                if (saveUser.getId() != null &&
                        saveUser.getId() > 0 &&
                        mailService.sendEmail(user.getEmail(), "", "Login Credentials", "Hello " + user.getName() + " your login password is " + password)) {
                    res += 1;
                }
            }
            logger.info("Exit from saveNewUser()");
        } catch (Exception e) {
            logger.error("Exception in saveNewUser():", e);
        }
        return res;
    }

    public boolean isEmailExist(String email) {
        return userDao.existsByEmail(email);
    }

    public boolean isPhoneExist(String phone) {
        return userDao.existsByPhone(phone);
    }

    public UserEntity validateCredential(String uname, String password) {

        try {
            logger.info("Entry in validateCredential()");
            Optional<UserEntity> user = userDao.findByEmailAndPassword(uname, password);
            if (user.isPresent()) {
                UserEntity userEntity = user.get();
                int countryId = userEntity.getCountry().getId();
                int stateId = userEntity.getState().getId();
                int cityId = userEntity.getCity().getId();

                String countryName = countryDao.getCountryEntityById(countryId).getName();
                String stateName = stateDao.getStateEntityById(stateId).getName();
                String cityName = cityDao.getCityEntityById(cityId).getName();

                userEntity.getCountry().setName(countryName);
                userEntity.getState().setName(stateName);
                userEntity.getCity().setName(cityName);
                return userEntity;
            }
            logger.info("Exit from validateCredential()");
        } catch (Exception e) {
            logger.error("Exception in validateCredential():", e);
        }
        return null;
    }

    public int updateNewPassword(UpdatePasswordRequest updatePasswordRequest) {
        int res = 0;
        try {
            logger.info("Entry in updateNewPassword()");
            Optional<UserEntity> user = userDao.findById(updatePasswordRequest.getId());
            if (user.isPresent()) {
                user.get().setPassword(updatePasswordRequest.getConfirmPassword());
                user.get().setIsFirstLogin("NO");
                UserEntity updatedUser = userDao.save(user.get());
                if (updatedUser.getId() > 0) {
                    res += 1;
                }
            }
            logger.info("Exit from updateNewPassword()");
        } catch (Exception e) {
            logger.error("Exception in updateNewPassword():", e);
        }
        return res;
    }

    public UserEntity findUser(int id) {
        Optional<UserEntity> user = userDao.findById(id);
        return user.isPresent() ? user.get() : null;
    }
}
