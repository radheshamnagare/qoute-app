package com.radhesham.controller;

import com.radhesham.bean.DefaultResponse;
import com.radhesham.bean.request.*;
import com.radhesham.bean.response.CityResponse;
import com.radhesham.bean.response.CountryResponse;
import com.radhesham.bean.response.StateResponse;
import com.radhesham.model.ManageCountryStateCity;
import com.radhesham.service.CityService;
import com.radhesham.service.CountryService;
import com.radhesham.service.StateService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class CountryStateCityController {

    @Autowired
    CountryService countryService;
    @Autowired
    StateService stateService;
    @Autowired
    CityService cityService;
    @Autowired
    HttpServletRequest request;

    private static final Logger logger = LoggerFactory.getLogger(CountryStateCityController.class);

    private void serviceInjector(ManageCountryStateCity manageCountryStateCity){
        manageCountryStateCity.setCountryService(countryService);
        manageCountryStateCity.setStateService(stateService);
        manageCountryStateCity.setCityService(cityService);
    }
    @PostMapping(value="/add/country" ,consumes =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DefaultResponse> addCountry(@RequestBody CountryAddRequest countryAddRequest){
        DefaultResponse response =null;
        try{
            logger.info("Entry in addCountry()");
            ManageCountryStateCity manageCountryStateCity = new ManageCountryStateCity();
            serviceInjector(manageCountryStateCity);
            response = manageCountryStateCity.manageAddCountry(countryAddRequest);
            logger.info("Exit from addCountry()");
        }catch (Exception e){
            logger.error("Exception in addCountry() :",e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value="/add/state" ,consumes =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DefaultResponse> addState(@RequestBody StateAddRequest stateAddRequest){
        DefaultResponse response =null;
        try{
            logger.info("Entry in addState()");
            ManageCountryStateCity manageCountryStateCity = new ManageCountryStateCity();
            serviceInjector(manageCountryStateCity);
            response = manageCountryStateCity.manageAddState(stateAddRequest);
            logger.info("Exit from addState()");
        }catch (Exception e){
            logger.error("Exception in addState() :",e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value="/add/city",consumes =  {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DefaultResponse> addCity(@RequestBody CityAddRequest cityAddRequest){
        DefaultResponse response =null;
        try{
            logger.info("Entry in addCity()");
            ManageCountryStateCity manageCountryStateCity = new ManageCountryStateCity();
            serviceInjector(manageCountryStateCity);
            response=manageCountryStateCity.manageAddCity(cityAddRequest);
            logger.info("Exit from addCity()");
        }catch (Exception e){
            logger.error("Exception in addCity() :",e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value="/country/lookup" ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CountryResponse> getAllCountry(){
        CountryResponse response=null;
        try{
            ManageCountryStateCity manageCountryStateCity = new ManageCountryStateCity();
            serviceInjector(manageCountryStateCity);
            response = manageCountryStateCity.countryDetails();
        }catch (Exception e){
            logger.error("Entry in getAllCountry():",e);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(value="/state/lookup" ,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<StateResponse> getAllState(@RequestBody StateLookupRequest stateLookupRequest){
        StateResponse response=null;
        try{
            ManageCountryStateCity manageCountryStateCity = new ManageCountryStateCity();
            serviceInjector(manageCountryStateCity);
            response = manageCountryStateCity.stateDetails(stateLookupRequest);
        }catch (Exception e){
            logger.error("Exception in getAllState() :",e);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(value="/city/lookup" ,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CityResponse> getAllCities(@RequestBody CityLookupRequest cityLookupRequest){
        CityResponse response=null;
        try{
            ManageCountryStateCity manageCountryStateCity = new ManageCountryStateCity();
            serviceInjector(manageCountryStateCity);
            response =manageCountryStateCity.cityDetails(cityLookupRequest);
        }catch (Exception e){
            logger.error("Exception in getAllCities():",e);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
