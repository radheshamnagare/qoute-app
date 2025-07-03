package com.radhesham.controller;

import com.radhesham.bean.DefaultResponse;
import com.radhesham.bean.request.UpdatePasswordRequest;
import com.radhesham.bean.request.UserLoginRequest;
import com.radhesham.bean.request.UserRegistrationRequest;
import com.radhesham.bean.response.UserLoginResponse;
import com.radhesham.model.ManageUser;
import com.radhesham.service.CityService;
import com.radhesham.service.CountryService;
import com.radhesham.service.StateService;
import com.radhesham.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @Autowired
    CountryService countryService;
    @Autowired
    StateService stateService;
    @Autowired
    CityService cityService;
    @Autowired

    private void serviceInjector(ManageUser manageUser){
        manageUser.setUserService(userService);
        manageUser.setCountryService(countryService);
        manageUser.setStateService(stateService);
        manageUser.setCityService(cityService);
    }

    @PostMapping(value="/register" ,consumes =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DefaultResponse> registerNewUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        DefaultResponse response = null;
        try {
            logger.info("Entry in registerNewUser()");
            ManageUser manageUser = new ManageUser();
            serviceInjector(manageUser);
            response = manageUser.registerNewUser(userRegistrationRequest);
            logger.info("Exit from registerNewUser()");
        } catch (Exception e) {
            logger.error("Exception in registerNewUser() :", e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value="/login" ,consumes =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest){
        UserLoginResponse userLoginResponse=null;
        try{
            logger.info("Entry in loginUser()");
            ManageUser manageUser = new ManageUser();
            serviceInjector(manageUser);
            userLoginResponse = manageUser.userLoginByCredential(userLoginRequest);
            logger.info("Entry in loginUser()");
        }catch (Exception e){
            logger.error("Exception in loginUser():",e);
        }
        return new ResponseEntity<>(userLoginResponse,HttpStatus.OK);
    }

    @PostMapping(value="/update/password",consumes =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DefaultResponse> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest){
        DefaultResponse response=null;
        try{
            logger.info("Entry in updatePassword()");
            ManageUser manageUser = new ManageUser();
            serviceInjector(manageUser);
            response=manageUser.updatePasswordByCredential(updatePasswordRequest);
            logger.info("Exit from updatePassword()");
        }catch (Exception e){
            logger.error("Exception in updatePassword():",e);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
