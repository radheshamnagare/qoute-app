package com.radhesham.service;

import com.radhesham.bean.CountryBean;
import com.radhesham.entity.CountryEntity;
import com.radhesham.repo.CountryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);
    @Autowired
    CountryDao countryDao;

    public int addCountryDetails(CountryEntity country) {
        int res = 0;
        try {
            logger.info("Entry in addCountryDetails()");
            if (Objects.nonNull(country)) {
                CountryEntity saveCountry = countryDao.save(country);
                if (saveCountry.getId()!=null && saveCountry.getId()>0)
                    res += 1;
            }
            logger.info("Exit from addCountryDetails()");
        } catch (Exception e) {
            logger.error("Exception in addCountryDetails():", e);
        }
        return res;
    }

    public boolean isValidCountryId(int countryId){
        return countryDao.existsById(countryId);
    }

    public List<CountryBean> getAllCountry(){
        List<CountryBean> country= new ArrayList<>();
        try{
            List<CountryEntity> listCountry = countryDao.findAll();
            listCountry.forEach(cnt->{
                CountryBean bean=new CountryBean();
                BeanUtils.copyProperties(cnt,bean);
                country.add(bean);
            });
        }catch (Exception e){
            logger.error("Exception in getAllCountry():",e);
        }
        return country;
    }
}
