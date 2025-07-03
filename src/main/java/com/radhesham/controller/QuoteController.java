package com.radhesham.controller;

import com.radhesham.bean.response.QuotesResponse;
import com.radhesham.common.CommonContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

    @PostMapping(value="/user/quote" ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<QuotesResponse> getNewQuotes(){
        QuotesResponse response=null;
        try{
            response=CommonContext.getQuotes();
            if(response==null){
                response = new QuotesResponse();
            }
        }catch (Exception e){
            logger.error("Exception in getNewQuotes():",e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
