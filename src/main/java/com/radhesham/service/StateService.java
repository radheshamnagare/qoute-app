package com.radhesham.service;

import com.radhesham.bean.StateBean;
import com.radhesham.entity.StateEntity;
import com.radhesham.repo.StateDao;
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
public class StateService {

    @Autowired
    StateDao stateDao;
    private static final Logger logger = LoggerFactory.getLogger(StateService.class);

    public int addStateDetails(StateEntity state){
        int res=0;
        try{
            logger.info("Entry in addStateDetails()");
            if(Objects.nonNull(state)){
                StateEntity saveState = stateDao.save(state);
                if(saveState.getId()!=null && saveState.getId()>0){
                    res+=1;
                }
            }
            logger.info("Exit from addStateDetails()");
        }catch (Exception e){
            logger.error("Exception in addStateDetails() :",e);
        }
        return res;
    }

    public boolean isValidStateId(int stateId){
        return stateDao.existsById(stateId);
    }

    public List<StateBean> getAllStateById(int id){
        List<StateBean>  stateDetails= new ArrayList<>();
        try{
           List<StateEntity> details = stateDao.findAllById(Collections.singletonList(id));
            details.forEach(s->{
                StateBean state = new StateBean();
                BeanUtils.copyProperties(s,state);
                stateDetails.add(state);
            });
        }catch (Exception e){
            logger.error("Exception in getAllStateById():",e);
        }
        return stateDetails;
    }
}
