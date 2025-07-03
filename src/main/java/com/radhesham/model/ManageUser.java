package com.radhesham.model;

import com.radhesham.bean.DefaultResponse;
import com.radhesham.bean.SystemError;
import com.radhesham.bean.request.UpdatePasswordRequest;
import com.radhesham.bean.request.UserLoginRequest;
import com.radhesham.bean.request.UserRegistrationRequest;
import com.radhesham.bean.response.UserLoginResponse;
import com.radhesham.common.CommonValidator;
import com.radhesham.common.ConstantsPool;
import com.radhesham.entity.CityEntity;
import com.radhesham.entity.CountryEntity;
import com.radhesham.entity.StateEntity;
import com.radhesham.entity.UserEntity;
import com.radhesham.service.CityService;
import com.radhesham.service.CountryService;
import com.radhesham.service.StateService;
import com.radhesham.service.UserService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
public class ManageUser {
    private static final Logger logger = LoggerFactory.getLogger(ManageUser.class);
    private UserService userService;
    private CountryService countryService;
    private StateService stateService;
    private CityService cityService;

    private SystemError isValidEmail(String emailId) {
        SystemError error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                .errorStatus(ConstantsPool.STATUS_SUCCESS)
                .errorDescription(ConstantsPool.STATUS_SUCCESS)
                .build();
        try {
            logger.info("Entry in isValidEmail()");

            if (Objects.isNull(emailId) || emailId.isEmpty()) {
               error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("email id should not be empty")
                        .build();

            } else if (!CommonValidator.isValidEmail(emailId)) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("email id not valid address")
                        .build();
            } else if (userService.isEmailExist(emailId)) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("email id already registered")
                        .build();
            }

            logger.info("Exit from isValidEmail()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            logger.error("Exception in isValidEmail():", e);
        }
        return error;
    }

    private SystemError isValidPhone(String phone) {
        SystemError error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                .errorStatus(ConstantsPool.STATUS_SUCCESS)
                .errorDescription(ConstantsPool.STATUS_SUCCESS)
                .build();
        try {
            logger.info("Entry in isValidPhone()");
            if (Objects.isNull(phone) || phone.isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("phone no  should not empty")
                        .build();
            } else if (!CommonValidator.isValidMobileNo(phone)) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("phone no not valid")
                        .build();
            } else if (userService.isPhoneExist(phone)) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("phone already registered")
                        .build();
            }
            logger.info("Exit from isValidPhone()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            logger.error("Exception inisValidPhone():", e);
        }
        return error;
    }

    private SystemError isValidNewUserRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        SystemError error ;
        try {
            logger.info("Entry in isValidNewUserRegistrationRequest()");
            if (Objects.isNull(userRegistrationRequest.getName()) || userRegistrationRequest.getName().isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("user name should not be empty")
                        .build();
                return error;
            } else  {
                error=isValidEmail(userRegistrationRequest.getEmail());
                if(!error.getErrorCode().equals(ConstantsPool.ERROR_CODE_SUCCESS)){
                    return error;
                }
            }

            error=isValidPhone(userRegistrationRequest.getPhone());
            if (!error.getErrorCode().equals(ConstantsPool.ERROR_CODE_SUCCESS)) {
                return error;
            } else if (userRegistrationRequest.getCountry() < 1 || !countryService.isValidCountryId(userRegistrationRequest.getCountry())) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("country must valid or should not empty")
                        .build();
            } else if (userRegistrationRequest.getState() < 1 || !stateService.isValidStateId(userRegistrationRequest.getState())) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("state must valid or should not empty")
                        .build();
            } else if (userRegistrationRequest.getCity() < 1 || !cityService.isValidCity(userRegistrationRequest.getCity())) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("city must valid or should not empty")
                        .build();
            }else{
               error= SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                        .errorStatus(ConstantsPool.STATUS_SUCCESS)
                        .errorDescription(ConstantsPool.STATUS_SUCCESS)
                        .build();
            }
            logger.info("Exit from isValidNewUserRegistrationRequest()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            logger.error("Exception in isValidNewUserRegistrationRequest():", e);
        }
        return error;
    }

    private UserEntity getUserEntity(UserRegistrationRequest userRegistrationRequest) {
        UserEntity userEntity = new UserEntity();
        try {
            CountryEntity country = new CountryEntity();
            country.setId(userRegistrationRequest.getCountry());
            userEntity.setCountry(country);
            StateEntity state = new StateEntity();
            state.setId(userRegistrationRequest.getState());
            userEntity.setState(state);
            CityEntity city = new CityEntity();
            city.setId(userRegistrationRequest.getCity());
            userEntity.setCity(city);
            userEntity.setIsFirstLogin("YES");
            BeanUtils.copyProperties(userRegistrationRequest, userEntity);
        } catch (Exception e) {
            logger.info("Exception in getUserEntity():", e);
        }
        return userEntity;
    }

    public DefaultResponse registerNewUser(UserRegistrationRequest userRegistrationRequest) {
        DefaultResponse response ;
        SystemError error ;
        try {
            logger.info("Entry in registerNewUser()");
            error = isValidNewUserRegistrationRequest(userRegistrationRequest);
            if (error.getErrorCode().equals("000")) {
                UserEntity newUser = getUserEntity(userRegistrationRequest);
                int res = userService.saveNewUser(newUser);
                if (res < 1) {
                    error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                            .errorStatus(ConstantsPool.STATUS_INVALID)
                            .errorDescription("Failed to save user registration")
                            .build();
                } else {
                    error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                            .errorStatus(ConstantsPool.STATUS_SUCCESS)
                            .errorDescription(ConstantsPool.STATUS_SUCCESS)
                            .build();
                }
            }
            response = new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            logger.info("Exit from registerNewUser()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            response = new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            logger.error("Exception in registerNewUser():", e);
        }
        return response;
    }

    private SystemError isValidLoginRequest(UserLoginRequest userLoginRequest) {
        SystemError error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                .errorStatus(ConstantsPool.STATUS_SUCCESS)
                .errorDescription(ConstantsPool.STATUS_SUCCESS)
                .build();
        try {
            logger.info("Entry in isValidLoginRequest()");
            if (Objects.isNull(userLoginRequest.getUserId()) || userLoginRequest.getUserId().isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("user id should not be empty")
                        .build();
            } else if (Objects.isNull(userLoginRequest.getPassword()) || userLoginRequest.getPassword().isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("password should not be empty")
                        .build();
            }
            logger.info("Exit from isValidLoginRequest()");
        } catch (Exception e) {
            logger.error("Exception in isValidLoginRequest():", e);
        }
        return error;
    }

    public UserLoginResponse userLoginByCredential(UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = null;
        SystemError error;
        try {
            logger.info("Entry in userLoginByCredential");
            error = isValidLoginRequest(userLoginRequest);
            if (error.getErrorCode().equals(ConstantsPool.ERROR_CODE_SUCCESS)) {
                UserEntity user = userService.validateCredential(userLoginRequest.getUserId(), userLoginRequest.getPassword());
                if (Objects.isNull(user)) {
                    error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                            .errorStatus(ConstantsPool.ERROR_CODE_INVALID)
                            .errorDescription("invalid user credentials")
                            .build();
                    userLoginResponse = UserLoginResponse.builder().build();
                } else {
                    userLoginResponse = UserLoginResponse.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .phone(user.getPhone())
                            .country(user.getCountry().getName())
                            .state(user.getState().getName())
                            .city(user.getCity().getName())
                            .isFirstLogin(user.getIsFirstLogin())
                            .build();
                }
            } else {
                userLoginResponse = UserLoginResponse.builder().build();
            }
            userLoginResponse.setError(error);

            logger.info("Exit from userLoginByCredential()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            userLoginResponse = UserLoginResponse.builder().build();
            userLoginResponse.setError(error);
            logger.error("Exception in userLoginByCredential():", e);
        }
        return userLoginResponse;
    }

    private SystemError isValidPasswordUpdateRequest(String oldPassword,UpdatePasswordRequest updatePasswordRequest){
        SystemError error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                .errorStatus(ConstantsPool.STATUS_SUCCESS)
                .errorDescription(ConstantsPool.STATUS_SUCCESS)
                .build();
        try{
            logger.info("Entry in isValidPasswordUpdateRequest()");
            if(Objects.isNull(updatePasswordRequest.getNewPassword()) || updatePasswordRequest.getNewPassword().isEmpty()){
                error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("new password should not empty")
                        .build();
            }else if (Objects.isNull(updatePasswordRequest.getConfirmPassword()) || updatePasswordRequest.getConfirmPassword().isEmpty()){
                error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("confirm password should not empty")
                        .build();
            }else if(!updatePasswordRequest.getConfirmPassword().equals(updatePasswordRequest.getNewPassword())){
                error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("new password and confirm password should be same")
                        .build();
            }else if(Objects.isNull(updatePasswordRequest.getCurrentPassword()) || updatePasswordRequest.getCurrentPassword().isEmpty()){
                error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("current password should not empty")
                        .build();
            }else if(updatePasswordRequest.getConfirmPassword().equals(updatePasswordRequest.getCurrentPassword())){
                error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("new password and current password should not be same")
                        .build();
            }else if(oldPassword.equals(updatePasswordRequest.getConfirmPassword())){
                error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("new password and current password should not be same")
                        .build();
            }
            logger.info("Exit from isValidPasswordUpdateRequest()");
        }catch (Exception e){
            error=SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            logger.error("Exception in isValidPasswordUpdateRequest():",e);
        }
        return error;
    }

    public DefaultResponse updatePasswordByCredential(UpdatePasswordRequest updatePasswordRequest){
        DefaultResponse response=null;
        SystemError error;
        try{
            logger.info("Entry in updatePasswordByCredential()");
            UserEntity userEntity = userService.findUser(updatePasswordRequest.getId());

            if(Objects.isNull(userEntity)){
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("user details not found")
                        .build();

            }else{
                error = isValidPasswordUpdateRequest(userEntity.getPassword(),updatePasswordRequest);
                if(error.getErrorCode().equals(ConstantsPool.ERROR_CODE_SUCCESS)){
                    updatePasswordRequest.setId(userEntity.getId());
                    int res =userService.updateNewPassword(updatePasswordRequest);
                    if(res>0){
                        error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                                .errorStatus(ConstantsPool.STATUS_SUCCESS)
                                .errorDescription(ConstantsPool.STATUS_SUCCESS)
                                .build();
                    }else{
                        error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                                .errorStatus(ConstantsPool.STATUS_INVALID)
                                .errorDescription("failed to update new password")
                                .build();
                    }
                }
            }

            response=new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            logger.info("Exit from updatePasswordByCredential()");
        }catch (Exception e){
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            response=new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            logger.error("Exception in updatePasswordByCredential():",e);
        }
        return response;
    }
}
