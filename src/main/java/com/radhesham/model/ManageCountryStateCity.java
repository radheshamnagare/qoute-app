package com.radhesham.model;

import com.radhesham.bean.*;
import com.radhesham.bean.request.*;
import com.radhesham.bean.response.CityResponse;
import com.radhesham.bean.response.CountryResponse;
import com.radhesham.bean.response.StateResponse;
import com.radhesham.common.ConstantsPool;
import com.radhesham.entity.CityEntity;
import com.radhesham.entity.CountryEntity;
import com.radhesham.entity.StateEntity;
import com.radhesham.service.CityService;
import com.radhesham.service.CountryService;
import com.radhesham.service.StateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Objects;
@Data
public class ManageCountryStateCity {

    private static final Logger logger = LoggerFactory.getLogger(ManageCountryStateCity.class);
    private CountryService countryService;
    private StateService stateService;
    private CityService cityService;
    private HttpServletRequest request;

    private SystemError isValidCountryAddReq(CountryAddRequest request) {
        SystemError error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                .errorStatus(ConstantsPool.STATUS_SUCCESS)
                .errorDescription(ConstantsPool.STATUS_SUCCESS)
                .build();
        try {
            if (Objects.isNull(request.getName()) || request.getName().isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("Country name not be empty").build();
            } else if (Objects.isNull(request.getIsoCode()) || request.getIsoCode().isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("Country iso code not be empty").build();
            } else if (Objects.isNull(request.getShortCode()) || request.getShortCode().isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("Country short code not be empty").build();
            }
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL).build();
            logger.error("Exception in isValidCountryAddReq :", e);
        }
        return error;
    }

    public DefaultResponse manageAddCountry(CountryAddRequest countryAddRequest) {
        DefaultResponse response;
        SystemError error;
        try {
            logger.info("Entry in manageAddCountry()");
            error = isValidCountryAddReq(countryAddRequest);
            if (error.getErrorCode().equals(ConstantsPool.ERROR_CODE_SUCCESS)) {
                CountryEntity country = new CountryEntity();
                BeanUtils.copyProperties(countryAddRequest, country);
                int res = countryService.addCountryDetails(country);
                if (res > 0) {
                    error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                            .errorStatus(ConstantsPool.STATUS_SUCCESS)
                            .errorDescription(ConstantsPool.STATUS_SUCCESS)
                            .build();
                } else {
                    error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                            .errorStatus(ConstantsPool.STATUS_INVALID)
                            .errorDescription("country details not saved successfully")
                            .build();
                }
            }
            response = new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            logger.info("Exit from manageAddCountry()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            response = new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            logger.error("Exception in manageAddCountry() :", e);
        }
        return response;
    }


    private SystemError isValidStateAddReq(StateAddRequest stateAddRequest) {
        SystemError error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                .errorStatus(ConstantsPool.STATUS_SUCCESS)
                .errorDescription(ConstantsPool.STATUS_SUCCESS)
                .build();
        try {
            logger.info("Entry in isValidStateAddReq()");
            if (Objects.isNull(stateAddRequest.getCountryId()) || stateAddRequest.getCountryId() <= 0) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("Country id should not be empty").build();
            } else if (Objects.isNull(stateAddRequest.getName()) || stateAddRequest.getName().isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("state name should not be empty").build();
            } else if (Objects.isNull(stateAddRequest.getShortCode()) || stateAddRequest.getShortCode().isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("state short code should  not be empty").build();
            }
            logger.info("Exit from isValidStateAddReq()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            logger.error("Exception from isValidStateAddReq():", e);
        }
        return error;
    }

    public DefaultResponse manageAddState(StateAddRequest stateAddRequest) {
        DefaultResponse response = null;
        SystemError error;
        try {
            logger.info("Entry in manageAddState()");
            error = isValidStateAddReq(stateAddRequest);
            if (error.getErrorCode().equals("000")) {
                StateEntity state = new StateEntity();
                CountryEntity country = new CountryEntity();
                country.setId(stateAddRequest.getCountryId());
                state.setCountry(country);
                BeanUtils.copyProperties(stateAddRequest, state);
                int res = stateService.addStateDetails(state);
                if (res > 0) {
                    error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                            .errorStatus(ConstantsPool.STATUS_SUCCESS)
                            .errorDescription(ConstantsPool.STATUS_SUCCESS)
                            .build();
                    response = new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
                }
            } else {
                SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("State details not saved successfully")
                        .build();
                response = new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            }
            logger.info("Exit from manageAddState()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            response = new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            logger.error("Exception in manageAddState() :", e);
        }
        return response;
    }

    private SystemError isValidCityReq(CityAddRequest cityAddRequest) {
        SystemError error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                .errorStatus(ConstantsPool.STATUS_SUCCESS)
                .errorDescription(ConstantsPool.STATUS_SUCCESS)
                .build();
        try {
            logger.info("Entry in isValidCityReq()");
            if (Objects.isNull(cityAddRequest.getStateId()) || cityAddRequest.getStateId() <= 0) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("state id should not be null").build();
            } else if (Objects.isNull(cityAddRequest.getName()) || cityAddRequest.getName().isEmpty()) {
                error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                        .errorStatus(ConstantsPool.STATUS_INVALID)
                        .errorDescription("city name should not be null").build();
            }
            logger.info("Exit from isValidCityReq()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL).build();
            logger.error("Exception in isValidCityReq():", e);
        }
        return error;
    }

    public DefaultResponse manageAddCity(CityAddRequest cityAddRequest) {
        DefaultResponse response;
        SystemError error;
        try {
            logger.info("Entry in manageAddCity()");
            error = isValidCityReq(cityAddRequest);
            if (error.getErrorCode().equals("000")) {
                CityEntity city = new CityEntity();
                StateEntity state = new StateEntity();
                state.setId(cityAddRequest.getStateId());
                city.setState(state);
                BeanUtils.copyProperties(cityAddRequest, city);
                int res = cityService.addCityDetails(city);
                if (res > 0) {
                    SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                            .errorStatus(ConstantsPool.STATUS_SUCCESS)
                            .errorDescription(ConstantsPool.STATUS_SUCCESS)
                            .build();
                } else {
                    SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_SUCCESS)
                            .errorStatus(ConstantsPool.STATUS_INVALID)
                            .errorDescription("details not saved successfully")
                            .build();
                }
            }
            response = new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            logger.info("Exit from manageAddCity()");
        } catch (Exception e) {
            error = SystemError.builder().errorCode(ConstantsPool.ERROR_CODE_INVALID)
                    .errorStatus(ConstantsPool.STATUS_INVALID)
                    .errorDescription(ConstantsPool.STATUS_FAIL)
                    .build();
            response = new DefaultResponse(error.getErrorCode(), error.getErrorStatus(), error.getErrorDescription());
            logger.error("Exception in manageAddCity() :", e);
        }
        return response;
    }

   public CountryResponse countryDetails(){
        CountryResponse response=new CountryResponse();
        try{
            logger.info("Entry in countryDetails()");
              List<CountryBean> countries = countryService.getAllCountry();
              response.setCountryDetails(countries);
            logger.info("Exit from countryDetails()");
        }catch (Exception e){
            logger.error("Exception in countryDetails() :",e);
        }
        return response;
    }

    public StateResponse stateDetails(StateLookupRequest stateLookupRequest){
        StateResponse response=new StateResponse();
        try{
            logger.info("Entry in stateDetails");
            List<StateBean> states= stateService.getAllStateById(stateLookupRequest.getId());
            response.setStateDetails(states);
            logger.info("Exit from stateDetails");
        }catch (Exception e){
            logger.error("Exception in stateDetails():",e);
        }
        return response;
    }

    public CityResponse cityDetails(CityLookupRequest cityLookupRequest){
        CityResponse response=new CityResponse();
        try{
            logger.info("Entry in cityDetails()");
              List<CityBean> details=  cityService.getAllCitiesById(cityLookupRequest.getId());
              response.setCityDetails(details);
              logger.info("Exit from cityDetails()");
        }catch (Exception e){
            logger.error("Exception in cityDetails():",e);
        }
        return response;
    }
}
