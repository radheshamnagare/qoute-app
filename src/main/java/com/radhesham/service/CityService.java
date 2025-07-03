package com.radhesham.service;

import com.radhesham.bean.CityBean;
import com.radhesham.entity.CityEntity;
import com.radhesham.repo.CityDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityService.class);
    @Autowired
    CityDao cityDao;

    public int addCityDetails(CityEntity city) {
        int res = 0;
        try {
            logger.info("Entry in addCityDetails()");
            if (Objects.nonNull(city)) {
                CityEntity saveCity = cityDao.save(city);
                if (saveCity.getId()!=null && saveCity.getId()>0)
                    res += 1;
            }
            logger.info("Exit from addCityDetails()");
        } catch (Exception e) {
            logger.error("Exception in addCityDetails() :", e);
        }
        return res;
    }

    public boolean isValidCity(int cityId){
        return cityDao.existsById(cityId);
    }

    public List<CityBean> getAllCitiesById(int id){
        List<CityBean> citiesDetails= new ArrayList<>();
        try{
            List<CityEntity> details = cityDao.findAllById(Collections.singletonList(id));
            details.forEach(c->{
                CityBean bean = new CityBean();
                BeanUtils.copyProperties(c,bean);
                citiesDetails.add(bean);
            });
        }catch (Exception e){
            logger.error("Entry in getAllStateById():",e);
        }
        return citiesDetails;
    }
}
