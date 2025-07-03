package com.radhesham.common;

import com.radhesham.bean.response.QuotesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class CommonContext {
    private static final Logger logger = LoggerFactory.getLogger(CommonContext.class);

    private CommonContext(){

    }
    public static QuotesResponse getQuotes() {
        QuotesResponse response = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.getForObject("https://dummyjson.com/quotes", QuotesResponse.class, new QuotesResponse());
        } catch (Exception e) {
            logger.error("Exception in getQuotes():", e);
        }
        return response;
    }
}
